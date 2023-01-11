package com.oti.myuniversity.domain.attendance.model;

public class AttendanceExceptionFile {
	private int attendanceExceptionFileId;
	private String attendanceExceptionFileName;
	private long attendanceExceptionFileSize;
	private byte[] attendanceExceptionFileData;
	private String attendanceExceptionFileContentType;
	private int attendanceExceptionId;
	
	public int getAttendanceExceptionFileId() {
		return attendanceExceptionFileId;
	}
	public void setAttendanceExceptionFileId(int attendanceExceptionFileId) {
		this.attendanceExceptionFileId = attendanceExceptionFileId;
	}
	public String getAttendanceExceptionFileName() {
		return attendanceExceptionFileName;
	}
	public void setAttendanceExceptionFileName(String attendanceExceptionFileName) {
		this.attendanceExceptionFileName = attendanceExceptionFileName;
	}
	public long getAttendanceExceptionFileSize() {
		return attendanceExceptionFileSize;
	}
	public void setAttendanceExceptionFileSize(long attendanceExceptionFileSize) {
		this.attendanceExceptionFileSize = attendanceExceptionFileSize;
	}
	public byte[] getAttendanceExceptionFileData() {
		return attendanceExceptionFileData;
	}
	public void setAttendanceExceptionFileData(byte[] attendanceExceptionFileData) {
		this.attendanceExceptionFileData = attendanceExceptionFileData;
	}
	public String getAttendanceExceptionFileContentType() {
		return attendanceExceptionFileContentType;
	}
	public void setAttendanceExceptionFileContentType(String attendanceExceptionFileContentType) {
		this.attendanceExceptionFileContentType = attendanceExceptionFileContentType;
	}
	public int getAttendanceExceptionId() {
		return attendanceExceptionId;
	}
	public void setAttendanceExceptionId(int attendanceExceptionId) {
		this.attendanceExceptionId = attendanceExceptionId;
	}
	
	@Override
	public String toString() {
		return "AttendanceExceptionFile [attendanceExceptionFileId=" + attendanceExceptionFileId
				+ ", attendanceExceptionFileName=" + attendanceExceptionFileName + ", attendanceExceptionFileSize="
				+ attendanceExceptionFileSize + ", attendanceExceptionFileContentType="
				+ attendanceExceptionFileContentType + ", attendanceExceptionId=" + attendanceExceptionId + "]";
	}
	
}
