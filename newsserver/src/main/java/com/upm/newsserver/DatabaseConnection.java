package com.upm.newsserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {

	static Connection conn;
	
	private DatabaseConnection() {

	}
	
	@SuppressWarnings("deprecation")
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/news_db?"
					+ "user=dbuser&password=password");
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
