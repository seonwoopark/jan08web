package com.seonwoo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// 싱글턴 --- static 싱글톤 패턴
	private static DBConnection dbConn = new DBConnection();
	
	//생성자
	private DBConnection() {
		
	}
	
	// 인스턴스 호출에 대응하는 메소드 getInstance
	public static DBConnection getInstance() {
		return dbConn;
	}
	
	public Connection getConn() {
		Connection conn= null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://guro.wisejia.com:3307/c23c_06";
			String id = "c23c_06";
			String pw = "!1q2w3e4r";
			conn = DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return conn;
	}
	
}
