package de.formularmanager.controller;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.databaseoperations.FormEdit;

@WebServlet("/PublicController")
public class PublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		FormEdit form = new FormEdit();

		String formId = request.getParameter("form_id") != null ? request.getParameter("form_id") : "false";		
		String country = request.getParameter("country") != null ? request.getParameter("country") : "DE";
		
		System.out.println(formId);
		
		Map<String, String> formData = null;
		try {
			formData = form.getFormData(formId);
			System.out.println(formData.get("form_title_DE"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!formData.isEmpty()) {					
			request.setAttribute("country", country);
			request.setAttribute("formData", formData);
			request.setAttribute("pageTitle", formData.get("formType").substring(0,1).toUpperCase() + formData.get("formType").substring(1));
			request.setAttribute("view", "public");
			
			getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);				
		}
		else {
			// Wenn formData leer ist Meldung ausgeben, dass Formular geloescht wurde 
			response.sendRedirect("/FormularManager/error");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
