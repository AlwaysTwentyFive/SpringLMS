package com.oti.myuniversity.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;
import com.oti.myuniversity.domain.board.model.Comment;

public interface IBoardService {
	public List<Board> selectArticleListByCategoryType(int categoryType,Pager pager);
	public int selectTotalArticleCount(int categoryType);
	//게시글 상세 조회
	public Board selectArticle(int boardId);

	int selectMaxBoardId();
	
	public Board selectScoreNContent(String memberId, int boardId);

	//자료실 관리자 글 입력
	public void insertLibrary(Board board, MultipartFile[] files);
	public void insertLibrary(Board board);
	
	//자료실 관리자 글 수정
	public void updateLibrary(Board board);
	public void updateReport(Board board);
	public void updateExistToNew(Board newBoard, MultipartFile[] files);
	public void updateNothingtoNew(Board newBoard, MultipartFile[] files);

	//과제실 관리자 글 입력
	public void insertNoticeReport(Board board);
	public void insertNoticeReport(Board board, MultipartFile[] files);
	
	//과제실 학생 과제 제출
	public void insertReport(Board board);
	public void insertReport(Board board, ArrayList<BoardFile> fileList);
	
	//한 학생의 과제 게시글 가져오기
	public Board selectReport(int boardId, String memberId);
	
	//여러 학생의 과제 게시글 가져오기
	public List<Board> selectStudentsReport (int boardId);
	
	//게시글 삭제
	public void deleteArticleByBoardId(int boardId);
	
	//댓글 입력
	public Comment insertComment(Comment comment);
	//댓글 삭제
	public List<Comment> deleteComment(Comment comment);
	//댓글 수정
	public List<Comment> updateComment(Comment comment);
	//댓글 리스트 출력
	public List<Comment> selectCommentList(int boardId);
	//과제 평가
	public void evaluateSubmittedReport(int boardId, int submissionScore);

}
