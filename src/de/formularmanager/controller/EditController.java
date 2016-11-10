package de.formularmanager.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.databaseoperations.FormEdit;

@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FormEdit form = new FormEdit();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		String action = request.getParameter("action");
		
		String pageId = request.getParameter("page_id") != null ? request.getParameter("page_id") : "false";			
//		String state = request.getParameter("state") != null ? request.getParameter("state") : "DRAFT";
		String country = request.getParameter("country");
		
		
		if ("save".equals(action)) {		
			Enumeration paramNames = request.getParameterNames();
			
			Map<String, String> globalData = new HashMap<String, String>();
			Map<String, String> metaData = new HashMap<String, String>();
			
			globalData.put("pageId", pageId);
		      
			while (paramNames.hasMoreElements()) {
				
				// Parameter Keys in String Array schreiben
				String paramName = (String) paramNames.nextElement();

				if (paramName.endsWith("_countryPlaceholder")) {
					String newParamName = paramName.replace("countryPlaceholder", country);
					metaData.put(newParamName, request.getParameter(paramName));					
				}
				else {
					globalData.put(paramName, request.getParameter(paramName));
				}
			}
			
//			for (Map.Entry<String, String> entry : metaData.entrySet()) {
//			    System.out.println(entry.getKey() + "/" + entry.getValue());
//			}
			
			try {
//				boolean writeDatabaseResponse = form.writeDatabaseForm(formData);
				boolean writeDatabaseResponse = form.writeDatabaseForm(globalData, metaData);
//				boolean writeDatabaseResponse = true;
				
				if (writeDatabaseResponse) {
					response.sendRedirect("/FormularManager/list");
					return;
				}
				else {					
					response.sendRedirect("/FormularManager/error");
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("pageTitle", "Formular bearbeiten");
			request.setAttribute("view", "edit");
			getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
