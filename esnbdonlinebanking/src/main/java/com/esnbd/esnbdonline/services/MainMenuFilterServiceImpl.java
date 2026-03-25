package com.esnbd.esnbdonline.services;

import com.esnbd.esnbdonline.dao.MainMenuFilterDAO;
import com.esnbd.esnbdonline.dto.User;

public class MainMenuFilterServiceImpl implements MainMenuFilterService{	
	
	private MainMenuFilterDAO mmfDAO;		
	
    public void setMmfDAO(MainMenuFilterDAO mmfDAO) {
		this.mmfDAO = mmfDAO;
	}

	@Override
	public User getUserDetails(String userName, String pwd) {
		return mmfDAO.getUserDetails(userName, pwd);		
	}
}
