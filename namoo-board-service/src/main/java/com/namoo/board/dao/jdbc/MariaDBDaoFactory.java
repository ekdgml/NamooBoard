package com.namoo.board.dao.jdbc;

import com.namoo.board.dao.ArticleDao;
import com.namoo.board.dao.BoardDao;
import com.namoo.board.dao.UserDao;
import com.namoo.board.dao.factory.DaoFactory;

public class MariaDBDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		//
		return new UserDaoJdbc();
	}

	@Override
	public BoardDao getBoardDao() {
		//
		return new BoardDaoJdbc();
	}

	@Override
	public ArticleDao getArticleDao() {
		//
		return new ArticleDaoJdbc();
	}

}
