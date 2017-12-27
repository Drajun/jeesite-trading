package com.jdbc;

import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceHolder {
	private BasicDataSource ds=new BasicDataSource();
	
	private DataSourceHolder() {
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/movieticketdb");
		ds.setUsername("JavaEE");
		ds.setPassword("JavaEE");
	}
	
	private static class SingletonHolder{
		private static DataSourceHolder instance=new DataSourceHolder();
	}
	
	public static DataSourceHolder getInstance() {
		return SingletonHolder.instance;
	}
	
	public DataSource getDataSource() {
		return ds;
	}
}
