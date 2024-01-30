package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.CommentDTO;

public class CommentDAO extends AbstractDAO {
	
	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM comment";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return result;
	}
	
	
	public List<CommentDTO> selectComment(int page){
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT cno,board_no,mno,ccomment, cdate,cdel,cip"
				+ " FROM comment"
				+ " ORDER BY cno DESC"
				+ " LIMIT ?,10";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setComment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setCdel(rs.getString("cdel"));
				dto.setIp(rs.getString("cip"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int commentWrite(CommentDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO comment (ccomment, board_no, mno, cip) "
				+ "VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int commentDelete(CommentDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET cdel='0' "
				+ "WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}		
		return result;
	}
	
	public int commentUpdate(CommentDTO dto) {
		Connection conn = db.getConnection();
		int result = 0;
		PreparedStatement pstmt =null;
		String sql ="UPDATE comment SET ccomment = ? "
				+ "WHERE mno = (SELECT mno FROM member WHERE mid=?) "
				+ "AND cno =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setString(2, dto.getMid());
			pstmt.setInt(3, dto.getCno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
