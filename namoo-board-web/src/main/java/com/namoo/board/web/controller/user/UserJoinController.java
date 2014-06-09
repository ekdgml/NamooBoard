package com.namoo.board.web.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.User;
import com.namoo.board.service.facade.UserService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/user/join.do")
public class UserJoinController extends HttpServlet {

	private static final long serialVersionUID = -6356530614672434854L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		
		User user = new User(userId, password, name, email);
		
		UserService service = NamooBoardServiceFactory.getInstance().getUserService();
		service.registUser(user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/main");
		dispatcher.forward(req, resp);
	}
	
	

}
