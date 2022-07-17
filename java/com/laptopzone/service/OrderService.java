package com.laptopzone.service;

import java.util.ArrayList;

import com.laptopzone.dao.CartDao;
import com.laptopzone.dao.OrderDao;
import com.laptopzone.dto.CartDto;
import com.laptopzone.dto.OrderDto;
import com.laptopzone.dto.Pagination;

public class OrderService {

	private static final int listSize = 10;
	private static final int paginationSize = 3;

	// 장바구니 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum, String memberId) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new OrderDao().getNumRecords(memberId);
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

	// 주문정보입력
	public void getInsertOrder(String memberId, int totalPrice, String receiverName, String receiverPhone,
			String zipcode, String address, String addressDetail, String addressEtc, int productNum, String productName,
			int productPrice, int amount) {

		OrderDto dto = new OrderDto();
		dto.setMemberId(memberId);
		dto.setTotalPrice(totalPrice);
		dto.setReceiverName(receiverName);
		dto.setReceiverPhone(receiverPhone);
		dto.setZipcode(zipcode);
		dto.setAddress(address);
		dto.setAddressDetail(addressDetail);
		dto.setAddressEtc(addressEtc);
		dto.setProductNum(productNum);
		dto.setProductName(productName);
		dto.setProductPrice(productPrice);
		dto.setAmount(amount);

		new OrderDao().insertOrder(dto);
	}

	// 장바구니 주문상세 정보입력
	public void getInsertCartOrder(String memberId, int totalPrice, String receiverName, String receiverPhone,
			String zipcode, String address, String addressDetail, String addressEtc) {

		OrderDto dto = new OrderDto();
		dto.setMemberId(memberId);
		dto.setTotalPrice(totalPrice);
		dto.setReceiverName(receiverName);
		dto.setReceiverPhone(receiverPhone);
		dto.setZipcode(zipcode);
		dto.setAddress(address);
		dto.setAddressDetail(addressDetail);
		dto.setAddressEtc(addressEtc);

		ArrayList<CartDto> cart = new CartDao().cartList(memberId);
		new OrderDao().insertCartOrder(dto, cart);
	}

	// 주문정보
	public OrderDto getOrderInfo(String memberId) {
		OrderDto dto = new OrderDao().orderInfo(memberId);
		return dto;
	}

	// 주문목록
	public ArrayList<OrderDto> getOrderList(String memberId, int pageNum) {
		return new OrderDao().orderList(memberId, (pageNum - 1) * listSize, listSize);
	}
}
