package com.oti.myuniversity.domain.attendance.repository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;

public interface IAttendanceRepository {
	void insertAttendance(Attendance attendance);
	void insertAttendanceWithoutTime(Attendance attendance);
	int checkAttendance(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	Attendance selectAttendanceById(@Param("attendanceId") int attendanceId);
	Attendance selectAttendanceByDate(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	void updateTimeStatus(Attendance attendance);
	void updateAttendanceStatus(Attendance attendance);
	int updateTodayAttendance(String attendanceDate);
	List<Map<String , Object>> getTotalAttendance(Pager pager);
	int getTotalAttendanceCount();
	LinkedList<Attendance> getPersonalAttendanceList(String memberId);
	int getAttendanceCount(@Param("memberId")String memberId, @Param("status")String status, @Param("date") Date sqlDate, @Param("startDate") Date startDate);
	int getMaxAttendanceId();
}
