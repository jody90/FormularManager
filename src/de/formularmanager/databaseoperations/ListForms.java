package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListForms {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void getForms() throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?user=root&password=root");

			preparedStatement = connect.prepareStatement("SELECT * FROM formular_manager.forms");
			
			ResultSet rs = preparedStatement.executeQuery();
			writeResultSet(rs);	
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			close();
		}
	}
	
	public List<String> getFormsList() throws Exception {	
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?user=root&password=root");

			preparedStatement = connect.prepareStatement("SELECT * FROM formular_manager.forms");
			
			ResultSet rs = preparedStatement.executeQuery();

			List<String> results = new ArrayList<String>();
			while(rs.next()) {
				results.add(rs.getString(1));
				results.add(rs.getString(2));
				results.add(rs.getString(3));
				results.add(rs.getString(4));
				results.add(rs.getString(5));
			}
			return results;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			close();
		}
		
	}
	

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			int id = resultSet.getInt("id");

			System.out.println("ID: " + id);

		}
	}

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
