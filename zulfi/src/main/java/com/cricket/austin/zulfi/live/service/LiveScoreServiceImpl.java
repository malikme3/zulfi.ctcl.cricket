package com.cricket.austin.zulfi.live.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.live.dao.InsertLiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.dao.LiveScoreDao;
import com.cricket.austin.zulfi.live.dao.LiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.dao.UpdateLiveScoreDaoImpl;
import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.PlayingXI;
import com.cricket.austin.zulfi.live.model.PreMatchInfoByUmpire;
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

		// sync Match data
		try {
			scoreForm.getMatch().setLive_game_id(scoreForm.getLive_game_id());
			rows = syncMatchData(scoreForm.getMatch());
		} catch (Exception ex) {
			logger.warn("Error in syncMatchData. " + ex);
		}

		// sync Batsman Data
		try {
			scoreForm.getBatsman_1().setLive_game_id(scoreForm.getLive_game_id());
			// TODO: check if striker batsman is 1 or 2
			rows = syncBatsmanData(scoreForm.getBatsman_1());
			// rows = syncBatsmanData(scoreForm.getBatsman_2());
		} catch (Exception ex) {
			logger.warn("Error in syncBatsmanData. " + ex);
		}

		// sync Bowler Data
		try {
			scoreForm.getBowler().setLive_game_id(scoreForm.getLive_game_id());
			// rows = syncBowlerData(scoreForm.getBowler());
		} catch (Exception ex) {
			logger.warn("Error in syncBowlerData. " + ex);
		}

		// sync Wicket Data
		try {
			scoreForm.getWicket().setLive_game_id(scoreForm.getLive_game_id());
			// rows = syncWicketData(scoreForm.getWicket());
		} catch (Exception ex) {
			logger.warn("Error in syncWicketData. " + ex);
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
	public ScoreForm getScoreFrom(String liveGameId) {
		ScoreForm scoreForm = new ScoreForm();
		Match match = LiveScoreDao.getMatchData(liveGameId);
		scoreForm.setMatch(match);
		Batsman batsman_1 = LiveScoreDao.getBatsmanData(liveGameId, 2);
		scoreForm.setBatsman_1(batsman_1);
		Batsman batsman_2 = LiveScoreDao.getBatsmanData(liveGameId, 3);
		scoreForm.setBatsman_2(batsman_2);
		Bowler bowler = LiveScoreDao.getBowlerData(liveGameId, 2);
		scoreForm.setBowler(bowler);
		Wicket wicket = LiveScoreDao.getWicketData(liveGameId, 2);
		scoreForm.setWicket(wicket);
		return scoreForm;
	}

	// Making sure, if update/insert fail then force to insert/update respectively
	// and vice versa ...

	protected int syncMatchData(Match match) {
		logger.info("In syncMatchData with isActive: " + match.isActive());
		int rows;
		if (!match.isActive()) {
			match.setLive_game_id("RRCC-11142017");
			rows = updateInsertMatchData(match);
		} else {
			match.setActive(true);
			rows = insertUpdateMatchData(match);
		}
		return rows;
	}

	protected int syncBatsmanData(Batsman batsman) {
		logger.info("In syncBatsmanchData with isActive: " + batsman.isActive());
		int rows;
		if (batsman.isActive()) {
			batsman.setLive_game_id("RRCC-11142017");
			rows = updateInsertBatsmanData(batsman);
		} else {
			batsman.setActive(true);
			rows = insertUpdateBatsmanData(batsman);
		}
		return rows;
	}

	protected int syncBowlerData(Bowler bowler) {
		logger.info("In syncBowlerData with isActive: " + bowler.isActive());
		int rows;
		if (bowler.isActive()) {
			bowler.setLive_game_id("RRCC-11142017");
			rows = updateInsertBowlerData(bowler);
		} else {
			bowler.setActive(true);
			rows = insertUpdateBowlerData(bowler);
		}
		return rows;
	}

	protected int syncWicketData(Wicket wicket) {
		logger.info("In syncBatsmanchData with isActive: " + wicket.isActive());
		int rows;
		if (wicket.isActive()) {
			wicket.setLive_game_id("RRCC-11142017");
			rows = updateInsertWicketData(wicket);
		} else {
			wicket.setActive(true);
			rows = insertUpdateWicketData(wicket);
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
	public int insertUpdateBatsmanData(Batsman batsman) {
		int rows = 0;

		try {
			logger.info("Try to insert Batsman Data");
			rows = insertLiveScoreDaoImpl.insertBatsmanData((batsman));
		} catch (Exception ex) {
			logger.info("insert failed, Try update Batsman Data");
			rows = updateLiveScoreDaoImpl.updateBatsmanData((batsman));
		}
		return rows;
	}

	@Override
	public int updateInsertBatsmanData(Batsman batsman) {
		int rows = 0;

		try {
			logger.info("updateInsertBatsmanData :update batsman data");
			rows = updateLiveScoreDaoImpl.updateBatsmanData((batsman));

		} catch (Exception ex) {
			logger.info("update failed, Try insert Batsman data");
			rows = insertLiveScoreDaoImpl.insertBatsmanData(batsman);
		}
		return rows;
	}

	/**** Batsman Data Insert/Update END ****/
	/**** Bowler Data Insert/Update Start ****/

	@Override
	public int insertUpdateBowlerData(Bowler bowler) {
		int rows = 0;

		try {
			logger.info("Try to insert Bolwer data");
			rows = insertLiveScoreDaoImpl.insertBowlerData(bowler);
		} catch (Exception ex) {
			logger.info("insert failed, Try update Bolwer data");
			rows = updateLiveScoreDaoImpl.updateBowlerData(bowler);

		}
		return rows;
	}

	@Override
	public int updateInsertBowlerData(Bowler bowler) {
		int rows = 0;

		try {
			logger.info("updateInsertBatsmanData :update data");
			rows = updateLiveScoreDaoImpl.updateBowlerData(bowler);

		} catch (Exception ex) {
			logger.info("update failed, Try insert data");
			rows = insertLiveScoreDaoImpl.insertBowlerData(bowler);
		}
		return rows;
	}

	/**** Bowler Data Insert/Update END ****/
	/**** Wicket Data Insert/Update Start ****/

	@Override
	public int insertUpdateWicketData(Wicket wicket) {
		int rows = 0;

		try {
			logger.info("Try to insert Wicket Data");
			rows = insertLiveScoreDaoImpl.insertWicketData((wicket));
		} catch (Exception ex) {
			logger.info("insert failed, Try update Wicket Data");
			rows = updateLiveScoreDaoImpl.updateWicketsData((wicket));

		}
		return rows;
	}

	@Override
	public int updateInsertWicketData(Wicket wicket) {
		int rows = 0;

		try {

			logger.info("updateInsertBatsmanData :update data");
			rows = updateLiveScoreDaoImpl.updateWicketsData((wicket));

		} catch (Exception ex) {
			logger.info("update failed, Try insert data");
			rows = insertLiveScoreDaoImpl.insertWicketData((wicket));

		}
		return rows;
	}

	/**** Wicket Data Insert/Update End ****/

	/**** Pre-match information submitted by Umpire ***/

	@Override
	public int insertUpdateUmpirePreMatch(PreMatchInfoByUmpire info) {
		int rows = 0;

		try {
			info.setMatch_date(getTodayDate());
			info.setLive_game_id(setLiveGameId(info));
			info.setMatch_week(getCurrentWeek());
			logger.info("Inserting PreMatch data by Umpire");
			rows = insertLiveScoreDaoImpl.insertUmpirePreMatch(info);

		} catch (Exception ex) {
			logger.info("Insert failed, Try update data");
			// rows = updateLiveScoreDaoImpl.updateUmpirePreMatch((info));

		}
		return rows;
	}

	@Override
	public int insertUpdatePlayingXI(PlayingXI xi) {
		int rows = 0;

		try {
			xi.setMatch_date(getTodayDate());
			logger.info("Inserting Playing XI");
			rows = insertLiveScoreDaoImpl.insertPlayingXI(xi);

		} catch (Exception ex) {
			logger.info("Insert failed, Try update data");
			// rows = updateLiveScoreDaoImpl.updateUmpirePreMatch((info));

		}
		return rows;
	}

	// get full current week #
	private int getCurrentWeek() {
		LocalDate date = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		return date.get(weekFields.weekOfWeekBasedYear());
	}

	public Date getTodayDate() {
		long millis = System.currentTimeMillis();
		return new java.sql.Date(millis);
	};

	// set live game id
	private String setLiveGameId(PreMatchInfoByUmpire info) {
		// constructing game id as bat first & second team id, ground id and today's
		// date.

		LocalDate localDate = LocalDate.now();
		String today = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(localDate);

		String liveGameId = info.getBatting_frst_team().getValue() + "-" + info.getBatting_second_team().getValue()
				+ "-" + info.getGround() + "-" + today;
		return liveGameId;
	}

	public boolean isEmptyZeroNull(String s) {
		if (s == null || ".".equalsIgnoreCase(s) || s.length() < 0) {
			return true;
		} else
			return false;
	}

}
