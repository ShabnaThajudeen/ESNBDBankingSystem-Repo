package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.services.UserAccountSearchService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAccountSearchServlet
 */

public class UserAccountSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private UserAccountSearchService userAccSearchService;

	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		userAccSearchService = context.getBean("userAccSearchService", UserAccountSearchService.class);
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Account> accounts = new ArrayList<Account>();
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("username");		
		
		try {
			accounts = userAccSearchService.getUserAccounts(userName);				
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		
		if(accounts != null) {				
			response.setContentType("application/json");
			JSONArray myJSON = new JSONArray();
			for(Account account: accounts) {
				Long accNo = account.getAccountNo();
				String accountNo = accNo.toString();
				
				String accType = account.getAccountType();
				System.out.println();
				System.out.println("Fetched account no and account type for the account " + account + ": " + accountNo + " | " + accType);	
				System.out.println();
				
				JSONObject jObj = new JSONObject();
				jObj.put("accountNo", accountNo);
				jObj.put("accType", accType);
				myJSON.put(jObj);				
			}			
			out.println(myJSON);
		}
		else {
			out.print("");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
}
