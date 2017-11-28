package com.cricket.austin.zulfi.live.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.MatchPlayer;
import com.cricket.austin.zulfi.live.model.PreMatchInfoByUmpire;
import com.cricket.austin.zulfi.live.model.Team;
import com.cricket.austin.zulfi.live.model.Wicket;

@Repository
public class InsertLiveScoreDaoImpl implements InsertLiveScoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	static final Logger logger = LoggerFactory.getLogger(LiveScoreDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int insertMatchData(Match match) {
		String sql = "INSERT INTO `world`.`match_livescore` (`live_game_id`, `score`, `balls`, `overs`, `wickets`, `fours`, `sixes`, `wides`, `noballs`, `byes`, `legbyes`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] mat = new Object[] { match.getLive_game_id(), match.getScore(), match.getBalls(), match.getOvers(),
				match.getWickets(), match.getFours(), match.getSixes(), match.getWides(), match.getNoballs(),
				match.getByes(), match.getLegbyes() };
		int rows = jdbcTemplate.update(sql, mat);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int insertBatsmanData(Batsman batsman) {
		String sql = "INSERT INTO `world`.`batsman_livescore` (`live_game_id`, `name`, `player_id`, `position`, `balls`, `overs`, `score`, `fours`, `sixes`) "
				+ "VALUES (?, ?, ?,  ?, ?, ?, ?, ?, ?)";
		Object[] bats = new Object[] { batsman.getLive_game_id(), batsman.getName(), batsman.getPlayer_id(),
				batsman.getPosition(), batsman.getBalls(), batsman.getOvers(), batsman.getScore(), batsman.getFours(),
				batsman.getSixes() };
		int rows = jdbcTemplate.update(sql, bats);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int insertBowlerData(Bowler bowler) {
		String sql = "INSERT INTO `world`.`bowler_livescore` (`live_game_id`, `name`, `player_id`, `position`, `balls`, `overs`, `maiden`, `score`, `wickets`, "
				+ "`fours`, `economy`, `sixes`, `wides`, `noballs`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] bowl = new Object[] { bowler.getLive_game_id(), bowler.getName(), bowler.getPlayer_id(),
				bowler.getPosition(), bowler.getBalls(), bowler.getOvers(), bowler.getMaiden(), bowler.getScore(),
				bowler.getWickets(), bowler.getFours(), bowler.getEconomy(), bowler.getSixes(), bowler.getWides(),
				bowler.getNoballs() };
		int rows = jdbcTemplate.update(sql, bowl);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int insertWicketData(Wicket wicket) {
		String sql = "INSERT INTO `world`.`wicket_livescore` (`live_game_id`, `wicket_number`, `fow_score`, `batsman_name`, `bowler_name`, `how_out`, `fielder_name`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] wik = new Object[] { wicket.getLive_game_id(), wicket.getWicket_number(), wicket.getFow_score(),
				wicket.getBatsman_name(), wicket.getBowler_name(), wicket.getHow_out(), wicket.getFielder_name() };
		int rows = jdbcTemplate.update(sql, wik);
		logger.info("rows are ::" + rows);
		return rows;

	}

	@Override
	public int insertUmpirePreMatch(PreMatchInfoByUmpire info) {
		String sql = "INSERT INTO `world`.`umpire_pre_match_livescore` "
				+ "(`live_game_id`, `league_id`, `ground_id`, `home_teamId`, `home_teamAbbrev`,`guest_teamId`, `guest_teamAbbrev`,`toss_won_teamId`,`toss_won_teamAbbrev`, "
				+ " `batting_first_teamId`,`batting_first_teamAbbrev`, `batting_second_teamId`,`batting_second_teamAbbrev`,`first_umpire_playerId`, "
				+ "`second_umpire_playerId`, `maxovers`, `match_date`, `match_week`, `comments`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Object[] match = new Object[] { info.getLive_game_id(), info.getLeague(), info.getGround(),
				info.getHome_team().getValue(), info.getHome_team().getLabel(), info.getGuest_team().getValue(),
				info.getGuest_team().getLabel(), info.getToss_won_team().getValue(), info.getToss_won_team().getLabel(),
				info.getBatting_frst_team().getValue(), info.getBatting_frst_team().getLabel(),
				info.getBatting_second_team().getValue(), info.getBatting_second_team().getLabel(),
				info.getUmpire_team_1().getValue(), info.getUmpire_team_2().getValue(), info.getMaxovers(),
				info.getMatch_date(), info.getMatch_week(), info.getComments() };
		int rows = jdbcTemplate.update(sql, match);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int insertPlayingXI(Team team, ArrayList<MatchPlayer> players, String type) {
		String sql = "INSERT INTO `world`.`playingXI_livescore` (`match_teamId`, `Match_teamAbbrev`, `player_id`, `player_abbrev`, "
				+ "`player_teamId`, `player_teamAbbrev`, `player_type` , `date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		// Object[] xi = new Object[] {};
		// int rows = jdbcTemplate.update(sql, xi);
		int[] rows = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				MatchPlayer player = ((List<MatchPlayer>) players).get(i);
				ps.setInt(1, team.getValue());
				ps.setString(2, team.getLabel());
				ps.setInt(3, player.getId());
				ps.setString(4, player.getItemName());
				ps.setInt(5, player.getTeamId());
				ps.setString(6, player.getItemName());
				ps.setString(7, type);
				ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
			}

			@Override
			public int getBatchSize() {
				return players.size();
			}
		});
		logger.info("rows are ::" + rows);
		return 1;
	}
}
