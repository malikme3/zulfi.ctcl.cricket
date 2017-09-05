package com.cricket.austin.zulfi.service;

import com.cricket.austin.zulfi.model.ScorecardBatting;
import com.cricket.austin.zulfi.model.ScorecardBowling;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

public interface MatchScoringService {

	public void submitResults(SubmitResults scoreDetails);

	public void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	int updateScorecardGameDetails(ScorecardGameDetails details);

	int updateInsertScorecardExtrasDetails(SorecardExtrasDetails details);

	int updateInsertScorecardTotalDetails(ScorecardTotalDetails details);

	int updateInsertScorecardFowDetails1(ScorecardFowDetails details);

	int updateScorecardBattingDetails(ScorecardBatting details);

	public int inertUupdateScorecardBowlingDetails(ScorecardBowling match);

}
