package com.shridhar.billing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CommonDAO {

	public static Connection getPostgresConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Postgres DAO call");
		Connection con = null;
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("driver"));
		con = DriverManager.getConnection(rb.getString("url"), rb.getString("userid"), rb.getString("password"));
		return con;
		
		}
	}


