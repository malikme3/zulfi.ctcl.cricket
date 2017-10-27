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
		
		// Setting explicit "null" value to use in sql query ifNull...
		
		if("null".equalsIgnoreCase(recordsInputs.getClubId()) || recordsInputs.getClubId().isEmpty()) {
			recordsInputs.setClubId(null);
		}
		if("null".equalsIgnoreCase(recordsInputs.getPlayerId()) || recordsInputs.getPlayerId().isEmpty()) {
			recordsInputs.setPlayerId(null);
		}
		if("null".equalsIgnoreCase(recordsInputs.getSeasonId()) || recordsInputs.getSeasonId().isEmpty()) {
			recordsInputs.setSeasonId(null);;
		}
		if("null".equalsIgnoreCase(recordsInputs.getSeasonYear()) || recordsInputs.getSeasonYear().isEmpty()) {
			recordsInputs.setSeasonYear(null);
		}
		if("null".equalsIgnoreCase(recordsInputs.getTeamId()) || recordsInputs.getTeamId().isEmpty()) {
			recordsInputs.setTeamId(null);
		}
		
		return battingRecordsDao.battingRecords(recordsInputs);

	}

	@Override
	public List<Map<String, Object>> bowlingRecords(RecordsInputs recordsInputs) {
		return battingRecordsDao.bowlingRecords(recordsInputs);

	}

}
