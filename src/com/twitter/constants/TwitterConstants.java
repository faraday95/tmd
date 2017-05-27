package com.twitter.constants;

public interface TwitterConstants {

	interface Message {

		String EMPTY_STOPWORD = "Stopword Cannot be Empty";
		String STOPWORD_ADD_FAILED = "Stopword Could not be Added";
		String STOPWORD_ADD_SUCESS = "Stopword Added Sucessfully";
		String INTERNAL_ERROR = "An Internal Error Ocurred.Please Contact System Administrator";
		String EMPTY_STOPWORDS = "No Stopwords Found";
		String STOPWORD_SUCESS = "Stopwords Retrived Sucessfully";
		String STOPWORD_EXIST = "Stopword Already Exist";
		String STOPWORD_REMOVE_SUCESS = "Stopword Removed Sucessfully";
		String STOPWORD_REMOVE_FAILED = "Failed to Remove Stopword";
		String NO_STOPWORD_EXIST = "No Stopword Exist";
		String EMPTY_APIKEY = "API Key is Empty";
		String EMPTY_SECRETKEY = "Secret Key is Empty";
		String EMPTY_USERID = "User Id is Empty";
		String USERADDITION_FAILED = "Could not Add User Id";
		String USERID_EXIST = "User Id Already Exist";
		String ADDUSERINFO_SUCESS = "UserId Added Sucessfully";
		String ADDUSERINFO_FAILURE = "User Id could not be Added";
		String APIKEY_INVALID = "API Key is Invalid";
		String SECRETKEY_INVALID = "Secret Key is Invalid";
		String USERID_INVALID = "User ID is Invalid";
		String UPDATEUSERINFO_SUCESS = "User ID Updated Sucessfully";
		String UPDATEUSERINFO_FAILURE = "User Id Could not be Updated";
		String USERID_DOESNOTEXIST = "User Id does not Exist";
		String USERINFO_REMOVE_SUCESS = "User Id Removed Sucessfully";
		String USERINFO_REMOVE_FAILED = "Could not Remove the User Information";
		String USERID_NOT_EXIST = "User ID does not Exist";
		String EMPTY_USERINFO = "No User Information exist";
		String USERINFO_SUCESS = "User Information retrived Sucessfully";
		String EMPTY_TWEETINFO = "Tweets Are Empty";
		String RETRIVE_TWEETS_FAILED = "Could not Retrive Tweets from the Time Line or No Users Might Exist in the System";
		String RETRIVE_TWEETS_SUCESS = "All the Tweets for the  Problems have been Retrived From Twitter.";
		String NO_USERS_FOUND = "No users have been Found in the Project. Please Configure the Users First";
		String HASHTAG_ADD_FAILED = "Failed To Add Hash Tag";
		String EMPTY_HASHTAG = "Hash Tag Cannot be Empty";
		String HASHTAG_ADD_SUCESS = "HashTag Added Sucessfully";
		String HASHTAG_INVALID = "Hash Tag is Invalid";
		String HASHTAG_EXIST = "Hash Tag Already exist";
		String EMPTY_HASHTAGS = "Could not Find Any Hash Tags";
		String HASHTAGS_SUCESS = "Hash Tags Retrived Sucessfully";
		String HASHTAGS_EMPTY = "There are no Hash Tags in the System";
		String EMPTY_TWEETS = "Tweets could not be Retrived";
		String TWEETS_SUCESS = "Retrival of Tweets is Sucessful";
		String REMOVE_NOISE_FAILED = "Noise Could not be removed from Tweets";
		String NOISEREDUCE_FAILED = "Noise Could not be removed";
		String EMPTY_CLEANTWEETS = "Clean Tweets are Empty";
		String EMPTY_TABLE = "Table Data Cannot be Empty";
		String TABLEDATA_REMOVE_FAILED = "The Data Could not be Removed at this point of Time";
		String TABLEDATA_REMOVE_SUCESS = "The Data has been Removed Sucessfuly";
		String RETRIVE_CLEANTWEETS_SUCESS = "All the Tweets for the  Problems have been Cleaned Sucessfully";
		String CATNAME_EMPTY = "Category Name Cannot be Empty";
		String CATWORD_EMPTY = "Category Word Cannot be Empty";
		String EMPTY_CATWORDS = "No Category Words Found";
		String CATWORDS_SUCESS = "Category Words Retrived Sucessfully";
		String EMPTY_CATWORD = "Category Word cannot be Empty Please Add";
		String CATWORD_ADD_FAILED = "Category Word Could not be Added";
		String CATWORD_ADD_SUCESS = "Category Word Added Sucessfully";
		String CATWORD_EXIST = "Similar Word Exist in the Category";
		String CATWORD_REMOVAL_FAILED = "Category Word Cannot be Removed";
		String NOWORDS = "No Words  are Existing in the Database";
		String CATWORD_DOESNOTEXIST = "No Such Word Exist";
		String CATWORD_REMOVE_SUCESS = "Category Word Removal was Sucessful";

		String CLEAN_TWEET_EMPTY = "Clean Tweets are Empty";
		String CATEGORIES_EMPTY = "Categories are Empty";
		String DELETE_PROBABILITIES = "Delete All Probabilities is not Sucessful";
		String PROBABILITY_EMPTY = "Probability Could not be Computed for Categories";
		String PROBABILITY_INSERT_FAILURE = "Insertion of Probability has Failed";
		String PROBABILITY_COMPUTATION_SUCESS = "Probability Computation is Sucessfull";
		String DELETE_CONTIGENCY = "Deletion of Contigency Failed";
		String NOPROBABILITIES_FOUND = "Probability Computatuon was not sucessful";
		String CLASSIFY_FAILED = "Classification of Tweets Failed";
		String CLASSIFY_SUCESS = "Classification of Tweets is Sucessful";
		String PLEASE_PERFORM_CONTIGENCY = "Please Perform Contigency before coming to this step";
		String NO_TWEETS_CONTIGENCY = "No Tweets Found Under Contigency";
		String CLASSIFY_NOT_POSSIBLE_AT_THIS_TIME = "Classification is not Possible at this point of Time";
		String PATIAL_CLASSIFICATION_DONE = "Partial Classification is done";
		String COULD_NOT_DELETE_OLD_CLASS_INFO = "Could not delete Old Classification Information";
		String EMPTY_CLASSIFIER = "Empty Classifier Information";
		String EMPTY_CLASSIFIER_COUNT = "Classifier Count is Empty";
		String CLASSIFY_COUNT_SUCESS = "Classifier COunt is Sucessfully";
		String PLEASE_PERFORM_PROBABILITY = "Please Perform Probability";
		String NO_TWEETS_PROBABILITY = "No Tweets Probability";
		String CONTIGENCY_COMPUTATION_SUCESS = "Contigency Computation is sucessful";
		String ENHANCECONTIGENCY_COULD_NOT_FOUND = "Could not Find Enhanced Contigency";
		String ENHANCE_CONTIGENCY_COMPUTATION_SUCESS = "Ehanced Contigency Computation is Sucessful";
		String EMPTY_ENHANCECONTIGENCY = "Enhance Contigency is Empty";
		String EMPTY_CONTIGENCY = "Contigency Information is Empty";
		String CONTIGENCY_COULD_NOT_FOUND = "Contigency Computation Could not be Found";
		String CONTIGENCY_COULD_NOT_INSERT = "Contigency Could not be Inserted";
		String DELETE_ENHANCE_CONTIGENCY_FAILED = "Deletion of Enhance Contigency is Sucessful";
		String ENHANCE_CONTIGENCY_INSERT_FAILED = "Enhance Contigency insertion has Failed";
		String PLEASE_PERFORM_ENHNACE_CONTIGENCY = "Please Perform Enhance Contigency";
		String NO_TWEETS_ENHANCECONTIGENCY = "No Tweets in Enhanced Contigency";
		String EMPTY_PROBABILITY = "Empty Probability";
		String CONTIGENCY_COMPUTATION_FAILED = "Contigency Computation Failed";
		String CONTIGENCY_SUCESS = "Contigency Retrival is Sucessful";

	}

	interface Views {

		String STOPWORD_INPUT = "addStopword";
		String VIEW_SUCESS_PAGE = "sucess";
		String REMOVESTOPWORD_INPUT = "removeStopword";
		String ADDUSERINFO_INPUT = "userinfo";
		String UPDATEUSERINFO_INPUT = "updateuserinfo";
		String DELETEUSERINFO_INPUT = "deleteuserinfo";
		String RETRIVETWEETS_VIEW = "tweetsubmission";
		String HASHTAG_INPUT = "hashtagsubmission";
		String NOISEREDUCTION_VIEW = "noisereduction";
		String DELETEDATA_INPUT_VIEW = "deleteData";
		String CATWORD_INPUT_VIEW = "addcatword";
		String REMOVECATWORD_INPUT_VIEW = "removeCatWord";
		String ERROR_VIEW = "error";

	}

	interface Keys {

		String OBJ = "obj";
		String STOPWORD_COL = "stopword";
		String USERIDKEY = "userId";
		String SECRETKEY = "secretKey";
		String APIKEY = "apiKey";

		String TWEETDESCKEY = "tweetDesc";
		String TWEET_LANG_KEY = "language";
		String TWEET_HASHTAG_KEY = "hashTag";
		String TWEET_SCREENNAME_KEY = "screenName";
		String TWEET_DESC_KEY = "tweetDesc";
		String TWEET_USERID_KEY = "userId";
		String STOPWORDS_SYMBOL = "[^A-Za-z]";
		String SPACE = " ";
		String HASH_SYMBOL = "#";
		String QUESTION_SYMBOL = "?";
		String HTTP_SYMBOL = "http://";
		String HTTPS_SYMBOL = "https://";
		String SLASH_SYMBOL = "/";
		String DOT_SYMBOL = ".";
		String CATNAME_KEY = "catName";
		String CATWORD_KEY = "catWord";
	}

	interface SQLS {

		String RETRIVE_STOPWORDS_SQL = "RETRIVE_STOPWORDS_SQL";
		String INSERT_STOPWORD_SQL = "INSERT_STOPWORD_SQL";
		String RETRIVE_STOPWORDS_FULL_SQL = "RETRIVE_STOPWORDS_FULL_SQL";
		String REMOVE_STOPWORD_SQL = "REMOVE_STOPWORD_SQL";
		String INSERT_USERINFO_SQL = "INSERT_USERINFO_SQL";
		String UPDATE_USERINFO_SQL = "UPDATE_USERINFO_SQL";
		String REMOVE_USERINFO_SQL = "REMOVE_USERINFO_SQL";
		String RETRIVE_USERINFO_FULL_SQL = "RETRIVE_USERINFO_FULL_SQL";
		String RETRIVE_USERINFO_FOR_USERID_SQL = "RETRIVE_USERINFO_FOR_USERID_SQL";
		String INSERT_TWEETINFO_SQL = "INSERT_TWEETINFO_SQL";
		String UPDATE_TWEETINFO_SQL = "UPDATE_TWEETINFO_SQL";
		String REMOVE_TWEETINFO_SQL = "REMOVE_TWEETINFO_SQL";
		String RETRIVE_TWEETINFO_FULL_SQL = "RETRIVE_TWEETINFO_FULL_SQL";
		String RETRIVE_TWEETINFO_FOR_USERID_SQL = "RETRIVE_TWEETINFO_FOR_USERID_SQL";
		String RETRIVE_USERIDS_SQL = "RETRIVE_USERIDS_SQL";
		String RETRIVE_HASHTAGS_SQL = "RETRIVE_HASHTAGS_SQL";
		String INSERT_HASHTAG_SQL = "INSERT_HASHTAG_SQL";
		String RETRIVE_HASHTAGS_FULL_SQL = "RETRIVE_HASHTAGS_FULL_SQL";
		String INSERT_CLEANTWEETINFO_SQL = "INSERT_CLEANTWEETINFO_SQL";
		String REMOVE_ALL_CLEANTWEETS_SQL = "REMOVE_ALL_CLEANTWEETS_SQL";
		String RETRIVE_CLEANTWEETINFO_FULL_SQL = "RETRIVE_CLEANTWEETINFO_FULL_SQL";
		String REMOVE_ALL_TWEETS_SQL = "REMOVE_ALL_TWEETS_SQL";
		String REMOVE_ALL_HASHTAGS_SQL = "REMOVE_ALL_HASHTAGS_SQL";
		String INSERT_CATINFO_SQL = "INSERT_CATINFO_SQL";
		String RETRIVE_CATINFO_SQL = "RETRIVE_CATINFO_SQL";
		String RETRIVE_CATWORDS_FOR_CATNAMESQL = "RETRIVE_CATWORDS_FOR_CATNAMESQL";
		String REMOVE_CATINFO_FOR_CATWORD_CATNAME_SQL = "REMOVE_CATINFO_FOR_CATWORD_CATNAME_SQL";
		String REMOVE_ALL_PROBABILITIES_SQL = "REMOVE_ALL_PROBABILITIES_SQL";
		String RETRIVE_DISTINCT_CATNAME_SQL = "RETRIVE_DISTINCT_CATNAME_SQL";
		String RETRIVE_KEYWORDS_FOR_CAT_SQL = "RETRIVE_KEYWORDS_FOR_CAT_SQL";
		String INSERT_PROBABILITY_INFO_SQL = "INSERT_PROBABILITY_INFO_SQL";
		String RETRIVE_PROBABILITY_FULL_SQL = "RETRIVE_PROBABILITY_FULL_SQL";
		String REMOVE_ALL_CONTIGENCY_SQL = "REMOVE_ALL_CONTIGENCY_SQL";
		String INSERT_CONTIGENCY_INFO_SQL = "INSERT_CONTIGENCY_INFO_SQL";
		String RETRIVE_CONTIGENCY_FULL_SQL = "RETRIVE_CONTIGENCY_FULL_SQL";
		String RETRIVE_DISTINCT_TWEETIDS_FROM_CONTGENCY_SQL = "RETRIVE_DISTINCT_TWEETIDS_FROM_CONTGENCY_SQL";
		String INSERT_ENHANCE_CONTIGENCY_INFO_SQL = "INSERT_ENHANCE_CONTIGENCY_INFO_SQL";
		String RETRIVE_ENHANCECONTIGENCY_FULL_SQL = "RETRIVE_ENHANCECONTIGENCY_FULL_SQL";
		String REMOVE_ALL_ENHANCECONTIGENCY_SQL = "REMOVE_ALL_ENHANCECONTIGENCY_SQL";
		String RETRIVE_DISTINCT_TWEETIDS_FROM_ENHANCECONTGENCY_SQL = "RETRIVE_DISTINCT_TWEETIDS_FROM_ENHANCECONTGENCY_SQL";
		String RETRIVE_DISTINCT_CLASSIFIER_FROM_CONTGENCY_SQL = "RETRIVE_DISTINCT_CLASSIFIER_FROM_CONTGENCY_SQL";
		String INSERT_CLASSIFIER_INFO_SQL = "INSERT_CLASSIFIER_INFO_SQL";
		String REMOVE_ALL_CLASSIFIER_SQL = "REMOVE_ALL_CLASSIFIER_SQL";
		String RETRIVE_CLASSINFO_FULL_SQL = "RETRIVE_CLASSINFO_FULL_SQL";
		String RETRIVE_RANKED_CLASSIFIER_FROM_CONTGENCY_WHERE_TWEETID_SQL = "RETRIVE_RANKED_CLASSIFIER_FROM_CONTGENCY_WHERE_TWEETID_SQL";
		String RETRIVE_CLASSIFYCOUNT_FULL_SQL = "RETRIVE_CLASSIFYCOUNT_FULL_SQL";
		String RETRIVE_DISTINCT_TWEETIDS_FROM_PROBABILITY_SQL = "RETRIVE_DISTINCT_TWEETIDS_FROM_PROBABILITY_SQL";
		String RETRIVE_RANKED_CLASSIFIER_FROM_PROBABILITY_WHERE_TWEETID_SQL = "RETRIVE_RANKED_CLASSIFIER_FROM_PROBABILITY_WHERE_TWEETID_SQL";

	}

	interface DatabaseColumns {

		String STOPWORDID_COL = "STOPWORDID";
		String STOPWORD_COL = "STOPWORD";
		String USERID_COL = "USERID";
		String SECRETKEY_COL = "SECRETKEY";
		String APPKEY_COL = "APIKEY";
		String TWEETDESC_COL = "TWEETDESC";
		String HASHTAGID_COL = "HASHTAGID";
		String HASHTAG_COL = "HASHTAG";
		String TWEETID_COL = "TWEETID";
		String TWEETLANGUAGE_COL = "LANGUAGE";
		String TWEETSCREEN_COL = "TWEETSCREENNAME";
		String CATID_COL = "CATID";
		String CATNAME_COL = "CATNAME";
		String CATWORD_COL = "CATKEYWORD";

	}

	interface TweetOAuthConstants {

		String OAUTHKEY1 = "GXvmth0FawnOZS1g8JlrWK54Q";
		String OAUTHKEY2 = "wnR1pVIsOaPnLzJZLi2cYUD505SHkHp7AzXHIJGYxlIJzi468A";

		String APIKEY = "048908211-qD3Dxr5hk0hZEQC7jqUkNStc04C3F1XtjcJhjVH";
		String SECRETKEY = "3btbAZ2Pd4UCSE1mlE1i46NqkOsydK5UBnAxHGs67jXns";
		int TWEETS_NOS = 10;

	}

	interface TableNames {

		String TWEET = "tweet";
		String CLEANTWEETS = "cleantweet";
		Object HASHTAGS = "hashtag";

	}

}
