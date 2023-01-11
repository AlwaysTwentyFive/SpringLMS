package com.oti.myuniversity.domain.attendance.service;

import java.util.List;

import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;

public interface IAttendanceExceptionFileService {
	List<AttendanceExceptionFile> getAttendanceExceptionFilesByExceptionId(int attendanceExceptionId);
	void insertAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile);
	void updateAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile);
	void deleteAttendanceExceptionFile(int attendanceExceptionFileId);
}
