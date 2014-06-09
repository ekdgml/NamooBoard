package com.namoo.board.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.board.dao.jdbc.UserDaoJdbc;
import com.namoo.board.domain.User;

public class UserDaoTest {
	//
	private UserDao dao;
	
	String userId = "_b_a_a_a";
	String password = "abcd";
	String name = "박상희";
	String email = "ekdgml@naver.com";

	@Before
	public void setUp() throws Exception {
		dao = new UserDaoJdbc();
		dao.deleteUser("_b_a_a_a");
	}
	
	@After
	public void tearDown() throws Exception {
		dao.deleteUser("_b_a_a_a");
	}
	
	@Test
	public void testReadAllUsers() {
		int before = dao.readAllUsers().size();
		createUser();
		int after = dao.readAllUsers().size();
		assertEquals(before+1, after);
	}

	@Test
	public void testCreateUser() {
		//
		createUser();
		//검증
		User user = dao.readUser(userId);
		assertEquals(userId, user.getUserId());
		assertEquals(password, user.getPassword());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
	}

	private void createUser() {
		User user = new User(userId, password, name, email);
		dao.createUser(user);
	}

	@Test
	public void testUpdateUser() {
		createUser();
		
		User user = dao.readUser(userId);
		user.setEmail("aaa@a.com");
		
		dao.updateUser(user);
		
		//검증
		user = dao.readUser(userId);
		assertEquals("aaa@a.com", user.getEmail());
	}
}
