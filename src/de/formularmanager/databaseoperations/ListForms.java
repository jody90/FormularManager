package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import de.formularmanager.storage.FormsListStorage;

public class ListForms {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ArrayList<FormsListStorage> getFormsList() throws Exception {	
		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?user=root&password=root");

			String sql = "SELECT * "
					+ "FROM formular_manager.forms "
					+ "WHERE delete_status != 1";
			
			preparedStatement = connect.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<FormsListStorage> results = new ArrayList<FormsListStorage>();		
			
			while(rs.next()) {
				String id = rs.getString("id");
				String type = rs.getString("type");
				String createdAt = rs.getString("created_at");
				String modifiedAt = rs.getString("modified_at");
				String formTitle = "kein Titel";
				
				sql = "SELECT meta_value "
				+ "FROM formular_manager.forms_meta "
				+ "WHERE formular_manager.forms_meta.meta_name LIKE 'form_title_%' "
				+ "AND formular_manager.forms_meta.form_id = " + id + "";

				preparedStatement = connect.prepareStatement(sql);				
				ResultSet rsMeta = preparedStatement.executeQuery();
				if (rsMeta.next()) {					
					formTitle = !rsMeta.getString("meta_value").isEmpty() ? rsMeta.getString("meta_value") : "kein Titel";
				}
				
				results.add(new FormsListStorage(id, type, createdAt, modifiedAt, formTitle));
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
