package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class FormEdit {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private boolean writeDatabaseResponse = false;
	
	public boolean insertForm(Map<String, String> globalData, Map<String, String> metaData) throws Exception {

		Connection connect = this.connect();
		String sql = "INSERT INTO formular_manager.forms values (default, ?, default, default)";
	
		preparedStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, globalData.get("formType"));
		preparedStatement.executeUpdate();
		
		
		ResultSet rs = preparedStatement.getGeneratedKeys();
        
		int lastInsertId = -1;
		
		if (rs.next()) {
            lastInsertId = rs.getInt(1);
        }
		else {
			this.close();
		}
		
		if (lastInsertId > -1) {
			sql = "INSERT INTO formular_manager.forms_meta values (default, ?, ?, ?)";
			for (Map.Entry<String, String> entry : metaData.entrySet()) {
				preparedStatement = connect.prepareStatement(sql);
				preparedStatement.setInt(1, lastInsertId);
				preparedStatement.setString(2, entry.getKey());
				preparedStatement.setString(3, entry.getValue());
				preparedStatement.executeUpdate();
			}
			
		}
		
		writeDatabaseResponse = true;
		return writeDatabaseResponse;
	}
	
	public boolean updateForm(Map<String, String> globalData, Map<String, String> metaData) throws Exception {
		
		Connection connect = this.connect();
		int formId = Integer.parseInt(globalData.get("formId"));

		String sql = "UPDATE "
				+ "formular_manager.forms "
				+ "SET type = ?, "
				+ "modified_at = default "
				+ "WHERE id = " + formId + "";
		
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, globalData.get("formType"));
		preparedStatement.executeUpdate();
		
		sql = "INSERT INTO "
			+ "formular_manager.forms_meta "
			+ "values (default, ?, ?, ?) "
			+ "ON DUPLICATE KEY UPDATE "
			+ "meta_name = ?, meta_value = ?";
		
		System.out.println(sql);
		
		for (Map.Entry<String, String> entry : metaData.entrySet()) {
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, formId);
			preparedStatement.setString(2, entry.getKey());
			preparedStatement.setString(3, entry.getValue());
			preparedStatement.setString(4, entry.getKey());
			preparedStatement.setString(5, entry.getValue());
			preparedStatement.executeUpdate();
		}		
		writeDatabaseResponse = true;
		return writeDatabaseResponse;
	}
	
	public Map<String, String> getFormData(String formId) throws Exception {
		
		Map<String, String> formData = new HashMap<String, String>();
		Connection connect = this.connect();

		String sql = "SELECT "
				+ "forms.id, "
				+ "forms.type, "
				+ "forms.created_at, "
				+ "forms.modified_at "
				+ "FROM formular_manager.forms "
				+ "WHERE forms.id = " + formId + "";
		
		preparedStatement = connect.prepareStatement(sql);				
		ResultSet rsData = preparedStatement.executeQuery();
		
		if (rsData.next()) {
			formData.put("formId", rsData.getString("id"));
			formData.put("formType", rsData.getString("type"));
			formData.put("createdAt", rsData.getString("created_at"));
			formData.put("modifiedAt", rsData.getString("modified_at"));
		}
		
		sql = "SELECT COUNT(*)"
				+ "FROM forms_meta "
				+ "WHERE forms_meta.form_id = " + formId + "";
		
		preparedStatement = connect.prepareStatement(sql);				
		ResultSet rsCount = preparedStatement.executeQuery();
		int count = 0;
		
		if (rsCount.next()) {
			count = rsCount.getInt(1);
		}
		
		for (int i = 1; i <= count; i++) {		
			sql = "SELECT meta_name, meta_value "
					+ "FROM forms_meta "
					+ "WHERE forms_meta.form_id = " + formId + " "
					+ "LIMIT 1 OFFSET " + (i-1) + "";
			
			preparedStatement = connect.prepareStatement(sql);				
			ResultSet rsMeta = preparedStatement.executeQuery();
			if (rsMeta.next()) {
				formData.put(rsMeta.getString("meta_name"), rsMeta.getString("meta_value"));
			}
		}
		
		return formData;
		
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
