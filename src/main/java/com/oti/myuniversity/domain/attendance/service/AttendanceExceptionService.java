package com.oti.myuniversity.domain.attendance.service;

import java.sql.Date;
import java.util.List;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionRepository;

@Service
public class AttendanceExceptionService implements IAttendanceExceptionService {

	@Autowired
	IAttendanceExceptionRepository attendanceExceptionRepository;
	
	@Override
	public int getTotalAttendanceExceptionCount() {
		return attendanceExceptionRepository.getTotalAttendanceExceptionCount(); 
	}

	@Override
	public List<AttendanceException> getTotalAttendanceException(Pager pager) {
		return attendanceExceptionRepository.getTotalAttendanceException(pager);
	}
	
	@Override
	public AttendanceException getAttendanceExceptionByAttendanceExceptionId(int attendanceId) {
		return attendanceExceptionRepository.getAttendanceExceptionByAttendanceExceptionId(attendanceId);
	}

	@Override
	public List<AttendanceException> getAttendanceExceptionsByApplyDate(Pager pager, Date attendanceExceptionApplyDate) {
		return attendanceExceptionRepository.getAttendanceExceptionsByApplyDate(pager, attendanceExceptionApplyDate);
	}

	@Override
	public List<AttendanceException> getAttendanceExceptionsByStatus(Pager pager, String attendanceExceptionStatus) {
		return attendanceExceptionRepository.getAttendanceExceptionsByStatus(pager, attendanceExceptionStatus);
	}

	@Override
	public List<AttendanceException> getAttendanceExceptionsByApproved(Pager pager, boolean attendanceExceptionApproved) {
		return attendanceExceptionRepository.getAttendanceExceptionsByApproved(pager, attendanceExceptionApproved);
	}

	@Override
	public void insertAttendanceException(AttendanceException attendanceException) {
		attendanceExceptionRepository.insertAttendanceException(attendanceException);
	}

	@Override
	public void updateAttendanceException(AttendanceException attendanceException) {
		attendanceExceptionRepository.updateAttendanceException(attendanceException);
	}

	@Override
	public void deleteAttendanceException(int attendanceExceptionId) {
		attendanceExceptionRepository.deleteAttendanceException(attendanceExceptionId);
	}

	@Override
	public int getMaxAttendanceExceptionId() {
		return attendanceExceptionRepository.getMaxAttendanceExceptionId();
	}

	@Override
	public AttendanceException getAttendanceExceptionByAttendanceId(int attendanceId) {
		// TODO Auto-generated method stub
		return attendanceExceptionRepository.getAttendanceExceptionByAttendanceId(attendanceId);
	}

}
