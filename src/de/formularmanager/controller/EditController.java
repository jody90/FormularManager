package de.formularmanager.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.databaseoperations.FormEdit;
import de.formularmanager.model.User;

@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
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
			
			request.setAttribute("firstname", userInfo.get("firstname"));
			
			FormEdit form = new FormEdit();
			
			String action = request.getParameter("action") != null ? request.getParameter("action") : "new";
			String formId = request.getParameter("form_id") != null ? request.getParameter("form_id") : "false";			
			String country = request.getParameter("country") != null ? request.getParameter("country") : "de";
			
			Enumeration<String> paramNames = request.getParameterNames();
			
			Map<String, String> globalData = new HashMap<String, String>();
			Map<String, String> metaData = new HashMap<String, String>();
			
			request.setAttribute("pageTitle", "Formular bearbeiten");
			request.setAttribute("formId", formId);
			request.setAttribute("country", country);
			request.setAttribute("view", "edit");
			
			globalData.put("formId", formId);
		      
			while (paramNames.hasMoreElements()) {
				
				// Parameter Keys in String Array schreiben
				String paramName = (String) paramNames.nextElement();
	
				if (paramName.startsWith("meta_")) {
					String newParamName = paramName.replace("meta_", "");
					metaData.put(newParamName, request.getParameter(paramName));					
				}
				else {
					globalData.put(paramName, request.getParameter(paramName));
				}
			}
			
			switch (action) {
				case "save" :
					try {
						boolean writeDatabaseResponse = false;
						if (formId.equals("false")) {
							writeDatabaseResponse = form.insertForm(globalData, metaData);
						}
						else {
							writeDatabaseResponse = form.updateForm(globalData, metaData);
						}
						
						
						if (writeDatabaseResponse) {
							response.sendRedirect("/FormularManager/list");
							return;
						}
						else {					
							response.sendRedirect("/FormularManager/error");
							return;
						}					
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				break;
				case "edit" :
					Map<String, String> formData = null;
					try {
						formData = form.getFormData(formId);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
					
					if (!formData.isEmpty()) {					
						request.setAttribute("country", country);
						request.setAttribute("formData", formData);
						getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);				
					}
					else {
						// Wenn formData leer ist Meldung ausgeben, dass Formular geloescht wurde 
						response.sendRedirect("/FormularManager/list");
						return;
					}
				break;
				case "delete" :
					try {
						boolean writeDatabaseResponse = false;
						if (!formId.equals("false")) {
							System.out.println("Delete aufrufen");
							writeDatabaseResponse = form.deleteForm(formId);
						}
						
						if (writeDatabaseResponse) {
							response.sendRedirect("/FormularManager/list");
							return;
						}
						else {					
							response.sendRedirect("/FormularManager/error");
							return;
						}					
					} 
					catch (Exception e) {
						e.printStackTrace();
					}			
				break;
				case "new" :
					getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);
				break;
			}		
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
