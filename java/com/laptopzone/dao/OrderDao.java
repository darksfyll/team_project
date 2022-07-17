package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.laptopzone.dto.CartDto;
import com.laptopzone.dto.OrderDto;


public class OrderDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 전체 개수 얻기
	public int getNumRecords(String memberId) {
		int numRecords = 0;
		String query = "SELECT COUNT(*) FROM orders A, order_detail B WHERE A.order_num = B.order_num AND A.member_id = ?";

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

	// 주문정보 입력
	public void insertOrder(OrderDto dto) {
		String query1 = "insert into orders values(order_num, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		String query2 = "select max(order_num) from orders";
		String query3 = "insert order_detail values(order_detail_num, ?, ?, ?, ? ,?)";
		int orderNum = 0;

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, dto.getTotalPrice());
			pstmt.setString(3, dto.getReceiverName());
			pstmt.setString(4, dto.getReceiverPhone());
			pstmt.setString(5, dto.getZipcode());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getAddressDetail());
			pstmt.setString(8, dto.getAddressEtc());
			pstmt.executeUpdate();

			pstmt.close();

			pstmt = conn.prepareStatement(query2);
			rs = pstmt.executeQuery();

			rs.next();
			orderNum = rs.getInt(1);

			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(query3);
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, dto.getProductNum());
			pstmt.setString(3, dto.getProductName());
			pstmt.setInt(4, dto.getProductPrice());
			pstmt.setInt(5, dto.getAmount());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 장바구니 주문정보 입력
	public void insertCartOrder(OrderDto dto, ArrayList<CartDto> cart) {
		String query1 = "insert into orders values(order_num, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		String query2 = "select max(order_num) from orders";
		String query3 = "insert order_detail values(order_detail_num, ?, ?, ?, ? ,?)";
		int orderNum = 0;

		try {
			conn = DBconnector.getConnection();
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, dto.getTotalPrice());
			pstmt.setString(3, dto.getReceiverName());
			pstmt.setString(4, dto.getReceiverPhone());
			pstmt.setString(5, dto.getZipcode());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getAddressDetail());
			pstmt.setString(8, dto.getAddressEtc());
			pstmt.executeUpdate();

			pstmt.close();

			pstmt = conn.prepareStatement(query2);
			rs = pstmt.executeQuery();

			rs.next();
			orderNum = rs.getInt(1);
			System.out.println(orderNum);

			rs.close();
			pstmt.close();

			for (int i = 0; i < cart.size(); i++) {
				pstmt = conn.prepareStatement(query3);
				pstmt.setInt(1, orderNum);
				pstmt.setInt(2, cart.get(i).getProductNum());
				pstmt.setString(3, cart.get(i).getProductName());
				pstmt.setInt(4, cart.get(i).getProductPrice());
				pstmt.setInt(5, cart.get(i).getAmount());
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {

		}
	}

	// 주문정보
	public OrderDto orderInfo(String memberId) {
		OrderDto dto = new OrderDto();
		String query = "select * from orders where member_id = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setOrderNum(rs.getInt("order_num"));
				dto.setMemberId(rs.getNString("member_id"));
				dto.setTotalPrice(rs.getInt("total_price"));
				dto.setReceiverName(rs.getString("receiver_name"));
				dto.setReceiverPhone(rs.getString("receiver_phone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressDetail(rs.getString("address_detail"));
				dto.setAddressEtc(rs.getString("address_etc"));
				dto.setOrderRegdate(rs.getString("order_regdate"));
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
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// 주문목록
	public ArrayList<OrderDto> orderList(String memberId, int start, int listSize) {
		ArrayList<OrderDto> list = new ArrayList<OrderDto>();
		String query = "SELECT A.order_num, A.order_regdate, B.product_name, B.product_price, B.amount FROM "
				+ "orders A, order_detail B WHERE A.order_num = B.order_num AND A.member_id = ? limit ?, ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setOrderNum(rs.getInt("order_num"));
				dto.setOrderRegdate(rs.getString("order_regdate"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setAmount(rs.getInt("amount"));

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
