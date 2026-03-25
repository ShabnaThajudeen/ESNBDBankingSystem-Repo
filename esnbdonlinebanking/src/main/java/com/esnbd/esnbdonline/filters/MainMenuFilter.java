package com.esnbd.esnbdonline.filters;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.esnbd.esnbdonline.configs.ApplicationContextFactory;
import com.esnbd.esnbdonline.dto.User;
import com.esnbd.esnbdonline.services.MainMenuFilterService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class MainMenuFilter
 */
@WebFilter(description = "Filter for MainMenuServlet", urlPatterns = { "/mainmenudisplay" })
public class MainMenuFilter extends HttpFilter implements Filter {	
	private static final long serialVersionUID = 1L;
	
	private static ApplicationContext context;
	@Autowired
	private MainMenuFilterService mmfs;			

	public void destroy() {	
		((ConfigurableApplicationContext)context).close();
	}		
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		String userName = request.getParameter("username");
		String password = request.getParameter("pwd");			
		
		User user = mmfs.getUserDetails(userName, password);
		
		if(user!= null) {
			if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				chain.doFilter(request, response);
			}
			else {
				PrintWriter out = response.getWriter();	
				out.println("<p style=\"text-align:center; color:red; font-size:25px\">Incorrect Password!!!</p>");				
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);				
			}
		}		
		else {	
			PrintWriter out = response.getWriter();	
			out.println("<p style=\"text-align:center; color:red; font-size:25px\">Invalid User!!!</p>");	
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);								
		}				
	}//doFilter
	
	public void init(FilterConfig fConfig) throws ServletException {		
		context = ApplicationContextFactory.getApplicationContext();
		mmfs = context.getBean("mmfService", MainMenuFilterService.class);		
	}
}
