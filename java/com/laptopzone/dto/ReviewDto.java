package com.laptopzone.dto;

public class ReviewDto {

	private int reviewNum;
	private int productNum;
	private String reviewTitle;
	private String reviewWriter;
	private String reviewContent;
//	private String reviewImage;
	private String reviewRegdate;
	private int reviewViews;
	
//	public String getReviewImage() {
//		return reviewImage;
//	}
//	public void setReviewImage(String reviewImage) {
//		this.reviewImage = reviewImage;
//	}
	public int getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewWriter() {
		return reviewWriter;
	}
	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewRegdate() {
		return reviewRegdate;
	}
	public void setReviewRegdate(String reviewRegdate) {
		this.reviewRegdate = reviewRegdate;
	}
	public int getReviewViews() {
		return reviewViews;
	}
	public void setReviewViews(int reviewViews) {
		this.reviewViews = reviewViews;
	}
	
	
	
}
