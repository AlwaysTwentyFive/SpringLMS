package com.oti.myuniversity.attendance.service;

import java.sql.Date;

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

	@Override
	public int checkAttendance(String memberId, Date sqlDate) {
		return attendanceRepository.checkAttendance( memberId, sqlDate);
	}

	@Override
	public Attendance selectAttendance(String memberId, Date sqlDate) {
		return attendanceRepository.selectAttendance(memberId, sqlDate);
		
	}

	@Override
	public void updateTimeStatus(Attendance attendance) {
		attendanceRepository.updateTimeStatus(attendance);
		
	}

}
