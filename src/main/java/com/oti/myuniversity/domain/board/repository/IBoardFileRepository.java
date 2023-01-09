package com.oti.myuniversity.domain.board.repository;

import java.util.List;

import com.oti.myuniversity.domain.board.model.BoardFile;

public interface IBoardFileRepository {
 public List<BoardFile> selectfileList(int boardId);
 public BoardFile getFile(int fileId);
}
