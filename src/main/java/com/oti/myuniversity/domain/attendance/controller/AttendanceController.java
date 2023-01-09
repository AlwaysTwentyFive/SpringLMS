package com.oti.myuniversity.domain.attendance.controller;

import static com.oti.myuniversity.common.Consts.PAGES_PER_GROUP;
import static com.oti.myuniversity.common.Consts.ROWS_PER_PAGE;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.component.AttendPolicy;
import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;

@Controller
public class AttendanceController {
	static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IAttendanceService attendanceService;

	@Autowired
	AttendPolicy oneDayPolicy;
	
	//in com.oti.myuniversity.config.AppConfig.java
	@Autowired
	Attendance initializedAttendance;
	
	@Autowired
	Pager pager;

	@RequestMapping(value = "/attendance/attend", method = RequestMethod.POST)
	public String attend(HttpSession session, RedirectAttributes redirectAttrs) {
		initializedAttendance.setAttendanceStatus("출근");

		attendanceService.insertAttendance(initializedAttendance);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/attend/leave", method = RequestMethod.POST)
	public String leave(HttpSession session, RedirectAttributes redirectAttrs) {
		String check = oneDayPolicy.evaluateAttend(initializedAttendance);
		initializedAttendance.setAttendanceStatus(check);
		
		attendanceService.updateTimeStatus(initializedAttendance);

		return "redirect:/home";
	}
	
	@RequestMapping(value="/attend/list/{pageNo}")
	public String getAttendanceList(HttpSession session, Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceService.getTotalAttendanceCount(), pageNo);
		model.addAttribute("attendanceList", attendanceService.getTotalAttendance(pager));
		model.addAttribute("pager", pager);
		return "attendance/totalList";
	}

}
