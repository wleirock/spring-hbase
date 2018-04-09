package org.wl.hbase.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseAccessor;
import org.springframework.stereotype.Service;
import org.wl.hbase.annotation.HbaseColumn;
import org.wl.hbase.annotation.HbaseTable;
import org.wl.hbase.util.HbaseConvertUtil;


@Service
public class HbaseCommonService extends HbaseAccessor {

	public HbaseCommonService() {

	}

	public HbaseCommonService(Configuration configuration) {
		this.setConfiguration(configuration);
		this.afterPropertiesSet();
	}

	/**
	 * 根据确定的rowKey查询返回javaBean结果集
	 * 
	 * @param obj
	 * @param rowkeys
	 * @return
	 */
	public <T> List<T> getBeanByRowKeys(T obj, String... rowkeys) {
		String tableName = getTableName(obj);
		if (StringUtils.isBlank(tableName)) {
			return null;
		}
		List<Result> reList = getByRowKeys(tableName, rowkeys);
		if (reList == null || reList.size() < 1) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (Result r : reList) {
			T bean = null;
			if (r == null || r.isEmpty()) {
				continue;
			}
			try {
				bean = HbaseConvertUtil.resultToBean(r, obj);
				list.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return list;
	}

	/**
	 * 根据确定的rowKey查询返回HBASE结果集
	 * 
	 * @param tableName
	 * @param rowkeys
	 * @return
	 */
	public List<Result> getByRowKeys(String tableName, String... rowkeys) {
		List<Result> resultList = new ArrayList<Result>();
		List<Get> gets = new ArrayList<Get>();
		for (String rowkey : rowkeys) {
			if (StringUtils.isBlank(rowkey)) {
				continue;
			}
			Get get = new Get(Bytes.toBytes(rowkey));
			gets.add(get);
		}
		Connection connection = null;
		Table table = null;
		try {
			long t1 = System.currentTimeMillis();
			connection = ConnectionFactory.createConnection(this.getConfiguration());
			long t2 = System.currentTimeMillis();
			System.out.println("打开连接-----" + (t2 - t1) + "毫秒");
			table = connection.getTable(TableName.valueOf(tableName));
			Result[] results = table.get(gets);
			Collections.addAll(resultList, results);
			long t3 = System.currentTimeMillis();
			System.out.println("查询耗时-----" + (t3 - t2) + "毫秒");
			return resultList;
		} catch (IOException e) {
			return resultList;
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 保存信息
	 * 
	 * @param objs
	 */
	public <T> void upsertBean(@SuppressWarnings("unchecked") T... objs) {
		List<Put> puts = new ArrayList<Put>();
		String tableName = "";
		for (Object obj : objs) {
			if (obj == null) {
				continue;
			}
			tableName = getTableName(obj);
			try {
				Put put = HbaseConvertUtil.javaBeanToPut(obj);
				puts.add(put);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		upsert(puts, tableName);
	}

	/**
	 * 保存PUT
	 * 
	 * @param puts
	 * @param tableName
	 */
	public void upsert(List<Put> puts, String tableName) {
		Connection connection = null;
		Table table = null;
		try {
			connection = ConnectionFactory.createConnection(this.getConfiguration());
			table = connection.getTable(TableName.valueOf(tableName));
			if (table != null) {
				table.put(puts);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据rowKey前缀分页查询
	 * 
	 * @param obj
	 * @param startRow
	 * @param rowPrefix
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getBeanByPrefixRow(T obj, String startRow, String rowPrefix, int pageSize) {
		String tableName = getTableName(obj);
		if (StringUtils.isBlank(tableName)) {
			return null;
		}
		List<Result> reList = getByPrefixRow(tableName, startRow, rowPrefix, pageSize);
		if (reList == null || reList.size() < 1) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (Result r : reList) {
			if (r == null || r.isEmpty()) {
				continue;
			}
			try {
				obj = HbaseConvertUtil.resultToBean(r, obj);
				T bean = (T) BeanUtils.cloneBean(obj);
				list.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return list;
	}

	/**
	 * 根据rowKey前缀分页查询
	 * 
	 * @param tableName
	 * @param startRow
	 * @param rowPrefix
	 * @param pageSize
	 * @return
	 */
	public List<Result> getByPrefixRow(String tableName, String startRow, String rowPrefix, int pageSize) {
		Connection connection = null;
		Table table = null;
		List<Result> resultList = new ArrayList<Result>();
		ResultScanner rs = null;
		try {
			connection = ConnectionFactory.createConnection(this.getConfiguration());
			long t1 = System.currentTimeMillis();
			table = connection.getTable(TableName.valueOf(tableName));

			Scan scan = new Scan();
			if (StringUtils.isNoneBlank(startRow)) {
				scan.setStartRow(Bytes.toBytes(startRow));
			}

			// 分页过滤器
			PageFilter pfilter = new PageFilter(pageSize);
			// 匹配rowKey前缀过滤器
			Filter rfilter = new PrefixFilter(Bytes.toBytes(rowPrefix));
			FilterList fList = new FilterList(pfilter, rfilter);
			scan.setFilter(fList);
			scan.setCaching(pageSize);
			rs = table.getScanner(scan);
			for (Result r : rs) {
				resultList.add(r);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("getByPrefixRow耗时-----" + (t2 - t1) + "毫秒");
			return resultList;
		} catch (IOException e) {
			return resultList;
		} finally {
			rs.close();
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据属性值获取startRow
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public <T> String getStartRowKeyByProperty(T obj) {
		String key = "";
		String tableName = getTableName(obj);
		Connection connection = null;
		Table table = null;
		try {
			Map<String, String> map = getValueColumnName(obj);
			if (map.isEmpty()) {
				return key;
			}
			connection = ConnectionFactory.createConnection(this.getConfiguration());

			long t1 = System.currentTimeMillis();
			table = connection.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(
					Bytes.toBytes(map.get("familyName"))));

			Filter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(
					Bytes.toBytes(map.get("columnName"))));

			Filter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(map
					.get("value"))));

			Filter pageFilter = new PageFilter(1);
			FilterList fls = new FilterList(familyFilter, qualifierFilter, valueFilter, pageFilter);
			scan.setFilter(fls);
			scan.setCaching(1);
			ResultScanner rs = table.getScanner(scan);
			for (Result r : rs) {
				key = new String(r.getRow());
			}
			long t2 = System.currentTimeMillis();
			System.out.println("getStartRowKeyByProperty消耗时间--" + (t2 - t1) + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return key;
	}

	private String getTableName(Object obj) {
		HbaseTable table = obj.getClass().getAnnotation(HbaseTable.class);
		return table.tableName();
	}

	/**
	 * 获取OBJ中有值的属性的列名
	 * 
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Map<String, String> getValueColumnName(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, String> map = new HashMap<String, String>();
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(HbaseColumn.class)) {
				continue;
			}
			f.setAccessible(true);
			HbaseColumn column = f.getAnnotation(HbaseColumn.class);
			String columnName = column.columnName();
			String familyName = column.familyName();
			boolean isRowKey = column.isRowKey();
			if (!isRowKey) {
				if (StringUtils.isBlank(columnName) || StringUtils.isBlank(familyName)) {
					continue;
				}
				Object value = f.get(obj);
				if (value != null && StringUtils.isNotBlank(value.toString())) {
					map.put("columnName", columnName);
					map.put("familyName", familyName);
					map.put("value", value.toString());
					break;
				}
			}
		}
		return map;
	}

	/**
	 * 创建表
	 * 
	 * @param tableNameStr 表名
	 * @param familys 列簇名
	 * @return
	 */
	public void createTable(String tableNameStr, String... familys) {
		Connection conn = null;
		HBaseAdmin admin = null;
		try {
			conn = ConnectionFactory.createConnection(this.getConfiguration());
			admin = (HBaseAdmin) conn.getAdmin();
			boolean flag = admin.tableExists(tableNameStr);
			// 如表不存在，则创建
			if (!flag) {
				HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableNameStr));
				for (String family : familys) {
					tableDescriptor.addFamily(new HColumnDescriptor(family));
				}
				admin.createTable(tableDescriptor);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (admin != null) {
				try {
					admin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据 rowkey删除一条或多条记录
	 * 
	 * @param tablename
	 * @param rowkeys
	 */
	public void deleteRow(String tablename, String... rowkeys) {
		Connection conn = null;
		try {
			conn = ConnectionFactory.createConnection(this.getConfiguration());
			Table table = conn.getTable(TableName.valueOf(tablename));
			List<Delete> list = new ArrayList<Delete>();
			Delete del = null;
			for (String rowkey : rowkeys) {
				del = new Delete(rowkey.getBytes());
				list.add(del);
			}
			table.delete(list);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
