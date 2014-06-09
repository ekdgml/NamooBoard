package com.namoo.board.service.facade;

import java.util.List;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Comment;

public interface BoardService {
	//
	List<Board> findAllBoards();  //모든Board목록 조회
	List<Board> findAllBoardsByManager(String userId); //userId가 관리하는 Board목록 조회
	Board findBoard(int boardNo, boolean withArticles); // boardNo로 board찾기
	void registBoard(Board board); //board 생성
	void modifyBoard(Board board); //board수정
	void removeBoard(int boardNo);
	void removeAll(int boardNo); // board & article삭제
	
	/**
	 * @param boardNo
	 * @param searchType   1:제목, 2:작성자ID, 3:내용, 4:......
	 * @param searchValue
	 * @return
	 */
	List<Article> searchAllArticles(int boardNo, int searchType, String searchValue); // board에 속한 article검색결과 목록
	Article findArticle(int articleNo); //articleNo로 article찾기
	void registArticle(int boardNo, Article article); // article 생성
	void modifyArticle(Article article); // article 수정
	void removeArticle(int articleNo); // article 삭제
	
	List<Comment> findAllComments(int articleNo); // article에 속한 comments목록 조회
	Comment findComment(int commentNo); //comment찾기
	void registComment(int articleNo, Comment comment); //comment 생성
	void modifyComment(Comment comment); //com 수정
	void removeComment(int commentNo); //comment 삭제
}
