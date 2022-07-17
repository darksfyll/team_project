package com.laptopzone.service;

import java.util.ArrayList;

import com.laptopzone.dao.ProductDao;
import com.laptopzone.dto.Pagination;
import com.laptopzone.dto.ProductDto;

public class ProductService {

	private static final int listSize = 10;
	private static final int paginationSize = 3;

	// 전체 카테고리 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new ProductDao().getNumRecords();
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

	// 특정 카테고리 페이지네이션
	public ArrayList<Pagination> getPagination(int pageNum, String category) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new ProductDao().getNumRecords(category);
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

	// 검색목록 페이지네이션
	public ArrayList<Pagination> getSearchPagination(int pageNum, String productName) {
		ArrayList<Pagination> pgnList = new ArrayList<Pagination>();
		String searchProduct = "%" + productName + "%";

		// 게시판의 전체 게시글 수를 담는다
		int numRecords = new ProductDao().getSearchRecords(searchProduct);
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

	// 제품 목록
	public ArrayList<ProductDto> getProductList(int pageNum) {
		return new ProductDao().productList((pageNum - 1) * listSize, listSize);
	}

	// 특정 카테고리 제품 목록
	public ArrayList<ProductDto> getProductList(int pageNum, String productCategory) {
		return new ProductDao().productList((pageNum - 1) * listSize, listSize, productCategory);
	}

	// 제품 전체 가격순, 인기순 정렬
	public ArrayList<ProductDto> getSortList(int pageNum, String where, String order) {
		return new ProductDao().sortList((pageNum - 1) * listSize, listSize, where, order);
	}

	// 특정 카테고리 가격순, 인기순 정렬
	public ArrayList<ProductDto> getSortList(int pageNum, String where, String productCategory, String order) {
		return new ProductDao().sortList((pageNum - 1) * listSize, listSize, where, productCategory, order);
	}

	// 제품상세
	public ProductDto getProductDetail(int productNum) {
		ProductDto dto = new ProductDao().productDetail(productNum, true);
		return dto;
	}

	// 메인 페이지 제품 이미지
	public ArrayList<ProductDto> getImage() {
		return new ProductDao().imageList();
	}

	// 제품등록
	public void getInsertProduct(String productCategory, String productName, String productCompany, int productPrice,
			int productStock, String productDetail, String productImage) {

		ProductDto dto = new ProductDto();
		dto.setProductCategory(productCategory);
		dto.setProductName(productName);
		dto.setProductCompany(productCompany);
		dto.setProductPrice(productPrice);
		dto.setProductStock(productStock);
		dto.setProductDetail(productDetail);
		dto.setProductImage(productImage);

		new ProductDao().insertProduct(dto);
	}

	// 제품 등록 페이지
	public ProductDto getWriteProduct(int productNum) {
		ProductDto dto = new ProductDao().productDetail(productNum, false);
		return dto;
	}

	// 제품수정
	public void getUpdateProduct(String productCategory, String productName, String productCompany, int productPrice,
			int productStock, String productDetail, String productImage, int productNum) {

		ProductDto dto = new ProductDto();
		dto.setProductCategory(productCategory);
		dto.setProductName(productName);
		dto.setProductCompany(productCompany);
		dto.setProductPrice(productPrice);
		dto.setProductStock(productStock);
		dto.setProductDetail(productDetail);
		dto.setProductImage(productImage);
		dto.setProductNum(productNum);

		new ProductDao().updateProduct(dto, productNum);
	}

	// 제품삭제
	public void getDeleteProduct(int productNum) {
		new ProductDao().deleteProduct(productNum);
	}

	// 제품 검색
	public ArrayList<ProductDto> getSearchProduct(int pageNum, String productName) {
		String searchProduct = "%" + productName + "%";
		return new ProductDao().searchProduct((pageNum - 1) * listSize, listSize, searchProduct);
	}
	
	//검색 정렬
	public ArrayList<ProductDto> getSearchProduct(int pageNum, String productName, String where, String order){
		String searchProduct = "%" + productName + "%";
		return new ProductDao().searchProduct((pageNum - 1) * listSize, listSize, searchProduct, where, order);
	}

}
