package com.cricket.austin.zulfi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.MatchDetails;
import com.cricket.austin.zulfi.model.ScorecardBatting;
import com.cricket.austin.zulfi.model.ScorecardBattingDetails;
import com.cricket.austin.zulfi.model.ScorecardBowling;
import com.cricket.austin.zulfi.model.ScorecardBowlingDetails;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

@Repository
public class MatchScoringDaoImpl implements MatchScoringDao {

	@Autowired
	private TeamDaoImpl teamDaoImpl;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static final Logger logger = LoggerFactory.getLogger(MatchScoringDaoImpl.class);

	@Override
	public void submitResults(SubmitResults scoreDetails) {
		String sql = "UPDATE RESULTS set played=played+?, won=won+?, lost=lost+?, tied=tied+?, nr=nr+? where team_id = ?";
		int rows = jdbcTemplate.update(sql, scoreDetails.getPlayed(), scoreDetails.getWon(), scoreDetails.getLost(),
				scoreDetails.getTied(), scoreDetails.getNr(), scoreDetails.getTeamID());
		logger.info("rows are ::" + rows);

	}

	@Override
	public void submitScore_gameDetails(ScorecardGameDetails gameDetails) {
		String sql = "INSERT INTO scorecard_game_details "
				+ "(league_id,season,week,awayteam,hometeam,game_date,result_won_id,forfeit,mom,umpire1,umpire2,maxovers,isactive) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";

		Object param = new Object[] { gameDetails.getLeagueId(), gameDetails.getSeason(), gameDetails.getWeek(),
				gameDetails.getAwayteam(), gameDetails.getHometeam(), gameDetails.getGameDate(),
				gameDetails.getResultWonId(), gameDetails.getForfeit(), gameDetails.getMom(), gameDetails.getUmpire1(),
				gameDetails.getUmpire2(), gameDetails.getMaxovers() };

		int rows = jdbcTemplate.update(sql, param);
		logger.info("rows are ::" + rows);
	}

	@Override
	public int updateScorecardGameDetails(ScorecardGameDetails details) {
		String sql = "INSERT INTO scorecard_game_details "
				+ "(league_id,season,week,awayteam,hometeam,game_date,result_won_id,forfeit,mom,umpire1,umpire2,maxovers,isactive) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";

		int rows = jdbcTemplate.update(sql, details.getLeagueId(), details.getSeason(), details.getWeek(),
				details.getAwayteam(), details.getHometeam(), details.getGameDate(), details.getResultWonId(),
				details.getForfeit(), details.getMom(), details.getUmpire1(), details.getUmpire2(),
				details.getMaxovers());
		return rows;
	}

	@Override
	public int updateInsertScorecardBattingDetails(ScorecardBatting details) {

		int rows = 0;
		MatchDetails match = details.getMatchInfo();
		if (match.getGame_id() < 1) {
			logger.warn("Ooopps GameId is not provided in updateInsertScorecardBattingDetails gameId = "
					+ match.getGame_id());
			return rows;
		}

		List row = teamDaoImpl.findScorecardBattingDetailsByGameId(2065, 1);
		if (row.isEmpty() || row.size() < 1) {
			logger.info("Data don't exist, Try to insert data");
			try {
				rows = insertScorecardBattingDetails(details);
			} catch (Exception ex) {
				logger.info("Insert failed, Try update data");
				rows = updatBattingDetails(details);
			}

		} else {
			logger.info("Data exist, Try update batting details");
			rows = updatBattingDetails(details);
			return rows;
		}
		return rows;
	}

	public int updatBattingDetails(ScorecardBatting details) {
		int rows = 0;
		MatchDetails match = details.getMatchInfo();
		for (ScorecardBattingDetails batsman : details.getBatsmanDetails()) {
			if (batsman.getPlayer_id() > 0) {
				rows = updateScorecardBattingDetails(match, batsman);
			} else {
				logger.warn("updatBattingDetails ::> No Batsman's playerId exist => " + batsman.getPlayer_id());
			}
		}
		return rows;
	};

	public int updateScorecardBattingDetails(MatchDetails match, ScorecardBattingDetails batsman) {
		String sql = "UPDATE scorecard_batting_details "
				+ " SET player_id=?,  how_out=?, runs=?, assist=?, bowler=?, balls=?, fours=?, sixes=?, notout=?"
				+ " WHERE game_id=? AND season= ? AND innings_id=? AND team = ? AND opponent=?  AND batting_position=?";

		int rows = jdbcTemplate.update(sql, batsman.getPlayer_id(), batsman.getHow_out(), batsman.getRuns(),
				batsman.getAssist(), batsman.getBowler(), batsman.getBalls(), batsman.getFours(), batsman.getSixes(),
				batsman.getNotout(), match.getGame_id(), match.getSeason(), match.getInnings_id(), match.getTeam(),
				match.getOpponent(), batsman.getBatting_position());
		logger.info("rows are ::" + rows);

		return 0;
	}

	public int insertScorecardBattingDetails(ScorecardBatting details) {

		String sql = "INSERT INTO  scorecard_batting_details (player_id,batting_position,how_out,runs,assist,bowler,balls,fours, "
				+ "sixes,notout, game_id,season,innings_id,team,opponent) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		List<Object[]> parameters = new ArrayList<Object[]>();

		MatchDetails match = details.getMatchInfo();

		for (ScorecardBattingDetails batsman : details.getBatsmanDetails()) {
			parameters.add(new Object[] { batsman.getPlayer_id(), batsman.getBatting_position(), batsman.getHow_out(),
					batsman.getRuns(), batsman.getAssist(), batsman.getBowler(), batsman.getBalls(), batsman.getFours(),
					batsman.getSixes(), batsman.getNotout(), match.getGame_id(), match.getSeason(),
					match.getInnings_id(), match.getTeam(), match.getOpponent() });
		}
		int rows = 0;
		try {
			int[] rowsv = jdbcTemplate.batchUpdate(sql, parameters);
			logger.info("rows are ::" + rowsv);
		} catch (Exception ex) {
			logger.warn("Update Batch exception => " + ex);
		}

		logger.info("rows are ::" + rows);

		return 0;
	}

	@Override
	public int updateInsertScorecardBowlingDetails(ScorecardBowling match, ScorecardBowlingDetails bowler) {
		int rows = 0;
		if (match.getGame_id() > 0) {
			try {
				rows = updateTotalsDetails(match, bowler);
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertTotalsDetails(match, bowler);
			}

		} else {
			logger.warn("Ooopps GameId is not provided");
			return rows;
		}
		return rows;
	}

	private int updateTotalsDetails(ScorecardBowling match, ScorecardBowlingDetails bowler) {
		// TODO: Need to figure solution how to iterate through bowlers
		logger.warn("Nothing Implemented here !!! ooops");

		return 0;
	}

	private int insertTotalsDetails(ScorecardBowling match, ScorecardBowlingDetails bowler) {
		String sql = "INSERT INTO  scorecard_bowling_details  "
				+ "(game_id,season,innings_id,player_id,bowling_position,overs,maidens,runs,wickets,noballs,wides,team,opponent) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardTotalDetails(ScorecardTotalDetails totalDetails) {

		int rows = 0;
		if (totalDetails.getGame_id() > 0) {
			try {
				rows = updateTotalsDetails(totalDetails);
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertTotalsDetails(totalDetails);
			}

		} else {
			logger.warn("Opps GameId is not provided");
			return rows;
		}
		return rows;

	}

	private int updateTotalsDetails(ScorecardTotalDetails totalDetails) {

		String sql = "UPDATE scorecard_total_details SET team=? ,wickets=? ,total=? ,overs=?  WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, totalDetails.getTeam(), totalDetails.getWickets(), totalDetails.getTotal(),
				totalDetails.getOvers(), totalDetails.getGame_id(), totalDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;
	}

	private int insertTotalsDetails(ScorecardTotalDetails totalDetails) {

		String sql = "INSERT INTO scorecard_total_details " + "(game_id,innings_id,team,wickets,total,overs) "
				+ "VALUES (?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, totalDetails.getGame_id(), totalDetails.getInnings_id(),
				totalDetails.getTeam(), totalDetails.getWickets(), totalDetails.getTotal(), totalDetails.getOvers());
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardFowDetails1(ScorecardFowDetails fowDetails) {
		int rows = 0;
		if (fowDetails.getGame_id() > 0) {
			try {
				rows = updateFowDetails(fowDetails);
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertFowDetails(fowDetails);
			}

		} else {
			logger.warn("Opps GameId is not provided");
			return rows;
		}
		return rows;

	}

	public int updateFowDetails(ScorecardFowDetails fowDetails) {
		String sql = "UPDATE SCORECARD_FOW_DETAILS SET fow1 = ?,fow2= ?,fow3= ?,fow4= ?,fow5= ?,fow6= ?,fow7= ?,fow8= ?,fow9= ?,fow10= ? WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, fowDetails.getFow1(), fowDetails.getFow2(), fowDetails.getFow3(),
				fowDetails.getFow4(), fowDetails.getFow5(), fowDetails.getFow6(), fowDetails.getFow7(),
				fowDetails.getFow8(), fowDetails.getFow9(), fowDetails.getFow10(), fowDetails.getGame_id(),
				fowDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;

	};

	public int insertFowDetails(ScorecardFowDetails fowDetails) {
		String sql = "INSERT INTO SCORECARD_FOW_DETAILS "
				+ "(game_id,innings_id,fow1,fow2,fow3,fow4,fow5,fow6,fow7,fow8,fow9,fow10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, fowDetails.getGame_id(), fowDetails.getInnings_id(), fowDetails.getFow1(),
				fowDetails.getFow2(), fowDetails.getFow3(), fowDetails.getFow4(), fowDetails.getFow5(),
				fowDetails.getFow6(), fowDetails.getFow7(), fowDetails.getFow8(), fowDetails.getFow9(),
				fowDetails.getFow10());
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardExtrasDetails(SorecardExtrasDetails extrasDetails) {
		int rows = 0;

		if (extrasDetails.getGame_id() > 0
				&& (extrasDetails.getInnings_id() == 1 || extrasDetails.getInnings_id() == 2)) {
			try {
				rows = updateExtrasDetails(extrasDetails);

				// Making sure to Insert record if no row is updated
				if (rows == 0) {
					logger.warn("NO row is updated, Try to insert data");
					rows = insertExtrasDetails(extrasDetails);
				}
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertExtrasDetails(extrasDetails);
			}

		} else {
			logger.error("Opps Game Id or Innings Id is not Correct");
			return rows;
		}
		return rows;

	}

	private int insertExtrasDetails(SorecardExtrasDetails extrasDetails) {
		String sql = "INSERT INTO scorecard_extras_details " + "(game_id,innings_id,legbyes,byes,wides,noballs,total) "
				+ "VALUES (?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, extrasDetails.getGame_id(), extrasDetails.getInnings_id(),
				extrasDetails.getLegbyes(), extrasDetails.getByes(), extrasDetails.getWides(),
				extrasDetails.getNoballs(), extrasDetails.getTotal());
		logger.info("R # of rows inserted are ::" + rows);
		if (rows < 1) {
			logger.error("Failed to Insert data");
		}
		return rows;
	}

	private int updateExtrasDetails(SorecardExtrasDetails extrasDetails) {
		String sql = "UPDATE scorecard_extras_details SET legbyes=? ,byes=? ,wides=? ,noballs=?, total=?  WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, extrasDetails.getLegbyes(), extrasDetails.getByes(),
				extrasDetails.getWides(), extrasDetails.getNoballs(), extrasDetails.getTotal(),
				extrasDetails.getGame_id(), extrasDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;
	}

}
