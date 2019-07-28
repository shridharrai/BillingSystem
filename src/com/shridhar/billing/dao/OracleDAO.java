package com.shridhar.billing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OracleDAO {
	public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Oracle DAO call");
		Connection con = null;
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("driver"));
		con = DriverManager.getConnection(rb.getString("url"), rb.getString("userid"), rb.getString("password"));
		return con;
		
		}

}
