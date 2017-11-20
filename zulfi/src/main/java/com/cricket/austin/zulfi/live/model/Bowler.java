package com.cricket.austin.zulfi.live.model;

public class Bowler {
	int id, player_id, position, balls, overs, maiden, score, wickets, economy, fours, sixes, wides, noballs;
	String live_game_id, name;

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

	public int getMaiden() {
		return maiden;
	}

	public void setMaiden(int maiden) {
		this.maiden = maiden;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getEconomy() {
		return economy;
	}

	public void setEconomy(int economy) {
		this.economy = economy;
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

	public String getLive_game_id() {
		return live_game_id;
	}

	public void setLive_game_id(String live_game_id) {
		this.live_game_id = live_game_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bowler [id=" + id + ", player_id=" + player_id + ", position=" + position + ", balls=" + balls
				+ ", overs=" + overs + ", maiden=" + maiden + ", score=" + score + ", wickets=" + wickets + ", economy="
				+ economy + ", fours=" + fours + ", sixes=" + sixes + ", wides=" + wides + ", noballs=" + noballs
				+ ", live_game_id=" + live_game_id + ", name=" + name + "]";
	}

}
