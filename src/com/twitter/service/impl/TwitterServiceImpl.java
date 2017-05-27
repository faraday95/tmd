package com.twitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
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
import com.twitter.service.inter.TwitterServiceIF;

public class TwitterServiceImpl implements TwitterServiceIF {

	@Autowired
	private TwitterDAOIF twitterDoa;

	@Override
	public StatusInfo addStopWord(String stopWord) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = twitterDoa.viewStopwords();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = twitterDoa.insertStopWord(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (keyWordList.contains(stopWord)) {
				statusInfo.setErrMsg(TwitterConstants.Message.STOPWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = twitterDoa.insertStopWord(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public List<StopWordsVO> viewStopWords() {
		List<StopWordsVO> stopWordList = null;
		try {
			stopWordList = twitterDoa.retriveStopWords();
			if (null == stopWordList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return stopWordList;
	}

	public TwitterDAOIF getTwitterDoa() {
		return twitterDoa;
	}

	public void setTwitterDoa(TwitterDAOIF twitterDoa) {
		this.twitterDoa = twitterDoa;
	}

	@Override
	public StatusInfo removeStopword(String stopWord) {

		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = twitterDoa.viewStopwords();

			if (!keyWordList.contains(stopWord)) {
				statusInfo
						.setErrMsg(TwitterConstants.Message.NO_STOPWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = twitterDoa.removeStopword(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}

	}

	@Override
	public StatusInfo retriveTweetsAndStore() {

		StatusInfo statusInfo = new StatusInfo();
		try {

			List<String> hashtags = twitterDoa.viewHashTags();

			if (null == hashtags || hashtags.isEmpty()) {
				statusInfo.setErrMsg(TwitterConstants.Message.HASHTAGS_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer("nPFfrhiQhPyAiEu3rAko7hzwU",
					"jmVHsFkIFgPoDG6akbsKXUJS4IXqIQ1SFtuzYzgTCkdoRMz6nH");
			twitter.setOAuthAccessToken(new AccessToken(
					"1048908211-qD3Dxr5hk0hZEQC7jqUkNStc04C3F1XtjcJhjVH",
					"3btbAZ2Pd4UCSE1mlE1i46NqkOsydK5UBnAxHGs67jXns"));

			// User user = twitter.verifyCredentials();
			List<TweetInfo> tweetInfoList = new ArrayList<TweetInfo>();
			for (String hashTag : hashtags) {
				Query query = new Query(hashTag);
				query.count(TwitterConstants.TweetOAuthConstants.TWEETS_NOS);
				QueryResult result = twitter.search(query);

				List<Status> statusList = result.getTweets();
				if (statusList != null) {
					for (Status status : statusList) {

						TweetInfo tweetInfo = new TweetInfo();
						if (checkValidTweet(status)) {
							tweetInfo.setHashTag(hashTag);
							tweetInfo.setLanguage(status.getLang());
							tweetInfo.setScreenName(status.getUser()
									.getScreenName());
							tweetInfo.setTweetDesc(status.getText());

							StringBuffer buffer = new StringBuffer();
							buffer.append(tweetInfo.getTweetDesc());
							if (tweetInfo.getTweetDesc() != null) {

								List<CategoryInfo> keywords = twitterDoa
										.retriveAllCategory();

								if (keywords != null) {

									int size = keywords.size();

									for (int i = 0; i < 5; i++) {
										int randInt = new Random()
												.nextInt(size);

										if (randInt < size) {
											String word = keywords.get(randInt)
													.getCatWord();

											buffer.append(" ");
											buffer.append(word);

										}
									}

									tweetInfo.setTweetDesc(buffer.toString());
								}

							}

							tweetInfo.setUserId((status.getUser().getName()));
						}
						tweetInfoList.add(tweetInfo);
					}
				}
			}

			statusInfo = twitterDoa.insertBlockTweetInfo(tweetInfoList);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
		}
		// TODO Auto-generated method stub
		return statusInfo;
	}

	private boolean checkValidTweet(Status status) {
		return status.getText() != null
				&& (status.getUser() != null && status.getUser().getName() != null)
				&& status.getLang() != null
				&& (status.getUser() != null && status.getUser()
						.getScreenName() != null);
	}

	@Override
	public StatusInfo addHashTag(String hashTag) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = twitterDoa.viewHashTags();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = twitterDoa.insertHashTag(hashTag);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (keyWordList.contains(hashTag)) {
				statusInfo.setErrMsg(TwitterConstants.Message.HASHTAG_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = twitterDoa.insertHashTag(hashTag);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}

	}

	@Override
	public List<HashTagsVO> viewHashTags() {
		List<HashTagsVO> hashT = null;
		try {
			hashT = twitterDoa.retriveHashTags();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public List<TweetInfo> viewTweets() {
		List<TweetInfo> hashT = null;
		try {
			hashT = twitterDoa.retriveAllTweetInfo();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public StatusInfo removeNoise() {
		StatusInfo statusInfo = null;
		try {

			statusInfo = new StatusInfo();
			List<TweetInfo> tweetInfoList = new ArrayList<TweetInfo>();
			// Remove All Clean Reviews
			statusInfo = twitterDoa.deleteAllNoiselessTweets();
			if (!statusInfo.isStatus()) {
				return statusInfo;
			}

			List<TweetInfo> tweetList = twitterDoa.retriveAllTweetInfo();

			List<String> stopWordsList = twitterDoa.viewStopwords();

			for (TweetInfo tweetInformation : tweetList) {

				String reviewDetails = tweetInformation.getTweetDesc();
				String str = reviewDetails.replaceAll("\\s", " ");

				String cleanTweet = str.replaceAll(
						TwitterConstants.Keys.STOPWORDS_SYMBOL,
						TwitterConstants.Keys.SPACE);

				StringTokenizer tok1 = new StringTokenizer(cleanTweet);
				StringBuilder buffer = new StringBuilder();
				String str1 = null;
				while (tok1.hasMoreTokens()) {
					str1 = (String) tok1.nextElement();
					str1 = str1.toLowerCase();

					if (null == str1 || str1.isEmpty() || str1.length() <= 0
							|| str1.trim().length() == 0) {
						continue;
					}
					if (str1 != null) {
						str1 = str1.trim();
					}
					if (stopWordsList.contains(str1)) {
						continue;
					} else {
						str1 = str1.replaceAll(
								TwitterConstants.Keys.STOPWORDS_SYMBOL,
								TwitterConstants.Keys.SPACE);
						str1 = str1.trim();
						if (str1.length() > 0) {
							buffer.append(TwitterConstants.Keys.SPACE);
							buffer.append(str1);
						}
						System.out.println("BUFFER" + buffer);
					}
				}

				// Now Create an Object of Clean Review

				TweetInfo tweetInfo = populateCleanTweetInfo(tweetInformation,
						buffer);

				tweetInfoList.add(tweetInfo);

			}

			statusInfo = twitterDoa.insertCleanTweets(tweetInfoList);
			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(TwitterConstants.Message.NOISEREDUCE_FAILED);
				return statusInfo;
			}
			statusInfo.setStatus(true);
			return statusInfo;
			// end of for Loop

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
	}

	private TweetInfo populateCleanTweetInfo(TweetInfo tweetInformation,
			StringBuilder buffer) {
		TweetInfo cleanTweetInfo = new TweetInfo();
		cleanTweetInfo.setHashTag(tweetInformation.getHashTag());
		cleanTweetInfo.setLanguage(tweetInformation.getLanguage());
		cleanTweetInfo.setScreenName(tweetInformation.getScreenName());
		if (buffer != null) {
			cleanTweetInfo.setTweetDesc(buffer.toString());
		} else {
			cleanTweetInfo.setTweetDesc(TwitterConstants.Keys.SPACE);
		}

		cleanTweetInfo.setUserId(tweetInformation.getUserId());
		return cleanTweetInfo;
	}

	@Override
	public List<TweetInfo> viewCleanTweets() {
		List<TweetInfo> hashT = null;
		try {
			hashT = twitterDoa.retriveAllCleanTweetInfo();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public StatusInfo removeTableData(String tableDataToRemove) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			if (null == tableDataToRemove || tableDataToRemove.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(TwitterConstants.Message.EMPTY_TABLE);

				return statusInfo;
			}

			if (tableDataToRemove.equals(TwitterConstants.TableNames.TWEET)) {
				statusInfo = twitterDoa.removeTweets();
			}

			if (tableDataToRemove
					.equals(TwitterConstants.TableNames.CLEANTWEETS)) {
				statusInfo = twitterDoa.deleteAllNoiselessTweets();
			}

			if (tableDataToRemove.equals(TwitterConstants.TableNames.HASHTAGS)) {
				statusInfo = twitterDoa.removeHashTags();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public StatusInfo insertCategory(CategoryInfo categoryInfo) {

		StatusInfo statusInfo = null;
		try {

			if (null == categoryInfo.getCatName()
					|| categoryInfo.getCatName().isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATNAME_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}
			if (null == categoryInfo.getCatWord()
					|| categoryInfo.getCatWord().isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATWORD_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<String> words = twitterDoa.retriveWordsForCatName(categoryInfo
					.getCatName());
			if (null == words || words.isEmpty()) {
				statusInfo = twitterDoa.insertCategoryBlock(categoryInfo);
				return statusInfo;
			}

			if (words.contains(categoryInfo.getCatWord())) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			statusInfo = twitterDoa.insertCategoryBlock(categoryInfo);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}

	}

	@Override
	public List<CategoryInfo> viewCatWords() {
		List<CategoryInfo> hashT = null;
		try {
			hashT = twitterDoa.retriveAllCategory();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public StatusInfo removeCatWord(CategoryInfo categoryInfo) {
		StatusInfo statusInfo = null;
		try {

			if (null == categoryInfo.getCatName()
					|| categoryInfo.getCatName().isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATNAME_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}
			if (null == categoryInfo.getCatWord()
					|| categoryInfo.getCatWord().isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATWORD_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<String> words = twitterDoa.retriveWordsForCatName(categoryInfo
					.getCatName());
			if (null == words || words.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.NOWORDS);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			if (!words.contains(categoryInfo.getCatWord())) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.CATWORD_DOESNOTEXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			statusInfo = twitterDoa.removeCategory(categoryInfo);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
	}

	@Override
	public StatusInfo doProbability() {

		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			// Delete the Probabilities
			statusInfo = twitterDoa.deleteAllProbabilities();

			if (!statusInfo.isStatus()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.DELETE_PROBABILITIES);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			// Obtain All the Clean Tweets

			List<TweetInfo> tweetInfoList = twitterDoa
					.retriveAllCleanTweetInfo();

			if (null == tweetInfoList || tweetInfoList.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.CLEAN_TWEET_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<String> uniqueCategories = twitterDoa.retriveDistinctCatInfo();

			if (null == uniqueCategories || uniqueCategories.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo.setErrMsg(TwitterConstants.Message.CATEGORIES_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<ProbabilityInfo> probabilityInfoList = new ArrayList<ProbabilityInfo>();

			for (TweetInfo tweet : tweetInfoList) {

				for (String categoryInfoTemp : uniqueCategories) {

					StringTokenizer tok1 = new StringTokenizer(
							tweet.getTweetDesc(), TwitterConstants.Keys.SPACE);

					List<String> keyWordsCat = twitterDoa
							.retriveKeywordsForCatName(categoryInfoTemp);

					// Computing the Probability of the Tweet for Specfic
					// Category
					String tokenName;
					int count = 0;
					int wordCount = 0;
					while (tok1.hasMoreTokens()) {
						tokenName = tok1.nextToken();
						tokenName = tokenName.toLowerCase();
						if (keyWordsCat.contains(tokenName)) {
							count = count + 1;
						}
						wordCount = wordCount + 1;
					}

					ProbabilityInfo probabilityInfo = new ProbabilityInfo();
					probabilityInfo.setCatName(categoryInfoTemp);
					probabilityInfo.setCount(count);
					probabilityInfo.setTotalWords(wordCount);
					// Because Infinity Can H
					if (wordCount == 0) {
						wordCount = -1;
					}
					double probability = (double) (double) count
							/ (double) wordCount;
					probabilityInfo.setProbability(probability);
					double negativeProbability = 1 - probability;
					probabilityInfo.setNegativeProbability(negativeProbability);
					probabilityInfo.setTweetId(tweet.getTweetId());
					probabilityInfoList.add(probabilityInfo);
				}
			}

			if (null == probabilityInfoList || probabilityInfoList.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.PROBABILITY_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			if (probabilityInfoList != null && !probabilityInfoList.isEmpty()) {

				for (ProbabilityInfo probabilityInfo : probabilityInfoList) {
					statusInfo = new StatusInfo();
					statusInfo = twitterDoa.insertProbability(probabilityInfo);
					if (!statusInfo.isStatus()) {
						statusInfo = new StatusInfo();
						statusInfo
								.setErrMsg(TwitterConstants.Message.PROBABILITY_INSERT_FAILURE);
						statusInfo.setStatus(false);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		return statusInfo;

	}

	@Override
	public List<ProbabilityInfo> viewProbability() {
		List<ProbabilityInfo> hashT = null;
		try {
			hashT = twitterDoa.retriveAllProbability();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public StatusInfo classifyTweet() {
		StatusInfo statusInfo = null;
		try {

			statusInfo = new StatusInfo();

			statusInfo = twitterDoa.deleteAllClassifier();

			if (!statusInfo.isStatus()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.COULD_NOT_DELETE_OLD_CLASS_INFO);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<EnhanceContigency> contigencyInfos = twitterDoa
					.retriveAllEnhanceContigency();

			if (null == contigencyInfos || contigencyInfos.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.PLEASE_PERFORM_ENHNACE_CONTIGENCY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			// Obtain Distinct Tweets from Comtigency Table
			List<Integer> distinctTweetIds = twitterDoa
					.retriveDistinctTweetIdsFromEnhanceContigency();

			if (null == distinctTweetIds || distinctTweetIds.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.NO_TWEETS_ENHANCECONTIGENCY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<ClassifierInfo> classifierInfo = new ArrayList<ClassifierInfo>();

			for (Integer tweetId : distinctTweetIds) {

				List<PartialClassifierInfo> classInfo = twitterDoa
						.retriveClassifierByRank(tweetId);

				if (classInfo != null && !classInfo.isEmpty()) {

					double probabilityPositive = 0;
					double probabilityNegative = 0;

					for (int i = 0; i < classInfo.size(); i++) {

						PartialClassifierInfo pcI = classInfo.get(i);

						if (i == 0) {
							ClassifierInfo classifierInfoTemp = new ClassifierInfo();
							classifierInfoTemp.setCatName(pcI.getCatName());
							classifierInfoTemp.setTweetId(pcI.getTweetId());
							classifierInfo.add(classifierInfoTemp);
							probabilityPositive = pcI.getPositiveRatio();
							probabilityNegative = pcI.getNegativeRatio();
						} else {
							double PP = pcI.getPositiveRatio();
							double np = pcI.getNegativeRatio();

							if (PP == probabilityPositive
									&& np == probabilityNegative) {

								ClassifierInfo classifierInfoTemp = new ClassifierInfo();
								classifierInfoTemp.setCatName(pcI.getCatName());
								classifierInfoTemp.setTweetId(pcI.getTweetId());
								classifierInfo.add(classifierInfoTemp);

							}
						}

					}
				}

			}

			// Now the List of Classification is Fed Into Database
			if (null == classifierInfo || classifierInfo.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.CLASSIFY_NOT_POSSIBLE_AT_THIS_TIME);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			// Now Fed into the Classifier Information
			for (ClassifierInfo ci : classifierInfo) {

				StatusInfo statusInfo2 = twitterDoa.insertClassfierInfo(ci);

				if (!statusInfo2.isStatus()) {

					statusInfo = new StatusInfo();
					statusInfo
							.setErrMsg(TwitterConstants.Message.PATIAL_CLASSIFICATION_DONE);
					statusInfo.setStatus(false);
					return statusInfo;
				}

			}

			statusInfo = new StatusInfo();
			statusInfo.setStatus(true);

		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public List<ClassifierInfo> viewClassifier() {
		List<ClassifierInfo> hashT = null;
		try {
			hashT = twitterDoa.retriveAllClassifierInfo();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public List<CategoryCountVO> viewClassifierCount() {

		List<CategoryCountVO> hashT = null;
		try {
			hashT = twitterDoa.viewClassifierCount();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;

	}

	@Override
	public StatusInfo doContigency() {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			statusInfo = new StatusInfo();
			// Delete the Probabilities
			statusInfo = twitterDoa.deleteAllContigency();
			if (!statusInfo.isStatus()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.DELETE_CONTIGENCY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			// Now Obtain the List of Probabilities
			List<ProbabilityInfo> probabilityInfos = twitterDoa
					.retriveAllProbability();
			if (null == probabilityInfos || probabilityInfos.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.NOPROBABILITIES_FOUND);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<ContigencyInfo> contigencyInfos = new ArrayList<ContigencyInfo>();
			// Now Loop through each and determine the Contigency Information
			for (ProbabilityInfo probabilityInfo : probabilityInfos) {
				// Create a Contigency Information and get back
				ContigencyInfo contigencyInfo = new ContigencyInfo();

				contigencyInfo.setTweetId(probabilityInfo.getTweetId());
				contigencyInfo.setNegativeProbability(probabilityInfo
						.getNegativeProbability());
				contigencyInfo.setProbability(probabilityInfo.getProbability());
				contigencyInfo.setCatName(probabilityInfo.getCatName());

				ContigencyInfo contigencyInfoTemp = twitterDoa
						.findTotalPosOthersAnsTotalNegativeOthers(
								probabilityInfo.getTweetId(),
								probabilityInfo.getCatName());

				if (null == contigencyInfoTemp) {
					statusInfo = new StatusInfo();
					statusInfo
							.setErrMsg(TwitterConstants.Message.CONTIGENCY_COULD_NOT_FOUND);
					statusInfo.setStatus(false);
					return statusInfo;
				}

				contigencyInfo.setTotalNegativeOthers(contigencyInfoTemp
						.getTotalNegativeOthers());
				contigencyInfo.setTotalPositiveOthers(contigencyInfoTemp
						.getTotalPositiveOthers());

				contigencyInfos.add(contigencyInfo);

			}

			// Now Insert the Contigency into the Database
			for (ContigencyInfo contigencyInfo : contigencyInfos) {
				StatusInfo statusInfo5 = twitterDoa
						.insertContigency(contigencyInfo);
				if (!statusInfo5.isStatus()) {
					statusInfo = new StatusInfo();
					statusInfo
							.setErrMsg(TwitterConstants.Message.CONTIGENCY_COULD_NOT_INSERT);
					statusInfo.setStatus(false);
					return statusInfo;
				}

			}

		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public List<EnhanceContigency> viewEnhanceContigency() {
		List<EnhanceContigency> enhanceContigencyList = null; 
		try {
			enhanceContigencyList = twitterDoa.retriveAllEnhanceContigency();
			if (null == enhanceContigencyList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return enhanceContigencyList;
	}

	@Override
	public StatusInfo doEnhanceContigency() {
		StatusInfo statusInfo = null;
		try {

			StatusInfo statusInfo2 = twitterDoa.deleteAllEnhanceContigency();

			if (!statusInfo2.isStatus()) {
				statusInfo
						.setErrMsg(TwitterConstants.Message.DELETE_ENHANCE_CONTIGENCY_FAILED);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			List<ContigencyInfo> contigencyInfos = twitterDoa
					.retriveAllContigency();

			if (null == contigencyInfos || contigencyInfos.isEmpty()) {
				statusInfo = new StatusInfo();
				statusInfo
						.setErrMsg(TwitterConstants.Message.PLEASE_PERFORM_CONTIGENCY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			for (ContigencyInfo contigencyInfo : contigencyInfos) {
				EnhanceContigency enhanceContigency = new EnhanceContigency();

				enhanceContigency.setTweetId(contigencyInfo.getTweetId());
				enhanceContigency.setCatName(contigencyInfo.getCatName());
				enhanceContigency.setPositiveCatRatio(contigencyInfo
						.getProbability()
						+ contigencyInfo.getTotalNegativeOthers());
				enhanceContigency.setOtherCatRatio(contigencyInfo
						.getNegativeProbability()
						+ contigencyInfo.getTotalPositiveOthers());

				statusInfo = twitterDoa
						.insertEhanceContigency(enhanceContigency);

				if (!statusInfo.isStatus()) {
					statusInfo
							.setErrMsg(TwitterConstants.Message.ENHANCE_CONTIGENCY_INSERT_FAILED);
					statusInfo.setStatus(false);
					return statusInfo;
				}

			}

			statusInfo.setStatus(true);

		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public List<ContigencyInfo> viewContigency() {
		List<ContigencyInfo> contigencyInfoList = null; 
		try {
			contigencyInfoList = twitterDoa.retriveAllContigency();
			if (null == contigencyInfoList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return contigencyInfoList;
	}
}
