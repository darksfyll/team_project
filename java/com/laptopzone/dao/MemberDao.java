package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.laptopzone.dto.MemberDto;
import com.laptopzone.dto.ProductDto;

public class MemberDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	//로그인
	public int memberLogin(String memberId, String memberPwd) {
		
		String query = "select count(member_id) from member where member_id = ? and member_pwd = ?";
		int check = 0;

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();

			rs.next();
			check = rs.getInt(1);

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
		return check;
	}

	//회원가입
	public void memberJoin(MemberDto dto) {
		String query = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, now())";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPwd());
			pstmt.setString(3, dto.getMemberName());
			pstmt.setString(4, dto.getMemberPhone());
			pstmt.setString(5, dto.getMemberZipcode());
			pstmt.setString(6, dto.getMemberAddress());
			pstmt.setString(7, dto.getMemberAddressDetail());
			pstmt.setString(8, dto.getMemberAddressEtc());
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
	
	//아이디 중복체크
	public int checkId(String memberId) {

		String query = "select count(member_id) from member where member_id = ?";
		int check = 0;

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			rs.next();
			check = rs.getInt(1);

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
				throw new RuntimeException(e);
			}
		}
		return check;
	}

	//회원 정보
	public MemberDto showMemberInfo(String id) {
		MemberDto dto = new MemberDto();
		
		String query = "select * from member where member_id = ?";
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPwd(rs.getString("member_pwd"));
				dto.setMemberName(rs.getString("member_name"));
				dto.setMemberPhone(rs.getString("member_phone"));
				dto.setMemberZipcode(rs.getString("member_zipcode"));
				dto.setMemberAddress(rs.getString("member_address"));
				dto.setMemberAddressDetail(rs.getString("member_address_detail"));
				dto.setMemberAddressEtc(rs.getString("member_address_etc"));
				dto.setRegdate(rs.getString("regdate"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	
	//회원정보수정
	public void memberUpdate(MemberDto dto) {
		String query = "update member set member_pwd = ?, member_name = ?, member_phone = ?, member_zipcode = ?, member_address = ?,"
				+ "member_address_detail = ?, member_address_etc = ? where member_id = ?";

		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getMemberPwd());
			pstmt.setString(2, dto.getMemberName());
			pstmt.setString(3, dto.getMemberPhone());
			pstmt.setString(4, dto.getMemberZipcode());
			pstmt.setString(5, dto.getMemberAddress());
			pstmt.setString(6, dto.getMemberAddressDetail());
			pstmt.setString(7, dto.getMemberAddressEtc());
			pstmt.setString(8, dto.getMemberId());
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
	
	
	//회원탈퇴
	public void memberDelete(String memberId) {
			
			String query = "delete from member where member_id = ?";
			
			try {
				conn = DBconnector.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				pstmt.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch(SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
	//아이디 찾기
	public String searchId(String memberName, String memberPhone) {
		String query = "select member_id from member where member_name=? and member_phone =?";
		String memberId = null;
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberId = rs.getString("member_id");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
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
				e.printStackTrace();
			}
		}
		
		return memberId;
	}
	
	
	//비밀번호 찾기
	public String searchPwd(String memberId, String memberPhone) {
		String query = "select member_pwd from member where member_id=? and member_Phone=?";	
		String memberPwd = null;
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPhone);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberPwd = rs.getString("member_pwd");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
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
				e.printStackTrace();
			}
		}
		
		return memberPwd;
	}
	
	//회원 목록
	public ArrayList<MemberDto> memberList(){
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String query = "select * from member order by regdate desc";
		
		try {
			conn = DBconnector.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPwd(rs.getString("member_pwd"));
				dto.setMemberName(rs.getString("member_name"));
				dto.setMemberPhone(rs.getString("member_phone"));
				dto.setMemberZipcode(rs.getString("member_zipcode"));
				dto.setMemberAddress(rs.getString("member_address"));
				dto.setMemberAddressDetail(rs.getString("member_address_detail"));
				dto.setMemberAddressEtc(rs.getString("member_address_etc"));
				dto.setRegdate(rs.getString("regdate"));

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
