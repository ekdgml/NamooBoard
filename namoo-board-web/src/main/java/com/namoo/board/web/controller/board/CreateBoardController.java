package com.namoo.board.web.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.Board;
import com.namoo.board.domain.User;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/board/createBoard.do")
public class CreateBoardController extends HttpServlet{

	private static final long serialVersionUID = -7706314120642776475L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		BoardService service = NamooBoardServiceFactory.getInstance().getBoardService();
		User user = (User) req.getSession().getAttribute("loginUser");
		String boardName = req.getParameter("boardName");
		Board board = new Board(boardName, user);
		
		service.registBoard(board);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/main");
		dispatcher.forward(req, resp);
		
	}

	
}
