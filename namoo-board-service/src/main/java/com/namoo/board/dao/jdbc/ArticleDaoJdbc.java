package com.namoo.board.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.board.dao.ArticleDao;
import com.namoo.board.domain.Article;
import com.namoo.board.domain.Comment;
import com.namoo.board.domain.User;

public class ArticleDaoJdbc implements ArticleDao {

	@Override
	public List<Article> readAllArticles(int boardNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Article> articles = new ArrayList<Article>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT article_no, board_no, author_id, author_nm, article_sj, article_ct, article_date FROM article WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int articleNo = rset.getInt("article_no");
				String Subject = rset.getString("article_sj");
				String content = rset.getString("article_ct");
				String authorId = rset.getString("author_id");
				User user = new User(authorId);
				String authorName = user.getName();
				Date articleDate = rset.getDate("article_date");
				
				Article article = new Article(Subject, content, user, authorName);
				article.setArticleNo(articleNo);
				article.setArticleDate(articleDate);
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return articles;
	}

	@Override
	public Article readArticle(int articleNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Article article = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT  article_no, board_no, author_id, author_nm, article_sj, article_ct, article_date FROM article WHERE article_no =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				String articleSubject = rset.getString("article_sj");
				String content = rset.getString("article_ct");
				String authorId = rset.getString("author_id");
				User user = new User(authorId);
				String authorName = user.getName();
				
				article = new Article(articleSubject, content, user, authorName);
				article.setArticleNo(articleNo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return article;
	}

	@Override
	public int createArticle(int boardNo, Article article) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int articleNo = 0;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO article(board_no, author_id, author_nm, article_sj, article_ct, article_date) VALUES (?, ?, ?, ?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, article.getAuthor().getUserId());
			pstmt.setString(3, article.getAuthor().getName());
			pstmt.setString(4, article.getArticleSubject());
			pstmt.setString(5, article.getContent());
			
			pstmt.executeUpdate();
			
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				articleNo = rset.getInt("article_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return articleNo;
	}

	@Override
	public void updateArticle(Article article) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE article SET article_sj =?, article_ct=?, article_date=sysdate() WHERE article_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getArticleSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArticleNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteArticle(int articleNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM article WHERE article_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public List<Comment> readAllComments(int articleNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT article_no, comment_no, author_id, author_nm, content, comment_date FROM comment WHERE (SELECT article_no FROM article WHERE article_no=?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int commentNo = rset.getInt("comment_no");
				String authorId = rset.getString("author_id");
				String content = rset.getString("content");
				Date commentDate = rset.getDate("comment_date");
				
				Comment comment = new Comment(content, new User(authorId));
				comment.setCommentDate(commentDate);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return comments;
	}
	
	@Override
	public Comment readComment(int commentNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Comment comment = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT article_no, comment_no, author_id, author_nm, content, comment_date FROM comment WHERE comment_no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);
			
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int articleNo = rset.getInt("article_no");
				String authorId = rset.getString("author_id");
				String content = rset.getString("content");
				
				comment = new Comment(content, new User(authorId));
				comment.setCommentNo(commentNo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return comment;
	}
	
	@Override
	public int createComment(int articleNo, Comment comment) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int commentNo = 0;
		try {
			conn = DbConnection.getConnection();
			commentNo = generateCommentNo(articleNo,conn);
			
			String sql = "INSERT INTO comment(article_no, comment_no, author_id, author_nm, content, comment_date) VALUES (?, ?, ?, ?, ?, sysdate())";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			pstmt.setInt(2, commentNo);
			pstmt.setString(3, comment.getCommenter().getUserId());
			pstmt.setString(4, comment.getCommenter().getName());
			pstmt.setString(5, comment.getComment());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return commentNo;
		
	}

	private int generateCommentNo(int articleNo, Connection conn) throws SQLException {
		// 
		int commentNo = 1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT max(comment_no) +1 FROM comment WHERE article_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				commentNo = rset.getInt(1);
			}
		} finally {
			if (rset != null) try {rset.close();} catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
		}
		return commentNo;
	}

	@Override
	public void updateComment(Comment comment) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE comment SET content=? WHERE comment_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getComment());
			pstmt.setInt(2, comment.getCommentNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		
	}

	@Override
	public void deleteComment(int commentNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM comment WHERE comment_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		
	}


}
