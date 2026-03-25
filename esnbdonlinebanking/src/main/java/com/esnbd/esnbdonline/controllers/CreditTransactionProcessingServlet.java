package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.services.TransactionService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreditTransactionProcessingServlet
 */
public class CreditTransactionProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private TransactionService txService;

	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		txService =  context.getBean("transService", TransactionService.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		PrintWriter out = response.getWriter();			
		
		String creditToAcc = request.getParameter("accno");
		String debitAmount = request.getParameter("amt");
		String debitFromAcc = request.getParameter("useracc");
		System.out.println();
		System.out.println("Initiating transfer of Rs." + debitAmount + " from account " + debitFromAcc + " to " + creditToAcc);
		System.out.println();
		
		try {
			message = txService.debitProcessing(debitFromAcc, debitAmount, creditToAcc);
			System.out.println();
			System.out.println(message);
			System.out.println();			
			
			response.setContentType("application/json");
			if(message != null) { 				
				JSONObject jObj = new JSONObject(); 
				jObj.put("message", message);
			    out.println(jObj); 
			    System.out.println(jObj); 
			} 
			else { 
				out.print(""); 
			}				
		}
		catch(Exception ex) {
			ex.printStackTrace();
			out.print("");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);	
	}
}
