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
	
	//글 추가하기
	public void insertLibrary(Board board);
	
	public Board getBoard(int boardId);
	
	public int getMaxBoardId();

}
