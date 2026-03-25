package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.services.AccountCreationService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountCreationServlet
 */
public class AccountCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static ApplicationContext context;	
	private AccountCreationService accCreationService;		

	public void init(ServletConfig config) throws ServletException {	
		context = ApplicationContextFactory.getApplicationContext();
		accCreationService = context.getBean("accCreationService", AccountCreationService.class);
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");		
		String addr = request.getParameter("addr");
		String mailId = request.getParameter("mail_id");
		String accType = request.getParameter("acc_type");
		
		String message = accCreationService.saveAccountHolderDetails(name, dob, addr, mailId, accType);
		
		if(message.equals(AppConstants.ACCOUNT_CREATION_SUCCESS)) {
			PrintWriter out = response.getWriter();			
			RequestDispatcher rd = request.getRequestDispatcher("accountcreation.jsp");
			rd.include(request, response);
			out.println("<div class=\"tab\" style=\"background-color:#9FE2BF; width:300px;height:40px\">");
			out.println("<p style=\"color:#033E3E;text-align:center\">" + message + "</p>");
			out.println("</div>");
		}
		else{
			PrintWriter out = response.getWriter();			
			RequestDispatcher rd = request.getRequestDispatcher("accountcreation.jsp");
			rd.include(request, response);
			out.println("<div class=\"tab\" style=\"background-color:#9FE2BF; width:300px;height:40px\">");
			out.println("<p style=\"color:red;text-align:center\">" + " " + message + "</p>");
			out.println("</div>");
		}			
	}
}
