package com.esnbd.esnbdonline.services;

public interface TransactionService {
	String debitProcessing(String debitFromAcc, String debitAmount, String creditToAcc);
}
