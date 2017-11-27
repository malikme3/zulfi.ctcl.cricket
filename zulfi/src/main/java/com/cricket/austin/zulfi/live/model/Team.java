package com.cricket.austin.zulfi.live.model;

public class Team {
	String label;
	int value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Team [label=" + label + ", value=" + value + "]";
	}
}