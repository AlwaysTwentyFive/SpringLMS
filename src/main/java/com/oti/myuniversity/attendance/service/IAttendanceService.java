package com.oti.myuniversity.attendance.service;

import java.sql.Date;

import com.oti.myuniversity.attendance.model.Attendance;

public interface IAttendanceService {
	void insertAttendance(Attendance attendance);
	int checkAttendance(String meberid, Date sqlDate);
	Attendance selectAttendance(String memberId, Date sqlDate);
	void updateTimeStatus(Attendance attendance);

}
