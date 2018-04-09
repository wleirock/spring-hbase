package org.wl.util;

import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @className: DBFieldCovertUtils.java
 * @author: DingTong
 * @version: v1.0
 * @date: 2018年3月21日 下午4:10:58
 * @description: 列名转换工具类
 */
public class DBFieldCovertUtils {

	/**
	 * 数据库列名转JAVA驼峰命名
	 * 
	 * @param datas
	 * @return
	 */
	public final static String convert1(String datas) {
		Object obj = JSON.parse(datas);
		convert2(obj);
		return JSON.toJSONString(obj);
	}

	public final static void convert2(Object json) {
		if (json instanceof JSONArray) {
			JSONArray arr = (JSONArray) json;
			for (Object obj : arr) {
				convert2(obj);
			}
		} else if (json instanceof JSONObject) {
			JSONObject jo = (JSONObject) json;
			Set<String> keys = jo.keySet();
			String[] array = keys.toArray(new String[keys.size()]);
			for (String key : array) {
				Object value = jo.get(key);
				String[] key_strs = key.toLowerCase().split("_");
				if (key_strs.length > 1) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < key_strs.length; i++) {
						String ks = key_strs[i];
						if (!"".equals(ks)) {
							if (i == 0) {
								sb.append(ks);
							} else {
								int c = ks.charAt(0);
								if (c >= 97 && c <= 122) {
									int v = c - 32;
									sb.append((char) v);
									if (ks.length() > 1) {
										sb.append(ks.substring(1));
									}
								} else {
									sb.append(ks);
								}
							}
						}
					}
					jo.remove(key);
					jo.put(sb.toString(), value);
				} else {
					jo.remove(key);
					jo.put(key.toLowerCase(), value);
				}
				convert2(value);
			}
		}
	}
}
