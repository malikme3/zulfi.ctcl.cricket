package com.cricket.austin.zulfi.live.model;

public class Batsman {

	int id, player_id, position, balls, overs, score, fours, sixes;
	String name, live_game_id;

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

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
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

	@Override
	public String toString() {
		return "Batsman [id=" + id + ", player_id=" + player_id + ", position=" + position + ", balls=" + balls
				+ ", overs=" + overs + ", score=" + score + ", fours=" + fours + ", sixes=" + sixes + ", name=" + name
				+ ", live_game_id=" + live_game_id + "]";
	}

}
