package com.esnbd.esnbdonline.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateService {
	private static Date date;
	
	public static String getCurrentDate() {
		String dateStr;
		
		date = Calendar.getInstance().getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM yyyy");				
		dateStr = dateFormat.format(date);
		
		return dateStr;
	}
	
	public static String dateStringFormatter(String dateToConvert) {
		String dateStr = "";
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date date = dateFormat1.parse(dateToConvert);
			DateFormat dateFormat2 =  new SimpleDateFormat("dd-MMM yyyy");
			dateStr = dateFormat2.format(date);
		} catch (ParseException e) {			
			e.printStackTrace();
		}		
		return dateStr;	
	}
	
	public static String getValidFromDateCreCard() {
		String dateStr;
		
		date = Calendar.getInstance().getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/yy");				
		dateStr = dateFormat.format(date);
		
		return dateStr;
	}
	
	@SuppressWarnings("deprecation")
	public static String getValidTillDateCreCard() {
		String dateStr;
		
		date = Calendar.getInstance().getTime();
		System.out.println(date);
		date.setYear(date.getYear() + 5);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/yy");				
		dateStr = dateFormat.format(date);
		
		return dateStr;
	}
	
	public static boolean notExpired(String date) {			
		Integer month = Integer.parseInt(date.substring(0, 2));		
		Integer year = Integer.parseInt(date.substring(3));
		
		LocalDate currentDate = LocalDate.now();
		String cDateYearStr = String.valueOf(currentDate.getYear()).substring(2);
		Integer cDateYear = Integer.parseInt(cDateYearStr);
		Integer cDateMonth = currentDate.getMonthValue();		
		
		if(year >= cDateYear) {
			if(month >= cDateMonth) {
				return true;
			}
		}
		
		return false;
	}	
}
