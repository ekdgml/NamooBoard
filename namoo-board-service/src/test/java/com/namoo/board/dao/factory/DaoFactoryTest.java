package com.namoo.board.dao.factory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.board.dao.factory.DaoFactory.DbType;
import com.namoo.board.dao.jdbc.MariaDBDaoFactory;

public class DaoFactoryTest {

	@Before
	public void setUp() throws Exception {
		//
	}

	@After
	public void tearDown() throws Exception {
		//
	}
	
	@Test
	public void testCreateFactory() {
		//
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		assertTrue(daoFactory instanceof MariaDBDaoFactory);
	}

}
