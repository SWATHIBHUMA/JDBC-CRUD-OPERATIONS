package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet res=null;
		
		String url="jdbc:mysql://localhost:3306/advancejava";
		String username="root";
		String password="Swathi@12@32";
		try {
			connection = DriverManager.getConnection(url,username,password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT * FROM EMPLOYEES";
		
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			res = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(res.next()) {
				int id = res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String dept=res.getString("department");
				int salary = res.getInt("salary");
				
				System.out.println(id+" "+name+" "+email+" "+dept+" "+salary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}