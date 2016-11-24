package de.formularmanager.databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import de.formularmanager.model.User;

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
	
	public void addAccount(Map<String, String> userData) throws Exception {
		User user = new User();
		
		Connect conClass = new Connect();
		connect = conClass.getConnection();

		String sql = "INSERT INTO "
				+ "formular_manager.users "
				+ "values (?, ?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "lastname = ?, firstname = ?, email = ?, roles = ?, rights = ?";
			
		
			String password = userData.get("password") == null ? user.md5Hash("1") : user.md5Hash(userData.get("password"));
			String rights = userData.get("rights").isEmpty() ? null : userData.get("rights");
			String roles = userData.get("roles").isEmpty() ? null : userData.get("roles");
		
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, userData.get("username"));
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, userData.get("lastname"));
			preparedStatement.setString(4, userData.get("firstname"));
			preparedStatement.setString(5, userData.get("email"));
			preparedStatement.setString(6, roles);
			preparedStatement.setString(7, rights);
			preparedStatement.setString(8, userData.get("lastname"));
			preparedStatement.setString(9, userData.get("firstname"));
			preparedStatement.setString(10, userData.get("email"));
			preparedStatement.setString(11, roles);
			preparedStatement.setString(12, rights);
			
			preparedStatement.executeUpdate();
	}



	
}
