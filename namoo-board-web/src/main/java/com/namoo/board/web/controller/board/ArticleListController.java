package com.namoo.board.web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/board/articleList.do")
public class ArticleListController extends HttpServlet{

	private static final long serialVersionUID= 9026586218175923876L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		BoardService service = NamooBoardServiceFactory.getInstance().getBoardService();
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		Board board = service.findBoard(boardNo, true);
		List<Article> articles = board.getArticles();
		
		req.setAttribute("articles", articles);
		req.setAttribute("boardNo", boardNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/articleList.jsp");
		dispatcher.forward(req, resp);
	}

	
}
