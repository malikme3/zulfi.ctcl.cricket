package com.cricket.austin.zulfi.service;

import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.RecordsInputs;

public interface BattingRecordsService {

	public List<Map<String, Object>> battingRecords(RecordsInputs recordsInputs);
}
