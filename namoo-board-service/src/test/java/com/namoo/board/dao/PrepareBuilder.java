package com.namoo.board.dao;

import com.namoo.board.dao.jdbc.BoardDaoJdbc;
import com.namoo.board.dao.jdbc.UserDaoJdbc;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.User;

public class PrepareBuilder {
	//
	static final String USER_ID = "_a_a_test";
	static int BOARD_NO;
	
	public static User createUser() {
		UserDao dao = new UserDaoJdbc();
		User user = new User(USER_ID, "abcd", "password", "abcd@abcd.abcd");
		dao.createUser(user);
		return user;
	}
	
	public static void deleteUser() {
		UserDao dao = new UserDaoJdbc();
		dao.deleteUser(USER_ID);
	}
	
	public static User readUser() {
		UserDao dao = new UserDaoJdbc();
		User user = dao.readUser(USER_ID);
		return user;
	}
	
	public static Board createBoard() {
		BoardDao dao = new BoardDaoJdbc();
		User author = createUser();
		Board board = new Board("TestBoard", author);
		BOARD_NO = dao.createBoard(board);
		board.setBoardNo(BOARD_NO);
		return board;
	}
	
	public static void deleteBoard() {
		BoardDao dao = new BoardDaoJdbc();
		dao.deleteBoard(BOARD_NO);
	}
	
}
