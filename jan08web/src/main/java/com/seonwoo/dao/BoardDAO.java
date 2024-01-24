package com.seonwoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seonwoo.db.DBConnection;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.dto.CommentDTO;
import com.seonwoo.util.Util;

public class BoardDAO extends AbstractDAO{

	
	public int totalCount() {		
		Connection conn = dbc.getConn();
		int result =0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS count FROM boardview";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		
		return result;
	}
	//수정하기
	public int updateBoard(BoardDTO dto) {		
		Connection conn = dbc.getConn();
		int result =0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no = ? "
				+ "AND mno = (SELECT mno FROM member WHERE mid = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_content());
			pstmt.setInt(3, dto.getBoard_no());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	
	//삭제하기
	public int deleteBoard(BoardDTO dto) {		
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del='0' "
				+ "WHERE board_no=? AND mno = (SELECT mno FROM member WHERE mid=?)";
		
		int result=0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBoard_no());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		return result;
	}
	
	//글쓰기
	public int write(BoardDTO dto) {		
		Connection conn = dbc.getConn();
		int result= 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title,board_content,mno,board_ip)"
				+ " VALUES (?,?,?,?)";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_content());
			pstmt.setString(3, dto.getMno());
			pstmt.setString(4, dto.getIp());
			
			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 게시판 초기
	public List<BoardDTO> selectBoard(int page) {		
		Connection conn = dbc.getConn();
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardview LIMIT ?,10";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_write(rs.getString("mname"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_count(rs.getInt("board_count"));
				dto.setComment(rs.getInt("comment"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	//상세보기
	public BoardDTO detailBoard(int no) {		
		Connection conn = dbc.getConn();
		BoardDTO dto = new BoardDTO();
		String sql = "SELECT board_no, board_title,board_content, b.mname as board_write, board_date, mid, a.board_ip ,"
				+ "(SELECT count(*) FROM visitcount c WHERE c.board_no = a.board_no) as board_count\r\n"
				+ "FROM board a LEFT OUTER JOIN member b ON a.mno = b.mno\r\n"
				+ "WHERE board_no = ?";
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_write(rs.getString("board_write"));
				dto.setMid(rs.getString("mid"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_count(rs.getInt("board_count"));
				dto.setIp(rs.getString("board_ip"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}
	// 조회수
		public void countUp(int no, String mid) {		
			Connection conn = dbc.getConn();
			String sql = "SELECT count(*) FROM visitcount "
					+ "WHERE board_no =? AND mno = (SELECT mno FROM member WHERE mid= ?)";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int result = rs.getInt(1);
					if(result == 0) {
						realCountUp(no,mid);
					} 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void realCountUp(int no, String mid) {		
			Connection conn = dbc.getConn();
			PreparedStatement pstmt=null;
			String sql ="INSERT INTO visitcount (board_no,mno) "
					+ "VALUES ( ?, (SELECT mno FROM member WHERE mid= ?) )";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, mid);
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 댓글
		public List<CommentDTO> selectComment(int board_no){		
			Connection conn = dbc.getConn();
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			String sql = "SELECT * FROM commentview WHERE board_no = ?";
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CommentDTO dto = new CommentDTO();
					dto.setCno(rs.getInt("cno"));
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setCcomment(rs.getString("ccomment"));
					dto.setCdate(rs.getString("cdate"));
					dto.setClike(rs.getInt("clike"));
					dto.setMno(rs.getInt("mno"));
					dto.setMid(rs.getString("mid"));
					dto.setMname(rs.getString("mname"));
					dto.setCdel(rs.getInt("cdel"));
					dto.setIp(Util.ipMasking(rs.getString("cip")));
					
					list.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
		
		public int commentDel(CommentDTO dto) {		
			Connection conn = dbc.getConn();
			int result = 0;
			String sql = "UPDATE comment SET cdel='0' WHERE cno=? AND mno = (SELECT mno FROM member WHERE mid =?)";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getCno());
				pstmt.setString(2, dto.getMid());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}
		

}





