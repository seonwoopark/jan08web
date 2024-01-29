package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.dto.CoffeeDTO;

public class CoffeeDAO extends AbstractDAO{
	
	public int idCheck(String id) {
		int result = 0;
		Connection conn = db.getConnection();
		String sql = "SELECT COUNT(*) FROM coffee WHERE coid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
	
	public int coffeeJoin(CoffeeDTO dto) {
		int result = 0;
		Connection conn = db.getConnection();
		PreparedStatement pstmt= null;
		String sql = "INSERT INTO coffee (coid,coname,copw) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
