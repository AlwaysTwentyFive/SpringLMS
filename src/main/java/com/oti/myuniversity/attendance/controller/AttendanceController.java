package com.oti.myuniversity.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.attendance.model.Attendance;
import com.oti.myuniversity.attendance.repository.IAttendanceRepository;
import com.oti.myuniversity.attendance.service.IAttendanceService;
import com.oti.myuniversity.component.AttendPolicy;
import com.oti.myuniversity.member.model.Member;

@Controller
public class AttendanceController {
	static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	@Autowired
	IAttendanceRepository attendanceRepository;
	
	@Autowired
	IAttendanceService attendanceService;
	
	@Autowired
	AttendPolicy oneDayPolicy;
	
	@RequestMapping(value="/attendance/attend" ,method = RequestMethod.POST)
	public String attend(HttpSession session, RedirectAttributes redirectAttrs) {	
		//오늘 날짜와 시간 가져와서 sqlDate로 변환
		Date utilDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String formattedDate = dateFormat.format(utilDate);
		String formattedTime = timeFormat.format(utilDate);
		
		java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
		java.sql.Time sqlTime = java.sql.Time.valueOf(formattedTime);
		
		Member member = (Member) session.getAttribute("member");
		//select where = 날짜 비교, 아이디 비교
		Attendance attendance = new Attendance();
		
		if(attendanceService.checkAttendance(member.getMemberId(), sqlDate)==1) {
			//있다면
			//update(퇴근) 진행
			attendance = attendanceService.selectAttendance(member.getMemberId(), sqlDate);
			System.out.println(attendance.getAttendanceArriveTime());
			attendance.setAttendanceDepartTime(sqlTime);
			String check = oneDayPolicy.evaluateAttend(attendance);
			attendance.setAttendanceStatus(check);
			attendanceService.updateTimeStatus(attendance);
			
			
		} else {
			//만약에 select한 값이 없다면 
			//insert(출근) 진행	
			attendance.setAttendanceArriveTime(sqlTime);
			attendance.setMemberId(member.getMemberId());
			attendance.setAttendanceDate(sqlDate);
			attendance.setAttendanceStatus("출근");
			attendanceService.insertAttendance(attendance);
		}
		
		
		
		
		
		
		
		
		return "redirect:/home";
	}
	
}
