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
 * Servlet implementation class DebitTransactionProcessingServlet
 */
public class DebitTransactionProcessingServlet extends HttpServlet {
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
		
		String debitFromAcc = request.getParameter("accno");
		String debitAmount = request.getParameter("amt");
		String creditToAcc = request.getParameter("useracc");
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
	/**		if(message.equals(AppConstants.TRANSACTION_SUCCESSFULL)) { 				
			    RequestDispatcher rd = request.getRequestDispatcher("perform_transactions.jsp"); 
				rd.forward(request, response); 
				out.println("<div class=\"tab\" style=\"background-color:#9FE2BF; width:300px\">"); 
				out.println("<p style=\"color:black\">" + message + "</p>");
				out.println("</div>"); 				
			} 
			else{ 				
				RequestDispatcher rd = request.getRequestDispatcher("perform_transactions.jsp"); 
				rd.forward(request, response); 
				out.println("<div class=\"tab\" style=\"background-color:yellow; width:300px\">"); 
				out.println("<p style=\"color:red\">" + " " + message + "</p>");
				out.println("</div>"); 				
			}	**/			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			out.print("");
		}		 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);	
	}
}
