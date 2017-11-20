package com.cricket.austin.zulfi.live.dao;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface LiveScoreDao {

	public Match getMatchData(String liveGameId);

	public Batsman getBatsmanData(String matchId, int playerId);

	public Bowler getBowlerData(String matchId, int playerId);

	public Wicket getWicketData(String matchId, int wicketPosition);

	public int submitScoreFormData(ScoreForm scoreForm);

	public ScoreForm getScoreFrom(String machId);

}
