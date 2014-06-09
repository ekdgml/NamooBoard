package com.namoo.board.web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.Board;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/board/boardList.do")
public class BoardListController extends HttpServlet{

	private static final long serialVersionUID = 6162511812054211365L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		BoardService service = NamooBoardServiceFactory.getInstance().getBoardService();
		List<Board> boards = service.findAllBoards();
		
		req.setAttribute("boards", boards);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp");
		dispatcher.forward(req, resp);
	}
	
	

}
