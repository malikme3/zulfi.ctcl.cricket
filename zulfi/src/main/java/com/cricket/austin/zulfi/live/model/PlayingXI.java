package com.cricket.austin.zulfi.live.model;

import java.sql.Date;
import java.util.ArrayList;

public class PlayingXI {
	Date match_date;
	Team team;
	ArrayList<MatchPlayer> regularPlayers;
	ArrayList<MatchPlayer> portablesPlayers;

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

	public ArrayList<MatchPlayer> getRegularPlayers() {
		return regularPlayers;
	}

	public void setRegularPlayers(ArrayList<MatchPlayer> regularPlayers) {
		this.regularPlayers = regularPlayers;
	}

	public ArrayList<MatchPlayer> getPortablesPlayers() {
		return portablesPlayers;
	}

	public void setPortablesPlayers(ArrayList<MatchPlayer> portablesPlayers) {
		this.portablesPlayers = portablesPlayers;
	}

	@Override
	public String toString() {
		return "PlayingXI [match_date=" + match_date + ", team=" + team + ", regularPlayers=" + regularPlayers
				+ ", portablesPlayers=" + portablesPlayers + "]";
	}

}
