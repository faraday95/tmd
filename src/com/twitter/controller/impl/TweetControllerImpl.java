package com.twitter.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twitter.constants.TwitterConstants;
import com.twitter.controller.inter.TweetController;
import com.twitter.delegate.inter.TwitterDelegateIF;
import com.twitter.model.AJAXResponse;
import com.twitter.model.CategoryCountVO;
import com.twitter.model.CategoryInfo;
import com.twitter.model.ClassifierInfo;
import com.twitter.model.ContigencyInfo;
import com.twitter.model.EnhanceContigency;
import com.twitter.model.HashTagsVO;
import com.twitter.model.Message;
import com.twitter.model.ProbabilityInfo;
import com.twitter.model.StatusInfo;
import com.twitter.model.StopWordsVO;
import com.twitter.model.TweetInfo;

@Controller
public class TweetControllerImpl implements TweetController {

	@Autowired
	private TwitterDelegateIF tweetDelegate;

	@Override
	@RequestMapping(value = "/addStopword.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView addStopWord(@RequestParam String stopWord) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == stopWord || stopWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_STOPWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.STOPWORD_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = tweetDelegate.addStopWord(stopWord);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.STOPWORD_ADD_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.STOPWORD_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.STOPWORD_ADD_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.STOPWORD_INPUT,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewStopwords.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public AJAXResponse viewStopWords(HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<StopWordsVO> keyWordList = tweetDelegate.viewStopWords();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_STOPWORDS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse.setMessage(TwitterConstants.Message.STOPWORD_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	public TwitterDelegateIF getTweetDelegate() {
		return tweetDelegate;
	}

	public void setTweetDelegate(TwitterDelegateIF tweetDelegate) {
		this.tweetDelegate = tweetDelegate;
	}

	@Override
	@RequestMapping(value = "/removeStopword.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView removeStopWord(String stopWord) {

		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == stopWord || stopWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_STOPWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.REMOVESTOPWORD_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = tweetDelegate.removeStopword(stopWord);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.STOPWORD_REMOVE_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.REMOVESTOPWORD_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.STOPWORD_REMOVE_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					TwitterConstants.Views.REMOVESTOPWORD_INPUT,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/retriveTweets.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView retriveTweetsForAllUsers() {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			StatusInfo statusInfo = tweetDelegate.retriveTweetsForAllUsers();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.RETRIVE_TWEETS_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.RETRIVETWEETS_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.RETRIVE_TWEETS_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.RETRIVETWEETS_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/submitHashTag.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView addHashTag(@RequestParam String hashTag) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == hashTag || hashTag.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_HASHTAG);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.HASHTAG_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}

			if (hashTag != null) {

				boolean value = true;
				// boolean value = true;
				if (!value) {

					List<Message> ebErrors = new ArrayList<Message>();
					ajaxRes.setStatus(false);
					Message webSiteUrlMsg = new Message();
					webSiteUrlMsg
							.setMsg(TwitterConstants.Message.HASHTAG_INVALID);
					ebErrors.add(webSiteUrlMsg);
					ajaxRes.setEbErrors(ebErrors);
					return new ModelAndView(
							TwitterConstants.Views.HASHTAG_INPUT,
							TwitterConstants.Keys.OBJ, ajaxRes);

				}
			}

			StatusInfo statusInfo = tweetDelegate.addHashTag(hashTag);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message statusMessage = new Message();

				if (statusInfo.getErrMsg() != null) {
					statusMessage.setMsg(statusInfo.getErrMsg());
				} else if (statusInfo.getExceptionMsg() != null) {
					statusMessage.setMsg(statusInfo.getExceptionMsg());
				} else {
					statusMessage
							.setMsg(TwitterConstants.Message.HASHTAG_ADD_FAILED);
				}

				ebErrors.add(statusMessage);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.HASHTAG_INPUT,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.HASHTAG_ADD_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.HASHTAG_INPUT,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewHashTags.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public AJAXResponse viewHashTags(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<HashTagsVO> keyWordList = tweetDelegate.viewHashTags();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_HASHTAGS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse.setMessage(TwitterConstants.Message.HASHTAGS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/viewTweets.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public AJAXResponse viewTweets(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<TweetInfo> tweets = tweetDelegate.viewTweets();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_TWEETS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.TWEETS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/reduceNoise.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView reduceNoise() {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			StatusInfo statusInfo = tweetDelegate.removeNoise();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message message = new Message();

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.REMOVE_NOISE_FAILED);
					ebErrors.add(m);
				}
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.NOISEREDUCTION_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.RETRIVE_CLEANTWEETS_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					TwitterConstants.Views.REMOVESTOPWORD_INPUT,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/cleanTweets.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewCleanTweets(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<TweetInfo> tweets = tweetDelegate.viewCleanTweets();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_CLEANTWEETS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.TWEETS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/deleteData.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView removeTableData(String tableDataToRemove) {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == tableDataToRemove || tableDataToRemove.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_TABLE);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.DELETEDATA_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = tweetDelegate
					.removeTableData(tableDataToRemove);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.TABLEDATA_REMOVE_FAILED);
					ebErrors.add(m);
				}

				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.DELETEDATA_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.TABLEDATA_REMOVE_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					TwitterConstants.Views.DELETEDATA_INPUT_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewCatWords.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewCatWords(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<CategoryInfo> categoryInfoList = tweetDelegate.viewCatWords();
			if (null == categoryInfoList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_CATWORDS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(categoryInfoList);
			ajaxResponse.setMessage(TwitterConstants.Message.CATWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/addCatWord.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView addCatWord(@RequestParam String catWord,
			@RequestParam String catName) {

		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == catWord || catWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_CATWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.CATWORD_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = tweetDelegate.addCatWord(catWord, catName);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.CATWORD_ADD_FAILED);
					ebErrors.add(m);
				}

				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.CATWORD_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.CATWORD_ADD_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.CATWORD_INPUT_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/removeCatWord.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView removeCatWord(@RequestParam String catWord,
			@RequestParam String catName) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == catWord || catWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.EMPTY_CATWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.REMOVECATWORD_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			}
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setCatName(catName);
			categoryInfo.setCatWord(catWord);
			StatusInfo statusInfo = tweetDelegate.removeCatWord(categoryInfo);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.CATWORD_REMOVAL_FAILED);
					ebErrors.add(m);
				}
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.REMOVECATWORD_INPUT_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.CATWORD_REMOVE_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					TwitterConstants.Views.REMOVESTOPWORD_INPUT,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/doProbability.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView doProbability() {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = tweetDelegate.doProbability();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.CATWORD_REMOVAL_FAILED);
					ebErrors.add(m);
				}
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.PROBABILITY_COMPUTATION_SUCESS);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewProbability.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewProbability(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<ProbabilityInfo> tweets = tweetDelegate.viewProbability();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_PROBABILITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.TWEETS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/doClassifier.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView classifyTweet(HttpServletRequest request) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			StatusInfo statusInfo = tweetDelegate.classifyTweet();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.CLASSIFY_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(TwitterConstants.Message.CLASSIFY_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewClassifier.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewClassifier(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<ClassifierInfo> tweets = tweetDelegate.viewClassifier();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_CLASSIFIER);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.CLASSIFY_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/viewCountsForClassifier.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewNoOfClassifier(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<CategoryCountVO> tweets = tweetDelegate.viewClassifierCount();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_CLASSIFIER_COUNT);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse
					.setMessage(TwitterConstants.Message.CLASSIFY_COUNT_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/doContigency.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView doContigency() {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = tweetDelegate.doContigency();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.CONTIGENCY_COMPUTATION_FAILED);
					ebErrors.add(m);
				}
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.CONTIGENCY_COMPUTATION_SUCESS);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}
	
	@Override
	@RequestMapping(value = "/doEnhanceContigency.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doEnhanceContigency() {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = tweetDelegate.doEnhanceContigency();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);

				if (statusInfo.getErrMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getErrMsg());
					ebErrors.add(m);
				} else if (statusInfo.getExceptionMsg() != null) {
					Message m = new Message();
					m.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(m);

				} else {
					Message m = new Message();
					m.setMsg(TwitterConstants.Message.ENHANCECONTIGENCY_COULD_NOT_FOUND);
					ebErrors.add(m);
				}
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
						TwitterConstants.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(TwitterConstants.Message.ENHANCE_CONTIGENCY_COMPUTATION_SUCESS);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						TwitterConstants.Views.VIEW_SUCESS_PAGE,
						TwitterConstants.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(TwitterConstants.Views.ERROR_VIEW,
					TwitterConstants.Keys.OBJ, ajaxRes);
		}
	}
	
	@Override
	@RequestMapping(value = "/viewEnhanceContigency.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewEnhanceContigency(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<EnhanceContigency> tweets = tweetDelegate
					.viewEnhanceContigency();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_ENHANCECONTIGENCY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.TWEETS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}
	
	@Override
	@RequestMapping(value = "/viewContigency.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewContigency(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<ContigencyInfo> tweets = tweetDelegate.viewContigency();
			if (null == tweets) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(TwitterConstants.Message.EMPTY_CONTIGENCY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(tweets);
			ajaxResponse.setMessage(TwitterConstants.Message.CONTIGENCY_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(TwitterConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}

	}

}
