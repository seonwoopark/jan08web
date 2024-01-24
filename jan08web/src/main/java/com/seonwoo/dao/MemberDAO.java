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
import com.seonwoo.dto.MemberDTO;

public class MemberDAO extends AbstractDAO{
	
	public int checkId(String id) {		
		Connection conn = dbc.getConn();
		int result=1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT count(*) FROM member WHERE mid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public int insertMember(MemberDTO dto) {		
		Connection conn = dbc.getConn();
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql ="INSERT INTO member (mid,mpw,mname) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			pstmt.setString(3, dto.getMname());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public void updatePw(MemberDTO dto) {		
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		String sql= "UPDATE member SET mpw = ? WHERE mno = (SELECT mno FROM member WHERE mid =?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMpw());
			pstmt.setString(2, dto.getMid());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MemberDTO myInfo(String mid) {		
		Connection conn = dbc.getConn();
		MemberDTO dto = new MemberDTO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM member WHERE mid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setMno(rs.getInt(1));
				dto.setMid(rs.getString(2));
				dto.setMpw(rs.getString(3));
				dto.setMname(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setMgrade(rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public MemberDTO login(MemberDTO dto) {
		
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "SELECT count(*) as count, mname,mno FROM member "
				+ "WHERE mid = ? "
				+ "AND mpw =?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setCount(rs.getInt("count"));
				dto.setMname(rs.getString("mname"));
				dto.setMno(rs.getInt("mno"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public List<Map<String, Object>> readData(MemberDTO dto){
		
		Connection conn = dbc.getConn();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "SELECT * FROM visitcount a LEFT OUTER JOIN board b ON a.board_no = b.board_no "
				+ "WHERE a.mno = (SELECT mno FROM member WHERE mid=?)";
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vno", rs.getInt("vno"));
				map.put("board_no", rs.getInt("board_no"));
				map.put("board_title", rs.getString("board_title"));
				//map.put("mno", rs.getInt("mno"));
				map.put("vdate", rs.getString("vdate"));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
}
