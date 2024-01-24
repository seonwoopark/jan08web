package com.seonwoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.views.AbstractView;

import com.seonwoo.dto.CommentDTO;

public class CommentDAO extends AbstractDAO{
	
	public int insertComment(CommentDTO dto) {		
		Connection conn = dbc.getConn();
		int result=0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO comment (ccomment,board_no,mno,cip) VALUES (?,?,(SELECT mno FROM member WHERE mid=?),?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCcomment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}














