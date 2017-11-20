package com.cricket.austin.zulfi.live.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.live.dao.LiveScoreDao;
import com.cricket.austin.zulfi.live.dao.LiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

@Service
@Component
public class LiveScoreServiceImpl implements LiveScoreService {
	@Autowired
	private LiveScoreDao LiveScoreDao;
	static final Logger logger = LoggerFactory.getLogger(LiveScoreDaoImpl.class);

	@Override
	public int insertWicket(Wicket wicket) {
		int x = 2;
		return x;
		// return LiveScoreDao.updateInsertWicketData(wicket);
	}

	@Override
	public ScoreForm getScoreFrom(String machId) {
		return LiveScoreDao.getScoreFrom(machId);
	}

	public boolean isEmptyNull(String s) {
		if (".".equalsIgnoreCase(s) || s.length() < 0 || s == null) {
			logger.warn("Error in provided of Id #" + s);
			return true;
		} else
			return false;
	}

}
