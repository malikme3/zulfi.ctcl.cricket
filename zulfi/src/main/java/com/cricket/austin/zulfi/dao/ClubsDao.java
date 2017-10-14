package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.ClubsPage;
import com.cricket.austin.zulfi.model.News;
import com.cricket.austin.zulfi.model.Roles;

public interface ClubsDao {

	public List<Map<String, Object>> clubsList();

	public List<ClubsPage> clubsDetails();

	public List<Roles> playersRole();

	public List<News> getNews();

}
