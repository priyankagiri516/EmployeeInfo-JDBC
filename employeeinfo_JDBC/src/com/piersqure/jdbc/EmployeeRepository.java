package com.piersqure.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

	public class EmployeeRepository {

		static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(EmployeeConstant.DB_URL, EmployeeConstant.USERNAME,
				EmployeeConstant.PASSWORD);
	}

	public static void insertEmployee(Scanner scanner) throws SQLException, ClassNotFoundException {
		System.out.println("Enter Employee id: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Employee name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Employee  department: ");
		String department = scanner.nextLine();
		System.out.println("Enter Employee salary: ");
		double salary = Double.parseDouble(scanner.nextLine());

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(EmployeeConstant.INSERT_QUERY);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, department);
			pstmt.setDouble(4, salary);
			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new employee was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void getEmployee(Scanner scanner) throws SQLException, ClassNotFoundException {
		System.out.println("Get all the existing Employee Data : ");
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(EmployeeConstant.GET_QUERY);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("ID           NAME          DEPARTMENT          SALARY");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String department = rs.getString("department");
				double salary = rs.getDouble("salary");

				System.out.println(id + "          " + name + "          " + department + "          " + salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void updateEmployee(Scanner scanner) throws ClassNotFoundException, SQLException {

		System.out.println("Updating Employee Salary By ID");
		System.out.println("Enter Id:");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Salary:");
		double salary = Double.parseDouble(scanner.nextLine());
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(EmployeeConstant.UPDATE_BY_ID_QUERY);
			pstmt.setDouble(1, salary);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();
			System.out.println("Employee salary was updated successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void deleteEmployee(Scanner scanner) throws ClassNotFoundException, SQLException {
		System.out.println("Enter Id to delete the particular Employee ::");
		int id = scanner.nextInt();
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(EmployeeConstant.DELETE_BY_ID_QUERY);
			pstmt.setInt(1, id);

			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Employee with ID " + id + " was deleted successfully!");
			} else {
				System.out.println("No employee found with ID " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}


