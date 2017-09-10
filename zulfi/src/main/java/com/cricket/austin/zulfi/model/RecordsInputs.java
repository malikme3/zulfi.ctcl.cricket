package com.cricket.austin.zulfi.model;

public class RecordsInputs {
	public String playerId;
	public String seasonId;
	public String seasonYear;
	public String teamId;
	public String clubId;

	public String getPlayerId() {
		return playerId;
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

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "RecordsInputs [playerId=" + playerId + ", seasonId=" + seasonId + ", seasonYear=" + seasonYear
				+ ", teamId=" + teamId + ", clubId=" + clubId + "]";
	}

}
