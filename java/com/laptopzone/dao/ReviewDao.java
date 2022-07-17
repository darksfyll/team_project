package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.laptopzone.dto.ReviewDto;

public class ReviewDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//리뷰 개수 얻기
		public int getNumRecords(int productNum) {
			int numRecords = 0;
			String query = "select count(*) from review where product_num = ?";
			
			try {
				conn = DBconnector.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, productNum);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					numRecords = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				}catch(SQLException e) {
					throw new RuntimeException(e);
				}
			}
			return numRecords;
		}
	
	
	//리뷰 목록
	public ArrayList<ReviewDto> reviewList(int productNum, int start, int listSize){
		ArrayList<ReviewDto> list = new ArrayList<ReviewDto>();
		String query = "select * from review where product_num = ? order by review_num desc limit ?, ?";
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNum);
			pstmt.setInt(2, start);
			pstmt.setInt(3, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDto dto = new ReviewDto();
				dto.setReviewNum(rs.getInt("review_num"));
				dto.setProductNum(rs.getInt("product_num"));
				dto.setReviewTitle(rs.getString("review_title"));
				dto.setReviewWriter(rs.getString("review_writer"));
				dto.setReviewContent(rs.getString("review_content"));
				//dto.setReviewImage(rs.getString("review_image"));
				dto.setReviewRegdate(rs.getString("review_regdate"));
				dto.setReviewViews(rs.getInt("review_views"));

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
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//리뷰 작성
	public void insertReview(ReviewDto dto) {
		String query = "insert into review values(review_num, ?, ?, ?, ?, now(), 0)";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getProductNum());
			pstmt.setString(2, dto.getReviewTitle());
			pstmt.setString(3, dto.getReviewWriter());
			pstmt.setString(4, dto.getReviewContent());
			//pstmt.setString(5, dto.getReviewImage());
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
	
	//리뷰 조회
	public ReviewDto selectReview(int reviewNum, boolean incViews) {
		String query1 = "select * from review where review_num = ?";
		String query2 = "update review set review_views = review_views + 1 where review_num = ?";
		ReviewDto dto = new ReviewDto();
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, reviewNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setReviewNum(rs.getInt("review_num"));
				dto.setProductNum(rs.getInt("product_num"));
				dto.setReviewTitle(rs.getString("review_title"));
				dto.setReviewWriter(rs.getString("review_writer"));
				dto.setReviewContent(rs.getString("review_content"));
				//dto.setReviewImage(rs.getString("review_image"));
				dto.setReviewRegdate(rs.getString("review_regdate"));
				dto.setReviewViews(rs.getInt("review_views"));
			}
			
			rs.close();
			pstmt.close();
			
			if(incViews) {
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, reviewNum);
				pstmt.executeUpdate();
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
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	//리뷰 수정
	public void updateReview(ReviewDto dto) {
		String query = "update review set review_title=?, review_content=? where review_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getReviewTitle());
			pstmt.setString(2, dto.getReviewContent());
			pstmt.setInt(3, dto.getReviewNum());
			//pstmt.setString(5, dto.getReviewImage());
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
	
	//리뷰 삭제
		public void deleteReview(int reviewNum) {
			
			String query = "delete from review where review_num=?";
			
			try {
				conn = DBconnector.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewNum);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	
	
	
	
}
