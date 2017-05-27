package com.twitter.dao.inter;

import java.util.List;

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

public interface TwitterDAOIF {

	StatusInfo insertStopWord(String stopword);

	List<String> viewStopwords();

	List<StopWordsVO> retriveStopWords();

	StatusInfo removeStopword(String stopWord);

	StatusInfo insertUserInfo(UserInfo userInfo);

	StatusInfo updateUserInfo(UserInfo userInfo);

	StatusInfo removeUserInfo(UserInfo userInfo);

	List<UserInfo> retriveAllUserInfo();

	UserInfo retriveSpecficUserInfo(UserInfo userInfo);

	StatusInfo insertTweetInfo(TweetInfo userInfo);

	StatusInfo updateTweetInfo(TweetInfo userInfo);

	StatusInfo removeTweetInfo(TweetInfo userInfo);

	List<TweetInfo> retriveAllTweetInfo();

	List<TweetInfo> retriveSpecficTweetInfo(UserInfo userInfo);

	List<String> retriveUserIds();

	List<String> viewHashTags();

	StatusInfo insertHashTag(String hashTag);

	List<HashTagsVO> retriveHashTags();

	StatusInfo insertBlockTweetInfo(List<TweetInfo> tweetInfoList);

	StatusInfo insertCleanTweets(List<TweetInfo> tweetInfoList);

	StatusInfo deleteAllNoiselessTweets();

	List<TweetInfo> retriveAllCleanTweetInfo();

	StatusInfo removeTweets();

	StatusInfo removeHashTags();

	public StatusInfo insertCategoryBlock(List<CategoryInfo> categoryInfo);

	public StatusInfo insertCategoryBlock(CategoryInfo categoryInfo);

	public List<CategoryInfo> retriveAllCategory();

	List<String> retriveWordsForCatName(String catName);

	StatusInfo removeCategory(CategoryInfo categoryInfo);

	StatusInfo deleteAllProbabilities();

	List<String> retriveDistinctCatInfo();

	List<String> retriveKeywordsForCatName(String categoryInfoTemp);

	StatusInfo insertProbability(ProbabilityInfo probabilityInfo);

	List<ProbabilityInfo> retriveAllProbability();

	StatusInfo deleteAllContigency();

	List<Integer> retriveDistinctTweetIdsFromContigency();

	List<PartialClassifierInfo> retriveClassifierByRank(int tweetId);

	StatusInfo insertClassfierInfo(ClassifierInfo ci);

	StatusInfo deleteAllClassifier();

	List<ClassifierInfo> retriveAllClassifierInfo();

	List<CategoryCountVO> viewClassifierCount();

	List<Integer> retriveDistinctTweetIdsFromProbability();

	StatusInfo insertContigency(ContigencyInfo contigencyInfo);

	ContigencyInfo findTotalPosOthersAnsTotalNegativeOthers(int tweetId,
			String catName);

	List<EnhanceContigency> retriveAllEnhanceContigency();

	StatusInfo deleteAllEnhanceContigency();

	List<ContigencyInfo> retriveAllContigency();

	StatusInfo insertEhanceContigency(EnhanceContigency enhanceContigency);

	List<Integer> retriveDistinctTweetIdsFromEnhanceContigency(); 

}
