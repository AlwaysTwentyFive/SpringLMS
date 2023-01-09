package com.oti.myuniversity.domain.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.domain.board.model.Board;

public interface IBoardRepository {

	public List<Board> selectArticleListByCatoryType(@Param("categoryType") int categoryType, @Param("start") int start,
			@Param("end") int end, @Param("pageNo") int pageNo);
	public int selectTotalArticleCountByCategoryType(int categoryType);
	public Board selectArticle(int boardId);

}
