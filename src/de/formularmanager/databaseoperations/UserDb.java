package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class UserDb {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	
	public String getUserPassword(String username) throws Exception {
		Connect conClass = new Connect();
		connect = conClass.getConnection();

		String sql = "SELECT password "
				+ "FROM users "
				+ "WHERE username = ?";
	
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, username);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		String password = null;
		
		while (rs.next()) {
			password = rs.getString("password");
		}
		
		return password;
		
	}
	
	public Map<String, String> getUserAccount(String username) throws Exception {
		Connect conClass = new Connect();
		connect = conClass.getConnection();

		String sql = "SELECT * "
				+ "FROM users "
				+ "WHERE username = ?";
	
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, username);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		Map<String, String> userInfo = new HashMap<String, String>();
		
		while (rs.next()) {
			userInfo.put("username", rs.getString("username"));
			userInfo.put("firstname", rs.getString("firstname"));
			userInfo.put("lastname", rs.getString("lastname"));
			userInfo.put("email", rs.getString("email"));
			userInfo.put("roles", rs.getString("roles"));
			userInfo.put("rights", rs.getString("rights"));
		}
		
		return userInfo;
	}



	
}
