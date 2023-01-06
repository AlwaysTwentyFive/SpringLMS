package com.oti.myuniversity.component;

import com.oti.myuniversity.attendance.model.Attendance;

public interface AttendPolicy {
	String evaluateAttend(Attendance attendance);
}
