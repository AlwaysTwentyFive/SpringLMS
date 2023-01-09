package com.oti.myuniversity.domain.board.service;

import java.util.List;

import com.oti.myuniversity.domain.board.model.Board;

public interface IBoardService {
	public List<Board> selectArticleListByCatoryType(int categoryType,int pageNo);
	public int selectTotalArticleCount(int categoryType);
	public Board selectArticle(int boardId);
}
