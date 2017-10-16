package com.cricket.austin.zulfi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cricket.austin.zulfi.dao.BattingRecordsDao;
import com.cricket.austin.zulfi.model.RecordsInputs;

@Service
@Component
public class BattingRecordsServiceImpl implements BattingRecordsService {
	@Autowired
	private BattingRecordsDao battingRecordsDao;

	@Override
	public List<Map<String, Object>> battingRecords(RecordsInputs recordsInputs) {
		return battingRecordsDao.battingRecords(recordsInputs);

	}

	@Override
	public List<Map<String, Object>> bowlingRecords(RecordsInputs recordsInputs) {
		return battingRecordsDao.bowlingRecords(recordsInputs);

	}

}
