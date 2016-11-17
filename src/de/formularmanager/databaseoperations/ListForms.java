package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.formularmanager.storage.FormsListStorage;

public class ListForms {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ArrayList<FormsListStorage> getFormsList(String country) throws Exception {	
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
				
				sql = "SELECT form_id, "
						+ "MAX(CASE WHEN meta_name = 'form_title_"+country+"' THEN meta_value END) as form_title, "
						+ "MAX(CASE WHEN meta_name = 'valid_from_"+country+"' THEN meta_value END) as valid_from, "
						+ "MAX(CASE WHEN meta_name = 'valid_to_"+country+"' THEN meta_value END) as valid_to "
						+ "FROM forms_meta "
						+ "WHERE form_id = " + id + " "
						+ "GROUP BY form_id";

				preparedStatement = connect.prepareStatement(sql);				
				ResultSet rsMeta = preparedStatement.executeQuery();
				
				Map<String, String> formMeta = new HashMap<String, String>();
				
				if (rsMeta.next()) {			
					formMeta.put("formTitle", rsMeta.getString("form_title"));
					formMeta.put("validFrom", rsMeta.getString("valid_from"));
					formMeta.put("validTo", rsMeta.getString("valid_to"));
				}
				
				results.add(new FormsListStorage(id, type, createdAt, modifiedAt, formMeta));
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
