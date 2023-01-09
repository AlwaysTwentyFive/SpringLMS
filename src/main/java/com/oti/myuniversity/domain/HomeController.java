package com.oti.myuniversity.domain;

import java.sql.Date;

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
		
		Attendance attendance = attendanceService.selectAttendance(memberId, ServerTimeSupplier.getDate());
		Date arriveTime = attendance.getAttendanceArriveTime();
		Date departTime = attendance.getAttendanceDepartTime();
		model.addAttribute("arriveTime", arriveTime);
		model.addAttribute("departTime", departTime);
		
		boolean isAttend = attendanceService.checkAttendance(memberId, attendance.getAttendanceDate());
		model.addAttribute("isAttend", isAttend);
		
		return "home";
	} 
	
}
