package com.esnbd.esnbdonline.dao;

import com.esnbd.esnbdonline.dto.User;

public interface MainMenuFilterDAO {
	User getUserDetails(String userName, String pwd);
}
