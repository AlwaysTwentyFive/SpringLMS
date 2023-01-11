package com.oti.myuniversity.domain.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.domain.board.model.Board;

public interface IBoardRepository {

	public List<Board> selectArticleListByCatoryType(@Param("categoryType") int categoryType, @Param("start") int start,
			@Param("end") int end, @Param("pageNo") int pageNo);
	
	//글 목록 출력하기
	public int selectTotalArticleCountByCategoryType(int categoryType);
	
	//글 상세 보기
	public Board selectArticle(int boardId);
	
	//자료실 관리자 글 추가하기
	public void insertLibrary(Board board);
	
	//과제실 관리자 글 추가하기
	public void insertNoticeReport(Board board);

	public int getMaxBoardId();

	//글의 마지막 번호 알기
	public int selectMaxBoardId();
	
	//학생의 과제글 읽기
	public Board selectReport(@Param("reportNoticeId")int reportNoticeId, @Param("memberId")String memberId);
	
	//학생의 과제글이 있는지 조회
	public int selectCountReport(@Param("reportNoticeId")int reportNoticeId, @Param("memberId")String memberId);

	//학생들의 제출한 과제 리스트 뽑기
	public List<Board> selectStudentsReport(int BoardId);
	
	//학생 과제물 입력하기
	public void insertReport(Board board);

	
	
}
