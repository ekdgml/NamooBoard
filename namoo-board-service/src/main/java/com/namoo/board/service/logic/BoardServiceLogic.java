package com.namoo.board.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.board.dao.ArticleDao;
import com.namoo.board.dao.BoardDao;
import com.namoo.board.dao.factory.DaoFactory;
import com.namoo.board.dao.factory.DaoFactory.DbType;
import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Comment;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.logic.exception.NamooBoardExceptionFactory;

public class BoardServiceLogic implements BoardService {

	private BoardDao boardDao;
	private ArticleDao articleDao;

	public BoardServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.boardDao = daoFactory.getBoardDao();
		this.articleDao = daoFactory.getArticleDao();
	}

	@Override
	public List<Board> findAllBoards() {
		//
		return boardDao.readAllBoards();
	}

	@Override
	public List<Board> findAllBoardsByManager(String userId) {
		//
		List<Board> boards = boardDao.readAllBoards();
		List<Board> myBoards = new ArrayList<Board>();
		for (Board board : boards) {
			if (userId.equals(board.getManager().getUserId())) {
				myBoards.add(board);
			}
		}
		return myBoards;
	}

	@Override
	public Board findBoard(int boardNo, boolean withArticles) {
		//
		Board board = boardDao.readBoard(boardNo);
		if (withArticles) {
			List<Article> articles = articleDao.readAllArticles(boardNo);
			for (Article article : articles) {
				board.addArticle(article);
			}
		}
		return board;
	}

	@Override
	public void registBoard(Board board) {
		//
		if (isExistBoardByName(board.getBoardSubject())) {
			throw NamooBoardExceptionFactory.createRuntime("이미 존재하는 게시판입니다.");
		}
		boardDao.createBoard(board);
	}

	private boolean isExistBoardByName(String boardSubject) {
		//
		List<Board> boards = boardDao.readAllBoards();

		if (boards != null && !boards.isEmpty()) {
			for (Board board : boards) {
				if (board.getBoardSubject().equals(boardSubject)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void removeBoard(int boardNo) {
		//
		boardDao.deleteBoard(boardNo);
	}
	
	@Override
	public void removeAll(int boardNo) {
		//
		Board board = boardDao.readBoard(boardNo);
		if ((board.getArticles())!= null) {
			throw NamooBoardExceptionFactory.createRuntime("해당 게시판에 게시글들이 있습니다. 확인하세요.");
		}
		boardDao.deleteBoard(boardNo);
	}

	@Override
	public void modifyBoard(Board board) {
		//
		boardDao.updateBoard(board);
	}

	@Override
	public List<Article> searchAllArticles(int boardNo, int searchType, String searchValue) {
		//
		List<Article> articles = articleDao.readAllArticles(boardNo);
		List<Article> results = new ArrayList<Article>();
		if (searchType == 1) {
			for (Article article : articles) {
				if (article.equals(searchValue)) {
					results.add(article);
				}
			}
		} else if (searchType == 2) {
			for (Article article : articles) {
				if (article.equals(searchValue)) {
					results.add(article);
				}
			}
		} else {
			for (Article article : articles) {
				if (article.equals(searchValue)) {
					results.add(article);
				}
			}
		}
		return results;
	}

	@Override
 	public Article findArticle(int articleNo) {
		//
		return articleDao.readArticle(articleNo);
	}

	@Override
	public void registArticle(int boardNo, Article article) {
		//
		articleDao.createArticle(boardNo, article);
	}

	@Override
	public void modifyArticle(Article article) {
		//
		articleDao.updateArticle(article);
	}

	@Override
	public void removeArticle(int articleNo) {
		//
		articleDao.deleteArticle(articleNo);
	}

	@Override
	public void registComment(int articleNo, Comment comment) {
		//
		articleDao.createComment(articleNo, comment);
	}

	@Override
	public List<Comment> findAllComments(int articleNo) {
		return null;
	}

	@Override
	public Comment findComment(int commentNo) {
		return articleDao.readComment(commentNo);
	}

	@Override
	public void modifyComment(Comment comment) {
		//
		articleDao.updateComment(comment);
	}

	@Override
	public void removeComment(int commentNo) {
		//
		articleDao.deleteComment(commentNo);
	}

}
