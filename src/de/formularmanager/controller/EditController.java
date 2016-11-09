package de.formularmanager.controller;

import java.io.IOException;
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
		String state = request.getParameter("state") != null ? request.getParameter("state") : "DRAFT";//		if ( request.getParameter("page_id") != null && !request.getParameter("page_id").isEmpty()) {

		Map<String, String> formData = new HashMap<String, String>();
		formData.put("pageId", pageId);
		formData.put("state", state);
		formData.put("formTitle", request.getParameter("form_title"));
		formData.put("formContent", request.getParameter("form_content"));
		formData.put("formType", request.getParameter("form_type"));
		formData.put("validFrom", request.getParameter("valid_from"));
		formData.put("validTo", request.getParameter("valid_to"));
		
		if ("save".equals(action)) {
			
			System.out.println("In db speichern");
			try {
				boolean writeDatabaseResponse = form.writeDatabaseForm(formData);
				
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
