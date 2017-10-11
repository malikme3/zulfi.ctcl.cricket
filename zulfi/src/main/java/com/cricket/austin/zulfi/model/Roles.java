package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Roles {

	private static final long serialVersionUID = 1L;
	private int player_id;
	private String player_name;
	private int player_club;
	private int player_team;
	private int captain;
	private int vice_captain;
	private int president;
	private int vice_president;
	private int secretary;
	private int treasurer;

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public int getPlayer_club() {
		return player_club;
	}

	public void setPlayer_club(int player_club) {
		this.player_club = player_club;
	}

	public int getPlayer_team() {
		return player_team;
	}

	public void setPlayer_team(int player_team) {
		this.player_team = player_team;
	}

	public int getCaptain() {
		return captain;
	}

	public void setCaptain(int captain) {
		this.captain = captain;
	}

	public int getVice_captain() {
		return vice_captain;
	}

	public void setVice_captain(int vice_captain) {
		this.vice_captain = vice_captain;
	}

	public int getPresident() {
		return president;
	}

	public void setPresident(int president) {
		this.president = president;
	}

	public int getVice_president() {
		return vice_president;
	}

	public void setVice_president(int vice_president) {
		this.vice_president = vice_president;
	}

	public int getSecretary() {
		return secretary;
	}

	public void setSecretary(int secretary) {
		this.secretary = secretary;
	}

	public int getTreasurer() {
		return treasurer;
	}

	public void setTreasurer(int treasurer) {
		this.treasurer = treasurer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Roles [player_id=" + player_id + ", player_name=" + player_name + ", player_club=" + player_club
				+ ", player_team=" + player_team + ", captain=" + captain + ", vice_captain=" + vice_captain
				+ ", president=" + president + ", vice_president=" + vice_president + ", secretary=" + secretary
				+ ", treasurer=" + treasurer + "]";
	}

}
