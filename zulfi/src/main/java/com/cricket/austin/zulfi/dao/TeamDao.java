package com.cricket.austin.zulfi.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.SubmitResults;

public interface TeamDao {
	List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	List<Map<String, Object>> getbasicScoreCard(int seasonId);

	List<Seasons> getSeasonGroups(String year);

	List<Map<String, Object>> getSchedule(String seasonId);

	List<Map<String, Object>> getDetailedScore(int gameId);

	List<Map<String, Object>> getTeamsName();

	List<Map<String, Object>> getBowlingDetails(int gameId);

	List<Map<String, Object>> getExtraScoreDetails(int gameId);

	void submitResults(SubmitResults scoreDetails);

	void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	void updatFname();

	void updatLname();

	List<Map<String, Object>> findPlayerByTeamId(int teamId);

	List<Map<String, Object>> findPlayer();

	int updateScorecardGameDetails(ScorecardGameDetails details);

	List<Map<String, Object>> findPlayerByIds(List<Integer> ids);

	List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate);

	List<Map<String, Object>> findHowOut();

}
