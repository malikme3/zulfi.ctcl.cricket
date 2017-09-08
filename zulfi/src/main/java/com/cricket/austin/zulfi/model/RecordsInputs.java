package com.cricket.austin.zulfi.model;

public class RecordsInputs {
	public String playerId;
	public String seasonId;
	public String seasonYear;
	public String teamId;
	public String clubdId;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(String seasonYear) {
		this.seasonYear = seasonYear;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getClubdId() {
		return clubdId;
	}

	public void setClubdId(String clubdId) {
		this.clubdId = clubdId;
	}

	@Override
	public String toString() {
		return "RecordsInputs [playerId=" + playerId + ", seasonId=" + seasonId + ", seasonYear=" + seasonYear
				+ ", teamId=" + teamId + ", clubdId=" + clubdId + "]";
	}

}
