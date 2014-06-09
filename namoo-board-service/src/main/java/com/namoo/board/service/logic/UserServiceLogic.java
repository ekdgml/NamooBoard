package com.namoo.board.service.logic;

import java.util.List;

import com.namoo.board.dao.BoardDao;
import com.namoo.board.dao.UserDao;
import com.namoo.board.dao.factory.DaoFactory;
import com.namoo.board.dao.factory.DaoFactory.DbType;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.User;
import com.namoo.board.service.facade.UserService;
import com.namoo.board.service.logic.exception.NamooBoardException;

public class UserServiceLogic implements UserService {
	//
	private UserDao userDao;
	private BoardDao boardDao;
	
	public UserServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.userDao = daoFactory.getUserDao();
	}

	@Override
	public boolean login(String userId, String password) {
		//
		User user = userDao.readUser(userId);
		if (user != null && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> findAllUsers() {
		//
		return userDao.readAllUsers();
	}

	@Override
	public User findUser(String userId) {
		//
		return userDao.readUser(userId);
	}

	@Override
	public void registUser(User user) {
		//
		if (userDao.readUser(user.getUserId()) != null) {
			throw new NamooBoardException("이미 가입되어있는 사용자입니다.");
		}
		userDao.createUser(user);
	}

	@Override
	public void modifyUser(User user) {
		//
		if (userDao.readUser(user.getUserId()) == null) {
			throw new NamooBoardException("해당하는 사용자가 없습니다.");
		} 
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(String userId) {
		List<Board> boards = boardDao.readAllBoards();
		if (boards != null) {
			for (Board board : boards) {
				if (userId.equals(board.getManager().getUserId())) {
					throw new NamooBoardException("게시판 관리자는 탈퇴할 수 없습니다.");
				}
				userDao.deleteUser(userId);
			}
		}
	}

}
