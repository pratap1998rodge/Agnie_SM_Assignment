package com.agnie.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agnie.bean.User;
public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/amol?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "mysql";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (fname, lname,dob,city,mobile_no) VALUES "
			+ " (?,?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select fname,lname,dob,city,mobile_no from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set fname = ?,lname = ?,dob= ?, city =?,mobile_no = ? where id = ?;";

	public UserDAO() {
	}

	//GetConnection()
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//catch
		return connection;
	}//getConnection()

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFname());
			preparedStatement.setString(2, user.getLname());
			preparedStatement.setString(3, user.getDob());
			preparedStatement.setString(4, user.getCity());
			preparedStatement.setString(5, user.getMobile_no());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}//try
		catch (SQLException e) {
			printSQLException(e);
		}//catch
	}//insertUser()

	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				//int id1=rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String dob = rs.getString("dob");
				String city = rs.getString("city");
				String mobile_no = rs.getString("mobile_no");
				user = new User(id, fname,lname,dob,city,mobile_no);
			}//while
		}//try
		catch (SQLException e) {
			printSQLException(e);
		}//catch
		return user;
	}//selectUser()

	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String dob = rs.getString("dob");
				String city = rs.getString("city");
				String mobile_no = rs.getString("mobile_no");
				users.add(new User(id, fname, lname, dob,city,mobile_no));
			}//while
		}//try 
		catch (SQLException e) {
			printSQLException(e);
		}//catch
		return users;
	}//selectAllUsers()

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}//try
		return rowDeleted;
	}//deleteUser()

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getFname());
			statement.setString(2, user.getLname());
			statement.setString(3, user.getDob());
			statement.setString(4, user.getCity());
			statement.setString(5, user.getMobile_no());
			statement.setInt(6, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}//try
		return rowUpdated;
	}//updateUser()

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}//while
			}//if
		}//for
	}//printSQLException()

}//class
