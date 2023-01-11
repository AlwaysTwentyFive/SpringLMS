package com.oti.myuniversity.domain.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.component.MultipartFileResolver;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
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
	
	@Autowired
	MultipartFileResolver multipartFileResolver;
	
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
	@Transactional
	public Board selectArticle(int boardId) {
		Board board = boardRepository.selectArticle(boardId);
		List<BoardFile> fileList = boardFileRepository.selectfileList(boardId);
		board.setFileList(fileList);
		
		return board;
	}

	@Override
	public void insertLibrary(Board board) {
		boardRepository.insertLibrary(board);
	}
	
	@Override
	@Transactional
	public void insertLibrary(Board board,MultipartFile[] files ) {

		boardRepository.insertLibrary(board);
		int boardId = boardRepository.selectMaxBoardId();
		List<BoardFile> fileList;
		try {
			fileList = multipartFileResolver.getBoardFileList(files, boardId);
		
			if(fileList.size() != 0) {
				for(int i = 0; i<fileList.size(); i++) {
					if(fileList.get(i).getBoardFileName() != null 
							&& !fileList.get(i).getBoardFileName().equals("")) {
						boardFileRepository.insertFileData(fileList.get(i));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
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
	
	@Override
	public void insertNoticeReport(Board board) {
		boardRepository.insertNoticeReport(board);		
	}
	
	@Transactional
	@Override
	public Board selectReport(int reportNoticeId, String memberId) {
		Board reportBoard = null;
		int countReport = boardRepository.selectCountReport(reportNoticeId,memberId);
		if(countReport != 0) {
			reportBoard = boardRepository.selectReport(reportNoticeId,memberId);
			List<BoardFile> fileList = boardFileRepository.selectfileList(reportBoard.getBoardId());
			reportBoard.setFileList(fileList);
		}
		return reportBoard;
	}
	

	@Override
	@Transactional
	public List<Board> selectStudentsReport(int boardId) {
		List<Board> studentsReports = boardRepository.selectStudentsReport(boardId);
		for(Board aReport: studentsReports) {
			aReport.setFileList(boardFileRepository.selectfileList(aReport.getBoardId()));
		}
		return studentsReports;
	}
	
	@Override
	public int selectMaxBoardId() {
		return boardRepository.selectMaxBoardId();
	}

	@Override
	public void insertReport(Board board) {
		boardRepository.insertReport(board);
	}
	
	@Transactional
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

	@Override
	public void updateLibrary(Board board) {
		boardRepository.updateLibrary(board);
	}

	@Override
	@Transactional
	public void updateExistToNew(Board newBoard, MultipartFile[] files) {
		//자료실 게시물 업데이트
		boardRepository.updateLibrary(newBoard);
		//기존 파일 삭제
		boardFileRepository.deleteFiles(newBoard.getBoardId());
		//파일 입력
		List<BoardFile> fileList = null;
		try {
			fileList = multipartFileResolver.getBoardFileList(files, newBoard.getBoardId());
			if(fileList.size() != 0) {
				for(int i = 0; i<fileList.size(); i++) {
					if(fileList.get(i).getBoardFileName() != null 
							&& !fileList.get(i).getBoardFileName().equals("")) {
						boardFileRepository.insertFileData(fileList.get(i));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void updateNothingtoNew(Board newBoard, MultipartFile[] files) {
		//자료실 게시물 업데이트
		boardRepository.updateLibrary(newBoard);
		//파일 입력
		List<BoardFile> fileList = null;
		try {
			fileList = multipartFileResolver.getBoardFileList(files, newBoard.getBoardId());
			if(fileList.size() != 0) {
				for(int i = 0; i<fileList.size(); i++) {
					if(fileList.get(i).getBoardFileName() != null 
							&& !fileList.get(i).getBoardFileName().equals("")) {
						boardFileRepository.insertFileData(fileList.get(i));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
