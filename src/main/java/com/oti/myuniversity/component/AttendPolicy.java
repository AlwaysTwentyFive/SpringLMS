package com.oti.myuniversity.component;

import com.oti.myuniversity.domain.attendance.model.Attendance;

public interface AttendPolicy {
	String evaluateAttend(Attendance attendance);
	String evaluateAttendTemp(Attendance attendance);
}
