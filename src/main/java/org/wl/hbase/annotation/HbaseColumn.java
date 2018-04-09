package org.wl.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Table.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年3月14日 上午11:54:07
 * @description: 列注解
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HbaseColumn {

	/**
	 * 列名
	 * 
	 * @return
	 */
	String columnName();

	/**
	 * 列族名
	 * 
	 * @return
	 */
	String familyName() default "cf";

	/**
	 * 是否是rowKey
	 * 
	 * @return
	 */
	boolean isRowKey() default false;
}
