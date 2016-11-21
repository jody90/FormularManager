package de.formularmanager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.databaseoperations.FormStatistics;
import de.formularmanager.storage.FromsStatisticsStorage;

@WebServlet("/StatisticsController")
public class StatisticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatisticsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action") != null ? request.getParameter("action") : "false";
		String formId = request.getParameter("form_id") != null ? request.getParameter("form_id") : "false";		
		String country = request.getParameter("country") != null ? request.getParameter("country") : "DE";
		
		FormStatistics stats = new FormStatistics();
		FromsStatisticsStorage statistics = new FromsStatisticsStorage();
		
		try {
			statistics = stats.getStatistics(formId, country);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson1 = new Gson(); 
		String statisticsValueJson = gson1.toJson(statistics.getStatisticsValue());

		Map<String, String> statisticsData = new HashMap<String, String>();
		statisticsData.put("resultsJson", statisticsValueJson);
		statisticsData.put("formJson", statistics.getJsonForm());
		
		Gson gson = new Gson();
		String json = gson.toJson(statisticsData);

		if (action.equals("getStatistics")) {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    return;
		}
		else {
			request.setAttribute("statistics", statistics);
			request.setAttribute("pageTitle", "Statistiken");
			request.setAttribute("view", "statistics");
			getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
