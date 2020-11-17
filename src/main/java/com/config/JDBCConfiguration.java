package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JDBCConfiguration {

	private static final Logger LOGGER = Logger.getLogger(JDBCConfiguration.class.getName());
	
	private JDBCConfiguration() {
		// TODO Auto-generated constructor stub
	}

	@Bean
	public static Connection getConnexionBDD() {

		String url = "jdbc:mysql://127.0.0.1:3306/twic_mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String user = "root";
		String motDePasse = "mysecretpassword";
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, motDePasse);
			System.out.println("Connecte");

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Erreur : " + e.getMessage(),
					e);
		}
		return conn;
	}
}
