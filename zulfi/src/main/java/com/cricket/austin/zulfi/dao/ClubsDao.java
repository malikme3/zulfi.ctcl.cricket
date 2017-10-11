package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.ClubsPage;
import com.cricket.austin.zulfi.model.Roles;

public interface ClubsDao {
	
	public List<Map<String, Object>> clubsList();
	public List<ClubsPage> clubsDetails();
	public List<Roles> playersRole();

}
