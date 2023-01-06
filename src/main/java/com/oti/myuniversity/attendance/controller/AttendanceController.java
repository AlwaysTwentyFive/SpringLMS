package com.oti.myuniversity.attendance.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import com.oti.myuniversity.component.AttendPolicy;
import com.oti.myuniversity.component.DateConverter;
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

	@Autowired
	DateConverter dateConverter;

	@RequestMapping(value = "/attendance/attend", method = RequestMethod.POST)
	public String attend(HttpSession session, RedirectAttributes redirectAttrs, String memberid) {
		// select where = 날짜 비교, 아이디 비교
		Attendance attendance = new Attendance();
		// 만약에 select한 값이 없다면
		// insert(출근) 진행
		attendance.setAttendanceArriveTime(dateConverter.getSqlTime());
		attendance.setMemberId(memberid);
		attendance.setAttendanceDate(dateConverter.getSqlDate());
		attendance.setAttendanceStatus("출근");

		attendanceService.insertAttendance(attendance);
		String arriveTime = attendance.getAttendanceArriveTime().toString().substring(11, 19);
		
		redirectAttrs.addFlashAttribute("arriveTime", arriveTime);
		redirectAttrs.addFlashAttribute("departTime", "-");

		return "redirect:/home";
	}
	

	public String leave(HttpSession session, RedirectAttributes redirectAttrs, String memberid) {
		// 있다면
		// update(퇴근) 진행
		Attendance attendance = new Attendance();
		attendance = attendanceService.selectAttendance(memberid, dateConverter.getSqlDate());
		attendance.setAttendanceDepartTime(dateConverter.getSqlTime());
		String check = oneDayPolicy.evaluateAttend(attendance);
		attendance.setAttendanceStatus(check);
		attendanceService.updateTimeStatus(attendance);

		String arriveTime = attendance.getAttendanceArriveTime().toString().substring(11, 19);
		String departTime = attendance.getAttendanceDepartTime().toString().substring(11, 19);
		redirectAttrs.addFlashAttribute("arriveTime", arriveTime);
		redirectAttrs.addFlashAttribute("departTime", departTime);

		return "redirect:/home";
	}
	

	public String check(HttpSession session, RedirectAttributes redirectAttrs) {
		Member member = (Member) session.getAttribute("member");
		if (attendanceService.checkAttendance(member.getMemberId(), dateConverter.getSqlDate()) == 1) {
			return leave(session, redirectAttrs, member.getMemberId());

		} else {
			return attend(session, redirectAttrs,member.getMemberId());

		}

	}

}
