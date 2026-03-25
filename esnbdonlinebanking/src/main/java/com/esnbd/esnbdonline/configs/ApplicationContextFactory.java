package com.esnbd.esnbdonline.configs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextFactory {
	private static ApplicationContext context;
	 public static ApplicationContext getApplicationContext() {
		 if(context == null) {
			 context = new ClassPathXmlApplicationContext("applicationcontext.xml");
		 }
		 return context;
	 }
}
