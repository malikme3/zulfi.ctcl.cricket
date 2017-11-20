package com.cricket.austin.zulfi.live.service;

import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;

public interface LiveScoreService {
	int insertWicket(Wicket wicket);

	public ScoreForm getScoreFrom(String machId);
}
