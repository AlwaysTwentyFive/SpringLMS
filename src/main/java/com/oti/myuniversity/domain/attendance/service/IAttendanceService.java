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

public interface IAttendanceService {
	Attendance selectAttendanceById(int attendanceId);
	Attendance selectAttendanceByDate(String studentId, Date sqlDate);
	boolean checkAttendance(String meberid, Date sqlDate);
	void insertAttendance(Attendance attendance);
	void insertAttendanceWithoutTime(Attendance attendance);
	void updateTimeStatus(Attendance attendance);
	void updateAttendanceStatus(Attendance attendance);
	List<Map<String , Object>> getTotalAttendance(Pager pager);
	int getTotalAttendanceCount();
	List<Attendance> getPersonalAttendanceList (String memberId);
	int getAttendanceCount(String memberId, String status);
	int getMaxAttendanceId();
	int applyException(AttendanceException attendanceException, MultipartFile[] attendanceExceptionFiles) throws IOException;
	Model getAttendance(int boardId, Model model);
	void manageAttendance(AttendanceException attendanceException, Date attendanceExceptionDate);
}
