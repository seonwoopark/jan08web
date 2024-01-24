package com.seonwoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDAO extends AbstractDAO{
	
	public void write(Map<String, Object> log) {
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO iplog (iip,iurl,idata) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)log.get("ip"));
			pstmt.setString(2, (String)log.get("url"));
			pstmt.setString(3, (String)log.get("data"));
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
}
