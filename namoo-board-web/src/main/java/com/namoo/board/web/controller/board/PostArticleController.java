package com.namoo.board.web.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.User;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/board/post.do")
public class PostArticleController extends HttpServlet {

	private static final long serialVersionUID = -3369003062994387915L;

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
		User user = (User) req.getSession().getAttribute("loginUser");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		Article article = new Article(subject, content, user, user.getName());
		service.registArticle(boardNo, article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/articleList.do");
		dispatcher.forward(req, resp);
	}
	
	

}
