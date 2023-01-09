package com.oti.myuniversity.domain.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.domain.board.model.BoardFile;
import com.oti.myuniversity.domain.board.repository.IBoardFileRepository;

@Service
public class BoardFileService implements IBoardFileService {

	@Autowired
	IBoardFileRepository boardFileRepository;
	
	@Override
	public BoardFile getFile(int fileId) {
		return boardFileRepository.getFile(fileId);
		
	}

}
