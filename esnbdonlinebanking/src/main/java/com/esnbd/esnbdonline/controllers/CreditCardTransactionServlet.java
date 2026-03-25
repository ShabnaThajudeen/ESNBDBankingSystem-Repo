package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.services.CreditCardTransactionService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthorizeCreditCardTransactionServlet
 */
public class CreditCardTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ApplicationContext context;		
	private CreditCardTransactionService ccTRansactionService;
	
	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		ccTRansactionService = context.getBean("cctService", CreditCardTransactionService.class);	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String creditCardNo = request.getParameter("cardno");		
		String cvv = request.getParameter("cvv");
		String amount = request.getParameter("amount");
		
		String message = ccTRansactionService.processTransaction(name, creditCardNo, cvv, amount);	
		
		if(message.equals(AppConstants.CC_TRANSACTION_SUCCESSFULL)) {
			PrintWriter out = response.getWriter();			
			RequestDispatcher rd = request.getRequestDispatcher("authorize_credit_card_transactions.jsp");
			rd.include(request, response);
			out.println("<div class=\"tab\" style=\"background-color:#9FE2BF; width:500px;height:40px\">");
			out.println("<p style=\"color:#033E3E;text-align:center\">" + message + "</p>");
			out.println("</div>");
		}
		else{
			PrintWriter out = response.getWriter();			
			RequestDispatcher rd = request.getRequestDispatcher("authorize_credit_card_transactions.jsp");
			rd.include(request, response);
			out.println("<div class=\"tab\" style=\"background-color:#9FE2BF; width:500px;height:40px\">");
			out.println("<p style=\"color:red;text-align:center\">" + " " + message + "</p>");
			out.println("</div>");
		}
	}
}
