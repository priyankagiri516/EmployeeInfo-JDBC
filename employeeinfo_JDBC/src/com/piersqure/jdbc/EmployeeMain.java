package com.piersqure.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeMain {

	public static void main(String[] args) throws SQLException {
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

	private static void deleteEmployee(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private static void updateEmployee(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private static void getEmployee(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	
}
