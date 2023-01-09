package com.oti.myuniversity.domain.board.repository;

import java.util.List;

import com.oti.myuniversity.domain.board.model.BoardFile;

public interface IBoardFileRepository {
	//파일 목록 조회
	public List<BoardFile> selectfileList(int boardId);
	//파일 다운로드
	 public BoardFile getFile(int fileId);
	//파일 넣기
	 public void insertFileData(BoardFile boardFile);
}
