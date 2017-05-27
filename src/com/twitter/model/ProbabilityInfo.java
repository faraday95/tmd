package com.twitter.model;

public class ProbabilityInfo {
	
	private int tweetId;
	
	private String catName;
	
	
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalWords() {
		return totalWords;
	}

	public void setTotalWords(int totalWords) {
		this.totalWords = totalWords;
	}

	
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}


	public double getNegativeProbability() {
		return negativeProbability;
	}

	public void setNegativeProbability(double negativeProbability) {
		this.negativeProbability = negativeProbability;
	}


	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}


	private int count;
	
	private int totalWords;
	
	private double probability;
	
	private double negativeProbability;

}
