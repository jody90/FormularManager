package de.formularmanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.formularmanager.model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageTitle", "Login");
		request.setAttribute("view", "login");
		
		// liest alle Cookies in cookies ein
		Cookie[] cookies = null;
		cookies = request.getCookies();

		String action = request.getParameter("action") != null ? request.getParameter("action") : "false";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		switch (action) {
			case "login" :
						
				if (!username.isEmpty() && !password.isEmpty()) {
					User user = new User();
					user.setServletRequest(request);
					user.setServletResponse(response);
					user.setUsername(username);
					user.setPassword(password);
					
					if (user.login()) {
						response.sendRedirect("/FormularManager/index");
						return;
					}
				}
				response.sendRedirect("/FormularManager/login");
				return;
			case "logout" :

				User user = new User();
				user.setServletRequest(request);
				user.setServletResponse(response);
				user.setUsername(username);
				boolean logoutResponse = user.logout(cookies);
				
				if (logoutResponse) {
					response.sendRedirect("/FormularManager/login");
					return;
				}
				
			break;
			default : 
				getServletContext().getRequestDispatcher("/layout.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
