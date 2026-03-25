package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public class AccountCreationDAOImpl implements AccountCreationDAO{	
	private HibernateTemplate session;
	
	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<AccountHolderDetails> checkExistingAccountHolder(String name, String dob) {
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
	
	@Override
	@Transactional(readOnly = false)
	public String createAccountHolder(AccountHolderDetails holder) {
		String message = "";
		try {
			session.save(holder);
			message = AppConstants.ACCOUNT_CREATION_SUCCESS;
		} catch (DataAccessException e) {
			message = AppConstants.ACCOUNT_CREATION_FAILED;
			e.printStackTrace();
		}
		return message;
	}
	
	@Override
	@Transactional(readOnly = false)
	public String updateAccountHolder(AccountHolderDetails holder) {
		String message = "";
		try {
			session.update(holder);								
			message = AppConstants.ACCOUNT_CREATION_SUCCESS;
		} catch (DataAccessException e) {
			message = AppConstants.ACCOUNT_CREATION_FAILED;
			e.printStackTrace();
		}
		return message;
	}
}
