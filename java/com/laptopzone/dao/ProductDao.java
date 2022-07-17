package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.laptopzone.dto.ProductDto;

public class ProductDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 전체 개수 얻기
	public int getNumRecords() {
		int numRecords = 0;
		String query = "select count(*) from product";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				numRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return numRecords;
	}

	// 특정 카테고리 제품 개수 얻기
	public int getNumRecords(String category) {
		int numRecords = 0;
		String query = "select count(*) from product where product_category = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				numRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return numRecords;
	}

	// 제품 전체 목록
	public ArrayList<ProductDto> productList(int start, int listSize) {

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product order by product_num desc limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	// 특정 카테고리 제품 목록
	public ArrayList<ProductDto> productList(int start, int listSize, String productCategory) {

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product where product_category = ? order by product_num desc limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	// 제품 전체 가격순, 인기순 정렬
	public ArrayList<ProductDto> sortList(int start, int listSize, String where, String order) {

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product order by " + where + " " + order + " limit ?, ?";
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	// 특정 카테고리 가격순, 인기순 정렬
	public ArrayList<ProductDto> sortList(int start, int listSize, String where, String productCategory, String order) {

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product where product_category = ? order by " + where + " " + order
				+ " limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	// 제품 상세
	public ProductDto productDetail(int productNum, boolean incViews) {
		ProductDto dto = new ProductDto();
		String query1 = "select * from product where product_num = ?";
		String query2 = "update product set product_views = product_views + 1 where product_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));
			}

			rs.close();
			pstmt.close();

			if (incViews) {
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, productNum);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;

	}

	// 메인 페이지 제품 이미지
	public ArrayList<ProductDto> imageList() {
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		String query = "SELECT product_image, product_num FROM product ORDER BY product_views desc LIMIT 12";
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductNum(rs.getInt("product_num"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	// 제품등록
	public void insertProduct(ProductDto dto) {
		String query = "insert into product values(product_num, ?, ?, ?, ?, ?, ?, ?, now(), 0)";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getProductCategory());
			pstmt.setString(2, dto.getProductName());
			pstmt.setString(3, dto.getProductCompany());
			pstmt.setInt(4, dto.getProductPrice());
			pstmt.setInt(5, dto.getProductStock());
			pstmt.setString(6, dto.getProductDetail());
			pstmt.setString(7, dto.getProductImage());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 제품수정
	public void updateProduct(ProductDto dto, int productNum) {
		String query = "update product set product_category = ?, product_name = ?, product_company = ?, product_price = ?,"
				+ "product_stock = ?, product_detail = ?, product_image = ? where product_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getProductCategory());
			pstmt.setString(2, dto.getProductName());
			pstmt.setString(3, dto.getProductCompany());
			pstmt.setInt(4, dto.getProductPrice());
			pstmt.setInt(5, dto.getProductStock());
			pstmt.setString(6, dto.getProductDetail());
			pstmt.setString(7, dto.getProductImage());
			pstmt.setInt(8, dto.getProductNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 제품삭제
	public void deleteProduct(int productNum) {
		String query = "delete from product where product_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNum);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 검색한 제품 수
	public int getSearchRecords(String searchProduct) {
		int numRecords = 0;
		String query = "select count(*) from product where product_name like ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchProduct);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				numRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return numRecords;
	}

	// 제품검색
	public ArrayList<ProductDto> searchProduct(int start, int listSize, String searchProduct) {
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product where product_name like ? limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchProduct);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return list;
	}

	//검색 정렬
	public ArrayList<ProductDto> searchProduct(int start, int listSize, String searchProduct, String where, String order) {
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		String query = "select * from product where product_name like ? order by " + where + " " + order + " limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchProduct);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt("product_num"));
				dto.setProductCategory(rs.getString("product_category"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductCompany(rs.getString("product_company"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductStock(rs.getInt("product_stock"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_image"));
				dto.setProductRegdate(rs.getString("product_regdate"));
				dto.setProductViews(rs.getInt("product_views"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return list;
	}

}
