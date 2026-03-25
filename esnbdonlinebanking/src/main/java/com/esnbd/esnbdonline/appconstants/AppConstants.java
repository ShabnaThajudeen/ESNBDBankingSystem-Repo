package com.esnbd.esnbdonline.appconstants;

public class AppConstants {
	public static final Float CURRENT_ACCOUNT_MIN_BALANCE = 3000.00f;
	public static final Float SAVINGS_ACCOUNT_MIN_BALANCE = 10000.00f;

	public static final String ACCOUNT_CREATION_SUCCESS = "Account created successfully.";
	public static final String ACCOUNT_CREATION_FAILED = "User Account is not created!";
	public static final String ACCOUNT_EXIST = "Account already exist for the user!";
	
	public static final String ACCOUNT_TYPE_SB = "SB";
	public static final String ACCOUNT_TYPE_CURRENT = "Current";
	
	public static final String TRANSACTION_SUCCESSFULL = "Transaction completed successfully.";
	public static final String TRANSACTION_FAILED = "Couldn't complete transaction!";
	public static final String TRANSACTION_ACCOUNT_NOT_EXIST = "account number doesn't exist!";
	public static final String TRANSACTION_INSUFFICIENT_BALANCE = "Insufficient balance in account: ";
	
	public static final String TRANSACTION_DESC_INITIAL_DEPOSIT = "Initial Deposit";
	public static final String TRANSACTION_DESC_ONELINE_DEBIT = "Withdrawal by Online Transaction";
	public static final String TRANSACTION_DESC_ONELINE_CREDIT = "Deposit by Online Transaction";
	
	public static final Float CREDITCARD_MONTHLY_LIMIT = 100000.00f;	
	
	public static final String CCARD_CREATION_SUCCESS = "Credit card issued successfully.";
	public static final String CCARD_CREATION_FAILED = "Credit card not issued!";
	public static final String CCARD_EXIST = "Credit card already exist for the user!";
	
	public static final String CC_TRANSACTION_SUCCESSFULL = "Transaction completed successfully.";
	public static final String CC_TRANSACTION_FAILED = "Couldn't complete transaction!";
	public static final String CC_TRANSACTION_INVALID_DETAILS = "Invalid credit card details!";
	public static final String CC_TRANSACTION_INSUFFICIENT_BALANCE = "Balance limit crossed!!";
	public static final String CC_TRANSACTION_CC_EXPIRED = "Credit Card Expired!!";
}
