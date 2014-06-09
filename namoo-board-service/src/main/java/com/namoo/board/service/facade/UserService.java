package com.namoo.board.service.facade;

import java.util.List;

import com.namoo.board.domain.User;

public interface UserService {
	//
	List<User> findAllUsers();
	User findUser(String userId);
	void registUser(User user);
	void modifyUser(User user);
	void removeUser(String userId);
	boolean login(String userId, String password);
}
