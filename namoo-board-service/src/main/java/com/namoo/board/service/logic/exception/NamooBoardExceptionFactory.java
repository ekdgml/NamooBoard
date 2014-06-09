package com.namoo.board.service.logic.exception;

public class NamooBoardExceptionFactory {

	private NamooBoardExceptionFactory() {
		//
	}

	public static NamooBoardException createRuntime(String msg) {
		//
		return new NamooBoardException(msg);
	}
}
