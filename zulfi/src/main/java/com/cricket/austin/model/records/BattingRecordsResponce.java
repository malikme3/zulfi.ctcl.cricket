package com.cricket.austin.model.records;

import java.util.List;

public class BattingRecordsResponce {
	public List<BattingRecordsOutputs> batting;

	public List<BattingRecordsOutputs> getBatting() {
		return batting;
	}

	public void setBatting(List<BattingRecordsOutputs> batting) {
		this.batting = batting;
	}

	@Override
	public String toString() {
		return "BattingRecordsResponce [batting=" + batting + "]";
	}

}
