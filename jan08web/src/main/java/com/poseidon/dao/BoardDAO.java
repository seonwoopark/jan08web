package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

public class BoardDAO extends AbstractDAO {
	
	public int updateDel(BoardDTO dto) {
		Connection conn = db.getConnection();
		PreparedStatement pstmt= null;
		String sql ="UPDATE board SET board_del=? WHERE board_no =?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(dto.getDel()));
			pstmt.setInt(2, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<BoardDTO> boardAdminList(String search) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT board_no,board_title,mname,board_date,board_del,board_ip,"
				+ "(SELECT COUNT(*) FROM visitcount c WHERE a.board_no = c.board_no) AS board_count, "
				+ "(SELECT COUNT(*) FROM comment d WHERE a.board_no = d.board_no) AS comment"
				+ " FROM board a LEFT OUTER JOIN member b ON a.mno = b.mno "
				+ "WHERE board_title LIKE CONCAT('%',?,'%') OR board_title LIKE CONCAT('%',?,'%') "
				+ " OR board_content LIKE CONCAT('%',?,'%') OR mname LIKE CONCAT('%',?,'%') ORDER BY board_no DESC "
				+ " LIMIT 0, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setString(4, search);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));
				e.setComment(rs.getInt("comment"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	
	
	public List<BoardDTO> boardAdminList(int page) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT board_no,board_title,mname,board_date,board_del,board_ip,"
				+ "(SELECT COUNT(*) FROM visitcount c WHERE a.board_no = c.board_no) AS board_count, "
				+ "(SELECT COUNT(*) FROM comment d WHERE a.board_no = d.board_no) AS comment"
				+ " FROM board a LEFT OUTER JOIN member b ON a.mno = b.mno ORDER BY board_no DESC LIMIT ?, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);// 수정해야합니다.
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));
				e.setComment(rs.getInt("comment"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	
	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);// 수정해야합니다.
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setComment(rs.getInt("comment"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public BoardDTO detail(int no) {
		BoardDTO dto = new BoardDTO();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write, m.mid, b.board_date, b.board_ip, "
				+ "(SELECT COUNT(*) FROM visitcount WHERE board_no=b.board_no) AS board_count "
				+ "FROM board b JOIN member m ON b.mno=m.mno WHERE b.board_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
				dto.setIp(rs.getString("board_ip"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dto;
	}

	public void countUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM visitcount WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int result = rs.getInt(1);
				if (result == 0) {
					realCountUp(no, mid);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	private void realCountUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitcount (board_no, mno) VALUES(?, (SELECT mno FROM member WHERE mid=?))";// 바꿔주세요.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	public int write(BoardDTO dto) {
		int result = 0;

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES(?,?,(SELECT mno FROM member WHERE mid=?), ?)";// 수정완

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid());// 수정완
			pstmt.setString(4, dto.getIp());// 아이피를 추가했습니다.
			result = pstmt.executeUpdate();// 영향받은 행을 result에 저장합니다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int delete(BoardDTO dto) {
		int result = 0;
		// conn
		Connection con = db.getConnection();
		// pstmt
		PreparedStatement pstmt = null;
		// sql
		String sql = "UPDATE board SET board_del='0' WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=? "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		return result;
	}

	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";
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

	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE board_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setComment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setMno(rs.getInt("mno"));
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setClike(rs.getInt("clike"));
				dto.setIp(Util.ipMasking(rs.getString("cip")));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
}
