package com.namoo.board.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.domain.User;
import com.namoo.board.service.facade.UserService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/user/userList.do")
public class UserListController extends HttpServlet{

	private static final long serialVersionUID = -3263436335065056861L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		UserService service = NamooBoardServiceFactory.getInstance().getUserService();
		List<User> users = service.findAllUsers();
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/userList.jsp");
		dispatcher.forward(req, resp);
	}
	
	

}
