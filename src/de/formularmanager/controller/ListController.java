package de.formularmanager.controller;

import java.io.IOException;
import java.util.ArrayList;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ListForms Forms = new ListForms();
		
		try {
			ArrayList<FormsListStorage> formsList = Forms.getFormsList();		
			request.setAttribute("formsList", formsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("pageTitle", "Übersicht");
		request.setAttribute("view", "list");
		
		getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
