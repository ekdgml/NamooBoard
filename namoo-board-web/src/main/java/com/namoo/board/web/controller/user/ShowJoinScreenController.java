package com.namoo.board.web.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/goJoin.do")
public class ShowJoinScreenController extends HttpServlet{

	private static final long serialVersionUID = -4014181188835093306L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/joinUser.jsp");
		dispatcher.forward(req, resp);
	}

	
}
