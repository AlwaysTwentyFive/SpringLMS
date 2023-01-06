package com.oti.myuniversity.board.service;

import java.util.List;

import com.oti.myuniversity.board.model.Board;

public interface IBoardService {
	public List<Board> selectArticleListByCatoryType(int categoryType,int pageNo);
	public int selectTotalArticleCount(int categoryType);
}
