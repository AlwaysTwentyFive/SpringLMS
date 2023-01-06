package com.oti.myuniversity.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.board.model.Board;
import com.oti.myuniversity.board.repository.IBoardRepository;

@Service
public class BoardService implements IBoardService {

	@Autowired
	IBoardRepository boardRepository;
	
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

}
