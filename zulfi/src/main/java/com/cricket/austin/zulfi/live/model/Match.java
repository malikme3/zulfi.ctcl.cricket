package com.cricket.austin.zulfi.live.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {
	boolean active;
	float overs, average;
	String live_game_id;
	int id, score, balls, wickets, fours, sixes, wides, noballs, byes, legbyes;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getOvers() {
		return overs;
	}

	public void setOvers(float overs) {
		this.overs = overs;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
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
		return "Match [active=" + active + ", overs=" + overs + ", average=" + average + ", live_game_id="
				+ live_game_id + ", id=" + id + ", score=" + score + ", balls=" + balls + ", wickets=" + wickets
				+ ", fours=" + fours + ", sixes=" + sixes + ", wides=" + wides + ", noballs=" + noballs + ", byes="
				+ byes + ", legbyes=" + legbyes + "]";
	}

}
