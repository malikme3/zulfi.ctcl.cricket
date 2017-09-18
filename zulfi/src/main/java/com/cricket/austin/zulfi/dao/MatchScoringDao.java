package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.ScorecardBatting;
import com.cricket.austin.zulfi.model.ScorecardBowling;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

public interface MatchScoringDao {

	int updateScorecardGameDetails(ScorecardGameDetails details);

	void submitResults(SubmitResults scoreDetails);

	void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	// int updateInsertScorecardBowlingDetails(ScorecardBowling match,
	// ScorecardBowlingDetails bowler);

	int updateInsertScorecardExtrasDetails(SorecardExtrasDetails details);

	int updateInsertScorecardTotalDetails(ScorecardTotalDetails details);

	int updateInsertScorecardFowDetails1(ScorecardFowDetails details);

	int updateInsertScorecardBattingDetails(ScorecardBatting details);

	int inertUupdateScorecardBowlingDetails(ScorecardBowling details);

	List<Map<String, Object>> getBattingScorecardByInnings(int gameId, int innings);

	List<Map<String, Object>> getScorecardInfoByInnings(int gameId, int innings);

}
