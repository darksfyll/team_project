package com.laptopzone.service;

import java.util.ArrayList;

import com.laptopzone.dao.CartDao;
import com.laptopzone.dto.CartDto;
import com.laptopzone.dto.Pagination;

public class CartService {

	private static final int listSize = 10;
	private static final int paginationSize = 3;
	
	// 장바구니 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum, String memberId) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new CartDao().getNumRecords(memberId);
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

	// 장바구니 담기
	public CartDto getAddCart(String memberId, int productNum, int amount) {
		CartDto dto = new CartDto();
		dto.setMemberId(memberId);
		dto.setProductNum(productNum);
		dto.setAmount(amount);

		ArrayList<CartDto> cart = new ArrayList<CartDto>();

		cart.add(dto);

		new CartDao().addCart(dto);

		return dto;
	}

	// 장바구니 목록 페이지네이션
	public ArrayList<CartDto> getCartList(String memberId, int pageNum) {
		return new CartDao().cartList(memberId, (pageNum - 1) * listSize, listSize);
	}
	
	// 장바구니 목록 
		public ArrayList<CartDto> getCartList(String memberId) {
			return new CartDao().cartList(memberId);
		}

	// 장바구니 가격 합계
	public int getSumPrice(String memberId) {
		return new CartDao().sumPrice(memberId);
	}

	// 장바구니 삭제
	public void getDeleteCart(int cartNum) {
		new CartDao().deleteCart(cartNum);
	}

	// 장바구니 삭제
	public void getDeleteCartAll(String memberId) {
		new CartDao().deleteCartAll(memberId);
	}
}
