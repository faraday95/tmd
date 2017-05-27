package com.twitter.delegate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitter.delegate.inter.TwitterDelegateIF;
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
import com.twitter.service.inter.TwitterServiceIF;

public class TwitterDelegateImpl implements TwitterDelegateIF {

	@Autowired
	private TwitterServiceIF twitterService;

	@Override
	public StatusInfo addStopWord(String stopword) {
		return twitterService.addStopWord(stopword);
	}

	@Override
	public List<StopWordsVO> viewStopWords() {
		return twitterService.viewStopWords();
	}

	public TwitterServiceIF getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterServiceIF twitterService) {
		this.twitterService = twitterService;
	}

	@Override
	public StatusInfo removeStopword(String stopWord) {
		return twitterService.removeStopword(stopWord);
	}

	
	@Override
	public StatusInfo retriveTweetsForAllUsers() {
		return twitterService.retriveTweetsAndStore();
	}

	@Override
	public StatusInfo addHashTag(String hashTag) {
		return twitterService.addHashTag(hashTag);
	}

	@Override
	public List<HashTagsVO> viewHashTags() {
		return twitterService.viewHashTags();
	}

	@Override
	public List<TweetInfo> viewTweets() {
		return twitterService.viewTweets();
	}

	@Override
	public StatusInfo removeNoise() {
		return twitterService.removeNoise();
	}

	@Override
	public List<TweetInfo> viewCleanTweets() {
		return twitterService.viewCleanTweets();
	}

	@Override
	public StatusInfo removeTableData(String tableDataToRemove) {
		return twitterService.removeTableData(tableDataToRemove);
	}

	@Override
	public List<CategoryInfo> viewCatWords() {
		return twitterService.viewCatWords();
	}

	@Override
	public StatusInfo addCatWord(String catWord, String catName) {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCatName(catName);
		categoryInfo.setCatWord(catWord);
		return twitterService.insertCategory(categoryInfo);
	}

	@Override
	public StatusInfo removeCatWord(CategoryInfo categoryInfo) {
		return twitterService.removeCatWord(categoryInfo);
	}

	@Override
	public StatusInfo doProbability() {
		return twitterService.doProbability();
	}

	@Override
	public List<ProbabilityInfo> viewProbability() {
		return twitterService.viewProbability();
	}

	@Override
	public StatusInfo classifyTweet() {
		return twitterService.classifyTweet();
	}

	
	@Override
	public List<ClassifierInfo> viewClassifier() {
		return twitterService.viewClassifier();
	}

	@Override
	public List<CategoryCountVO> viewClassifierCount() {
		return twitterService.viewClassifierCount();
	}

	@Override
	public StatusInfo doContigency() {
		return twitterService.doContigency();
	}

	@Override
	public StatusInfo doEnhanceContigency() {
		return twitterService.doEnhanceContigency();
	}

	@Override
	public List<EnhanceContigency> viewEnhanceContigency() {
		return twitterService.viewEnhanceContigency();
	}

	@Override
	public List<ContigencyInfo> viewContigency() {
		return twitterService.viewContigency();
	}

}
