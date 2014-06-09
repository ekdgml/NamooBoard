package com.namoo.board.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.board.dao.BoardDao;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.User;

public class BoardDaoJdbc implements BoardDao {

	@Override
	public List<Board> readAllBoards() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Board> boards = new ArrayList<Board>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT board_no, user_id, board_sj FROM board";

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int boardNo = rset.getInt("board_no");
				String boardSubject = rset.getString("board_sj");
				String userId = rset.getString("user_id");
				
				Board board = new Board(boardSubject, new User(userId));
				board.setBoardNo(boardNo);
				boards.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return boards;
	}

	@Override
	public Board readBoard(int boardNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT board_no, user_id, board_sj FROM board WHERE board_no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String userId = rset.getString("user_id");
				String boardSubject = rset.getString("board_sj");
				
				board = new Board(boardSubject, new User(userId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return board;
	}

	@Override
	public int createBoard(Board board) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO board(board_sj, user_id) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardSubject());
			pstmt.setString(2, board.getManager().getUserId());
			
			pstmt.executeUpdate();
			
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				boardNo = rset.getInt("board_no");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		
		return boardNo;
	}

	@Override
	public void updateBoard(Board board) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE board SET board_sj=? WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardSubject());
			pstmt.setInt(2, board.getBoardNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteBoard(int boardNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM board WHERE board_no =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

}
