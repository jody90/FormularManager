package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import de.formularmanager.storage.FromsStatisticsStorage;

public class FormStatistics {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private FromsStatisticsStorage statisticsStorage = new FromsStatisticsStorage();
	
	public FromsStatisticsStorage getStatistics(String formId, String country) throws Exception {
		
		Connection connect = this.connect();
		String sql = "SELECT * "
				+ "FROM forms_response "
				+ "WHERE form_id = ?";
	
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, formId);
		ResultSet rsData = preparedStatement.executeQuery();
		
		while (rsData.next()) {
			String value = rsData.getString("value");
			String createdAt = rsData.getString("created_at");
			int id = rsData.getInt("id");
			int userId = rsData.getInt("user_id");

			statisticsStorage.setStatisticsValue(value, createdAt, id, userId);
			
			statisticsStorage.setFormId(rsData.getInt("form_id"));
		}
		
		String sqla = "SELECT meta_value "
				+ "FROM forms_meta "
				+ "WHERE form_id = ? "
				+ "AND meta_name = 'formContentJson'";
		
		preparedStatement = connect.prepareStatement(sqla);
		preparedStatement.setString(1, formId);
		ResultSet rsJsonForm = preparedStatement.executeQuery();
		
		while (rsJsonForm.next()) {
			String jsonForm = rsJsonForm.getString("meta_value");
			statisticsStorage.setJsonForm(jsonForm);
		}
	
		this.close();
		return statisticsStorage;
	}
	
	private Connection connect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/formular_manager?" + "user=root&password=root");			
		}
		catch (Exception e) {
			throw e;
		}
		return connect;
	}
	
	// You need to close the resultSet
	private void close() throws Exception {
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
		} 
		catch (Exception e) {
			throw e;
		}
	}
}
