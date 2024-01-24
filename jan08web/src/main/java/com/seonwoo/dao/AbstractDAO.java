package com.seonwoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seonwoo.db.DBConnection;

// 부모 DAO = DBConn, Close
public abstract class AbstractDAO {
	DBConnection dbc = DBConnection.getInstance();
	
	public void write(String ip, String url, String data) {
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO iplog (iip,iurl,idata) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, url);
			pstmt.setString(3, data);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	
	
	
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
