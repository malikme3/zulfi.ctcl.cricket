package com.cricket.austin.zulfi.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.ClubsPage;
import com.cricket.austin.zulfi.model.News;
import com.cricket.austin.zulfi.model.Roles;

@Repository
public class ClubsDaoImpl implements ClubsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	static final Logger logger = LoggerFactory.getLogger(BattingRecordsDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Map<String, Object>> clubsList() {
		String sql = "SELECT * FROM clubs WHERE ClubActive=1 AND LeagueID = 1 ORDER BY ClubName";
		List<Map<String, Object>> clubs = jdbcTemplate.queryForList(sql);
		return clubs;
	}

	@Override
	public List<ClubsPage> clubsDetails() {
		// @formatter:off
		String sql = "SELECT DISTINCT 'ctcl' as league_name, "+
				"cl.ClubID as club_id, cl.ClubName as club_name, cl.ClubURL as club_url, cl.ClubColour as club_color, "+
				"te.TeamID as team_id, te.TeamName as team_name, te.TeamAbbrev as team_abbrev, te.TeamColour as team_color, "+
				"gr.GroundName as ground_name, gr.GroundID as ground_id "+
				"FROM (clubs cl \r\n" + 
				"LEFT JOIN players pl ON cl.ClubID = pl.PlayerClub) \r\n" + 
				"LEFT JOIN teams te ON pl.PlayerTeam = te.TeamID \r\n" + 
				"LEFT JOIN grounds gr ON cl.GroundID = gr.GroundID " + 
				"WHERE te.teamActive = 1 "+
				"AND  cl.clubActive=1 " +
				"AND cl.LeagueID = 1 "+
				"ORDER BY cl.ClubName";
		// @formatter:on	

		List<ClubsPage> clubs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ClubsPage>(ClubsPage.class));
		List<Roles> players = playersRole();
		for (ClubsPage club : clubs) {
			for (Roles roles : players) {
				if (club.getTeam_id() == roles.getPlayer_team()) {
					if (roles.getCaptain() == 1) {
						club.setCaptain_name(roles.getPlayer_name());
						logger.info("Capatins are " + roles.getPlayer_name());
					} else if (roles.getVice_captain() == 1) {
						club.setVice_captain_name(roles.getPlayer_name());
						logger.info("Vice Capatins are " + roles.getPlayer_name());
					} else if (roles.getPresident() == 1) {
						club.setPresident_name(roles.getPlayer_name());
						logger.info("Presidents are " + roles.getPlayer_name());
					} else if (roles.getVice_president() == 1) {
						club.setVice_president_name(roles.getPlayer_name());
						logger.info("Vice Presidents are " + roles.getPlayer_name());
					} else if (roles.getSecretary() == 1) {
						club.setSecretary_name(roles.getPlayer_name());
						logger.info("Secretaries are " + roles.getPlayer_name());
					}
				}
			}

		}
		;
		return clubs;
	}

	@Override
	public List<Roles> playersRole() {
		// @formatter:off
		String sql = "SELECT  \r\n" + 
				"PlayerID as player_id, \r\n" + 
				"CONCAT(PlayerFName, ' ', PlayerLName) AS player_name,\r\n" + 
				"PlayerClub as player_club, \r\n" + 
				"PlayerTeam as player_team, \r\n" + 
				"IsCaptain as captain, \r\n" + 
				"IsViceCaptain as vice_captain, \r\n" + 
				"IsPresident as president,\r\n" + 
				"IsVicePresident as vice_president, \r\n" + 
				"IsSecretary as secretary, \r\n" + 
				"IsTreasurer as treasurer\r\n" + 
				"FROM players \r\n" + 
				"WHERE \r\n" + 
				"IsCaptain = 1 \r\n" + 
				"OR IsViceCaptain = 1 \r\n" + 
				"OR IsPresident = 1 \r\n" + 
				"OR IsVicePresident = 1 \r\n" + 
				"OR IsSecretary = 1 \r\n" + 
				"OR IsTreasurer = 1";
		// @formatter:on
		List<Roles> roles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Roles>(Roles.class));
		return roles;

	};

	@Override
	public List<Map<String, Object>> clubsInfo() {
		// @formatter:off
		String sql = "SELECT \r\n" + 
				"cl.ClubID as club_id, cl.ClubName as club_name, cl.ClubURL as club_url,\r\n" + 
				"cl.PresidentName as president_name, cl.VicePresidentName as vice_president_name,\r\n" + 
				"cl.SecretaryName as secretary_name, cl.TreasurerName as treasurer_name,\r\n" + 
				" cl.ClubColour as club_color, cl.GroundID as ground_id, gr.GroundName as ground_name\r\n" + 
				" FROM clubs cl\r\n" + 
				" LEFT JOIN grounds gr ON cl.GroundID = gr.GroundID \r\n" + 
				" WHERE cl.ClubActive = 1\r\n" + 
				" AND cl.LeagueId = 1";
		// @formatter:on
		List<Map<String, Object>> info = jdbcTemplate.queryForList(sql);
		return info;

	}

	@Override
	public List<News> getNews() {

		// @formatter:off
		String sql = "SELECT *, "
				+ "CONCAT('Author: ',author, '     Date Added: ', added) AS writer_info ,"
				+ "DATE_FORMAT(added, \"%Y\") as news_year "
				+ "FROM news "
				+ "WHERE IsPending = 0 "
				+ "AND added > '2007-01-01' "
				+ "ORDER BY added DESC, id DESC "
				+ "LIMIT 100";
            //@formatter:on

		List<News> scoreCard = jdbcTemplate.query(sql, new BeanPropertyRowMapper<News>(News.class));
		return scoreCard;

	}

}
