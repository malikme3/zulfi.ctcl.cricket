package com.cricket.austin.zulfi.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Schedule;
import com.cricket.austin.zulfi.model.ScoreCardBasic;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.Seasons;

public interface TeamService {
	public List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	public List<ScoreCardBasic> getbasicScoreCard(int seasonId);

	public List<Seasons> getSeasonGroups(String year);

	public List<Schedule> getSchedule(String seasonId);

	public List<Map<String, Object>> getDetailedScore(int gameId);

	public List<Map<String, Object>> getBowlingDetails(int gameId);

	public List<Map<String, Object>> getExtraScoreDetails(int gameId) throws Exception;

	public List<Map<String, Object>> getTeamsName() throws Exception;

	public void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate);

	List<Map<String, Object>> findPlayerByTeamId(String teamId);

	List<Map<String, Object>> findPlayer() throws Exception;

	List<Map<String, Object>> findPlayerByIds(List<Integer> ids);

	List<Map<String, Object>> findHowOut();

}
