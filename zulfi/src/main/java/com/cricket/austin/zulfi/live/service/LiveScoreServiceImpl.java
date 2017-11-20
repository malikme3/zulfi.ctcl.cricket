package com.cricket.austin.zulfi.live.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.live.dao.InsertLiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.dao.LiveScoreDao;
import com.cricket.austin.zulfi.live.dao.LiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.dao.UpdateLiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

@Service
@Component
public class LiveScoreServiceImpl implements LiveScoreService {
	@Autowired
	private LiveScoreDao LiveScoreDao;
	@Autowired
	private InsertLiveScoreDaoImpl insertLiveScoreDaoImpl;
	@Autowired
	private UpdateLiveScoreDaoImpl updateLiveScoreDaoImpl;
	static final Logger logger = LoggerFactory.getLogger(LiveScoreDaoImpl.class);

	@Override
	public int syncScoreForm(ScoreForm scoreForm) {
		int rows = 0;
		// Checking if liveGameId is provided or not
		try {
			if (isEmptyZeroNull(scoreForm.getLive_game_id())) {
				return rows;
			}
		} catch (Exception ex) {
			logger.warn("Error in syncScoreForm. LiveGame is not valid. " + ex);
		}

		try {
			rows = syncMatchData(scoreForm.getMatch());
		} catch (Exception ex) {
			logger.warn("Error in syncMatchData. " + ex);
		}

		return rows;
	}

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

	// Making sure, if update/insert fail then force to insert/update respectively
	// and vice versa ...

	protected int syncMatchData(Match match) {
		logger.info("In syncMatchData with isActive: " + match.isActive());
		int rows;
		if (match.isActive()) {
			rows = updateInsertMatchData(match);
		} else {
			rows = insertUpdateMatchData(match);
		}
		return rows;
	}

	/**** Match Data Insert/Update Start ****/

	@Override
	public int insertUpdateMatchData(Match match) {

		int rows = 0;

		if (match.getId() < 1) {
			logger.info("Data don't exist, Try to insert Match data");
			try {
				rows = insertLiveScoreDaoImpl.insertMatchData(match);
			} catch (Exception ex) {
				logger.info("Insert failed, Try update Match data");
				rows = updateLiveScoreDaoImpl.updateMatchData(match);
			}

		} else {
			logger.info("Data exist, Try update Match data");
			rows = updateLiveScoreDaoImpl.updateMatchData(match);
			return rows;
		}
		return rows;
	}

	@Override
	public int updateInsertMatchData(Match match) {

		int rows = 0;

		try {
			logger.info("Try to insert Match data");
			rows = updateLiveScoreDaoImpl.updateMatchData(match);

		} catch (Exception ex) {
			logger.info("update failed, Try insert Match data");
			rows = insertLiveScoreDaoImpl.insertMatchData(match);
		}
		return rows;
	}

	/**** Match Data Insert/Update END ****/
	/**** Bowler Data Insert/Update Start ****/

	@Override
	public int insertUpdateBatsmanData(ScoreForm scoreForm) {
		int rows = 0;

		try {
			logger.info("Try to insert Batsman Data");
			// TODO: check for batsman 1&2
			rows = insertLiveScoreDaoImpl.insertBatsmanData((scoreForm.getBatsman_1()));
			rows = insertLiveScoreDaoImpl.insertBatsmanData((scoreForm.getBatsman_1()));
		} catch (Exception ex) {
			logger.info("insert failed, Try update Batsman Data");
			// TODO: check for batsman 1&2
			rows = updateLiveScoreDaoImpl.updateBatsmanData((scoreForm.getBatsman_1()));
			rows = updateLiveScoreDaoImpl.updateBatsmanData((scoreForm.getBatsman_1()));

		}
		return rows;
	}

	@Override
	public int updateInsertBatsmanData(ScoreForm scoreForm) {
		int rows = 0;

		try {
			// TODO: check for batsman 1&2
			logger.info("updateInsertBatsmanData :update batsman data");
			rows = updateLiveScoreDaoImpl.updateBatsmanData((scoreForm.getBatsman_1()));
			rows = updateLiveScoreDaoImpl.updateBatsmanData((scoreForm.getBatsman_2()));

		} catch (Exception ex) {
			logger.info("update failed, Try insert Batsman data");
			// TODO: check for batsman 1&2
			rows = insertLiveScoreDaoImpl.insertBatsmanData(scoreForm.getBatsman_1());
			rows = insertLiveScoreDaoImpl.insertBatsmanData(scoreForm.getBatsman_2());
		}
		return rows;
	}

	/**** Batsman Data Insert/Update END ****/
	/**** Bowler Data Insert/Update Start ****/

	@Override
	public int insertUpdateBowlerData(ScoreForm scoreForm) {
		int rows = 0;

		try {
			logger.info("Try to insert Bolwer data");
			rows = insertLiveScoreDaoImpl.insertBowlerData(scoreForm.getBowler());
		} catch (Exception ex) {
			logger.info("insert failed, Try update Bolwer data");
			rows = updateLiveScoreDaoImpl.updateBowlerData(scoreForm.getBowler());

		}
		return rows;
	}

	@Override
	public int updateInsertBowlerData(ScoreForm scoreForm) {
		int rows = 0;

		try {
			logger.info("updateInsertBatsmanData :update data");
			rows = updateLiveScoreDaoImpl.updateBowlerData(scoreForm.getBowler());

		} catch (Exception ex) {
			logger.info("update failed, Try insert data");
			rows = insertLiveScoreDaoImpl.insertBowlerData(scoreForm.getBowler());
		}
		return rows;
	}

	/**** Bowler Data Insert/Update END ****/
	/**** Wicket Data Insert/Update Start ****/

	@Override
	public int insertUpdateWicketData(ScoreForm scoreForm) {
		int rows = 0;

		try {
			logger.info("Try to insert Wicket Data");
			rows = insertLiveScoreDaoImpl.insertWicketData((scoreForm.getWicket()));
		} catch (Exception ex) {
			logger.info("insert failed, Try update Wicket Data");
			rows = updateLiveScoreDaoImpl.updateWicketsData((scoreForm.getWicket()));

		}
		return rows;
	}

	@Override
	public int updateInsertWicketData(ScoreForm scoreForm) {
		int rows = 0;

		try {

			logger.info("updateInsertBatsmanData :update data");
			rows = updateLiveScoreDaoImpl.updateWicketsData((scoreForm.getWicket()));

		} catch (Exception ex) {
			logger.info("update failed, Try insert data");
			rows = insertLiveScoreDaoImpl.insertWicketData((scoreForm.getWicket()));

		}
		return rows;
	}

	/**** Wicket Data Insert/Update End ****/

	public boolean isEmptyZeroNull(String s) {
		if (s == null || ".".equalsIgnoreCase(s) || s.length() < 0) {
			return true;
		} else
			return false;
	}

}
