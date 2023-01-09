package com.oti.myuniversity.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.board.model.Board;

public interface IBoardRepository {

	public List<Board> selectArticleListByCatoryType(@Param("categoryType") int categoryType, @Param("start") int start,
			@Param("end") int end, @Param("pageNo") int pageNo);
	
	//글 목록 출력하기
	public int selectTotalArticleCountByCategoryType(int categoryType);
	
	//글 상세 보기
	public Board selectArticle(int boardId);
	
	//글 추가하기
	public void insertLibrary(Board board);
	
	//글의 마지막 번호 알기
//	public int selectMaxBoardId();

}
