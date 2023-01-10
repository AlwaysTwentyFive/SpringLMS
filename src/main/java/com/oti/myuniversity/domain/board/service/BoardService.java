package com.oti.myuniversity.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void insertLibrary(Board board) {
		//board.setBoardId(boardRepository.selectMaxBoardId() + 1); //시퀀스 설정해서 문제 없을 것 같은데
		boardRepository.insertLibrary(board);
	}

	@Transactional
	public void insertLibrary(Board board, ArrayList<BoardFile> fileList) {
		boardRepository.insertLibrary(board);
		if(fileList.size() != 0) {
			for(int i = 0; i<fileList.size(); i++) {
				if(fileList.get(i).getBoardFileName() != null 
						&& !fileList.get(i).getBoardFileName().equals("")) {
					fileList.get(i).setBoardId(board.getBoardId());
					boardFileRepository.insertFileData(fileList.get(i));
				}
			}
		}
	}
	@Transactional
	public void insertNoticeReport(Board board, ArrayList<BoardFile> fileList) {
		boardRepository.insertNoticeReport(board);
		if(fileList.size() != 0) {
			for(int i = 0; i<fileList.size(); i++) {
				if(fileList.get(i).getBoardFileName() != null 
						&& !fileList.get(i).getBoardFileName().equals("")) {
					fileList.get(i).setBoardId(board.getBoardId());
					boardFileRepository.insertFileData(fileList.get(i));
				}
			}
		}
	}
	@Transactional
	public void insertNoticeReport(Board board) {
		boardRepository.insertNoticeReport(board);		
	}
	@Override
	public Board selectReport(int boardId, String memberId) {
		return boardRepository.selectReport(boardId,memberId);
	}

	@Override
	public List<Board> selectStudentsReport(int boardId) {
		return boardRepository.selectStudentsReport(boardId);
	}
	
	@Override
	public int selectMaxBoardId() {
		return boardRepository.selectMaxBoardId();
	}

	@Override
	public void insertReport(Board board) {
		boardRepository.insertReport(board);
	}

	@Override
	public void insertReport(Board board, ArrayList<BoardFile> fileList) {
		boardRepository.insertReport(board);
		if(fileList.size() != 0) {
			for(int i = 0; i<fileList.size(); i++) {
				if(fileList.get(i).getBoardFileName() != null 
						&& !fileList.get(i).getBoardFileName().equals("")) {
					fileList.get(i).setBoardId(board.getBoardId());
					boardFileRepository.insertFileData(fileList.get(i));
				}
			}
		}
	}

	


}
