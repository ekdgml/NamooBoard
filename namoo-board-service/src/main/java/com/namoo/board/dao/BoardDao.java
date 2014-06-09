package com.namoo.board.dao;

import java.util.List;

import com.namoo.board.domain.Board;

public interface BoardDao {
	//
	List<Board> readAllBoards();
	Board readBoard(int boardNo);
	int createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardNo);
}
