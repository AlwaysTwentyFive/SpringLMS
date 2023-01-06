package com.oti.myuniversity.attendance.repository;

import java.sql.Date;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.attendance.model.Attendance;

public interface IAttendanceRepository {
	void insertAttendance(Attendance attendance);
	int checkAttendance(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	Attendance selectAttendance(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	void updateTimeStatus(Attendance attendance);

}
