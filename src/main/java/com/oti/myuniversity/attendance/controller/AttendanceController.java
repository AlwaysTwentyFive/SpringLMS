package com.oti.myuniversity.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.attendance.model.Attendance;
import com.oti.myuniversity.attendance.repository.IAttendanceRepository;
import com.oti.myuniversity.attendance.service.IAttendanceService;
import com.oti.myuniversity.member.model.Member;

@Controller
public class AttendanceController {
	static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	@Autowired
	IAttendanceRepository attendanceRepository;
	
	@Autowired
	IAttendanceService attendanceService;
	
	@RequestMapping(value="/attendance/attend" ,method = RequestMethod.POST)
	public String attend(HttpSession session, RedirectAttributes redirectAttrs) {
		Date utilDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(utilDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
		Member member = (Member) session.getAttribute("member");
		//select where = 날짜 비교, 아이디 비교
		
		//만약에 select한 값이 없다면 
		//insert(출근) 진행
		Attendance attendance = new Attendance();
		attendance.setAttendanceArriveTime(sqlDate);
		attendance.setMemberId(member.getMemberId());
		attendanceService.insertAttendance(attendance);
		
		//있다면
		//update(퇴근) 진행
		
		
		
		
		return "redirect:/home";
	}
}
