package com.oti.myuniversity.board.service;

import java.util.ArrayList;
import java.util.List;

import com.oti.myuniversity.board.model.Board;
import com.oti.myuniversity.board.model.BoardFile;

public interface IBoardService {
	public List<Board> selectArticleListByCatoryType(int categoryType,int pageNo);
	public int selectTotalArticleCount(int categoryType);
	public Board selectArticle(int boardId);
	public void insertArticle(Board board);
	public void insertArticle(Board board, ArrayList<BoardFile> fileList);
}
