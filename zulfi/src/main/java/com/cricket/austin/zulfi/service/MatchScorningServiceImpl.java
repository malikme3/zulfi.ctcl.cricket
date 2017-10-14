package com.cricket.austin.zulfi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.controller.AppController;
import com.cricket.austin.zulfi.dao.MatchScoringDao;
import com.cricket.austin.zulfi.model.ScorecardBatting;
import com.cricket.austin.zulfi.model.ScorecardBowling;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

@Service
@Component
public class MatchScorningServiceImpl implements MatchScoringService {

	static final Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private MatchScoringDao matchScoringDao;

	@Override
	public void submitResults(SubmitResults scoreDetails) {
		matchScoringDao.submitResults(scoreDetails);
	}

	@Override
	public void submitScore_gameDetails(ScorecardGameDetails gameDetails) {
		matchScoringDao.submitScore_gameDetails(gameDetails);

	}

	@Override
	public int updateScorecardGameDetails(ScorecardGameDetails details) {
		return matchScoringDao.updateScorecardGameDetails(details);

	}

	@Override
	public int updateInsertScorecardExtrasDetails(SorecardExtrasDetails details) {
		return matchScoringDao.updateInsertScorecardExtrasDetails(details);
	}

	@Override
	public int updateInsertScorecardTotalDetails(ScorecardTotalDetails details) {
		return matchScoringDao.updateInsertScorecardTotalDetails(details);
	}

	@Override
	public int updateInsertScorecardFowDetails1(ScorecardFowDetails details) {
		return matchScoringDao.updateInsertScorecardFowDetails1(details);
	}

	@Override
	public int updateScorecardBattingDetails(ScorecardBatting details) {
		return matchScoringDao.updateInsertScorecardBattingDetails(details);
	}

	@Override
	public int inertUupdateScorecardBowlingDetails(ScorecardBowling details) {
		return matchScoringDao.inertUupdateScorecardBowlingDetails(details);

	}

	@Override
	public List<Map<String, Object>> getBattingScorecardByInnings(int gameId, int innings) {
		return matchScoringDao.getBattingScorecardByInnings(gameId, innings);
	}

	@Override
	public List<Map<String, Object>> getScorecardInfoByInnings(int gameId, int innings) {
		return matchScoringDao.getScorecardInfoByInnings(gameId, innings);
	}

	@Override
	public List<Map<String, Object>> getBowlingScorecardByInnings(int gameId, int innings) {
		return matchScoringDao.getBowlingScorecardByInnings(gameId, innings);
	}

	@Override
	public List<Map<String, Object>> getDNBScorecardByInnings(int gameId, int innings) {
		return matchScoringDao.getDNBScorecardByInnings(gameId, innings);
	}

	@Override
	public List<Map<String, Object>> getLatesMatchesSummary() {
		return matchScoringDao.getLatesMatchesSummary();
	}

}
