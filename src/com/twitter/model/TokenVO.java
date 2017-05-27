package com.twitter.model;

public class TokenVO {
	
	private int tokenId;
	
	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}


	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getProductType() {
		return productType;
	}


	private String tokenName;
	
	private int reviewId;
	
	private int productId;
	
	private int productType;
	
	
}
