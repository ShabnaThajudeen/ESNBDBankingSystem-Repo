package com.esnbd.esnbdonline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.services.CreditCardSearchService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreditCardNoSearchServlet
 */
public class CreditCardNoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private CreditCardSearchService ccSearchService;
	
	public void init(ServletConfig config) throws ServletException {
		context = ApplicationContextFactory.getApplicationContext();
		ccSearchService = context.getBean("ccsService", CreditCardSearchService.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");			
		
		String creditCardNo = "";
		try {
			creditCardNo = ccSearchService.getCreditCardNo(userName);				
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		PrintWriter out = response.getWriter();
		if(creditCardNo != null) {				
			response.setContentType("application/json");
			
			JSONObject jObj =  new JSONObject();
			jObj.put("cardNo", creditCardNo);						
			out.println(jObj);
		}
		else {
			out.print("");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
