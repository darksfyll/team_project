package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.laptopzone.dto.CartDto;

public class CartDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 전체 개수 얻기
	public int getNumRecords(String memberId) {
		int numRecords = 0;
		String query = "select count(*) from cart where member_id = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	// 장바구니 담기
	public void addCart(CartDto dto) {

		String query = "insert into cart values(cart_num, ?, ?, ?)";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, dto.getProductNum());
			pstmt.setInt(3, dto.getAmount());
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

	// 장바구니 목록 페이지네이션
	public ArrayList<CartDto> cartList(String memberId, int start, int listSize) {
		ArrayList<CartDto> list = new ArrayList<CartDto>();
		String query = "SELECT A.cart_num, A.member_id, A.product_num, C.product_name, "
				+ "C.product_price, A.amount, C.product_detail, C.product_regdate, C.product_image "
				+ "FROM cart A, member B, product C WHERE A.member_id = B.member_id "
				+ "AND A.product_num = C.product_num AND B.member_id = ? ORDER BY cart_num DESC limit ?, ?";
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartDto dto = new CartDto();
				dto.setCartNum(rs.getInt("cart_num"));
				dto.setMemberId(rs.getString("member_id"));
				dto.setProductNum(rs.getInt("product_num"));
				dto.setAmount(rs.getInt("amount"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setProductDetail(rs.getString("product_detail"));
				dto.setProductImage(rs.getString("product_Image"));
				dto.setProductRegdate(rs.getString("product_regdate"));

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
	
	// 장바구니 목록
		public ArrayList<CartDto> cartList(String memberId) {
			ArrayList<CartDto> list = new ArrayList<CartDto>();
			String query = "SELECT A.cart_num, A.member_id, A.product_num, C.product_name, "
					+ "C.product_price, A.amount, C.product_detail, C.product_regdate, C.product_image "
					+ "FROM cart A, member B, product C WHERE A.member_id = B.member_id "
					+ "AND A.product_num = C.product_num AND B.member_id = ? ORDER BY cart_num DESC";

			try {
				conn = DBconnector.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					CartDto dto = new CartDto();
					dto.setCartNum(rs.getInt("cart_num"));
					dto.setMemberId(rs.getString("member_id"));
					dto.setProductNum(rs.getInt("product_num"));
					dto.setAmount(rs.getInt("amount"));
					dto.setProductName(rs.getString("product_name"));
					dto.setProductPrice(rs.getInt("product_price"));
					dto.setProductDetail(rs.getString("product_detail"));
					dto.setProductImage(rs.getString("product_Image"));
					dto.setProductRegdate(rs.getString("product_regdate"));

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

	// 장바구니 가격 합계
	public int sumPrice(String memberId) {
		String query = "SELECT SUM(B.product_price * A.amount) FROM cart A, product B WHERE A.member_id = ? and A.product_num = B.product_num";
		int sum = 0;

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			rs.next();
			sum = rs.getInt(1);

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
				e.printStackTrace();
			}
		}
		return sum;
	}

	// 장바구니 삭제
	public void deleteCart(int cartNum) {
		String query = "delete from cart where cart_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cartNum);
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

	// 장바구니 전체 삭제
	public void deleteCartAll(String memberId) {
		String query = "delete from cart where member_id = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

}
