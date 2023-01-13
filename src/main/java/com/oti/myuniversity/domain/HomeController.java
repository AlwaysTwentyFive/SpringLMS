package com.oti.myuniversity.domain;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;
import com.oti.myuniversity.domain.member.model.Member;

@Controller
public class HomeController {
	
	@Autowired
	IAttendanceService attendanceService;

	@RequestMapping(value = "/home")
	public String home(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String memberId = member.getMemberId();	
		Attendance attendance = attendanceService.selectAttendanceByDate(memberId, ServerTimeSupplier.getDate());
		model.addAttribute("day", ServerTimeSupplier.getDayOfWeek());
		//출근 기록 있음
		if (attendance != null) {
			Timestamp arriveTime = attendance.getAttendanceArriveTime();
			Timestamp departTime = attendance.getAttendanceDepartTime();
			String status = attendance.getAttendanceStatus();
			boolean isAttend = attendanceService.checkAttendance(memberId, attendance.getAttendanceDate());
			model.addAttribute("arriveTime", arriveTime);
			model.addAttribute("departTime", departTime);
			model.addAttribute("isAttend", isAttend);
			model.addAttribute("status", status);
		}
		//출근 기록 없음
		else {
			Timestamp arriveTime = null;
			Timestamp departTime = null;
			String status = "출근";
			model.addAttribute("arriveTime", arriveTime);
			model.addAttribute("departTime", departTime);
			model.addAttribute("isAttend", false);
			model.addAttribute("status", status);
		}
	
		return "home";
	} 
	
}
