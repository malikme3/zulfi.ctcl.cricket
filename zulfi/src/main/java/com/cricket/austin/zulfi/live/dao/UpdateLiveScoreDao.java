package com.cricket.austin.zulfi.live.dao;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface UpdateLiveScoreDao {
	public int updateMatchData(Match match);

	public int updateBatsmanData(Batsman batsman);

	public int updateBowlerData(Bowler bowler);

	public int updateWicketsData(Wicket wicket);
}
