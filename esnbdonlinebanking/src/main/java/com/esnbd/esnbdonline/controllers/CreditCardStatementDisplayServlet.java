package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;
import com.esnbd.esnbdonline.services.CreditCardStatementDisplayService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreditCardStatementDisplayServlet
 */
public class CreditCardStatementDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private CreditCardStatementDisplayService ccsdService;
	
	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		ccsdService = context.getBean("ccsdService", CreditCardStatementDisplayService.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ccNo = request.getParameter("cardno");
		String fromDate = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");		
		
		List<CreditCardTransactions> transactions = new ArrayList<CreditCardTransactions>();
		try {			
			transactions = ccsdService.getStatement(ccNo, fromDate, toDate);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		request.setAttribute("creditTransactions", transactions);		
		
		RequestDispatcher rd = request.getRequestDispatcher("credittransactions.jsp");
		rd.forward(request, response);		
	}
}
