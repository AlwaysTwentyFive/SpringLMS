package com.oti.myuniversity.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.oti.myuniversity.common.jUnitTest;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;

public class AttendanceServiceTest extends jUnitTest{
	
	@Autowired
	IAttendanceService attendanceService;
	
	@Autowired
	Attendance initializedAttendance;

}
