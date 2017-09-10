package com.cricket.austin.zulfi.model;

import java.util.List;

public class BattingRecordsResponce {
	public List<ScorecardBattingDetails> batting;
	public long hundreds;
	public long fifties;
	public long ducks;
	public long totalnotOuts;
	public long totalSix;
	public long totalFours;
	public long highestScore;
	public long stumpeds;
	public long caughts;
	public long caughtAndBowleds;
	public long totalRuns;

	public List<ScorecardBattingDetails> getBatting() {
		return batting;
	}

	public void setBatting(List<ScorecardBattingDetails> batting) {
		this.batting = batting;
	}

	public long getHundreds() {
		return hundreds;
	}

	public void setHundreds(long hundreds) {
		this.hundreds = hundreds;
	}

	public long getFifties() {
		return fifties;
	}

	public void setFifties(long fifties) {
		this.fifties = fifties;
	}

	public long getDucks() {
		return ducks;
	}

	public void setDucks(long ducks) {
		this.ducks = ducks;
	}

	public long getTotalnotOuts() {
		return totalnotOuts;
	}

	public void setTotalnotOuts(long totalnotOuts) {
		this.totalnotOuts = totalnotOuts;
	}

	public long getTotalSix() {
		return totalSix;
	}

	public void setTotalSix(long totalSix) {
		this.totalSix = totalSix;
	}

	public long getTotalFours() {
		return totalFours;
	}

	public void setTotalFours(long totalFours) {
		this.totalFours = totalFours;
	}

	public long getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(long highestScore) {
		this.highestScore = highestScore;
	}

	public long getStumpeds() {
		return stumpeds;
	}

	public void setStumpeds(long stumpeds) {
		this.stumpeds = stumpeds;
	}

	public long getCaughts() {
		return caughts;
	}

	public void setCaughts(long caughts) {
		this.caughts = caughts;
	}

	public long getCaughtAndBowleds() {
		return caughtAndBowleds;
	}

	public void setCaughtAndBowleds(long caughtAndBowleds) {
		this.caughtAndBowleds = caughtAndBowleds;
	}

	public long getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(long totalRuns) {
		this.totalRuns = totalRuns;
	}

	@Override
	public String toString() {
		return "BattingRecordsResponce [batting=" + batting + ", hundreds=" + hundreds + ", fifties=" + fifties
				+ ", ducks=" + ducks + ", totalnotOuts=" + totalnotOuts + ", totalSix=" + totalSix + ", totalFours="
				+ totalFours + ", highestScore=" + highestScore + ", stumpeds=" + stumpeds + ", caughts=" + caughts
				+ ", caughtAndBowleds=" + caughtAndBowleds + ", totalRuns=" + totalRuns + "]";
	}

}
