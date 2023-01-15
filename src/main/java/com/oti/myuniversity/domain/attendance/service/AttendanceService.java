package com.oti.myuniversity.domain.attendance.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.component.MultipartFileResolver;
import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionFileRepository;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceExceptionRepository;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;
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
	public void insertAttendanceWithoutTime(Attendance attendance) {
		attendanceRepository.insertAttendanceWithoutTime(attendance);
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
	public Attendance selectAttendanceById(int attendanceId) {
		return attendanceRepository.selectAttendanceById(attendanceId);
		
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
	public List<Attendance> getPersonalAttendanceList(String memberId) {
		return attendanceRepository.getPersonalAttendanceList(memberId);
	}

	@Override
	public int getAttendanceCount(String memberId, String status, Date sqlDate, Date startDate) {
		return attendanceRepository.getAttendanceCount(memberId, status, sqlDate, startDate);
	}

	@Override
	public Attendance selectAttendanceByDate(String studentId, Date sqlDate) {
		return attendanceRepository.selectAttendanceByDate(studentId, sqlDate);
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
		AttendanceException attendanceException = attendanceExceptionRepository.getAttendanceExceptionByAttendanceExceptionId(attendanceExceptionId);
		model.addAttribute("attendanceException", attendanceException);
		
		List<AttendanceExceptionFile> attendanceExceptionFiles = attendanceExceptionFileRepository.getAttendanceExceptionFilesByExceptionId(attendanceExceptionId);
		model.addAttribute("attendanceExceptionFiles", attendanceExceptionFiles);
		
		return model;
	}
	
	@Override
	@Transactional
	public void manageAttendance(AttendanceException attendanceException, Date attendanceExceptionDate) {
		//승인 되면 Attendance를 기록
		if (attendanceException.getAttendanceExceptionApproved() == true) {
			int attendanceId = attendanceException.getAttendanceId();
			
			//미래에 발생 할 (또는 과거에 기록 된) 출결 예외 인정 처리
			if (attendanceId < 0)  {
				Date date = ServerTimeSupplier.timestampToDate(attendanceException.getAttendanceExceptionApplyDate());
				Attendance attendance = attendanceRepository.selectAttendanceByDate(attendanceException.getMemberId(), date);
				
				//신청 날짜에  출근 기록이 존재하면 insert가 아니라 update를 해야 함
				if (attendance == null) {
					attendance = new Attendance();
					attendance.setMemberId(attendanceException.getMemberId());
					attendance.setAttendanceDate(attendanceExceptionDate);
					attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
					attendanceRepository.insertAttendanceWithoutTime(attendance);
				}
				else {
					attendance.setMemberId(attendanceException.getMemberId());
					attendance.setAttendanceDate(attendanceExceptionDate);
					attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
					attendanceRepository.updateAttendanceStatus(attendance);
				}
			}
			
			//과거에 기록 된 출결에서 예외 인정 처리 
			else {
				Attendance attendance = new Attendance();
				attendance.setMemberId(attendanceException.getMemberId());
				attendance.setAttendanceDate(attendanceException.getAttendanceExceptionDate());
				attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
				attendanceRepository.updateAttendanceStatus(attendance);
			}
		}
		attendanceExceptionRepository.updateAttendanceException(attendanceException);
	}


}
