package com.piersqure.jdbc;

/**
 * The EmployeeConstant class holds the database configuration constants 
 * such as URL, username, and password for the JDBC connection. These values 
 * are used throughout the application to ensure a consistent configuration 
 * and avoid hardcoding values in multiple places.
 *
 * <p>This is a utility class and should not be instantiated.</p>
 * 
 * <p>Developer: Priyanka</p>
 * <p>Date: July 25, 2024</p>
 */

public class EmployeeConstant {
	
   private EmployeeConstant( ) {
	   
   }
   public static final String DB_URL = "jdbc:mysql://localhost:3306/GitHubProjects";
   public static final String USERNAME = "root";
   public static final String PASSWORD = "root";
   public static final String INSERT_QUERY = "INSERT INTO JDBCEmployee (id, name, department , salary) VALUES (?, ?, ?, ?)";
}
