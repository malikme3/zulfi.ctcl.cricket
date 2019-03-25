package com.cricket.austin.zulfi.live.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.live.model.Batsman;
import com.cricket.austin.zulfi.live.model.Bowler;
import com.cricket.austin.zulfi.live.model.Match;
import com.cricket.austin.zulfi.live.model.ScoreForm;
import com.cricket.austin.zulfi.live.model.Wicket;
import com.cricket.austin.zulfi.live.service.LiveScoreServiceImpl;

/** Use for getting && submitting main request from client side **/
@Repository
public class LiveScoreDaoImpl implements LiveScoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	LiveScoreServiceImpl liveScoreServiceImpl;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static final Logger logger = LoggerFactory.getLogger(LiveScoreDaoImpl.class);

	@Override
	public int submitScoreFormData(ScoreForm scoreForm) {
		// int row = insertUpdateMatchData(scoreForm);
		return 0;
	}

	@Override
	public ScoreForm getScoreFrom(String liveGameId) {
		ScoreForm scoreForm = new ScoreForm();
		int pId = 2;
		String gId = "2";

		scoreForm.setMatch(getMatchData(gId));
		scoreForm.setBatsman_1(getBatsmanData(gId, 2));
		scoreForm.setBatsman_2(getBatsmanData(gId, 3));
		scoreForm.setBowler(getBowlerData(gId, pId));
		scoreForm.setWicket(getWicketData(gId, 2));
		return scoreForm;
	}

	@Override
	public Match getMatchData(String liveGameId) {
		String sql = "Select * FROM `world`.`match_livescore` where live_game_id =?";
		Match match = null;
		try {
			match = (Match) jdbcTemplate.queryForObject(sql, new Object[] { liveGameId },
					new BeanPropertyRowMapper<Match>(Match.class));

		} catch (Exception e) {
			match = null;
			logger.warn("MatchData is not available for liveGameId#: " + liveGameId);
		}
		return match;
	}

	@Override
	public Batsman getBatsmanData(String liveGameId, int playerId) {
		String sql = "Select * FROM `world`.`batsman_livescore` where live_game_id =? and player_id  = ? ";
		Batsman batsman = null;
		try {
			batsman = (Batsman) jdbcTemplate.queryForObject(sql, new Object[] { liveGameId, playerId },
					new BeanPropertyRowMapper<Batsman>(Batsman.class));

		} catch (Exception e) {
			batsman = null;
			logger.warn("BatsmanData is not available for liveGameId# " + liveGameId);
		}
		return batsman;
	}

	@Override
	public Bowler getBowlerData(String liveGameId, int playerId) {
		String sql = "Select * FROM `world`.`bowler_livescore` where live_game_id =?";
		Bowler bowler = null;
		try {
			bowler = (Bowler) jdbcTemplate.queryForObject(sql, new Object[] { liveGameId },
					new BeanPropertyRowMapper<Bowler>(Bowler.class));

		} catch (Exception e) {
			bowler = null;
			logger.warn("BowlerData is not available for liveGameId# " + liveGameId);
		}
		return bowler;
	}

	@Override
	public Wicket getWicketData(String liveGameId, int wicketPosition) {
		String sql = "Select * FROM `world`.`wicket_livescore` where live_game_id =?";
		Wicket wicket = null;
		try {
			wicket = (Wicket) jdbcTemplate.queryForObject(sql, new Object[] { liveGameId },
					new BeanPropertyRowMapper<Wicket>(Wicket.class));

		} catch (Exception e) {
			wicket = null;
			logger.warn("WicketData is not available for liveGameId# " + liveGameId);
		}
		return wicket;
	}

}
