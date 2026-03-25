package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dto.CreditCard;

public class CreditCardTransactionDAOImpl implements CreditCardTransactionDAO{
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	public List<CreditCard> getCreditCardDetails(String name, String creditCardNo, String cvv) {
		String hql = "from com.esnbd.esnbdonline.dto.CreditCard where creditCardNo= :ccNo and cvv= :cvv and name= :name";
		
		String[] paramNames = {"ccNo", "cvv", "name"};
		String[] params = {creditCardNo, cvv, name};
		
		List<CreditCard> cards = new ArrayList<CreditCard>();
		
		try {
			cards = (List<CreditCard>)session.findByNamedParam(hql, paramNames, params);			
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return cards;		
	}
	
	@Override
	@Transactional(readOnly = false)
	public String processTranaction(CreditCard card) {
		String message = "";		
		try {
			session.update(card);
			message = AppConstants.CC_TRANSACTION_SUCCESSFULL;
		} catch (DataAccessException e) {
			message = AppConstants.CC_TRANSACTION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}
}
