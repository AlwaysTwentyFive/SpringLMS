package com.oti.myuniversity.domain.attendance.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;

@Service
public class AttendanceService implements IAttendanceService {
	
	@Autowired
	IAttendanceRepository attendanceRepository;

	@Override
	public void insertAttendance(Attendance attendance) {
		attendanceRepository.insertAttendance(attendance);
	}

	@Override
	public boolean checkAttendance(String memberId, Date sqlDate) {
		int attenddanceCount = attendanceRepository.checkAttendance(memberId, sqlDate);
		if (attenddanceCount == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Attendance selectAttendance(String memberId, Date sqlDate) {
		return attendanceRepository.selectAttendance(memberId, sqlDate);
		
	}

	@Override
	public void updateTimeStatus(Attendance attendance) {
		attendanceRepository.updateTimeStatus(attendance);
	}

	@Override
	public List<Map<String , Object>> getTotalAttendance(Pager pager) {
		return attendanceRepository.getTotalAttendance(pager);
	}

	@Override
	public int getTotalAttendanceCount() {
		return attendanceRepository.getTotalAttendanceCount();
	}


}
