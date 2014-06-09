package com.namoo.board.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.board.service.facade.UserService;
import com.namoo.board.service.factory.NamooBoardServiceFactory;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 631953665753271517L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		UserService service = NamooBoardServiceFactory.getInstance().getUserService();
		String userId = req.getParameter("loginId");
		String password = req.getParameter("password");
		if (service.login(userId, password)) {
			req.getSession().setAttribute("loginUser", service.findUser(userId));
			resp.sendRedirect(req.getServletContext().getContextPath()+"/main");
		} else {
			resp.sendRedirect("/main");
		}
	}

	
}
