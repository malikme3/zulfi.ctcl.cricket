package com.cricket.austin.zulfi.service;

import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.ClubsPage;
import com.cricket.austin.zulfi.model.Roles;

public interface ClubsService {
	public List<Map<String, Object>> clubsList();
	public List<ClubsPage> clubsDetails();
	public List<Roles> playersRole();
}
