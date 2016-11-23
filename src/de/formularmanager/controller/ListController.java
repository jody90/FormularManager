package de.formularmanager.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.databaseoperations.ListForms;
import de.formularmanager.storage.FormsListStorage;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListController() {
		super();
	}

    public boolean isActive(String validFrom, String validTo) {
		boolean formIsActive = false;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	    Date startDate;
	    Date endDate;
	    Date currentDate = new Date();
	    try {
	    	startDate = dateFormat.parse(validFrom);
	    	endDate = dateFormat.parse(validTo);
	    	
	    	if (endDate.after(currentDate) && startDate.before(currentDate)) {
	    		formIsActive = true;
	    	}
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return formIsActive;
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ListForms Forms = new ListForms();
		
		String filter = request.getParameter("filter") != null ? request.getParameter("filter") : "false";
//		String country = request.getParameter("country") != null ? request.getParameter("country") : "de";
		
		try {
			ArrayList<FormsListStorage> formsList = Forms.getFormsList();		
			request.setAttribute("formsList", formsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String pageTitle = filter != "false" ? "Übersicht Aktiv" : "Übersicht";
		
		request.setAttribute("pageTitle", pageTitle);
		request.setAttribute("filter", filter);
		request.setAttribute("view", "list");
		
		getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
