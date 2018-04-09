package org.wl.hbase.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.wl.hbase.annotation.HbaseColumn;


/**
 * @className: HbaseConvertUtil.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年3月14日 下午2:30:09
 * @description: hBase列数据与java实体的转换
 */
public class HbaseConvertUtil {

	/**
	 * JAVA实体转换为PUT对象
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> Put javaBeanToPut(T obj) throws Exception {
		String rowKey = getRowKeyFromBean(obj);
		if (StringUtils.isBlank(rowKey)) {
			return null;
		}
		Put put = new Put(Bytes.toBytes(rowKey));
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(HbaseColumn.class)) {
				continue;
			}
			f.setAccessible(true);
			HbaseColumn column = f.getAnnotation(HbaseColumn.class);
			String familyName = column.familyName();
			String columnName = column.columnName();
			boolean isRowKey = column.isRowKey();
			if (!isRowKey) {
				if (StringUtils.isBlank(columnName) || StringUtils.isBlank(familyName)) {
					continue;
				}
				Object value = f.get(obj);
				if (value != null && StringUtils.isNotBlank(value.toString())) {
					put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
							Bytes.toBytes(value.toString()));
				}
			}
		}
		return put;
	}

	/**
	 * hBase查询结果转换为javaBean
	 * 
	 * @param result
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> T resultToBean(Result result, T obj) throws Exception {
		if (result == null) {
			return null;
		}
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(HbaseColumn.class)) {
				continue;
			}
			HbaseColumn c = field.getAnnotation(HbaseColumn.class);
			String familyName = c.familyName();
			String columnName = c.columnName();
			boolean isRowKey = c.isRowKey();
			if (StringUtils.isBlank(familyName) || StringUtils.isBlank(columnName)) {
				continue;
			}
			String fieldName = field.getName();
			String value = "";
			if (isRowKey) {
				value = new String(result.getRow());
			} else {
				if (result.getValue(Bytes.toBytes(familyName), Bytes.toBytes(columnName)) != null) {
					value = new String(result.getValue(Bytes.toBytes(familyName), Bytes.toBytes(columnName)));
				}
			}
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			Method setMethod = clazz.getMethod(setMethodName, new Class[] { field.getType() });
			setMethod.invoke(obj, new Object[] { value });
		}
		return obj;
	}

	/**
	 * 获取实体中rowKey的值
	 * 
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> String getRowKeyFromBean(T obj) throws IllegalArgumentException, IllegalAccessException {
		String value = "";
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(HbaseColumn.class)) {
				continue;
			}
			f.setAccessible(true);
			HbaseColumn column = f.getAnnotation(HbaseColumn.class);
			boolean isRowKey = column.isRowKey();
			if (isRowKey) {
				value = f.get(obj).toString();
			}
		}
		return value;
	}
}
