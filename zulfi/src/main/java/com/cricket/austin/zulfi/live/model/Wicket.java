package com.cricket.austin.zulfi.live.model;

public class Wicket {
	boolean active;
	int id, wicket_number, fow_score;
	String live_game_id, batsman_name, bowler_name, how_out, fielder_name;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWicket_number() {
		return wicket_number;
	}

	public void setWicket_number(int wicket_number) {
		this.wicket_number = wicket_number;
	}

	public int getFow_score() {
		return fow_score;
	}

	public String getLive_game_id() {
		return live_game_id;
	}

	public void setLive_game_id(String live_game_id) {
		this.live_game_id = live_game_id;
	}

	public void setFow_score(int fow_score) {
		this.fow_score = fow_score;
	}

	public String getBatsman_name() {
		return batsman_name;
	}

	public void setBatsman_name(String batsman_name) {
		this.batsman_name = batsman_name;
	}

	public String getBowler_name() {
		return bowler_name;
	}

	public void setBowler_name(String bowler_name) {
		this.bowler_name = bowler_name;
	}

	public String getHow_out() {
		return how_out;
	}

	public void setHow_out(String how_out) {
		this.how_out = how_out;
	}

	public String getFielder_name() {
		return fielder_name;
	}

	public void setFielder_name(String fielder_name) {
		this.fielder_name = fielder_name;
	}

	@Override
	public String toString() {
		return "Wicket [active=" + active + ", id=" + id + ", wicket_number=" + wicket_number + ", fow_score="
				+ fow_score + ", live_game_id=" + live_game_id + ", batsman_name=" + batsman_name + ", bowler_name="
				+ bowler_name + ", how_out=" + how_out + ", fielder_name=" + fielder_name + "]";
	}

}
