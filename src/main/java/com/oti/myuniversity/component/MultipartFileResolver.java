package com.oti.myuniversity.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.board.model.BoardFile;

@Component
public class MultipartFileResolver {
	
	private List<BoardFile> boardFileList;
	private List<AttendanceExceptionFile> attendanceExceptionFileList;
	
	public List<BoardFile> getBoardFileList(MultipartFile[] multipartFile, int boardId) throws IOException  {
		boardFileList = new ArrayList<BoardFile>();
		for(MultipartFile multiPartFile : multipartFile) {
			BoardFile boardFile = new BoardFile(); 
			boardFile.setBoardId(boardId);
			boardFile.setBoardFileName(multiPartFile.getOriginalFilename());
			boardFile.setBoardFileSize(multiPartFile.getSize());
			boardFile.setBoardFileData(multiPartFile.getBytes());
			boardFile.setBoardFileContentType(multiPartFile.getContentType());
			boardFileList.add(boardFile);
		}
		return boardFileList;
	}
	
	public List<AttendanceExceptionFile> getAttendanceExceptionFileList(MultipartFile[] multipartFile, int attendanceExceptionId) throws IOException {
		attendanceExceptionFileList = new ArrayList<AttendanceExceptionFile>();
		for(MultipartFile multiPartFile : multipartFile) {
			AttendanceExceptionFile attendanceExceptionFile = new AttendanceExceptionFile(); 
			attendanceExceptionFile.setAttendanceExceptionId(attendanceExceptionId);
			attendanceExceptionFile.setAttendanceExceptionFileName(multiPartFile.getOriginalFilename());
			attendanceExceptionFile.setAttendanceExceptionFileSize(multiPartFile.getSize());
			attendanceExceptionFile.setAttendanceExceptionFileData(multiPartFile.getBytes());
			attendanceExceptionFile.setAttendanceExceptionFileContentType(multiPartFile.getContentType());
			attendanceExceptionFileList.add(attendanceExceptionFile);
		}
		return attendanceExceptionFileList;
	}
}
