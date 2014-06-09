package com.namoo.board.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	//
	private int boardNo;
	private String boardSubject;
	private User manager;
	
	private List<Article> articles;
	
	//-------------------------------------------------------------------------
	public Board(String boardSubject, User user) {
		this.boardSubject = boardSubject;
		this.manager = user;
		this.articles = new ArrayList<>();
	}

	//-------------------------------------------------------------------------

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	//-------------------------------------------------------------------------
	
	public void addArticle(Article article) {
		//
		articles.add(article);
	}
	
	public void removeArticle(int articleNo) {
		//
		Article found = null;
		for (Article article : articles) {
			if (articleNo==article.getArticleNo()) {
				found = article;
			}
		}
		if (found != null) {
			articles.remove(found);
		}
	}
}
