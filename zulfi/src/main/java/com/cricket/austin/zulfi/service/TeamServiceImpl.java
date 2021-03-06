package com.cricket.austin.zulfi.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cricket.austin.zulfi.dao.MatchScoringDao;
import com.cricket.austin.zulfi.dao.TeamDao;
import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.Seasons;

@Service
@Component
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private MatchScoringDao matchScoringDao;

	@Override
	public List<Ladder> getTeamPosition(String seasonYear, String seasonName) {
		return teamDao.getTeamPosition(seasonYear, seasonName);
	}

	@Override
	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName) {
		return teamDao.getTeamsIdTeamsAbbrv(seasonYear, seasonName);
	}

	@Override
	public List<Map<String, Object>> getbasicScoreCard(int seasonId) {
		return teamDao.getbasicScoreCard(seasonId);
	}

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		return teamDao.getSeasonGroups(year);
	}

	@Override
	public List<Map<String, Object>> getSchedule(String seasonId) {
		return teamDao.getSchedule(seasonId);
	}

	@Override
	public List<Map<String, Object>> getDetailedScore(int gameId) {
		return teamDao.getDetailedScore(gameId);
	}

	@Override
	public List<Map<String, Object>> getTeamsName() {
		return teamDao.getTeamsName();
	}

	@Override
	public List<Map<String, Object>> getBowlingDetails(int gameId) {
		return teamDao.getBowlingDetails(gameId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public List<Map<String, Object>> getExtraScoreDetails(int gameId) throws Exception {
		/*
		 * try { update(); insert(); } catch (Exception ex) { System.out.println(ex);
		 * throw ex; }
		 */
		return teamDao.getExtraScoreDetails(gameId);
	}

	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor =
	// {Exception.class})
	public void update() throws Exception {
		teamDao.updatFname();
	}

	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor =
	// {Exception.class})
	public void insert() throws Exception {
		try {
			teamDao.updatLname();
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw ex;
		}
	}

	@Override
	public void submitScore_gameDetails(ScorecardGameDetails gameDetails) {
		matchScoringDao.submitScore_gameDetails(gameDetails);

	}

	@Override
	public List<Map<String, Object>> findPlayerByTeamId(String teamId) {
		return teamDao.findPlayerByTeamId(teamId);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public List<Map<String, Object>> findPlayer() throws Exception {
		try {
			// teamDao.updatFname();

			System.out.println("first name");

			// teamDao.updatLname();
			System.out.println("updated");

			return teamDao.findPlayer();

		} catch (Exception ex) {
		}
		throw new RuntimeException();

	}

	@Override
	public List<Map<String, Object>> findPlayerByIds(List<Integer> ids) {
		return teamDao.findPlayerByIds(ids);

	}

	@Override
	public List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate) {
		return teamDao.findMatchByDate(homeTeam, awayTeam, matchDate);

	}

	@Override
	public List<Map<String, Object>> findHowOut() {
		return teamDao.findHowOut();

	}

}
