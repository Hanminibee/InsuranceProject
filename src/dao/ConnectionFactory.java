package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import type.JDBCValue;

public class ConnectionFactory {
	
	private static ConnectionFactory connectionFactory = null;
	
	public ConnectionFactory() {
			try {
				Class.forName(JDBCValue.DRIVER.getValue());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(JDBCValue.URL.getValue(), JDBCValue.USER.getValue(), JDBCValue.PASSWORD.getValue());
		return connection;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
}