/**
 * 
 */
package com.twitter.delegate.inter;

import java.util.List;

import com.twitter.model.CategoryCountVO;
import com.twitter.model.CategoryInfo;
import com.twitter.model.ClassifierInfo;
import com.twitter.model.ContigencyInfo;
import com.twitter.model.EnhanceContigency;
import com.twitter.model.HashTagsVO;
import com.twitter.model.ProbabilityInfo;
import com.twitter.model.StatusInfo;
import com.twitter.model.StopWordsVO;
import com.twitter.model.TweetInfo;
import com.twitter.model.UserInfo;

/**
 * @author Adithk
 *
 */
public interface TwitterDelegateIF {

	public StatusInfo addStopWord(String stopword);

	public List<StopWordsVO> viewStopWords();

	public StatusInfo removeStopword(String stopWord);

	public StatusInfo retriveTweetsForAllUsers();

	public StatusInfo addHashTag(String hashTag);

	public List<HashTagsVO> viewHashTags();

	public List<TweetInfo> viewTweets();

	public StatusInfo removeNoise();

	public List<TweetInfo> viewCleanTweets();

	public StatusInfo removeTableData(String tableDataToRemove);

	public List<CategoryInfo> viewCatWords();

	public StatusInfo addCatWord(String catWord, String catName);

	public StatusInfo removeCatWord(CategoryInfo categoryInfo);

	public StatusInfo doProbability();

	public List<ProbabilityInfo> viewProbability();

	public StatusInfo classifyTweet();

	public List<ClassifierInfo> viewClassifier();

	public List<CategoryCountVO> viewClassifierCount();

	public StatusInfo doContigency();

	public StatusInfo doEnhanceContigency();

	public List<EnhanceContigency> viewEnhanceContigency();

	public List<ContigencyInfo> viewContigency();

}
