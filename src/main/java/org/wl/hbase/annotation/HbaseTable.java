package org.wl.hbase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Table.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年3月14日 下午12:23:46
 * @description: 表名注解
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HbaseTable {
	/**
	 * 表名
	 */
	String tableName();

	/**
	 * 列族名
	 */
	String columnFamilyName();
}
