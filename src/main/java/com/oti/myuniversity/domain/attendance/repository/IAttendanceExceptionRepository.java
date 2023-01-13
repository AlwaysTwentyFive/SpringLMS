package com.oti.myuniversity.domain.attendance.repository;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;

public interface IAttendanceExceptionRepository {
	int getTotalAttendanceExceptionCount();
	List<AttendanceException> getTotalAttendanceException(Pager pager);
	AttendanceException getAttendanceExceptionByAttendanceExceptionId(int attendanceExceptionId);
	AttendanceException getAttendanceExceptionByAttendanceId(int attendanceId);
	List<AttendanceException> getAttendanceExceptionsByApplyDate(Pager pager, @Param("attendanceExceptionApplyDate") Date attendanceExceptionApplyDate);
	List<AttendanceException> getAttendanceExceptionsByStatus(Pager pager, @Param("attendanceExceptionStatus") String attendanceExceptionStatus);
	List<AttendanceException> getAttendanceExceptionsByApproved(Pager pager, @Param("attendanceExceptionApproved") boolean attendanceExceptionApproved);
	void insertAttendanceException(AttendanceException attendanceException);
	void updateAttendanceException(AttendanceException attendanceException);
	void deleteAttendanceException(int attendanceExceptionId);
	int getMaxAttendanceExceptionId();
}
