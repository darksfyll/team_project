package com.laptopzone.service;

import java.util.ArrayList;

import com.laptopzone.dao.ProductDao;
import com.laptopzone.dao.ReviewDao;
import com.laptopzone.dto.Pagination;
import com.laptopzone.dto.ReviewDto;


public class ReviewService {
	
	private static final int listSize = 10;
	private static final int paginationSize = 3;

	// 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum, int productNum) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new ReviewDao().getNumRecords(productNum);
		// 게시글 리스트를 표시하는데 총 몇 페이지가 필요한지 계산
		int numPages = (int) Math.ceil((double) numRecords / listSize);

		// 컨트롤의 첫 번째 숫자를 계산
		int firstLink = ((pageNum - 1) / paginationSize) * paginationSize + 1;

		// 컨트롤의 마지막 번호를 계산
		int lastLink = firstLink + paginationSize - 1;
		if (lastLink > numPages) {
			lastLink = numPages;
		}
		// 컨트롤 데이터를 ArrayList에 담는다
		if (firstLink > 1) {
			pgnList.add(new Pagination("이전", pageNum - paginationSize, false));
		}

		for (int i = firstLink; i <= lastLink; i++) {
			pgnList.add(new Pagination("" + i, i, i == pageNum));
		}

		if (lastLink < numPages) {
			int tmpPageNum = pageNum + paginationSize;
			if (tmpPageNum > numPages) {
				tmpPageNum = numPages;
			}
			pgnList.add(new Pagination("다음", tmpPageNum, false));
		}

		return pgnList;
	}

	//리뷰 목록
	public ArrayList<ReviewDto> getReviewList(int productNum, int pageNum){
		return new ReviewDao().reviewList(productNum, (pageNum - 1) * listSize, listSize);
	}
	
	//리뷰 작성
	public void getInsertReview(int productNum, String reviewTitle, String reviewWriter, String reviewContent) {
		ReviewDto dto = new ReviewDto();
		dto.setProductNum(productNum);
		dto.setReviewTitle(reviewTitle);
		dto.setReviewWriter(reviewWriter);
		dto.setReviewContent(reviewContent);
		//dto.setReviewImage(reviewImage);
		
		new ReviewDao().insertReview(dto);	
	}
	
	//리뷰 조회
	public ReviewDto getSelectReview(int reviewNum) {
		ReviewDto dto = new ReviewDao().selectReview(reviewNum, true);
		dto.setReviewTitle(dto.getReviewTitle().replace(" ", "&nbsp;"));
		dto.setReviewContent(dto.getReviewContent().replace(" ", "&nbsp;").replace("\n", "<br>"));
		
		return dto;
	}
	
	//리뷰 작성 페이지
	public ReviewDto getSelectReviewUpdate(int reviewNum) {
		return new ReviewDao().selectReview(reviewNum, false);
	}
	
	//리뷰 수정
	public void getUpdateReview(int reviewNum, String reviewTitle, String reviewContent) {
		ReviewDto dto = new ReviewDto();
		dto.setReviewNum(reviewNum);
		dto.setReviewTitle(reviewTitle);
		dto.setReviewContent(reviewContent);
		//dto.setReviewImage(reviewImage);
		
		new ReviewDao().updateReview(dto);	
	}
	
	//리뷰 삭제
	public void getDeleteReview(int reviewNum) {
		new ReviewDao().deleteReview(reviewNum);
	}
	
}
