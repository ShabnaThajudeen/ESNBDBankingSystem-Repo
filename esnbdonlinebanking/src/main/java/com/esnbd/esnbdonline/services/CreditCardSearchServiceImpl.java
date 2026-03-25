package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dao.CreditCardSearchDAO;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.CreditCard;

public class CreditCardSearchServiceImpl implements CreditCardSearchService{
	private CreditCardSearchDAO ccSearchDAO;
	
	public void setCcSearchDAO(CreditCardSearchDAO ccSearchDAO) {
		this.ccSearchDAO = ccSearchDAO;
	}
	
	@Override
	public String getCreditCardNo(String userName){
		String creditCardNo = "";
		try {
			List<AccountHolderDetails> holders = ccSearchDAO.getCreditCardDetails(userName);			
			creditCardNo = this.getCreditCard(holders);			
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		return creditCardNo;
	}
	
	@Override
	public String getCreditCard(List<AccountHolderDetails> holders) {
		String creditCardNo = "";
		if(holders.size() != 0) {
			for(AccountHolderDetails holder: holders) {
				CreditCard card = holder.getCreditCard();
				creditCardNo = card.getCreditCardNo();				
			}			
		}
		return creditCardNo;		
	}
}
