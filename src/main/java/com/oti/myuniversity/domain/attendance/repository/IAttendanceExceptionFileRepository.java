package com.oti.myuniversity.domain.attendance.repository;

import java.util.List;

import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;

public interface IAttendanceExceptionFileRepository {
	List<AttendanceExceptionFile> getAttendanceExceptionFilesByExceptionId(int attendanceExceptionId);
	void insertAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile);
	void updateAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile);
	void deleteAttendanceExceptionFile(int attendanceExceptionFileId);
}
