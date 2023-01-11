package com.oti.myuniversity.domain.attendance.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.board.model.Board;

public interface IAttendanceService {
	Attendance selectAttendance(String memberId, Date sqlDate);
	boolean checkAttendance(String meberid, Date sqlDate);
	void insertAttendance(Attendance attendance);
	void updateTimeStatus(Attendance attendance);
	void updateAttendanceStatus(Attendance attendance);
	List<Map<String , Object>> getTotalAttendance(Pager pager);
	int getTotalAttendanceCount();
	int getMaxAttendanceId();
	
	int applyException(AttendanceException attendanceException, MultipartFile[] attendanceExceptionFiles) throws IOException;
	Model getAttendance(int boardId, Model model);
	void manageAttendance(Attendance attendance, AttendanceException attendanceException);
}
