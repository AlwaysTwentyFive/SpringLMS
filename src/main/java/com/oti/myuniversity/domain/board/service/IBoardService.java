package com.oti.myuniversity.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;

public interface IBoardService {
	public List<Board> selectArticleListByCatoryType(int categoryType,int pageNo);
	public int selectTotalArticleCount(int categoryType);
	public Board selectArticle(int boardId);

	int selectMaxBoardId();

	//자료실 관리자 글 입력
	public void insertLibrary(Board board, MultipartFile[] files);
	public void insertLibrary(Board board);
	
	//자료실 관리자 글 수정
	public void updateLibrary(Board board);
	public void updateExistToNew(Board newBoard, MultipartFile[] files);
	public void updateNothingtoNew(Board newBoard, MultipartFile[] files);

	//과제실 관리자 글 입력
	public void insertNoticeReport(Board board);
	public void insertNoticeReport(Board board, ArrayList<BoardFile> fileList);
	
	//과제실 학생 과제 제출
	public void insertReport(Board board);
	public void insertReport(Board board, ArrayList<BoardFile> fileList);
	
	//한 학생의 과제 게시글 가져오기
	public Board selectReport(int boardId, String memberId);
	
	//여러 학생의 과제 게시글 가져오기
	public List<Board> selectStudentsReport (int boardId);
	
	

}
