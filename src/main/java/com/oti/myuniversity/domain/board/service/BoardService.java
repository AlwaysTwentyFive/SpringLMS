package com.oti.myuniversity.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.model.BoardFile;
import com.oti.myuniversity.domain.board.repository.IBoardFileRepository;
import com.oti.myuniversity.domain.board.repository.IBoardRepository;

@Service
public class BoardService implements IBoardService {

	@Autowired
	IBoardRepository boardRepository;
	
	@Autowired
	IBoardFileRepository boardFileRepository;
	
	//게시판 리스트 가져오기
	@Override
	public List<Board> selectArticleListByCatoryType(int categoryType, int pageNo) {
		int start = (pageNo-1) *10 +1;
		
		return boardRepository.selectArticleListByCatoryType(categoryType, start, start+10, pageNo);
	}
	
	//게시물의 총 개수 알기
	@Override
	public int selectTotalArticleCount(int categoryType) {
		return boardRepository.selectTotalArticleCountByCategoryType(categoryType);
	}

	@Override
	public Board selectArticle(int boardId) {
		Board board = boardRepository.selectArticle(boardId);
		List<BoardFile> fileList = boardFileRepository.selectfileList(boardId);
		board.setFileList(fileList);
		
		return board;
	}

}
