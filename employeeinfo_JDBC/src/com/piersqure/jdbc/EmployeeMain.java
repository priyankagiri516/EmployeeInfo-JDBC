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
			EmployeeRepository.insertEmployee(scanner);
			break;
		case "get":
			EmployeeRepository.getEmployee(scanner);
			break;
		case "update":
			EmployeeRepository.updateEmployee(scanner);
			break;
		case "delete":
			EmployeeRepository.deleteEmployee(scanner);
			break;
		case "exit":
			System.out.println("Exting..");
			
		}
	}
	
}