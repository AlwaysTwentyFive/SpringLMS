package com.oti.myuniversity.domain.attendance.service;

import java.sql.Date;
import java.util.List;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;

import com.oti.myuniversity.component.Pager;

public interface IAttendanceExceptionService {
	int getTotalAttendanceExceptionCount();
	List<AttendanceException> getTotalAttendanceException(Pager pager);
	AttendanceException getAttendanceExceptionByAttendanceId(int attendanceId);
	List<AttendanceException> getAttendanceExceptionsByApplyDate(Pager pager, Date attendanceExceptionApplyDate);
	List<AttendanceException> getAttendanceExceptionsByStatus(Pager pager, String attendanceExceptionStatus);
	List<AttendanceException> getAttendanceExceptionsByApproved(Pager pager, boolean attendanceExceptionApproved);
	void insertAttendanceException(AttendanceException attendanceException);
	void updateAttendanceException(AttendanceException attendanceException);
	void deleteAttendanceException(int attendanceExceptionId);
	int getMaxAttendanceExceptionId();
}
