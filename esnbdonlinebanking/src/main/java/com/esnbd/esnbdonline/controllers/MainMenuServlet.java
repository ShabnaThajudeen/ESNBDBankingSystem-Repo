package com.esnbd.esnbdonline.controllers;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.services.UserNameSearchService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MainMenuServlet
 */
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static ApplicationContext context;
	private static UserNameSearchService uNameSearchServ;
	
	
	public void init(ServletConfig config) {	
		context = ApplicationContextFactory.getApplicationContext();
		uNameSearchServ = context.getBean("unsService", UserNameSearchService.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		
		String username = request.getParameter("username");		
		httpSession.setAttribute("UserName", username);
		
		String name = uNameSearchServ.getName(username);
		httpSession.setAttribute("Name", name);
		
		RequestDispatcher rd = request.getRequestDispatcher("mainmenu.jsp");
		rd.forward(request, response);
	}
	
	public void destroy() {		
	}
}
