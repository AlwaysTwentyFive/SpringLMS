package com.oti.myuniversity.domain.attendance.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;

public interface IAttendanceService {
	Attendance selectAttendanceById(int attendanceId);
	Attendance selectAttendanceByDate(String studentId, Date sqlDate);
	boolean checkAttendance(String meberid, Date sqlDate);
	void insertAttendance(Attendance attendance);
	void updateTimeStatus(Attendance attendance);
	List<Map<String , Object>> getTotalAttendance(Pager pager);
	int getTotalAttendanceCount();
	List<Attendance> getPersonalAttendanceList (String memberId);
	int getAttendanceCount(String memberId, String status);
}
