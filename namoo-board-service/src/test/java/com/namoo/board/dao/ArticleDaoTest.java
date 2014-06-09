package com.namoo.board.dao;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.board.dao.jdbc.ArticleDaoJdbc;
import com.namoo.board.domain.Article;
import com.namoo.board.domain.Comment;
import com.namoo.board.domain.User;

public class ArticleDaoTest {

	private ArticleDao dao;
	
	int articleNo;
	int commentNo;
	String articleSubject = "articleTest";
	String content = "test success";
	String commentContent = "test Comment";
	
	@Before
	public void setUp() throws Exception {
		//
		dao = new ArticleDaoJdbc();
		PrepareBuilder.createBoard();
	}

	@After
	public void tearDown() throws Exception {
		dao.deleteComment(commentNo);
		dao.deleteArticle(articleNo);
		PrepareBuilder.deleteBoard();
		PrepareBuilder.deleteUser();
	}
	
	@Test
	public void testReadAllArticles() {
		int before = dao.readAllArticles(PrepareBuilder.BOARD_NO).size();
		createArticle();
		int after = dao.readAllArticles(PrepareBuilder.BOARD_NO).size();
		
		assertEquals(before+1, after);
	}

	@Test
	public void testCreateArticle() {
		createArticle();
		
		//검증
		Article article = dao.readArticle(articleNo); 
		assertEquals(articleNo, article.getArticleNo());
		assertEquals(articleSubject, article.getArticleSubject());
		assertEquals(content, article.getContent());
		assertEquals(PrepareBuilder.USER_ID, article.getAuthor().getUserId());
	}

	private void createArticle() {
		User author = PrepareBuilder.readUser();
		String authorName = author.getName();
		Article article = new Article(articleSubject, content, author, authorName);
		articleNo = dao.createArticle(PrepareBuilder.BOARD_NO, article);
	}

	@Test
	public void testUpdateArticle() {
		createArticle();
		Article article = dao.readArticle(articleNo);
		article.setArticleSubject("AfterArticleSubject");
		article.setContent("AfterArticleContent");
		dao.updateArticle(article);
		
		//검증
		article = dao.readArticle(articleNo);
		assertEquals("AfterArticleSubject", article.getArticleSubject());
		assertEquals("AfterArticleContent", article.getContent());
	}

	@Test
	public void testReadAllComments() {
		createArticle();
		int before = dao.readAllComments(articleNo).size();
		createComment(articleNo);
		int after = dao.readAllComments(articleNo).size();
		
		assertEquals(before+1, after);
	}

	private void createComment(int articleNo) {
		User author = PrepareBuilder.readUser();
		Comment comment = new Comment(commentContent, author);
		commentNo = dao.createComment(articleNo, comment);
	}

	@Test
	public void testUpdateComment() {
		createArticle();
		createComment(articleNo);
		Comment comment = dao.readComment(commentNo);
		comment.setCommentNo(commentNo);
		comment.setComment("AfterComment");
		dao.updateComment(comment);
		
		//검증
		comment = dao.readComment(commentNo);
		assertEquals("AfterComment", comment.getComment());
	}
}
