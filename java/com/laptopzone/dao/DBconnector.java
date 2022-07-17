package com.laptopzone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
	//DAO에서 모든 메소드에 필요한 DB연결 문법을 미리 메소드로 선언한다
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			//드라이버를 메모리에 할당
			Class.forName("org.mariadb.jdbc.Driver");
			//입력한 정보를 전달하여 드라이버를 통해 연결 객체를 가져온다
			conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:8080/student202", "student202", "1234!!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
