package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class ListForms {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ResultSet getForms() throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?" + "user=root&password=root");

			preparedStatement = connect.prepareStatement("SELECT * formular_manager.form");
			
			ResultSet rs = preparedStatement.executeQuery();
			return rs;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			close();
		}
	}

//	private void writeResultSet(ResultSet resultSet) throws SQLException {
//		// ResultSet is initially before the first data set
//		while (resultSet.next()) {
//			// It is possible to get the columns via name
//			// also possible to get the columns via the column number
//			// which starts at 1
//			// e.g. resultSet.getSTring(2);
//			String user = resultSet.getString("myuser");
//			String website = resultSet.getString("webpage");
//			String summary = resultSet.getString("summary");
//			Date date = resultSet.getDate("datum");
//			String comment = resultSet.getString("comments");
//			System.out.println("User: " + user);
//			System.out.println("Website: " + website);
//			System.out.println("summary: " + summary);
//			System.out.println("Date: " + date);
//			System.out.println("Comment: " + comment);
//		}
//	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
