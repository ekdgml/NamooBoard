package com.namoo.board.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.board.dao.UserDao;
import com.namoo.board.domain.User;

public class UserDaoJdbc implements UserDao {

	@Override
	public List<User> readAllUsers() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> users = new ArrayList<User>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT user_id, user_nm, email FROM user_tb";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String userId = rset.getString("user_id");
				String name = rset.getString("user_nm");
				String email = rset.getString("email");
				
				User user = new User(userId, null, name, email);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return users;
	}

	@Override
	public User readUser(String userId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT user_id, user_pw, user_nm, email FROM user_tb WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			// 파라미터 세팅
			pstmt.setString(1, userId);
			
			// 쿼리 수행
			rset = pstmt.executeQuery();
			
			// 결과를 객체로 변환
			if (rset.next()) {
				String userId2 = rset.getString("user_id");
				String password = rset.getString("user_pw");
				String name = rset.getString("user_nm");
				String email = rset.getString("email");
				user = new User(userId2, password, name, email);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return user;
	}

	@Override
	public void createUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO user_tb (user_id, user_nm, user_pw, email) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void updateUser(User user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE user_tb SET user_pw=?, email=? WHERE user_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUserId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteUser(String userId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM user_tb WHERE user_id =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

}
