package com.namoo.board.dao;

import java.util.List;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Comment;

public interface ArticleDao {
	//
	List<Article> readAllArticles(int boardNo);
	Article readArticle(int articleNo);
	int createArticle(int boardNo, Article article);
	void updateArticle(Article article);
	void deleteArticle(int articleNo);
	List<Comment> readAllComments(int articleNo);
	Comment readComment(int commentNo);
	int createComment(int articleNo, Comment comment);
	void updateComment(Comment comment);
	void deleteComment(int commentNo);

}
