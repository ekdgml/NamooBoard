package com.namoo.board.dao.factory;

import com.namoo.board.dao.ArticleDao;
import com.namoo.board.dao.BoardDao;
import com.namoo.board.dao.UserDao;
import com.namoo.board.dao.jdbc.MariaDBDaoFactory;

public abstract class DaoFactory {
	//
	public static enum DbType {
		MariaDB
	}
	
	public static DaoFactory createFactory(DbType dbType) {
		//
		if (dbType == DbType.MariaDB) {
			return new MariaDBDaoFactory();
		}
		return null;
	}
	
	public abstract UserDao getUserDao();
	public abstract BoardDao getBoardDao();
	public abstract ArticleDao getArticleDao();

}
