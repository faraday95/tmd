package com.twitter.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.twitter.constants.TwitterConstants;
import com.twitter.dao.inter.TwitterDAOIF;
import com.twitter.model.CategoryCountVO;
import com.twitter.model.CategoryInfo;
import com.twitter.model.ClassifierInfo;
import com.twitter.model.ContigencyInfo;
import com.twitter.model.EnhanceContigency;
import com.twitter.model.HashTagsVO;
import com.twitter.model.PartialClassifierInfo;
import com.twitter.model.ProbabilityInfo;
import com.twitter.model.StatusInfo;
import com.twitter.model.StopWordsVO;
import com.twitter.model.TweetInfo;
import com.twitter.model.UserInfo;

public class TwitterDAOImpl implements TwitterDAOIF {

	@Autowired
	protected MessageSource sqlProperties;

	public MessageSource getSqlProperties() {
		return sqlProperties;
	}

	public void setSqlProperties(MessageSource sqlProperties) {
		this.sqlProperties = sqlProperties;
	}

	@Autowired
	private NamedParameterJdbcTemplate npjt;

	public NamedParameterJdbcTemplate getNpjt() {
		return npjt;
	}

	public void setNpjt(NamedParameterJdbcTemplate npjt) {
		this.npjt = npjt;
	}

	@Override
	public StatusInfo insertStopWord(String stopword) {
		StatusInfo insertStopWordStatus = null;
		try {
			insertStopWordStatus = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_STOPWORD_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("stopword", stopword);

			npjt.update(sql, paramMap);
			insertStopWordStatus.setStatus(true);
			return insertStopWordStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertStopWordStatus = new StatusInfo();
			insertStopWordStatus.setErrMsg(e.getMessage());
			insertStopWordStatus.setStatus(false);
			return insertStopWordStatus;

		}
	}

	@Override
	public List<String> viewStopwords() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_STOPWORDS_SQL, null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<StopWordsVO> retriveStopWords() {
		List<StopWordsVO> stopWordList = null;
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_STOPWORDS_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new StopWordsVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class StopWordsVOMapper implements RowMapper<StopWordsVO> {

		public StopWordsVO mapRow(ResultSet rs, int arg1) throws SQLException {
			StopWordsVO webSiteDataVO = new StopWordsVO();
			webSiteDataVO.setStopWordId(rs
					.getInt(TwitterConstants.DatabaseColumns.STOPWORDID_COL));
			webSiteDataVO.setStopWord(rs
					.getString(TwitterConstants.DatabaseColumns.STOPWORD_COL));
			return webSiteDataVO;

		}
	}

	@Override
	public StatusInfo removeStopword(String stopWord) {

		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_STOPWORD_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.STOPWORD_COL, stopWord);

			npjt.update(sql, paramMap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}

	}

	@Override
	public StatusInfo insertUserInfo(UserInfo userInfo) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_USERINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());
			paramMap.put(TwitterConstants.Keys.SECRETKEY,
					userInfo.getSecretKey());
			paramMap.put(TwitterConstants.Keys.APIKEY, userInfo.getApiKey());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public StatusInfo updateUserInfo(UserInfo userInfo) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.UPDATE_USERINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());
			paramMap.put(TwitterConstants.Keys.SECRETKEY,
					userInfo.getSecretKey());
			paramMap.put(TwitterConstants.Keys.APIKEY, userInfo.getApiKey());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public StatusInfo removeUserInfo(UserInfo userInfo) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_USERINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public List<UserInfo> retriveAllUserInfo() {
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_USERINFO_FULL_SQL,
							null, null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new UserInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class UserInfoMapper implements RowMapper<UserInfo> {

		public UserInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			UserInfo webSiteDataVO = new UserInfo();
			webSiteDataVO.setUserId(rs
					.getString(TwitterConstants.DatabaseColumns.USERID_COL));
			webSiteDataVO.setSecretKey(rs
					.getString(TwitterConstants.DatabaseColumns.SECRETKEY_COL));
			webSiteDataVO.setApiKey(rs
					.getString(TwitterConstants.DatabaseColumns.APPKEY_COL));
			return webSiteDataVO;

		}
	}

	@Override
	public UserInfo retriveSpecficUserInfo(UserInfo userInfo) {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_USERINFO_FOR_USERID_SQL,
					null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());

			return npjt.query(sql, paramMap, new UserInfoMapper()).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo insertTweetInfo(TweetInfo userInfo) {
		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_TWEETINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.TWEETDESCKEY,
					userInfo.getTweetDesc());
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());

			npjt.update(sql, paramMap);
			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}
	}

	@Override
	public StatusInfo updateTweetInfo(TweetInfo userInfo) {
		StatusInfo updateTweetInfo = null;
		try {
			updateTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.UPDATE_TWEETINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.TWEETDESCKEY,
					userInfo.getTweetDesc());
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());

			npjt.update(sql, paramMap);
			updateTweetInfo.setStatus(true);
			return updateTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			updateTweetInfo = new StatusInfo();
			updateTweetInfo.setErrMsg(e.getMessage());
			updateTweetInfo.setStatus(false);
			return updateTweetInfo;

		}
	}

	@Override
	public StatusInfo removeTweetInfo(TweetInfo userInfo) {

		StatusInfo removeTweetInfo = null;
		try {
			removeTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_TWEETINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());

			npjt.update(sql, paramMap);
			removeTweetInfo.setStatus(true);
			return removeTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			removeTweetInfo = new StatusInfo();
			removeTweetInfo.setErrMsg(e.getMessage());
			removeTweetInfo.setStatus(false);
			return removeTweetInfo;

		}
	}

	@Override
	public List<TweetInfo> retriveAllTweetInfo() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_TWEETINFO_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new TweetInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class TweetInfoMapper implements RowMapper<TweetInfo> {

		public TweetInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			TweetInfo tweetInfo = new TweetInfo();
			tweetInfo.setUserId(rs
					.getString(TwitterConstants.DatabaseColumns.USERID_COL));
			tweetInfo.setTweetDesc(rs
					.getString(TwitterConstants.DatabaseColumns.TWEETDESC_COL));
			tweetInfo.setTweetId(rs
					.getInt(TwitterConstants.DatabaseColumns.TWEETID_COL));
			tweetInfo.setHashTag(rs
					.getString(TwitterConstants.DatabaseColumns.HASHTAG_COL));
			tweetInfo
					.setLanguage(rs
							.getString(TwitterConstants.DatabaseColumns.TWEETLANGUAGE_COL));
			tweetInfo
					.setScreenName(rs
							.getString(TwitterConstants.DatabaseColumns.TWEETSCREEN_COL));

			return tweetInfo;

		}
	}

	@Override
	public List<TweetInfo> retriveSpecficTweetInfo(UserInfo userInfo) {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_TWEETINFO_FOR_USERID_SQL,
					null, null);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.USERIDKEY, userInfo.getUserId());
			return npjt.query(sql, paramMap, new TweetInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> retriveUserIds() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_USERIDS_SQL, null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> viewHashTags() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_HASHTAGS_SQL, null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo insertHashTag(String hashTag) {
		StatusInfo insertStopWordStatus = null;
		try {
			insertStopWordStatus = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_HASHTAG_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("hashtag", hashTag);

			npjt.update(sql, paramMap);
			insertStopWordStatus.setStatus(true);
			return insertStopWordStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertStopWordStatus = new StatusInfo();
			insertStopWordStatus.setErrMsg(e.getMessage());
			insertStopWordStatus.setStatus(false);
			return insertStopWordStatus;

		}
	}

	@Override
	public List<HashTagsVO> retriveHashTags() {
		List<HashTagsVO> stopWordList = null;
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_HASHTAGS_FULL_SQL,
							null, null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new HashTagVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class HashTagVOMapper implements RowMapper<HashTagsVO> {

		public HashTagsVO mapRow(ResultSet rs, int arg1) throws SQLException {
			HashTagsVO hashTagVO = new HashTagsVO();
			hashTagVO.setHashTagId(rs
					.getInt(TwitterConstants.DatabaseColumns.HASHTAGID_COL));
			hashTagVO.setHashtag(rs
					.getString(TwitterConstants.DatabaseColumns.HASHTAG_COL));
			return hashTagVO;

		}
	}

	@Override
	public StatusInfo insertBlockTweetInfo(List<TweetInfo> tweetInfoList) {
		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_TWEETINFO_SQL, null, null);

			for (TweetInfo tweetInfo : tweetInfoList) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(TwitterConstants.Keys.TWEET_DESC_KEY,
						tweetInfo.getTweetDesc());
				paramMap.put(TwitterConstants.Keys.TWEET_USERID_KEY,
						tweetInfo.getUserId());
				paramMap.put(TwitterConstants.Keys.TWEET_HASHTAG_KEY,
						tweetInfo.getHashTag());
				paramMap.put(TwitterConstants.Keys.TWEET_LANG_KEY,
						tweetInfo.getLanguage());
				paramMap.put(TwitterConstants.Keys.TWEET_SCREENNAME_KEY,
						tweetInfo.getScreenName());
				npjt.update(sql, paramMap);
			}
			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}
	}

	@Override
	public StatusInfo insertCleanTweets(List<TweetInfo> tweetInfoList) {
		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.INSERT_CLEANTWEETINFO_SQL,
							null, null);

			for (TweetInfo tweetInfo : tweetInfoList) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(TwitterConstants.Keys.TWEET_DESC_KEY,
						tweetInfo.getTweetDesc());
				paramMap.put(TwitterConstants.Keys.TWEET_USERID_KEY,
						tweetInfo.getUserId());
				paramMap.put(TwitterConstants.Keys.TWEET_HASHTAG_KEY,
						tweetInfo.getHashTag());
				paramMap.put(TwitterConstants.Keys.TWEET_LANG_KEY,
						tweetInfo.getLanguage());
				paramMap.put(TwitterConstants.Keys.TWEET_SCREENNAME_KEY,
						tweetInfo.getScreenName());
				npjt.update(sql, paramMap);
			}
			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}
	}

	@Override
	public StatusInfo deleteAllNoiselessTweets() {

		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_ALL_CLEANTWEETS_SQL, null,
					null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}

	}

	@Override
	public List<TweetInfo> retriveAllCleanTweetInfo() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CLEANTWEETINFO_FULL_SQL,
					null, null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new TweetInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removeTweets() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_ALL_TWEETS_SQL, null, null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo removeHashTags() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_ALL_HASHTAGS_SQL, null, null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo insertCategoryBlock(List<CategoryInfo> categoryInfo) {

		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_CATINFO_SQL, null, null);

			for (CategoryInfo catInfo : categoryInfo) {
				Map<String, Object> paramMap = new HashMap<String, Object>();

				paramMap.put(TwitterConstants.Keys.CATNAME_KEY,
						catInfo.getCatName());
				paramMap.put(TwitterConstants.Keys.CATWORD_KEY,
						catInfo.getCatWord());
				npjt.update(sql, paramMap);
			}
			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}

	}

	@Override
	public StatusInfo insertCategoryBlock(CategoryInfo categoryInfo) {
		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_CATINFO_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("CATNAME", categoryInfo.getCatName());
			paramMap.put("CATKEYWORD", categoryInfo.getCatWord());
			npjt.update(sql, paramMap);

			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}
	}

	@Override
	public List<CategoryInfo> retriveAllCategory() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CATINFO_SQL, null, null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new CategoryInfoVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class CategoryInfoVOMapper implements RowMapper<CategoryInfo> {

		public CategoryInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			CategoryInfo webSiteDataVO = new CategoryInfo();
			webSiteDataVO.setCatId((rs
					.getInt(TwitterConstants.DatabaseColumns.CATID_COL)));
			webSiteDataVO.setCatName(rs
					.getString(TwitterConstants.DatabaseColumns.CATNAME_COL));
			webSiteDataVO.setCatWord(rs
					.getString(TwitterConstants.DatabaseColumns.CATWORD_COL));
			return webSiteDataVO;

		}
	}

	@Override
	public List<String> retriveWordsForCatName(String catName) {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CATWORDS_FOR_CATNAMESQL,
					null, null);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.CATNAME_KEY, catName);
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removeCategory(CategoryInfo categoryInfo) {
		StatusInfo removeCatStatus = null;
		try {
			removeCatStatus = new StatusInfo();

			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.REMOVE_CATINFO_FOR_CATWORD_CATNAME_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(TwitterConstants.Keys.CATWORD_KEY,
					categoryInfo.getCatWord());
			paramMap.put(TwitterConstants.Keys.CATNAME_KEY,
					categoryInfo.getCatName());

			npjt.update(sql, paramMap);
			removeCatStatus.setStatus(true);
			return removeCatStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			removeCatStatus = new StatusInfo();
			removeCatStatus.setErrMsg(e.getMessage());
			removeCatStatus.setStatus(false);
			return removeCatStatus;

		}
	}

	@Override
	public StatusInfo deleteAllProbabilities() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_ALL_PROBABILITIES_SQL, null,
					null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<String> retriveDistinctCatInfo() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_DISTINCT_CATNAME_SQL, null,
					null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> retriveKeywordsForCatName(String categoryInfoTemp) {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_KEYWORDS_FOR_CAT_SQL, null,
					null);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("catName", categoryInfoTemp);
			return npjt.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	// INSERT_PROBABILITY_INFO_SQL=INSERT INTO
	// PROBABILITY(TWEETID,PROBABILITY,CATNAME,NEGATIVEPROBABILITY,COUNT,TOTALWORDS)
	// VALUES(:tweetId,:probability,:catName,:negativeProb,:count,:totalWords)
	@Override
	public StatusInfo insertProbability(ProbabilityInfo probabilityInfo) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_PROBABILITY_INFO_SQL, null,
					null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tweetId", probabilityInfo.getTweetId());
			paramMap.put("probability", probabilityInfo.getProbability());
			paramMap.put("catName", probabilityInfo.getCatName());
			paramMap.put("negativeProb",
					probabilityInfo.getNegativeProbability());
			paramMap.put("count", probabilityInfo.getCount());
			paramMap.put("totalWords", probabilityInfo.getTotalWords());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	// SELECT TWEETID,PROBABILITY,CATNAME,NEGATIVEPROBABILITY,COUNT,TOTALWORDS
	// FROM PROBABILITY
	public List<ProbabilityInfo> retriveAllProbability() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_PROBABILITY_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new ProbabilityInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}

	}

	private final class ProbabilityInfoMapper implements
			RowMapper<ProbabilityInfo> {

		public ProbabilityInfo mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ProbabilityInfo probabilityInfo = new ProbabilityInfo();

			probabilityInfo.setCatName(rs.getString("CATNAME"));
			probabilityInfo.setCount(rs.getInt("COUNT"));
			probabilityInfo.setNegativeProbability(rs
					.getDouble("NEGATIVEPROBABILITY"));
			probabilityInfo.setProbability(rs.getDouble("PROBABILITY"));
			probabilityInfo.setTotalWords(rs.getInt("TOTALWORDS"));
			probabilityInfo.setTweetId(rs.getInt("TWEETID"));

			return probabilityInfo;

		}
	}

	@Override
	public StatusInfo deleteAllContigency() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.REMOVE_ALL_CONTIGENCY_SQL,
							null, null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<Integer> retriveDistinctTweetIdsFromContigency() {
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_DISTINCT_TWEETIDS_FROM_CONTGENCY_SQL,
							null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	// SELECT CATNAME,POSITIVECATRATIO,OTHERCATRATIO FROM ENHANCEDCONTIGENCY
	// WHERE TWEETID =:tweetId ORDER BY POSITIVECATRATIO DESC, OTHERCATRATIO ASC
	public List<PartialClassifierInfo> retriveClassifierByRank(int tweetId) {

		List<PartialClassifierInfo> partialClassList = null;
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_RANKED_CLASSIFIER_FROM_CONTGENCY_WHERE_TWEETID_SQL,
							null, null);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tweetId", tweetId);

			partialClassList = npjt.query(sql, paramMap,
					new PartialClassifierMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;

		}

		return partialClassList;

	}

	// SELECT TWEETID,POSITIVECATRATIO,OTHERCATRATIO,CATNAME FROM
		// ENHANCEDCONTIGENCY
		private final class PartialClassifierMapper implements
				RowMapper<PartialClassifierInfo> {

			public PartialClassifierInfo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				PartialClassifierInfo probabilityInfo = new PartialClassifierInfo();

				probabilityInfo.setCatName(rs.getString("CATNAME"));
				probabilityInfo
						.setPositiveRatio((rs.getDouble("POSITIVECATRATIO")));
				probabilityInfo.setNegativeRatio(rs.getDouble("OTHERCATRATIO"));
				probabilityInfo.setTweetId(rs.getInt("TWEETID"));

				return probabilityInfo;

			}
		}

	@Override
	public StatusInfo insertClassfierInfo(ClassifierInfo ci) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_CLASSIFIER_INFO_SQL, null,
					null);
			// INSERT INTO classifier(TWEETID,CATNAME) VALUES(:tweetId,:catName)

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("tweetId", ci.getTweetId());
			paramMap.put("catName", ci.getCatName());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public StatusInfo deleteAllClassifier() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.REMOVE_ALL_CLASSIFIER_SQL,
							null, null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<ClassifierInfo> retriveAllClassifierInfo() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CLASSINFO_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new ClassifierInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class ClassifierInfoMapper implements
			RowMapper<ClassifierInfo> {

		public ClassifierInfo mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ClassifierInfo probabilityInfo = new ClassifierInfo();

			probabilityInfo.setCatName(rs.getString("CATNAME"));
			probabilityInfo.setTweetId(rs.getInt("TWEETID"));

			return probabilityInfo;

		}
	}

	@Override
	// SELECT DISTINCT CATNAME AS CATNAME, COUNT(*) AS COUNTER FROM classifier
	// GROUP BY CATNAME
	public List<CategoryCountVO> viewClassifierCount() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CLASSIFYCOUNT_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new CategoryCountVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class CategoryCountVOMapper implements
			RowMapper<CategoryCountVO> {

		public CategoryCountVO mapRow(ResultSet rs, int arg1)
				throws SQLException {
			CategoryCountVO probabilityInfo = new CategoryCountVO();

			probabilityInfo.setCatName(rs.getString("CATNAME"));
			probabilityInfo.setNoOfTweets((rs.getInt("COUNTER")));

			return probabilityInfo;

		}
	}

	@Override
	public List<Integer> retriveDistinctTweetIdsFromProbability() {
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_DISTINCT_TWEETIDS_FROM_PROBABILITY_SQL,
							null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo insertContigency(ContigencyInfo contigencyInfo) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_CONTIGENCY_INFO_SQL, null,
					null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tweetId", contigencyInfo.getTweetId());
			paramMap.put("positiveProbability", contigencyInfo.getProbability());
			paramMap.put("catName", contigencyInfo.getCatName());
			paramMap.put("negativeProbability",
					contigencyInfo.getNegativeProbability());
			paramMap.put("positiveOther",
					contigencyInfo.getTotalPositiveOthers());
			paramMap.put("negativeOther",
					contigencyInfo.getTotalNegativeOthers());

			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public ContigencyInfo findTotalPosOthersAnsTotalNegativeOthers(int tweetId,
			String catName) {
		ContigencyInfo contigencyInfo = null;
		try {

			StringBuilder str = new StringBuilder();
			str.append("SELECT SUM(PROBABILITY) AS POSITIVEOTHER ,SUM(NEGATIVEPROBABILITY) AS NEGATIVEOTHER FROM PROBABILITY WHERE TWEETID=");
			str.append(tweetId);
			str.append("  AND CATNAME NOT IN('");
			str.append(catName);
			str.append("')");

			System.out.println("SQL IS =" + str.toString());

			Map<String, Object> paramMap = null;

			contigencyInfo = npjt.query(str.toString(), paramMap,
					new PartialContigencyMapper()).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return contigencyInfo;
	}

	private final class PartialContigencyMapper implements
			RowMapper<ContigencyInfo> {

		public ContigencyInfo mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ContigencyInfo webSiteDataVO = new ContigencyInfo();
			webSiteDataVO.setTotalPositiveOthers(rs.getDouble("POSITIVEOTHER"));
			webSiteDataVO.setTotalNegativeOthers(rs.getDouble("NEGATIVEOTHER"));
			return webSiteDataVO;
		}
	}

	@Override
	public List<EnhanceContigency> retriveAllEnhanceContigency() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_ENHANCECONTIGENCY_FULL_SQL,
					null, null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new EnhanceContigencyMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	// SELECT TWEETID,POSITIVECATRATIO,OTHERCATRATIO,CATNAME FROM
	// ENHANCEDCONTIGENCY
	private final class EnhanceContigencyMapper implements
			RowMapper<EnhanceContigency> {

		public EnhanceContigency mapRow(ResultSet rs, int arg1)
				throws SQLException {
			EnhanceContigency probabilityInfo = new EnhanceContigency();

			probabilityInfo.setCatName(rs.getString("CATNAME"));
			probabilityInfo
					.setPositiveCatRatio((rs.getDouble("POSITIVECATRATIO")));
			probabilityInfo.setOtherCatRatio(rs.getDouble("OTHERCATRATIO"));
			probabilityInfo.setTweetId(rs.getInt("TWEETID"));

			return probabilityInfo;

		}
	}

	@Override
	public StatusInfo deleteAllEnhanceContigency() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.REMOVE_ALL_ENHANCECONTIGENCY_SQL,
					null, null);
			SqlParameterSource parammap = null;
			npjt.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<ContigencyInfo> retriveAllContigency() {
		try {
			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.RETRIVE_CONTIGENCY_FULL_SQL, null,
					null);
			Map<String, Object> paramMap = null;
			return npjt.query(sql, paramMap, new ContigencyInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class ContigencyInfoMapper implements
			RowMapper<ContigencyInfo> {

		public ContigencyInfo mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ContigencyInfo probabilityInfo = new ContigencyInfo();

			probabilityInfo.setCatName(rs.getString("CATNAME"));
			probabilityInfo.setTotalNegativeOthers(rs
					.getDouble("TOTALNEGATIVEOTHER"));
			probabilityInfo.setNegativeProbability(rs
					.getDouble("NEGATIVEPROBABILITY"));
			probabilityInfo.setProbability(rs.getDouble("POSITIVEPROBABILITY"));
			probabilityInfo.setTotalPositiveOthers((rs
					.getDouble("TOTALPOSITIVEOTHER")));
			probabilityInfo.setTweetId(rs.getInt("TWEETID"));

			return probabilityInfo;

		}
	}

	@Override
	public StatusInfo insertEhanceContigency(EnhanceContigency enhanceContigency) {
		StatusInfo insertUserInfo = null;
		try {
			insertUserInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(
					TwitterConstants.SQLS.INSERT_ENHANCE_CONTIGENCY_INFO_SQL,
					null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tweetId", enhanceContigency.getTweetId());
			paramMap.put("positive", enhanceContigency.getPositiveCatRatio());
			paramMap.put("other", enhanceContigency.getOtherCatRatio());
			paramMap.put("catName", enhanceContigency.getCatName());
			npjt.update(sql, paramMap);
			insertUserInfo.setStatus(true);
			return insertUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertUserInfo = new StatusInfo();
			insertUserInfo.setErrMsg(e.getMessage());
			insertUserInfo.setStatus(false);
			return insertUserInfo;

		}
	}

	@Override
	public List<Integer> retriveDistinctTweetIdsFromEnhanceContigency() {
		try {
			String sql = sqlProperties
					.getMessage(
							TwitterConstants.SQLS.RETRIVE_DISTINCT_TWEETIDS_FROM_ENHANCECONTGENCY_SQL,
							null, null);
			SqlParameterSource paramMap = null;
			return npjt.queryForList(sql, paramMap, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

}
