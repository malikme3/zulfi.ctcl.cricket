package com.cricket.austin.zulfi.live.service;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.PlayingXI;
import com.cricket.austin.zulfi.live.model.PreMatchInfoByUmpire;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface LiveScoreService {
	int insertWicket(Wicket wicket);

	public ScoreForm getScoreFrom(String machId);

	public int syncScoreForm(ScoreForm scoreForm);

	public int updateInsertMatchData(Match match);

	public int insertUpdateMatchData(Match match);

	public int updateInsertBatsmanData(Batsman batsman);

	public int insertUpdateBatsmanData(Batsman batsman);

	public int updateInsertBowlerData(Bowler bowler);

	public int insertUpdateBowlerData(Bowler bowler);

	public int updateInsertWicketData(Wicket wicket);

	public int insertUpdateWicketData(Wicket wicket);

	public int insertUpdateUmpirePreMatch(PreMatchInfoByUmpire info);

	public int insertUpdatePlayingXI(PlayingXI xi);
}
