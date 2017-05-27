package com.twitter.controller.inter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twitter.model.AJAXResponse;
import com.twitter.model.UserInfo;

public interface TweetController {

	public ModelAndView addStopWord(String stopWord);

	public @ResponseBody AJAXResponse viewStopWords(HttpServletRequest request);

	public @ResponseBody AJAXResponse viewTweets(HttpServletRequest request);

	public ModelAndView removeStopWord(@RequestParam String stopWord);

	public ModelAndView retriveTweetsForAllUsers();

	public ModelAndView addHashTag(String hashTag);

	public @ResponseBody AJAXResponse viewHashTags(HttpServletRequest request);

	public ModelAndView reduceNoise();

	public @ResponseBody AJAXResponse viewCleanTweets(HttpServletRequest request);

	public ModelAndView removeTableData(@RequestParam String tableDataToRemove);

	public @ResponseBody AJAXResponse viewCatWords(HttpServletRequest request);

	public ModelAndView addCatWord(String catWord, String catName);

	public ModelAndView removeCatWord(@RequestParam String catWord,
			@RequestParam String catName);

	public ModelAndView doProbability();

	AJAXResponse viewProbability(HttpServletRequest request);

	public ModelAndView classifyTweet(HttpServletRequest request);

	AJAXResponse viewClassifier(HttpServletRequest request);
	
	public @ResponseBody AJAXResponse viewNoOfClassifier(HttpServletRequest request);

	ModelAndView doContigency();

	ModelAndView doEnhanceContigency();

	AJAXResponse viewEnhanceContigency(HttpServletRequest request);

	AJAXResponse viewContigency(HttpServletRequest request);
}
