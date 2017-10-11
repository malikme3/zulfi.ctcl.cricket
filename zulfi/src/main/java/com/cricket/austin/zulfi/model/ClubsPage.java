package com.cricket.austin.zulfi.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonInclude(Include.NON_NULL)
public class ClubsPage {

	private static final long serialVersionUID = 1L;
	// private int id;

	/*
	 * "league_name": "ctcl", "club_id": 10, "club_name": "Round Rock Cricket Club",
	 * "club_url": "", "club_color": "", "team_id": 34, "team_name":
	 * "Round Rock Cricket Club Panthers", "team_abbrev": "Panthers", "team_color":
	 * "", "ground_name": "Round Rock Cricket Ground", "ground_id": 16
	 */

	private String league_name;
	int club_id;
	String club_name;
	String club_url;
	String club_color;
	int team_id;
	String team_name;
	String team_captain;
	String team_abbrev;
	String team_color;

	String ground_name;
	String ground_id;

	String captain_name;
	String vice_captain_name;
	String president_name;
	String vice_president_name;
	String secretary_name;
	String treasurer_name;

	public String getLeague_name() {
		return league_name;
	}

	public void setLeague_name(String league_name) {
		this.league_name = league_name;
	}

	public int getClub_id() {
		return club_id;
	}

	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getGround_id() {
		return ground_id;
	}

	public void setGround_id(String ground_id) {
		this.ground_id = ground_id;
	}

	public String getGround_name() {
		return ground_name;
	}

	public void setGround_name(String ground_name) {
		this.ground_name = ground_name;
	}

	public String getClub_color() {
		return club_color;
	}

	public void setClub_color(String club_color) {
		this.club_color = club_color;
	}

	public String getClub_url() {
		return club_url;
	}

	public void setClub_url(String club_url) {
		this.club_url = club_url;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_abbrev() {
		return team_abbrev;
	}

	public void setTeam_abbrev(String team_abbrev) {
		this.team_abbrev = team_abbrev;
	}

	public String getTeam_color() {
		return team_color;
	}

	public void setTeam_color(String team_color) {
		this.team_color = team_color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTeam_captain() {
		return team_captain;
	}

	public void setTeam_captain(String team_captain) {
		this.team_captain = team_captain;
	}

	public String getCaptain_name() {
		return captain_name;
	}

	public void setCaptain_name(String captain_name) {
		this.captain_name = captain_name;
	}

	public String getVice_captain_name() {
		return vice_captain_name;
	}

	public void setVice_captain_name(String vice_captain_name) {
		this.vice_captain_name = vice_captain_name;
	}

	public String getPresident_name() {
		return president_name;
	}

	public void setPresident_name(String president_name) {
		this.president_name = president_name;
	}

	public String getVice_president_name() {
		return vice_president_name;
	}

	public void setVice_president_name(String vice_president_name) {
		this.vice_president_name = vice_president_name;
	}

	public String getSecretary_name() {
		return secretary_name;
	}

	public void setSecretary_name(String secretary_name) {
		this.secretary_name = secretary_name;
	}

	public String getTreasurer_name() {
		return treasurer_name;
	}

	public void setTreasurer_name(String treasurer_name) {
		this.treasurer_name = treasurer_name;
	}

	@Override
	public String toString() {
		return "ClubsPage [league_name=" + league_name + ", club_id=" + club_id + ", club_name=" + club_name
				+ ", club_url=" + club_url + ", club_color=" + club_color + ", team_id=" + team_id + ", team_name="
				+ team_name + ", team_captain=" + team_captain + ", team_abbrev=" + team_abbrev + ", team_color="
				+ team_color + ", ground_name=" + ground_name + ", ground_id=" + ground_id + ", captain_name="
				+ captain_name + ", vice_captain_name=" + vice_captain_name + ", president_name=" + president_name
				+ ", vice_president_name=" + vice_president_name + ", secretary_name=" + secretary_name
				+ ", treasurer_name=" + treasurer_name + "]";
	}

}