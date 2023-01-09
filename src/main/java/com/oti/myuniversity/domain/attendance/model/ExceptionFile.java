package com.oti.myuniversity.domain.attendance.model;

public class ExceptionFile {
	private int exceptionFileId;
	private String exceptionFileName;
	private String exceptionFileSize;
	private byte[] exceptionFileData;
	private String exceptionFileContentType;
	private int exceptionId;
	
	
	public byte[] getExceptionFileData() {
		return exceptionFileData;
	}
	public void setExceptionFileData(byte[] exceptionFileData) {
		this.exceptionFileData = exceptionFileData;
	}
	public int getExceptionFileId() {
		return exceptionFileId;
	}
	public void setExceptionFileId(int exceptionFileId) {
		this.exceptionFileId = exceptionFileId;
	}
	public String getExceptionFileName() {
		return exceptionFileName;
	}
	public void setExceptionFileName(String exceptionFileName) {
		this.exceptionFileName = exceptionFileName;
	}
	public String getExceptionFileSize() {
		return exceptionFileSize;
	}
	public void setExceptionFileSize(String exceptionFileSize) {
		this.exceptionFileSize = exceptionFileSize;
	}
	public String getExceptionFileContentType() {
		return exceptionFileContentType;
	}
	public void setExceptionFileContentType(String exceptionFileContentType) {
		this.exceptionFileContentType = exceptionFileContentType;
	}
	public int getExceptionId() {
		return exceptionId;
	}
	public void setExceptionId(int exceptionId) {
		this.exceptionId = exceptionId;
	}
	
	@Override
	public String toString() {
		return "ExceptionFile [exceptionFileId=" + exceptionFileId + ", exceptionFileName=" + exceptionFileName
				+ ", exceptionFileSize=" + exceptionFileSize + ", exceptionFileContentType=" + exceptionFileContentType
				+ ", exceptionId=" + exceptionId + "]";
	}
	
	
	
	
}
