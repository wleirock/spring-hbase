package org.wl.dbcp;


import com.alibaba.druid.pool.DruidDataSource;
import org.wl.util.DESEncryptor;

public class MyDbcpDataSource extends DruidDataSource {

	private static final long serialVersionUID = 1L;

	public void setPassword(String password) {
		DESEncryptor des = new DESEncryptor();
		super.password = des.decrypt(password);
	}
}
