package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public class NewCreditCardDAOImpl implements NewCreditCardDAO{
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	/**
	 * Query to check if user details exist
	 */
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<AccountHolderDetails> checkCreditCardExisting(String name, String dob) {
        List<AccountHolderDetails> holders = new ArrayList<AccountHolderDetails>();		
		
		String hql = "from com.esnbd.esnbdonline.dto.AccountHolderDetails where name = :uName and dob = :dofb";
		String[] params = {"uName", "dofb"};
		String[] val = {name, dob};
		try {
			holders = (List<AccountHolderDetails>)session.findByNamedParam(hql, params, val);
		}
		catch(DataAccessException e) {
			e.printStackTrace();
		}
		return holders;
	}
	/**
	 * Query to create new user and credit card details
	 */
	
	@Override
	@Transactional(readOnly = false)
	public String issueNewCreditCard(AccountHolderDetails holder) {
		String message = "";
		
		try {
			int n  = (int)session.save(holder);
			if(n > 0) {
				message = AppConstants.CCARD_CREATION_SUCCESS;
			}		
			else {
				message = AppConstants.CCARD_CREATION_FAILED;
			}
		} catch (DataAccessException e) {
			message = AppConstants.CCARD_CREATION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}
	
	/**
	 * Query to activate credit card for exisitng user
	 */
	@Override
	@Transactional(readOnly = false)
	public String activateCreditCard(AccountHolderDetails holder) {
		String message = "";
		
		try {
			session.update(holder);
			message = AppConstants.CCARD_CREATION_SUCCESS;
		} catch (DataAccessException e) {
			message =  AppConstants.CCARD_CREATION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}		
}
