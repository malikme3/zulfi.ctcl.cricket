package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.RecordsInputs;

public interface BattingRecordsDao {
	public List<Map<String, Object>> battingRecords(RecordsInputs recordsInputs);

	public List<Map<String, Object>> bowlingRecords(RecordsInputs inputs);
}
