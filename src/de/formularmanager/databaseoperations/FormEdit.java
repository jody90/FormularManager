package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class FormEdit {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private boolean writeDatabaseResponse = false;
	
	public boolean writeDatabaseForm(Map<String, String> globalData, Map<String, String> metaData) throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?" + "user=root&password=root");

//			Neuanlage von Formular
			if (globalData.get("pageId") == "false") {
				preparedStatement = connect.prepareStatement("INSERT INTO formular_manager.forms values (default, ?, default, default)", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, globalData.get("formType"));
				preparedStatement.executeUpdate();
				
				ResultSet rs = preparedStatement.getGeneratedKeys();
	            
				int lastInsertId = -1;
				
				if (rs.next()) {
	                lastInsertId = rs.getInt(1);
	            }
				else {
					close();
				}
				
				if (lastInsertId > -1) {
					for (Map.Entry<String, String> entry : metaData.entrySet()) {
						preparedStatement = connect.prepareStatement("INSERT INTO formular_manager.forms_meta values (default, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setInt(1, lastInsertId);
						preparedStatement.setString(2, entry.getKey());
						preparedStatement.setString(3, entry.getValue());
						preparedStatement.executeUpdate();
					}
					
					System.out.println(lastInsertId);
				}
			}
			writeDatabaseResponse = true;
		}
		catch (Exception e) {
			throw e;
		}
		return writeDatabaseResponse;
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
