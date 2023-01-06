package com.oti.myuniversity.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.attendance.model.Attendance;
import com.oti.myuniversity.attendance.repository.IAttendanceRepository;

@Service
public class AttendanceService implements IAttendanceService {
	
	@Autowired
	IAttendanceRepository attendanceRepository;

	@Override
	public void insertAttendance(Attendance attendance) {
		attendanceRepository.insertAttendance(attendance);
		
	}

}
