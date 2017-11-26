package com.cricket.austin.zulfi.live.model;

import java.sql.Date;

public class PreMatchInfoByUmpire {
	int id, league, ground, home_team, guest_team, toss_won_team, batting_frst_team, batting_second_team, umpire_team_1,
			umpire_team_2, maxovers, match_week;
	Date match_date;
	String live_game_id, comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeague() {
		return league;
	}

	public void setLeague(int league) {
		this.league = league;
	}

	public int getGround() {
		return ground;
	}

	public void setGround(int ground) {
		this.ground = ground;
	}

	public int getHome_team() {
		return home_team;
	}

	public void setHome_team(int home_team) {
		this.home_team = home_team;
	}

	public int getGuest_team() {
		return guest_team;
	}

	public void setGuest_team(int guest_team) {
		this.guest_team = guest_team;
	}

	public int getToss_won_team() {
		return toss_won_team;
	}

	public void setToss_won_team(int toss_won_team) {
		this.toss_won_team = toss_won_team;
	}

	public int getBatting_frst_team() {
		return batting_frst_team;
	}

	public void setBatting_frst_team(int batting_frst_team) {
		this.batting_frst_team = batting_frst_team;
	}

	public int getBatting_second_team() {
		return batting_second_team;
	}

	public void setBatting_second_team(int batting_second_team) {
		this.batting_second_team = batting_second_team;
	}

	public int getUmpire_team_1() {
		return umpire_team_1;
	}

	public void setUmpire_team_1(int umpire_team_1) {
		this.umpire_team_1 = umpire_team_1;
	}

	public int getUmpire_team_2() {
		return umpire_team_2;
	}

	public void setUmpire_team_2(int umpire_team_2) {
		this.umpire_team_2 = umpire_team_2;
	}

	public int getMaxovers() {
		return maxovers;
	}

	public void setMaxovers(int maxovers) {
		this.maxovers = maxovers;
	}

	public int getMatch_week() {
		return match_week;
	}

	public void setMatch_week(int match_week) {
		this.match_week = match_week;
	}

	public Date getMatch_date() {
		return match_date;
	}

	public void setMatch_date(Date match_date) {
		this.match_date = match_date;
	}

	public String getLive_game_id() {
		return live_game_id;
	}

	public void setLive_game_id(String live_game_id) {
		this.live_game_id = live_game_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PreMatchInfoByUmpire [id=" + id + ", league=" + league + ", ground=" + ground + ", home_team="
				+ home_team + ", guest_team=" + guest_team + ", toss_won_team=" + toss_won_team + ", batting_frst_team="
				+ batting_frst_team + ", batting_second_team=" + batting_second_team + ", umpire_team_1="
				+ umpire_team_1 + ", umpire_team_2=" + umpire_team_2 + ", maxovers=" + maxovers + ", match_week="
				+ match_week + ", match_date=" + match_date + ", live_game_id=" + live_game_id + ", comments="
				+ comments + "]";
	}

}
