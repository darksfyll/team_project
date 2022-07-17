package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.laptopzone.dto.QnADto;

public class QnADao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// QnA 개수 얻기
	public int getNumRecords() {
		String query = "select count(*) from qna";
		int numRecords = 0;

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

	// QnA 목록
	public ArrayList<QnADto> qnaList(int start, int listSize) {
		ArrayList<QnADto> list = new ArrayList<QnADto>();
		String query = "select * from ( SELECT CASE WHEN LEVEL-1 > 0 \r\n"
				+ "THEN CONCAT(CONCAT(REPEAT('     ', level - 1),'   ㄴre:'), b.qna_title)\r\n" + "ELSE b.qna_title\r\n"
				+ "END AS qna_title\r\n" + ",b.qna_writer\r\n" + ",b.qna_regdate\r\n" + ",b.qna_views\r\n"
				+ ",b.qna_num\r\n" + ",b.parent_num\r\n" + ",fnc.level\r\n" + "FROM\r\n"
				+ "(SELECT fnc_qna() AS id, @level AS LEVEL\r\n"
				+ "FROM (SELECT @start_with:=0, @id:=@start_with, @level:=0) vars\r\n" + "JOIN qna\r\n"
				+ "WHERE @id IS NOT NULL) fnc\r\n" + "JOIN qna b ON fnc.id = b.qna_num ) a  limit ?, ? ";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, listSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				QnADto dto = new QnADto();
				dto.setQnaNum(rs.getInt("qna_num"));
				dto.setParentNum(rs.getInt("parent_num"));
				dto.setQnaTitle(rs.getString("qna_title"));
				dto.setQnaWriter(rs.getString("qna_writer"));
				dto.setQnaRegdate(rs.getString("qna_regdate"));
				dto.setQnaViews(rs.getInt("qna_views"));

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

	// QnA 보기
	public QnADto selectQnA(int qnaNum, boolean incViews) {
		String query1 = "select * from qna where qna_num=?";
		String query2 = "update qna set qna_views = qna_views + 1 where qna_num = ?";
		QnADto dto = new QnADto();

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setQnaNum(rs.getInt("qna_num"));
				dto.setParentNum(rs.getInt("parent_num"));
				dto.setQnaTitle(rs.getString("qna_title"));
				dto.setQnaWriter(rs.getString("qna_writer"));
				dto.setQnaContent(rs.getString("qna_content"));
				dto.setQnaRegdate(rs.getString("qna_regdate"));
				dto.setQnaViews(rs.getInt("qna_views"));
			}

			rs.close();
			pstmt.close();

			if (incViews) {
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, qnaNum);
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
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// QnA 작성
	public void insertQnA(QnADto dto) {
		String query = "insert into qna values(qna_num, 0, ?, ?, ?, now(), 0)";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getQnaTitle());
			pstmt.setString(2, dto.getQnaWriter());
			pstmt.setString(3, dto.getQnaContent());
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

	// QnA 수정
	public void updateQnA(QnADto dto) {
		String query = "update qna set qna_title = ?, qna_content = ? where qna_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getQnaTitle());
			pstmt.setString(2, dto.getQnaContent());
			pstmt.setInt(3, dto.getQnaNum());
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

	// QnA 삭제
	public void deleteQnA(int qnaNum) {
		String query = "delete from qna where qna_num = ? or parent_num = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNum);
			pstmt.setInt(2, qnaNum);
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

	// QnA 답글
	public void insertReply(QnADto dto) {
		String query = "insert into qna values(qna_num, ?, ?, ?, ?, now(), 0)";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getParentNum());
			pstmt.setString(2, dto.getQnaTitle());
			pstmt.setString(3, dto.getQnaWriter());
			pstmt.setString(4, dto.getQnaContent());
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
