package com.oti.myuniversity.domain.attendance.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.component.MultipartFileResolver;
import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionFileRepository;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionRepository;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;
import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.repository.IBoardRepository;

@Service
public class AttendanceService implements IAttendanceService {
	
	@Autowired
	IAttendanceRepository attendanceRepository;
	
	@Autowired
	IAttendanceExceptionRepository attendanceExceptionRepository;
	
	@Autowired
	IAttendanceExceptionFileRepository attendanceExceptionFileRepository;

	@Autowired
	IBoardRepository boardRepository;
	
	@Autowired
	MultipartFileResolver multipartFileResovler;
	
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
	public void updateAttendanceStatus(Attendance attendance) {
		attendanceRepository.updateAttendanceStatus(attendance);
	}
	
	@Override
	public List<Map<String , Object>> getTotalAttendance(Pager pager) {
		return attendanceRepository.getTotalAttendance(pager);
	}

	@Override
	public int getTotalAttendanceCount() {
		return attendanceRepository.getTotalAttendanceCount();
	}

	@Override
	public int getMaxAttendanceId() {
		return attendanceRepository.getMaxAttendanceId();
	}

	@Override
	@Transactional
	public int applyException(AttendanceException attendanceException, MultipartFile[] attendanceExceptionFiles) throws IOException {
		attendanceExceptionRepository.insertAttendanceException(attendanceException);
		int attendanceExceptionId = attendanceExceptionRepository.getMaxAttendanceExceptionId();
		List<AttendanceExceptionFile> attendanceExceptionFileList = multipartFileResovler.getAttendanceExceptionFileList(attendanceExceptionFiles, attendanceExceptionId);
		for(AttendanceExceptionFile attendanceExceptionFile : attendanceExceptionFileList) {
			attendanceExceptionFileRepository.insertAttendanceExceptionFile(attendanceExceptionFile);
		}
		return attendanceExceptionId;
	}

	@Override
	@Transactional
	public Model getAttendance(int attendanceExceptionId, Model model) {
		int attendanceId = attendanceRepository.getMaxAttendanceId();
		AttendanceException attendanceException = attendanceExceptionRepository.getAttendanceExceptionByAttendanceId(attendanceId);
		model.addAttribute("attendanceException", attendanceException);
		
		List<AttendanceExceptionFile> attendanceExceptionFiles = attendanceExceptionFileRepository.getAttendanceExceptionFilesByExceptionId(attendanceExceptionId);
		model.addAttribute("attendanceExceptionFiles", attendanceExceptionFiles);
		
		return model;
	}
	
	@Override
	@Transactional
	public void manageAttendance(Attendance attendance, AttendanceException attendanceException) {
		attendanceRepository.updateAttendanceStatus(attendance);
		attendanceExceptionRepository.updateAttendanceException(attendanceException);
	}

}
