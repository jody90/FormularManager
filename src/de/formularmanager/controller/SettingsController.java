package de.formularmanager.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.formularmanager.databaseoperations.SettingsDb;
import de.formularmanager.databaseoperations.UserDb;
import de.formularmanager.model.User;

@WebServlet("/SettingsController")
public class SettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SettingsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();

		// liest alle Cookies in cookies ein
		Cookie[] cookies = null;
		cookies = request.getCookies();
		
		// prueft ob User angemeldet ist
		String username = user.isLoggedIn(cookies);
		
		if (username != null) {
			Map<String, String> userInfo = user.getUserInfo();
			
			String action = request.getParameter("action");
			String editUser = request.getParameter("editUser");
			request.setAttribute("firstname", userInfo.get("firstname"));
			SettingsDb settingsDb = new SettingsDb();
			
			switch (action) {
				case "manageUsers" :
					if (editUser != null) {
						try {
							List<Map<String, String>> usersToEdit = settingsDb.getUsers(editUser);
							request.setAttribute("users", usersToEdit);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					request.setAttribute("view", "manageUsers");
					request.setAttribute("pageTitle", "Benutzer verwalten");
				break;
				case "getEditUserData" :
					try {

						Map<String, String> userToEdit = settingsDb.getUser(editUser);
						List<Map<String, String>> roles = settingsDb.getRoles();
						List<Map<String, String>> rights = settingsDb.getRights();

						Gson gsonUser = new Gson();
						String userJson = gsonUser.toJson(userToEdit);
						
						Gson gsonRoles = new Gson();
						String rolesJson = gsonRoles.toJson(roles);
						
						Gson gsonRights = new Gson();
						String rightsJson = gsonRights.toJson(rights);
						
						String EditUserData = "["+userJson+","+rolesJson+","+rightsJson+"]";
						
						response.setContentType("text/plain");
					    response.setCharacterEncoding("UTF-8");
					    response.getWriter().write(EditUserData);
					    return;

					} catch (Exception e) {
						e.printStackTrace();
					}
				break;
				case "saveEditUser" :
					UserDb userDb = new UserDb();
					
					Map<String, String> updateData = new HashMap<String, String>();
					
					Enumeration<String> paramNames = request.getParameterNames();
					
					while (paramNames.hasMoreElements()) {
						String paramName = (String) paramNames.nextElement();
						if (!paramName.equals("action")) {
							updateData.put(paramName, request.getParameter(paramName));
						}
					}
					
					try {
						userDb.addAccount(updateData);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					response.sendRedirect("/FormularManager/settings?action=manageUsers");
					return;
				case "manageRoles" :
					request.setAttribute("view", "manageRoles");
					request.setAttribute("pageTitle", "Rollen verwalten");				
				break;
				case "manageRights" :
					request.setAttribute("view", "manageRights");
					request.setAttribute("pageTitle", "Rechte verwalten");
				break;
				default :					
					request.setAttribute("view", "settings");
					request.setAttribute("pageTitle", "Einstellungen");
			}
			getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);
			
		}
		else {
			response.sendRedirect("/FormularManager/login");
			return;
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
