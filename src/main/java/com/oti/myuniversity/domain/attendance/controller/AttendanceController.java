package com.oti.myuniversity.domain.attendance.controller;

import static com.oti.myuniversity.common.Consts.PAGES_PER_GROUP;
import static com.oti.myuniversity.common.Consts.ROWS_PER_PAGE;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oti.myuniversity.component.AttendPolicy;
import com.oti.myuniversity.component.MultipartFileResolver;
import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;
import com.oti.myuniversity.domain.attendance.service.IAttendanceExceptionFileService;
import com.oti.myuniversity.domain.attendance.service.IAttendanceExceptionService;
import com.oti.myuniversity.domain.board.model.Board;
import com.oti.myuniversity.domain.board.service.IBoardService;
import com.oti.myuniversity.domain.member.model.Member;

@Controller
public class AttendanceController {
	static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@Autowired
	IAttendanceService attendanceService;
	
	@Autowired
	IAttendanceExceptionService attendanceExceptionService;
	
	@Autowired
	IAttendanceExceptionFileService attendanceExceptionFileService;
	
	@Autowired
	IBoardService boardService;

	@Autowired
	AttendPolicy oneDayPolicy;
	
	//in com.oti.myuniversity.config.AppConfig.java
	@Autowired
	Attendance initializedAttendance;
	
	@Autowired
	Pager pager;

	@RequestMapping(value = "/attendance/attend", method = RequestMethod.POST)
	public String attend(HttpSession session) {
		initializedAttendance.setAttendanceStatus("출근");
		Member member = (Member) session.getAttribute("member");
		initializedAttendance.setMemberId(member.getMemberId());
		attendanceService.insertAttendance(initializedAttendance);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/attendance/leave", method = RequestMethod.POST)
	public String leave(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		initializedAttendance.setMemberId(member.getMemberId());
		String check = oneDayPolicy.evaluateAttend(initializedAttendance);
		initializedAttendance.setAttendanceStatus(check);
		attendanceService.updateTimeStatus(initializedAttendance);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/attendance/list/{pageNo}")
	public String getAttendanceList(Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceService.getTotalAttendanceCount(), pageNo);
		model.addAttribute("attendanceList", attendanceService.getTotalAttendance(pager));
		model.addAttribute("pager", pager);
		return "attendance/totalList";
	}
	
	@GetMapping("/attendance/write")
	public String applyException(Model model, Integer attendanceId) {
		if (attendanceId == null) {
			attendanceId = -1;
		}
		model.addAttribute("attendanceId", attendanceId);
		return "attendance/write";
	}
	
	@PostMapping("/attendance/write")
	public String applyException(HttpSession session, AttendanceException attendanceException, String date, String time, MultipartFile[] attendanceExceptionFiles) throws IOException {
		ServerTimeSupplier.setTime();
		attendanceException.setAttendanceExceptionDate(ServerTimeSupplier.getDate());
		attendanceException.setAttendanceExceptionApplyDate(Date.valueOf(date + " " + time + ":00"));
		
		if (attendanceException.getAttendanceId() > 0) {
			int attendanceExceptionId = attendanceService.applyException(attendanceException, attendanceExceptionFiles);
			return "redirect:/attendance/exception/" + attendanceExceptionId;
		}
		else {
			Attendance attendance = new Attendance();
			Member member = (Member) session.getAttribute("member");
			attendance.setMemberId(member.getMemberId());
			attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
			attendance.setAttendanceDate(attendanceException.getAttendanceExceptionApplyDate());
			attendanceService.insertAttendance(attendance);
			attendanceException.setAttendanceId(attendanceService.getMaxAttendanceId() + 1);
			int attendanceExceptionId = attendanceService.applyException(attendanceException, attendanceExceptionFiles);
			return "redirect:/attendance/exception/" + attendanceExceptionId;
		}

	}
	
	@GetMapping("/attendance/exception/{attendanceExceptionId}")
	public String getAttendance(Model model, @PathVariable int attendanceExceptionId) {
		attendanceService.getAttendance(attendanceExceptionId, model);
		return "attendance/view";
	}
	
	@PostMapping("/attendance/manage")
	public String manageException(HttpSession session, AttendanceException attendanceException, String memberId, int boardId) {
		Attendance attendance = new Attendance();
		attendance.setMemberId(memberId);
		attendance.setAttendanceDate(attendanceException.getAttendanceExceptionApplyDate());
		attendanceService.manageAttendance(attendance, attendanceException);
		return "redirect:/attendance/exception/" + boardId;
	}
	
	@GetMapping("/attendance/exception")
	public String getExceptionList() {
		return "redirect:/attendance/exception/1";
	}
	
	@GetMapping("/attendance/exception/{pageNo}")
	public String getExceptionList(Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceExceptionService.getTotalAttendanceExceptionCount(), pageNo);
		model.addAttribute("attendanceExceptionList", attendanceExceptionService.getTotalAttendanceException(pager));
		model.addAttribute("pager", pager);
		return "attendance/excpetionlist";
	}
}
