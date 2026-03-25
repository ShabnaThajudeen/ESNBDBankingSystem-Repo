package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.dto.AccountTransactions;
import com.esnbd.esnbdonline.services.BankStatementDisplayService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankStatementDisplayServlet
 */
public class BankStatementDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private BankStatementDisplayService bsdService;

	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		bsdService = context.getBean("bsdService", BankStatementDisplayService.class);		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNo = request.getParameter("accounts");
		String fromDate = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");		
		
		List<AccountTransactions> transactions = bsdService.getStatement(accountNo, fromDate, toDate);
		
		request.setAttribute("transactionsList", transactions);		
		
		RequestDispatcher rd = request.getRequestDispatcher("transactions.jsp");
		rd.forward(request, response);		
	}
}
