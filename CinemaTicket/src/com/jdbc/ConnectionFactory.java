package com.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=DataSourceHolder.getInstance().getDataSource().getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
