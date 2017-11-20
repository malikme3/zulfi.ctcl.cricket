package com.cricket.austin.zulfi.live.model;

public class Match {
	boolean isActive;
	String live_game_id;
	int id, score, balls, overs, wickets, fours, sixes, wides, noballs, byes, legbyes;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLive_game_id() {
		return live_game_id;
	}

	public void setLive_game_id(String live_game_id) {
		this.live_game_id = live_game_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getNoballs() {
		return noballs;
	}

	public void setNoballs(int noballs) {
		this.noballs = noballs;
	}

	public int getByes() {
		return byes;
	}

	public void setByes(int byes) {
		this.byes = byes;
	}

	public int getLegbyes() {
		return legbyes;
	}

	public void setLegbyes(int legbyes) {
		this.legbyes = legbyes;
	}

	@Override
	public String toString() {
		return "Match [isActive=" + isActive + ", live_game_id=" + live_game_id + ", id=" + id + ", score=" + score
				+ ", balls=" + balls + ", overs=" + overs + ", wickets=" + wickets + ", fours=" + fours + ", sixes="
				+ sixes + ", wides=" + wides + ", noballs=" + noballs + ", byes=" + byes + ", legbyes=" + legbyes + "]";
	}

}
