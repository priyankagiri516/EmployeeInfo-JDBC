package com.piersqure.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeMain {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String operation = "";
		
		System.out.println("Enter opreation ( Insert, Get, Update, Delete, Exit):");
		operation = scanner.nextLine();
		
		switch(operation.toLowerCase()){
		case "insert":
			insertEmployee(scanner);
			break;
		case "get":
			getEmployee(scanner);
			break;
		case "update":
			updateEmployee(scanner);
			break;
		case "delete":
			deleteEmployee(scanner);
			break;
		case "exit":
			System.out.println("Exting..");
			return;
		}
	}
	
	private static void insertEmployee(Scanner scanner) throws SQLException {
		System.out.println("Enter Employee id: ");
		int id = scanner.nextInt();
		System.out.println("Enter Employee name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Employee department: ");
		String department = scanner.nextLine();
		System.out.println("Enter Employee salary: ");
		double salary = Double.parseDouble(scanner.nextLine());
	
		Connection connection = DriverManager.getConnection(EmployeeConstant.DB_URL, EmployeeConstant.USERNAME, EmployeeConstant.PASSWORD);
		PreparedStatement pstmt = connection.prepareStatement(EmployeeConstant.INSERT_QUERY);
		pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3,department);
        pstmt.setDouble(4, salary);
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new employee was inserted successfully!");
        }
	}

	private static void deleteEmployee(Scanner scanner) throws SQLException, ClassNotFoundException {
		System.out.println("Enter Id to delete the particular Employee ::");
		int id = scanner.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(EmployeeConstant.DB_URL, EmployeeConstant.USERNAME, EmployeeConstant.PASSWORD);
		PreparedStatement pstmt  = connection.prepareStatement(EmployeeConstant.DELETE_BY_ID_QUERY);
		pstmt.setInt(1, id);
		
		int rowsDeleted = pstmt.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Employee with ID " + id + " was deleted successfully!");
        } else {
            System.out.println("No employee found with ID " + id);
        }
		
		
	}

	private static void updateEmployee(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private static void getEmployee(Scanner scanner) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
System.out.println("Get all the existing Employee Data : ");
		
        Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(EmployeeConstant.DB_URL, EmployeeConstant.USERNAME, EmployeeConstant.PASSWORD);
		PreparedStatement pstmt  = connection.prepareStatement(EmployeeConstant.GET_QUERY);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("ID           NAME          DEPARTMENT          SALARY");
		
		while(rs.next()) {
			int id = rs.getInt("id");
            String name = rs.getString("name");
            String department = rs.getString("department");
            double salary = rs.getDouble("salary");
            
            System.out.println(id+"          "+name+"          "+department+"          "+salary);
		}
	}

	
}
