package com.cricket.austin.zulfi.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.MatchDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.SubmitResults;

@Repository
public class TeamDaoImpl implements TeamDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static final Logger logger = LoggerFactory.getLogger(TeamDaoImpl.class);

	@Override
	public List<Ladder> getTeamPosition(String conferenceAbbrev, String seasonName) {

		String sql = "SELECT t.teamAbbrev , la.*, co.* FROM ladder la "
				+ "INNER JOIN conferencemanagement co ON la.conference = co.ConferenceID "
				+ "INNER JOIN teams t on la.team = t.teamId "
				+ "WHERE la.season in (select SeasonId from seasons s where s.seasonName = ? ) "
				+ "and  co.conferenceAbbrev = ? ORDER BY la.totalpoints DESC";

		List<Ladder> teamsPoints = jdbcTemplate.query(sql, new String[] { seasonName, conferenceAbbrev },
				new BeanPropertyRowMapper<Ladder>(Ladder.class));

		return teamsPoints;
	}

	@Override
	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName) {
		List<Ladder> teamsNames = new ArrayList<Ladder>();

		String sql = "SELECT t.teamId, t.teamAbbrev from teams t where t.teamID in (SELECT l.team FROM Ladder l"
				+ " where  season in (Select seasonID from seasons where seasonYear = ? and seasonName = ? )) "
				+ "ORDER BY t.teamId ";

		List<Map<String, Object>> teamNames = jdbcTemplate.queryForList(sql, new Object[] { seasonYear, seasonName });

		for (Map row : teamNames) {

			Ladder teamPoints = new Ladder();
			teamPoints.setTeam((int) row.get("teamId"));
			teamPoints.setTeamName((String) row.get("TeamAbbrev"));
			teamsNames.add(teamPoints);
		}

		return teamsNames;

	};

	@Override
	public List<Map<String, Object>> getbasicScoreCard(int seasonId) {

		// @formatter:off
		String sql = " SELECT  s.game_id, se.seasonName as season, s.game_date, "
				+ "concat(p.playerFName, ' ', p.playerLName) as man_of_the_match, "
				+ "s.result,a.TeamAbbrev AS guest_team, h.TeamAbbrev AS host_team "
				+ "FROM  scorecard_game_details s "
				+ "INNER JOIN  teams a ON s.awayteam = a.TeamID "
				+ "INNER JOIN  teams h ON s.hometeam = h.TeamID "
				+ "INNER JOIN players p on s.mom = p.playerID "
				+ "INNER JOIN seasons se on s.season = se.seasonId "
				+ "WHERE s.season= ? "
				+ "AND s.isactive= 0 "
				+ "ORDER BY  s.week, s.game_date, s.game_id";
		// @formatter:on

		List<Map<String, Object>> teamNames = jdbcTemplate.queryForList(sql, new Object[] { seasonId });

		return teamNames;
	}

	@Override
	public List<Map<String, Object>> getDetailedScore(int gameId) {

		String sql = "SELECT bf.TeamAbbrev AS BatFirst, bs.TeamAbbrev AS BatSecond, sc.result, tea.teamAbbrev AS WonToss,g.GroundName, "
				+ "CONCAT(pl.PlayerFName ,' ' ,pl.PlayerLName) as PlayerFullName, t.teamAbbrev as batting_team, te.teamAbbrev as bowling_team, "
				+ "s.game_id, s.innings_id, s.batting_position, s.runs, s.balls, s.fours, s.sixes, p.PlayerID AS BatterID, "
				+ "CONCAT(p.PlayerFName,' ',p.PlayerLName) AS BatterFullName, LEFT(p.PlayerFName,1) AS BatterFInitial, h.HowOutID, h.HowOutName,"
				+ " h.HowOutAbbrev, CONCAT(a.PlayerFName,' ',a.PlayerLName) AS AssistFullName, LEFT(a.PlayerFName,1) AS AssistFInitial, "
				+ "CONCAT(b.PlayerLName , ' ',b.PlayerFName) AS BowlerFullName, " + "sco.wickets,sco.total,sco.overs, "
				+ "LEFT(b.PlayerFName,1) AS BowlerFInitial " + "FROM scorecard_batting_details s "
				+ "INNER JOIN teams t on s.team = t.teamid "
				+ "INNER JOIN scorecard_game_details sc on sc.game_id = s.game_id "
				+ "INNER JOIN teams tea on sc.toss_won_id = tea.teamid "
				+ "INNER JOIN teams te on s.opponent = te.teamid "
				+ "INNER JOIN teams bf ON sc.batting_first_id = bf.TeamID "
				+ "INNER JOIN teams bs ON sc.batting_second_id = bs.TeamID "
				+ "INNER JOIN grounds g ON sc.ground_id = g.GroundID "
				+ "INNER JOIN scorecard_total_details sco on sc.game_id = sco.game_id "
				+ "LEFT JOIN players pl ON sc.mom = pl.PlayerID " + "LEFT JOIN players a ON a.PlayerID = s.assist "
				+ "LEFT JOIN players p ON p.PlayerID = s.player_id " + "LEFT JOIN players b ON b.PlayerID = s.bowler "
				+ "INNER JOIN howout h ON h.HowOutID = s.how_out WHERE s.game_id = ? AND s.how_out <> 1 "
				+ "ORDER BY s.batting_position ;";

		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getTeamsName() {

		String sql = "select teamabbrev as label, teamid as value from teams where TeamActive = '1'";

		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql);
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getBowlingDetails(int gameId) {

		String sql = "SELECT t.teamAbbrev as batting_team, te.teamAbbrev as bowling_team,s.game_id, s.innings_id, s.bowling_position,"
				+ " s.overs, s.maidens, s.runs, s.wickets, s.noballs, s.wides, p.PlayerID AS BowlerID, p.PlayerLName AS BowlerLName, "
				+ "p.PlayerFName AS BowlerFName, LEFT(p.PlayerFName,1) AS BowlerFInitial "
				+ "FROM scorecard_bowling_details s " + "INNER JOIN teams t on s.team = t.teamid "
				+ "INNER JOIN teams te on s.opponent = te.teamid " + "LEFT JOIN players p ON p.PlayerID = s.player_id "
				+ "WHERE s.game_id = ? " + "ORDER BY s.bowling_position and innings_id;";

		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getExtraScoreDetails(int gameId) {
		String sql = "SELECT s.game_id, s.innings_id, s.batting_position, "
				+ "CONCAT(p.PlayerLName , ' ',p.PlayerFName) AS batsmanFullName, "
				+ "LEFT(p.PlayerFName,1) AS batsmanFInitial, ex.legbyes, ex.byes, ex.wides, ex.noballs, ex.total as extraTotal, tot.wickets as totalWickets, tot.total as totalRuns, tot.overs as totalOvers "
				+ "FROM scorecard_batting_details s "
				+ "LEFT JOIN scorecard_total_details tot ON tot.game_id = s.game_id and tot.innings_id = s.innings_id "
				+ "LEFT JOIN scorecard_extras_details ex ON ex.game_id = s.game_id and ex.innings_id = s.innings_id "
				+ "LEFT JOIN players a ON a.PlayerID = s.assist LEFT JOIN players p ON p.PlayerID = s.player_id "
				+ "LEFT JOIN players b ON b.PlayerID = s.bowler INNER JOIN howout h ON h.HowOutID = s.how_out "
				+ "WHERE s.game_id = ? " + "AND s.how_out = 1 " + "ORDER BY s.innings_id , " + "s.batting_position ;";

		List<Map<String, Object>> extras = jdbcTemplate.queryForList(sql, new Object[] { gameId });

		/*
		 * updatFname(); try{ updatLname(); } catch (Exception e){ throw e; }
		 */
		return extras;

	};

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		List<Seasons> groups = new ArrayList<Seasons>();
		String sql = "SELECT DISTINCT co.conferenceAbbrev, s.seasonName " + "from  conferencemanagement co "
				+ "INNER JOIN ladder la ON la.conference = co.ConferenceID "
				+ "INNER JOIN seasons s on s.seasonId = la.season where  s.seasonYear = ? ";

		List<Map<String, Object>> sGroups = jdbcTemplate.queryForList(sql, new Object[] { year });

		for (Map row : sGroups) {
			Seasons seasonGroups = new Seasons();
			seasonGroups.setSeasonName((String) row.get("seasonName"));
			seasonGroups.setGroupCategory((String) row.get("conferenceAbbrev"));

			groups.add(seasonGroups);

		}

		return groups;
	}

	@Override
	public List<Map<String, Object>> getSchedule(String seasonId) {
		if (seasonId.equalsIgnoreCase("null")) {
			seasonId = null;
		}

		/** Schedule for last 6 month and future **/
		int days = 180;
		String sql = "SELECT @row := @row + 1 as row_number, s.seasonName, concat(th.teamAbbrev, ' VS ', t.teamabbrev) as teams , "
				+ "t.teamId as away_team_id, th.teamId as home_team_id, t.teamAbbrev as away_team_name, th.teamAbbrev as home_team_name, "
				+ "concat(p.playerFname, ' ', p.playerLName) as umpireName,sch.date,DATE_FORMAT(sch.date, '%b %e') as "
				+ "formatted_date,s.seasonId, sch.week, grn.GroundName as ground , grn.GroundAbbrev as groundName "
				+ "FROM schedule sch " + "INNER JOIN players p on sch.umpire1 =  p.playerID "
				+ "INNER JOIN teams t on sch.awayteam = t.teamId " + "INNER JOIN teams th on sch.hometeam = th.teamId "
				+ "INNER JOIN seasons s on sch.season = s.seasonId , grounds grn " + "JOIN (SELECT @row :=0) r "
				+ "WHERE  sch.venue = grn.GroundID AND  sch.date >= NOW() - INTERVAL ? DAY and s.seasonId = IFNULL(?, s.seasonId ) ORDER BY sch.date DESC, sch.id ";

		List<Map<String, Object>> listSchedule = jdbcTemplate.queryForList(sql, new Object[] { days, seasonId });
		return listSchedule;

	}

	@SuppressWarnings("unused")
	@Override
	public void updatFname() {
		String sql = "UPDATE players SET PlayerFName = '1', PlayerLName = '34' WHERE PlayerID = 1";

		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);

	}

	@SuppressWarnings("unused")
	@Override
	public void updatLname() {

		String sql = "UPDATE players SET PlayerLNfame = 'f000-786', PlayerLName = 'l000-786' WHERE PlayerID = 1";
		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);
	}

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
	public List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate) {
		String sql = "SELECT s.* " + "FROM scorecard_game_details s "
				+ "INNER JOIN grounds g ON s.ground_id = g.GroundID " + "INNER JOIN teams a ON s.awayteam = a.TeamID "
				+ "INNER JOIN teams h ON s.hometeam = h.TeamID " + "LEFT JOIN teams u ON s.umpires = u.TeamID "
				+ "LEFT JOIN teams t ON s.toss_won_id = t.TeamID "
				+ "INNER JOIN teams b ON s.batting_first_id = b.TeamID "
				+ "INNER JOIN teams n ON s.batting_second_id = n.TeamID "
				+ "WHERE  s.hometeam = ? AND s.awayteam = ? AND s.game_date = ? ";
		List<Map<String, Object>> match = jdbcTemplate.queryForList(sql, homeTeam, awayTeam, matchDate);
		return match;
	}

	@Override
	public List<Map<String, Object>> findPlayerByTeamId(String teamId) {
		String sql = "SELECT  concat(PlayerFName, ' ', PlayerLName) as label, playerId as value "
				+ "FROM players where playerTeam = IFNULL(?, playerTeam ) and isactive = 0 ORDER BY PlayerFName,PlayerLName";

		List<Map<String, Object>> playersList = jdbcTemplate.queryForList(sql, teamId);
		return playersList;
	}

	@Override
	public List<Map<String, Object>> findPlayer() {

		String sql = "SELECT CONCAT(p.PlayerFName, ' ', p.PlayerLName) as label, p.playerId as value  "
				+ "FROM players p INNER JOIN teams t ON t.TeamID = p.PlayerTeam "
				+ "WHERE p.isactive = 0  ORDER BY p.PlayerFName,p.PlayerLName;";

		List<Map<String, Object>> playersList = jdbcTemplate.queryForList(sql);
		return playersList;
	}

	@Override
	public List<Map<String, Object>> findPlayerByIds(List<Integer> ids) {
		String sql = "SELECT CONCAT(p.PlayerFName, ' ', p.PlayerLName) as label, p.PlayerId as value  "
				+ "FROM PLAYERS p INNER JOIN TEAMS t ON t.TeamID = p.PlayerTeam "
				+ "WHERE p.playerTeam IN (:teamsIds)  and p.isactive = 0 ORDER BY PlayerFName,PlayerLName";

		Map<String, List<Integer>> paramMap = Collections.singletonMap("teamsIds", ids);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<Map<String, Object>> playersList = template.queryForList(sql, paramMap);
		logger.info("PlayersList is :: " + playersList);
		return playersList;
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

	public List findScorecardBattingDetailsByGameId(MatchDetails match) {
		String sql = "SELECT * FROM scorecard_batting_details s where s.game_id = ? AND s.innings_id = ? AND s.team = ? AND s.opponent=?";
		List score = jdbcTemplate.queryForList(sql, match.getGame_id(), match.getInnings_id(), match.getTeam(),
				match.getOpponent());
		System.out.println("score: " + score);
		return score;

	}

	public List findScorecardBowlingDetailsByGameId(MatchDetails match) {
		String sql = "SELECT * FROM scorecard_bowling_details s where s.game_id =? AND s.innings_id =? AND s.team =? AND s.opponent =? ";
		List score = jdbcTemplate.queryForList(sql, match.getGame_id(), match.getInnings_id(), match.getTeam(),
				match.getOpponent());
		System.out.println("score: " + score);
		return score;

	}

	@Override
	public List<Map<String, Object>> findHowOut() {
		String sql = "SELECT howOutName as label , howOutId as value from howout order by howOutId";
		List<Map<String, Object>> howOutList = jdbcTemplate.queryForList(sql);
		return howOutList;

	}

}
