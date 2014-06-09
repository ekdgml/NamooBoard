package com.namoo.board.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {
	//
	private int articleNo;
	private String articleSubject;
	private String content;
	private Date articleDate;
	private User author;
	private String authorName;
	
	private List<Comment> comments;
	
	//-------------------------------------------------------------------------
	public Article(String articleSubject, String content, User user, String authorName) {
		//
		this.articleSubject = articleSubject;
		this.content = content;
		this.author = user;
		this.authorName = user.getName();
		
		this.comments = new ArrayList<>();
	}

	//-------------------------------------------------------------------------
	
	public String getArticleSubject() {
		return articleSubject;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public void setArticleSubject(String articleSubject) {
		this.articleSubject = articleSubject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getAuthor() {
		return author;
	}

	public Date getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}	
	
	
	//-------------------------------------------------------------------------
	//method
	
	
}
