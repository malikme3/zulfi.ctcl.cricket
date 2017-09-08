package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.RecordsInputs;

@Repository
public class BattingRecordsDaoImpl implements BattingRecordsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	static final Logger logger = LoggerFactory.getLogger(BattingRecordsDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Map<String, Object>> battingRecords(RecordsInputs inputs) {

		// @formatter:off
		String sql = "SELECT * FROM scorecard_batting_details b "
				+ "INNER JOIN seasons s ON b.season = s.SeasonID "
				+ "INNER JOIN scorecard_game_details g ON b.game_id = g.game_id "
				+ "INNER JOIN clubs c ON g.ground_Id = c.groundId "
				+ "INNER JOIN teams t ON b.team = t.teamID "
				+ "WHERE "
				+ "b.player_id = IFNULL( ?, b.player_id ) "
				+ "AND c.clubId = IFNULL( ?, c.clubId ) "
				+ "AND s.SeasonID = IFNULL( ?, s.SeasonID )"
				+ "AND s.SeasonYear = IFNULL( ?, s.SeasonYear ) "
				+ "AND t.teamID = IFNULL( ?, t.teamID ) ";
		// @formatter:on

		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql, inputs.getPlayerId(), inputs.getClubdId(),
				inputs.getSeasonId(), inputs.getSeasonYear(), inputs.getTeamId());

		return records;
	}

}
