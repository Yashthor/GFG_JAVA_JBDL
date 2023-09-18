package com.blog.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	
	@Bean
	public Connection getConnection() {
		try {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_57", "root", "root");
		return connection;
	}catch (SQLException e) {
		throw new RuntimeException();
	}
	}
}
