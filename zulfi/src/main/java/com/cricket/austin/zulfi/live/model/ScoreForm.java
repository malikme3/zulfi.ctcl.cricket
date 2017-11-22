package com.cricket.austin.zulfi.live.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreForm {
	int id, current_ball_runs;
	boolean is_batsman_out;
	String live_game_id, extras_types;

	Match match;
	Batsman batsman_1;
	Batsman batsman_2;
	Bowler bowler;
	Wicket wicket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrent_ball_runs() {
		return current_ball_runs;
	}

	public void setCurrent_ball_runs(int current_ball_runs) {
		this.current_ball_runs = current_ball_runs;
	}

	public boolean isIs_batsman_out() {
		return is_batsman_out;
	}

	public void setIs_batsman_out(boolean is_batsman_out) {
		this.is_batsman_out = is_batsman_out;
	}

	public String getExtras_types() {
		return extras_types;
	}

	public void setExtras_types(String extras_types) {
		this.extras_types = extras_types;
	}

	public String getLive_game_id() {
		return live_game_id;
	}

	public void setLive_game_id(String live_game_id) {
		this.live_game_id = live_game_id;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Batsman getBatsman_1() {
		return batsman_1;
	}

	public void setBatsman_1(Batsman batsman_1) {
		this.batsman_1 = batsman_1;
	}

	public Batsman getBatsman_2() {
		return batsman_2;
	}

	public void setBatsman_2(Batsman batsman_2) {
		this.batsman_2 = batsman_2;
	}

	public Bowler getBowler() {
		return bowler;
	}

	public void setBowler(Bowler bowler) {
		this.bowler = bowler;
	}

	public Wicket getWicket() {
		return wicket;
	}

	public void setWicket(Wicket wicket) {
		this.wicket = wicket;
	}

	@Override
	public String toString() {
		return "ScoreForm [id=" + id + ", current_ball_runs=" + current_ball_runs + ", is_batsman_out=" + is_batsman_out
				+ ", live_game_id=" + live_game_id + ", extras_types=" + extras_types + ", match=" + match
				+ ", batsman_1=" + batsman_1 + ", batsman_2=" + batsman_2 + ", bowler=" + bowler + ", wicket=" + wicket
				+ "]";
	}

}
