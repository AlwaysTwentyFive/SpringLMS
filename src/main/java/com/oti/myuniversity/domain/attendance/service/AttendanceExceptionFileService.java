package com.oti.myuniversity.domain.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionFileRepository;

@Service
public class AttendanceExceptionFileService implements IAttendanceExceptionFileService {
	
	@Autowired
	IAttendanceExceptionFileRepository attendanceExceptionFileRepository;

	@Override
	public List<AttendanceExceptionFile> getAttendanceExceptionFilesByExceptionId(int attendanceExceptionId) {
		return attendanceExceptionFileRepository.getAttendanceExceptionFilesByExceptionId(attendanceExceptionId);
	}

	@Override
	public void insertAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile) {
		attendanceExceptionFileRepository.insertAttendanceExceptionFile(attendanceExceptionFile);
	}

	@Override
	public void updateAttendanceExceptionFile(AttendanceExceptionFile attendanceExceptionFile) {
		attendanceExceptionFileRepository.updateAttendanceExceptionFile(attendanceExceptionFile);
	}

	@Override
	public void deleteAttendanceExceptionFile(int attendanceExceptionFileId) {
		attendanceExceptionFileRepository.deleteAttendanceExceptionFile(attendanceExceptionFileId);
	}

}
