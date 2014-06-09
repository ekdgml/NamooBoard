package com.namoo.board.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.board.dao.jdbc.BoardDaoJdbc;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.User;

public class BoardDaoTest {
	//
	private BoardDao dao;
	
	int boardNo;
	String boardSubject = "board_test";

	@Before
	public void setUp() throws Exception {
		//
		dao = new BoardDaoJdbc();
		PrepareBuilder.createUser();
	}

	@After
	public void tearDown() throws Exception {
		dao.deleteBoard(boardNo);
		PrepareBuilder.deleteUser();
	}

	@Test
	public void testReadAllBoards() {
		int before = dao.readAllBoards().size();
		createBoard();
		int after = dao.readAllBoards().size();
		assertEquals(before+1, after);
	}

	@Test
	public void testCreateBoard() {
		createBoard();
		
		//검증
		Board board = dao.readBoard(boardNo);
		assertEquals(boardNo, board.getBoardNo());
		assertEquals(boardSubject, board.getBoardSubject());
		assertEquals(PrepareBuilder.USER_ID, board.getManager().getUserId());
	}

	private void createBoard() {
		Board board = new Board(boardSubject, new User(PrepareBuilder.USER_ID));
		boardNo = dao.createBoard(board);
	}

	@Test
	public void testUpdateBoard() {
		createBoard();
		Board board = dao.readBoard(boardNo);
		board.setBoardSubject("board_test2");
		
		dao.updateBoard(board);
		
		//검증
		board = dao.readBoard(boardNo);
		assertEquals("board_test2", board.getBoardSubject());
	}


}
