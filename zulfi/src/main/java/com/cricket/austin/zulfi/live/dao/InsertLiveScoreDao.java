package com.cricket.austin.zulfi.live.dao;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface InsertLiveScoreDao {

	public int insertMatchData(Match match);

	public int insertBatsmanData(Batsman batsman);

	public int insertBowlerData(Bowler bowler);

	public int insertWicketData(Wicket wicket);

}
