package com.oti.myuniversity.domain.attendance.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;

public interface IAttendanceRepository {
	void insertAttendance(Attendance attendance);
	int checkAttendance(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	Attendance selectAttendance(@Param("memberId") String memberId, @Param("sqlDate") Date sqlDate);
	void updateTimeStatus(Attendance attendance);
	List<Map<String , Object>> getTotalAttendance(Pager pager);
	int getTotalAttendanceCount();

}
