package com.laptopzone.service;

import java.util.ArrayList;


import com.laptopzone.dao.QnADao;
import com.laptopzone.dto.Pagination;
import com.laptopzone.dto.QnADto;

public class QnAService {

	private static final int listSize = 10;
	private static final int paginationSize = 3;

	// 전체 카테고리 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new QnADao().getNumRecords();
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

	// QnA 목록
	public ArrayList<QnADto> getQnaList(int pageNum) {
		return new QnADao().qnaList((pageNum - 1) * listSize, listSize);
	}

	// QnA 조회
	public QnADto getSelectQnA(int qnaNum) {
		QnADto dto = new QnADao().selectQnA(qnaNum, true);
		dto.setQnaTitle(dto.getQnaTitle().replace(" ", "&nbsp;"));
		dto.setQnaContent(dto.getQnaContent().replace(" ", "&nbsp;").replace("\n", "<br>"));

		return dto;
	}

	// QnA 작성페이지
	public QnADto getSelectQnaUpate(int qnaNum) {
		return new QnADao().selectQnA(qnaNum, false);
	}

	// QnA 작성
	public void getInsertQnA(String qnaTitle, String qnaWriter, String qnaContent) {
		QnADto dto = new QnADto();
		dto.setQnaTitle(qnaTitle);
		dto.setQnaWriter(qnaWriter);
		dto.setQnaContent(qnaContent);

		new QnADao().insertQnA(dto);
	}

	// QnA 수정
	public void getUpdateQnA(int qnaNum, String qnaTitle, String qnaContent) {
		QnADto dto = new QnADto();
		dto.setQnaNum(qnaNum);
		dto.setQnaTitle(qnaTitle);
		dto.setQnaContent(qnaContent);

		new QnADao().updateQnA(dto);
	}

	// QnA 삭제
	public void getDeleteQnA(int qnaNum) {
		new QnADao().deleteQnA(qnaNum);
	}

	// QnA 답글 작성
	public void getInsertReply(int parentNum, String qnaTitle, String qnaWriter, String qnaContent) {
		QnADto dto = new QnADto();
		dto.setParentNum(parentNum);
		dto.setQnaTitle(qnaTitle);
		dto.setQnaWriter(qnaWriter);
		dto.setQnaContent(qnaContent);

		new QnADao().insertReply(dto);
	}

}
