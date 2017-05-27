package com.twitter.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String secretKey;
	
	private String apiKey;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
	private String oauthKey1;
	
	
	public String getOauthKey1() {
		return oauthKey1;
	}

	public void setOauthKey1(String oauthKey1) {
		this.oauthKey1 = oauthKey1;
	}

	public String getOauthKey2() {
		return oauthKey2;
	}

	public void setOauthKey2(String oauthKey2) {
		this.oauthKey2 = oauthKey2;
	}


	private String oauthKey2;

}
