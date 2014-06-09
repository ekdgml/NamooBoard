package com.namoo.board.domain;

import java.util.Date;

public class Comment {
	//
	private int commentNo;
	private String comment;
	private Date commentDate;
	private User commenter;
	
	public Comment(String comment, User user) {
		//
		this.comment = comment;
		this.commenter = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public User getCommenter() {
		return commenter;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}	
	
	
	
	
}
