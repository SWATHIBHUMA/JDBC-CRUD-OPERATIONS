package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc1 {
	
	public static void display(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException{
		String SQL = "select * from employee";
		prestatement = connection.prepareStatement(SQL);
		res = prestatement.executeQuery(SQL);
		System.out.println("Table:");
		System.out.println("+-----+-----------+----------------------+-------------+--------------+");
		while(res.next()) {
			
			System.out.printf("| %-3d | %-9s | %-20s | %-10s | %-13f |\n", res.getInt("id"),res.getString("ename"),
					res.getString("email"), res.getString("department"), res.getFloat("salary"));
		}
		System.out.println("+-----+-----------+----------------------+-------------+--------------+");
	}
	    
	public static void insert(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException{

		Scanner scanner = new Scanner(System.in);
		String SQL1 = "INSERT INTO employee(`id`,`ename`,`email`,`department`,`salary`) VALUES(?,?,?,?,?);";
		prestatement = connection.prepareStatement(SQL1);
		System.out.println("The current table is");
		display(connection, prestatement, res);  
		
		String s="y";
		do {
			System.out.println("Enter the data to insert");
			System.out.println("Enter ID");
			int a = scanner.nextInt();
			System.out.println("Enter Name");
			String b = scanner.next();       
			System.out.println("Enter Email");
			String c = scanner.next();
			System.out.println("Enter Department");
			String d = scanner.next();
			System.out.println("Enter Salary");  
			float e = scanner.nextFloat();
			
			prestatement.setInt(1, a);              
			prestatement.setString(2, b);    
			prestatement.setString(3, c); 
			prestatement.setString(4,d); 
			prestatement.setFloat(5, e);
			prestatement.executeUpdate();
			System.out.println("Data Inserted Successfully");
			display(connection, prestatement, res);
			System.out.println("Do you want to insert another row? -> If so press Y else N");
		} while (s.equalsIgnoreCase(scanner.next()));
		
		System.out.println("Do you want to perform other operations? -> if so press Y else N");
		
	}
	
	public static void update(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException{
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("The current table is:");
		display(connection, prestatement, res);
		
		String s="y", no="n";  
		
		do {
			System.out.println("Enter 1 to Update the ename");
			System.out.println("Enter 2 to Update the email");
			System.out.println("Enter 3 to Update the department");
			System.out.println("Enter 4 to Display the salary");
				
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
					String SQL1 = "UPDATE employee SET ename = ? where id = ?";
					prestatement = connection.prepareStatement(SQL1);
					System.out.println("Enter the Ename");
					String s1 = scanner.next();
					System.out.println("Enter the id where you want to update");
					int id1 = scanner.nextInt();
					prestatement.setString(1, s1);
					prestatement.setInt(2, id1);
					prestatement.executeUpdate();
					System.out.println("Data Updated Successfully. Do you want to update any other? If so press y else n");
					break;
				case 2:
					String SQL2 = "UPDATE employee SET email = ? where id = ?";
					prestatement = connection.prepareStatement(SQL2);
					System.out.println("Enter the email: ");
					String s2 = scanner.next();
					System.out.println("Enter the id where you want to update");
					int id2 = scanner.nextInt();
					prestatement.setString(1, s2);
					prestatement.setInt(2, id2);
					prestatement.executeUpdate();
					System.out.println("Data Updated Successfully. Do you want to update any other? If so press y else n");
					break;
				case 3:
					String SQL3 = "UPDATE employee SET department = ? where id = ?";
					prestatement = connection.prepareStatement(SQL3);
					System.out.println("Enter the department");
					String s3 = scanner.next();
					System.out.println("Enter the id where you want to update");
					int id3 = scanner.nextInt();
					prestatement.setString(1, s3);
					prestatement.setInt(2, id3);
					prestatement.executeUpdate();
					System.out.println("Data Updated Successfully. Do you want to update any other? If so press y else n");
					break;
				case 4:
					String SQL4 = "UPDATE employee SET salary = ? where id = ?";
					prestatement = connection.prepareStatement(SQL4);
					System.out.println("Enter the Salary");
					String s4 = scanner.next();
					System.out.println("Enter the id where you want to update");
					int id4 = scanner.nextInt();
					prestatement.setString(1, s4);
					prestatement.setInt(2, id4); 
					prestatement.executeUpdate();
					System.out.println("Data Updated Successfully. Do you want to update any other? If so press 'Y' else 'N");
					break;
				default:
					System.out.println("Make sure correct key should be pressed. Do you want to Update? if so press 'Y' else 'N'");
					break;
				}
		}while(s.equalsIgnoreCase(scanner.next()));    
		
		if(no.equalsIgnoreCase("n")) {
			System.out.println("No Updations Done. Do you want to perform other operations? If so press 'Y' else 'N' ");
		}
	}

	public static void delete(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		String SQL = "DELETE FROM employee where id=?";
		
		prestatement = connection.prepareStatement(SQL);
		
		System.out.println("Enter the ID you want to delete");
		
		prestatement.setInt(1, scanner.nextInt());   
		
		prestatement.executeUpdate();
		
		System.out.println("The table after deletion is");
		display(connection, prestatement, res);
		
		System.out.println("Do you want to perform other operations -> if so press 'Y' else 'N' ");
	}

  
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/database1";
		String username = "root";
		String password = "root";
		
		Connection connection = null;
		PreparedStatement prestatement = null;
		ResultSet res = null;
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("<!-------Welcome to Console Based Application--------!>");
			System.out.println("Enter the number to perform CRUD Operations");
			
			String yes="y";
			String no="n";
			do {
				System.out.println("Enter 1 to Insert the row");
				System.out.println("Enter 2 to Update the row");
				System.out.println("Enter 3 to Delete the row");
				System.out.println("Enter 4 to Display the Table");
				                    
				int num = scanner.nextInt();
				
				switch(num) {
				case 1: 
					insert(connection, prestatement, res);
					break; 
				case 2:
					update(connection, prestatement, res);
					break;
				case 3:
					delete(connection, prestatement, null);
					break;
				case 4:
					display(connection, prestatement, res);
					System.out.println("Do you want to perform other operations?, if so press 'Y' else 'N' ");
					break;
				default:
					System.out.println("Make sure you enter correct number." + "Do you want to continue, if so press 'Y' else 'N' ");
					break;
				} 
	    
				
			}while(yes.equalsIgnoreCase(scanner.next()));
			 
			if(no.equalsIgnoreCase("n")) {
				System.out.println("Thank You...!!");
			}    
				
		} catch (SQLException e) {  
			e.printStackTrace();        
		}
		finally {    
			scanner.close();
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}