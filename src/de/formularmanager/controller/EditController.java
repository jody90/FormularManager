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

		String pageId = "false";
		String action = request.getParameter("action");
		
		if ( request.getParameter("page_id") != null && !request.getParameter("page_id").isEmpty()) {
			pageId = request.getParameter("page_id");			
		}
		
		Map<String, String> formData = new HashMap<String, String>();
		formData.put("pageId", pageId);
		formData.put("formTitle", request.getParameter("form_title"));
		formData.put("formContent", request.getParameter("form_content"));
		formData.put("validFrom", request.getParameter("valid_from"));
		formData.put("validTo", request.getParameter("valid_to"));
		
		if ("save".equals(action)) {
			System.out.println("In db speichern");
			try {
				boolean writeDatabaseResponse = form.writeDatabaseForm(formData);
				
				if (writeDatabaseResponse) {
					response.sendRedirect("list.jsp");
				}
				else {					
					response.sendRedirect("templates/error.jsp");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("nicht in db speichern");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
