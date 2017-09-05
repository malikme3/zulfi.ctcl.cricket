package com.cricket.austin.zulfi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBowling {

	private MatchDetails matchInfo;

	private List<ScorecardBowlingDetails> bowlingDetails;

	public MatchDetails getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchDetails matchInfo) {
		this.matchInfo = matchInfo;
	}

	public List<ScorecardBowlingDetails> getBowlingDetails() {
		return bowlingDetails;
	}

	public void setBowlingDetails(List<ScorecardBowlingDetails> bowlingDetails) {
		this.bowlingDetails = bowlingDetails;
	}

	@Override
	public String toString() {
		return "ScorecardBowling [matchInfo=" + matchInfo + ", bowlingDetails=" + bowlingDetails + "]";
	}

}
