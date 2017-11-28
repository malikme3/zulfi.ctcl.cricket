package com.cricket.austin.zulfi.live.model;

import java.sql.Date;

public class PlayingXI {
	Date match_date;
	Team team, player;
	String player_type;

	public Date getMatch_date() {
		return match_date;
	}

	public void setMatch_date(Date match_date) {
		this.match_date = match_date;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getPlayer() {
		return player;
	}

	public void setPlayer(Team player) {
		this.player = player;
	}

	public String getPlayer_type() {
		return player_type;
	}

	public void setPlayer_type(String player_type) {
		this.player_type = player_type;
	}

	@Override
	public String toString() {
		return "PlayingXI [match_date=" + match_date + ", team=" + team + ", player=" + player + ", player_type="
				+ player_type + "]";
	}

}
