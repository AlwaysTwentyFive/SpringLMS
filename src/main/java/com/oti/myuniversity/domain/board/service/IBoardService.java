package com.oti.myuniversity.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;

public interface IBoardService {
	public List<Board> selectArticleListByCatoryType(int categoryType,int pageNo);
	public int selectTotalArticleCount(int categoryType);
	public Board selectArticle(int boardId);
	//자료실 관리자 글 입력
	public void insertLibrary(Board board);
	public void insertLibrary(Board board, ArrayList<BoardFile> fileList);
	//과제실 관리자 글 입력
	public void insertNoticeReport(Board board);
	public void insertNoticeReport(Board board, ArrayList<BoardFile> fileList);
	//과제실 학생 과제 제출
	public void insertReport(Board board);
	public void insertReport(Board board, ArrayList<BoardFile> fileList);
	
	
	public Board selectReport(int boardId, String memberId);
	public List<Board> selectStudentsReport (int boardId);
	int selectMaxBoardId();
}
