package com.cricket.austin.zulfi.live.model;

public class MatchPlayer {
	int id, teamId;
	String itemName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "MatchPlayer [id=" + id + ", teamId=" + teamId + ", itemName=" + itemName + "]";
	}

}
