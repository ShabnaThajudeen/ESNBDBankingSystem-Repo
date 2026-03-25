package com.esnbd.esnbdonline.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.User;

public class MainMenuFilterDAOImpl implements MainMenuFilterDAO{	
	private HibernateTemplate session;	
	
	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@Transactional(readOnly = true)
	@Override
	public User getUserDetails(String userName, String pwd) {		
		return session.get(User.class, userName);		
	}
}
