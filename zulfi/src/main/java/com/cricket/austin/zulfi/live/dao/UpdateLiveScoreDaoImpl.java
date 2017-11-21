/** @author zulifqar.ahmad 11-20-2017 **/
package com.cricket.austin.zulfi.live.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.Wicket;

@Repository
public class UpdateLiveScoreDaoImpl implements UpdateLiveScoreDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	static final Logger logger = LoggerFactory.getLogger(LiveScoreDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int updateMatchData(Match match) {

		String sql = "UPDATE `world`.`match_livescore` "
				+ "SET `balls`= IFNULL(?, balls ), `overs`=IFNULL(?, overs), `score`=IFNULL(?, score) , `wickets`=IFNULL(?,wickets) , `fours` =IFNULL(? ,fours) , `sixes` =IFNULL(? ,sixes), "
				+ "`wides` =IFNULL(? ,wides), `noballs` =IFNULL(? ,noballs), `byes` =IFNULL(? ,byes), `legbyes` =IFNULL(? ,legbyes) "
				+ "WHERE live_game_id =?";
		Object[] mat = new Object[] { match.getBalls(), match.getOvers(), match.getScore(), match.getWickets(),
				match.getFours(), match.getSixes(), match.getWides(), match.getNoballs(), match.getByes(),
				match.getLegbyes(), match.getLive_game_id() };
		int rows = 0;
		try {
			rows = jdbcTemplate.update(sql, mat);
		} catch (Exception ex) {
			logger.error("Error in updateMatchData. GameLiveId #" + match.getLive_game_id());
		}
		logger.info("Update Match data rows #:" + rows);
		return rows;

	}

	@Override
	public int updateBatsmanData(Batsman bats) {
		String sql = "UPDATE `world`.`batsman_livescore` "
				+ "SET `live_game_id`=IFNULL(?, live_game_id ), name`= IFNULL(?, name ), `player_id`=IFNULL(?, player_id), `balls`= IFNULL(?, balls ), `overs`=IFNULL(?, overs), "
				+ "`score`=IFNULL(?, score), " + "`fours`=IFNULL(?, fours),`sixes`=IFNULL(?, sixes) " + "WHERE `id`= ?";
		Object[] batsman = new Object[] { bats.getLive_game_id(), bats.getName(), bats.getPlayer_id(), bats.getBalls(),
				bats.getOvers(), bats.getScore(), bats.getFours(), bats.getSixes(), bats.getId() };
		int rows = jdbcTemplate.update(sql, batsman);
		logger.info("Update Batsman data rows #" + rows);
		return rows;
	}

	@Override
	public int updateBowlerData(Bowler bow) {
		String sql = "UPDATE `world`.`bowler_livescore` "
				+ "SET `live_game_id`=IFNULL(?, live_game_id ), name`= IFNULL(?, name ), `player_id`=IFNULL(?, player_id), `balls`=IFNULL(?, balls ), `overs`=IFNULL(?, overs), "
				+ "`maiden`=IFNULL(?, maiden), `score`=IFNULL(?, score), `wickets`=IFNULL(?, wickets),` fours`=IFNULL(?, fours), `sixes`=IFNULL(?, sixes), "
				+ "`economy`=IFNULL(?, economy), " + "`wides`=IFNULL(?, wides), `noballs`=IFNULL(?, noballs) "
				+ "WHERE `id`= ? ";
		Object[] bowler = new Object[] { bow.getLive_game_id(), bow.getName(), bow.getPlayer_id(), bow.getBalls(),
				bow.getOvers(), bow.getMaiden(), bow.getScore(), bow.getWickets(), bow.getFours(), bow.getSixes(),
				bow.getEconomy(), bow.getWides(), bow.getNoballs(), bow.getId() };
		int rows = jdbcTemplate.update(sql, bowler);
		logger.info("Update Bowler data rows #" + rows);
		return rows;
	}

	@Override
	public int updateWicketsData(Wicket wick) {
		String sql = "UPDATE `world`.`wicket_livescore` "
				+ "SET `live_game_id`=IFNULL(?, live_game_id ), `wicket_number`=IFNULL(?, wicket_number ), `fow_score`=IFNULL(?, fow_score ), `batsman_name`=IFNULL(?, batsman_name ), "
				+ "`bowler_name`=IFNULL(?, bowler_name ), `how_out`=IFNULL(?, how_out ), `fielder_name`=IFNULL(?, fielder_name ) "
				+ "WHERE `id`= ?";
		Object[] wicket = new Object[] { wick.getLive_game_id(), wick.getWicket_number(), wick.getFow_score(),
				wick.getBatsman_name(), wick.getBowler_name(), wick.getHow_out(), wick.getFielder_name() };
		int rows = jdbcTemplate.update(sql, wicket);
		logger.info("Update Wicket data rows #" + rows);
		return rows;
	}
}
