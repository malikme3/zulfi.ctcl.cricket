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
		String sql = "SELECT "
				+ "g.season, n.SeasonName, "
				+ "n.SeasonYear as season_year, "
				+ "n.SeasonID as season_id, "
				+ "COUNT(s.player_id) AS matches, "
				+ "SUM(s.runs) AS runs, "
				+ "SUM(s.notout) AS notOuts, "
				+ "COUNT(s.player_id) - SUM(s.notout) AS innings, "
				+ "ROUND(SUM(s.runs) / (COUNT(s.player_id) - SUM(s.notout)),2) AS average, "
				+ "COUNT("
				+ "CASE WHEN s.runs >= 100 THEN 1 ELSE NULL END) AS hundreds, "
				+ "COUNT(CASE WHEN s.runs BETWEEN 50 AND 99 THEN 1 ELSE NULL END) AS fifties, "
				+ "COUNT(CASE WHEN s.how_out = 4 THEN 1 ELSE NULL END) AS caught, "
				+ "COUNT(CASE WHEN s.how_out = 5 THEN 1 ELSE NULL END) AS cAndB, "
				+ "COUNT(CASE WHEN s.how_out = 10 THEN 1 ELSE NULL END) AS stumped, "
				+ "s.player_id, "
				+ "CONCAT(p.PlayerFName,' ',p.PlayerLName) AS playerFullName "
				+ "FROM "
				+ "scorecard_batting_details s "
				+ "INNER JOIN players p ON s.player_id = p.PlayerID "
				+ "INNER JOIN scorecard_game_details g ON s.game_id = g.game_id "
				+ "INNER JOIN seasons n ON g.season = n.SeasonID "
				+ "INNER JOIN teams t ON s.team = t.teamID "
				+ "INNER JOIN clubs c ON t.clubId = c.clubId "
				+ "WHERE s.how_out != 1 "
				+ "AND s.player_id = IFNULL(null, s.player_id ) "
				+ "AND c.clubId = IFNULL(?, c.clubId ) "
				+ "AND n.SeasonID = IFNULL(?, n.SeasonID ) "
				+ "AND n.SeasonYear = IFNULL(?, n.SeasonYear ) "
				+ "AND t.teamID = IFNULL(?, t.teamID ) "
				+ "GROUP BY s.player_id "
				+ "ORDER BY Runs "
				+ "DESC, p.PlayerLName, p.PlayerFName";
		// @formatter:on

		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql, inputs.getClubId(), inputs.getSeasonId(),
				inputs.getSeasonYear(), inputs.getTeamId());

		return records;
	}

	@Override
	public List<Map<String, Object>> bowlingRecords(RecordsInputs inputs) {
		// @formatter:off
				String sql ="SELECT "
						+ "g.season,COUNT(b.player_id) AS matches, n.SeasonName as season_name, \r\n" +
						"t.TeamID as team_id, t.TeamAbbrev as team_abbrev, b.player_id, SUM(IF(INSTR(overs, '.'),"
						+ "((LEFT(overs, INSTR(overs, '.') - 1) * 6) + RIGHT(overs, INSTR(overs, '.') - 1)),\r\n" +
						"(overs * 6))) AS balls, SUM( b.maidens ) AS maidens, SUM( b.runs ) AS bowling_runs, \r\n" +
						"SUM( b.wickets ) AS wickets, SUM( b.runs ) / SUM( b.wickets ) AS average,\r\n" +
						"CONCAT(p.PlayerFName,' ',p.PlayerLName) AS full_name, \r\n" +
						"p.PlayerLName as last_name, p.PlayerFName as first_name,\r\n" +
						"LEFT(p.PlayerFName,1) AS player_initial \r\n" +
						"FROM scorecard_bowling_details b \r\n" +
						"INNER JOIN players p ON b.player_id = p.PlayerID \r\n" +
						"INNER JOIN scorecard_game_details g ON b.game_id = g.game_id \r\n" +
						"INNER JOIN teams t ON p.PlayerTeam = t.TeamID \r\n" +
						"INNER JOIN seasons n ON g.season = n.SeasonID\r\n" +
						"INNER JOIN clubs c ON t.clubId = c.clubId  \r\n" +
						"WHERE \r\n" +
						"b.player_id = IFNULL(null, b.player_id ) "+
						"AND c.clubId = IFNULL(?, c.clubId ) "+
						"AND n.SeasonID = IFNULL(?, n.SeasonID ) "+
						"AND n.SeasonYear = IFNULL(?, n.SeasonYear ) "+
						"AND t.teamID = IFNULL(?, t.teamID ) "+
						"GROUP BY b.player_id \r\n" +
						"HAVING Balls >=24 \r\n" +
						"ORDER BY WICKETS\r\n" +
						"DESC";
		// @formatter:on

		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql, inputs.getClubId(), inputs.getSeasonId(),
				inputs.getSeasonYear(), inputs.getTeamId());
		return records;
	}

}
