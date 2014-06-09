package com.namoo.board.service.factory;

import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.facade.UserService;
import com.namoo.board.service.logic.BoardServiceLogic;
import com.namoo.board.service.logic.UserServiceLogic;

public class NamooBoardServiceFactory {
	
	private static NamooBoardServiceFactory instance = new NamooBoardServiceFactory();
	
	private NamooBoardServiceFactory() {
		//
	}
	
	public static NamooBoardServiceFactory getInstance() {
		//
		return instance;
	}
	
	public BoardService getBoardService() {
		//
		return new BoardServiceLogic();
	}
	
	public UserService getUserService() {
		//
		return new UserServiceLogic();
	}

}
