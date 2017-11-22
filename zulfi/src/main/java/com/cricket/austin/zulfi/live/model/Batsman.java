package com.cricket.austin.zulfi.live.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Batsman {
	boolean active, striker;
	float overs, strike_rate;
	String name, live_game_id;
	int id, player_id, position, balls, score, fours, sixes;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isStriker() {
		return striker;
	}

	public void setStriker(boolean striker) {
		this.striker = striker;
	}

	public float getOvers() {
		return overs;
	}

	public void setOvers(float overs) {
		this.overs = overs;
	}

	public float getStrike_rate() {
		return strike_rate;
	}

	public void setStrike_rate(float strike_rate) {
		this.strike_rate = strike_rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	@Override
	public String toString() {
		return "Batsman [active=" + active + ", striker=" + striker + ", overs=" + overs + ", strike_rate="
				+ strike_rate + ", name=" + name + ", live_game_id=" + live_game_id + ", id=" + id + ", player_id="
				+ player_id + ", position=" + position + ", balls=" + balls + ", score=" + score + ", fours=" + fours
				+ ", sixes=" + sixes + "]";
	}

}
