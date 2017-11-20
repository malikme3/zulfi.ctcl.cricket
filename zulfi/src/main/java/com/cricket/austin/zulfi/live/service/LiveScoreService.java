package com.cricket.austin.zulfi.live.service;

import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface LiveScoreService {
	int insertWicket(Wicket wicket);

	public ScoreForm getScoreFrom(String machId);

	public int syncScoreForm(ScoreForm scoreForm);

	public int updateInsertMatchData(Match match);

	public int insertUpdateMatchData(Match match);

	public int updateInsertBatsmanData(ScoreForm scoreForm);

	public int insertUpdateBatsmanData(ScoreForm scoreForm);

	public int updateInsertBowlerData(ScoreForm scoreForm);

	public int insertUpdateBowlerData(ScoreForm scoreForm);

	public int updateInsertWicketData(ScoreForm scoreForm);

	public int insertUpdateWicketData(ScoreForm scoreForm);
}
