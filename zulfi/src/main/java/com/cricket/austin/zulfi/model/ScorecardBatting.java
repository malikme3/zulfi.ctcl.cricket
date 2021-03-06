package com.cricket.austin.zulfi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBatting {

	private MatchDetails matchInfo;

	private List<ScorecardBattingDetails> batsmanDetails;

	public MatchDetails getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchDetails matchInfo) {
		this.matchInfo = matchInfo;
	}

	public List<ScorecardBattingDetails> getBatsmanDetails() {
		return batsmanDetails;
	}

	public void setBatsmanDetails(List<ScorecardBattingDetails> batsmanDetails) {
		this.batsmanDetails = batsmanDetails;
	}

	@Override
	public String toString() {
		return "ScorecardBatting [matchInfo=" + matchInfo + ", batsmanDetails=" + batsmanDetails + "]";
	}

}
