package com.oti.myuniversity.domain.board.model;

public class BoardFile {
	private int boardFileId;
	private String boardFileName;
	private long boardFileSize;
	private byte[] boardFileData;
	private String boardFileContentType;
	private int boardId;
	
	public int getBoardFileId() {
		return boardFileId;
	}
	public void setBoardFileId(int boardFileId) {
		this.boardFileId = boardFileId;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public long getBoardFileSize() {
		return boardFileSize;
	}
	public void setBoardFileSize(long boardFileSize) {
		this.boardFileSize = boardFileSize;
	}
	public byte[] getBoardFileData() {
		return boardFileData;
	}
	public void setBoardFileData(byte[] boardFileData) {
		this.boardFileData = boardFileData;
	}
	public String getBoardFileContentType() {
		return boardFileContentType;
	}
	public void setBoardFileContentType(String boardFileContentType) {
		this.boardFileContentType = boardFileContentType;
	}
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	@Override
	public String toString() {
		return "BoardFile [boardFileId=" + boardFileId + ", boardFileName=" + boardFileName + ", boardFileSize="
				+ boardFileSize + ", boardFileContentType=" + boardFileContentType + ", boardId="  + "]";
	}
	
}
