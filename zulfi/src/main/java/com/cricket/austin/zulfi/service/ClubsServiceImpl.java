package com.cricket.austin.zulfi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.dao.ClubsDao;
import com.cricket.austin.zulfi.model.ClubsPage;
import com.cricket.austin.zulfi.model.News;
import com.cricket.austin.zulfi.model.Roles;

@Service
@Component
public class ClubsServiceImpl implements ClubsService {
	@Autowired
	private ClubsDao clubsDao;

	@Override
	public List<Map<String, Object>> clubsList() {
		return clubsDao.clubsList();
	}

	@Override
	public List<ClubsPage> clubsDetails() {

		return clubsDao.clubsDetails();
	}

	@Override
	public List<Roles> playersRole() {

		return clubsDao.playersRole();
	}

	@Override
	public List<News> getNews() {
		return clubsDao.getNews();
	}

}
