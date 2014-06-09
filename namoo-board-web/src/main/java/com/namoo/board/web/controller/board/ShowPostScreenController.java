package com.namoo.board.web.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/goPost.do")
public class ShowPostScreenController extends HttpServlet{

	private static final long serialVersionUID = -6715330289779583035L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		req.setAttribute("boardNo", boardNo);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/postArticle.jsp");
		dispatcher.forward(req, resp);
	}
	
	

}
