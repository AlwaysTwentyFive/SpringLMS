package com.oti.myuniversity.domain.attendance.controller;

import static com.oti.myuniversity.common.Consts.PAGES_PER_GROUP;
import static com.oti.myuniversity.common.Consts.ROWS_PER_PAGE;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oti.myuniversity.common.Consts;
import com.oti.myuniversity.component.AttendPolicy;
import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.service.IAttendanceExceptionFileService;
import com.oti.myuniversity.domain.attendance.service.IAttendanceExceptionService;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;
import com.oti.myuniversity.domain.board.service.IBoardService;
import com.oti.myuniversity.domain.member.model.Member;
import com.oti.myuniversity.domain.member.service.IMemberService;

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
	
	@Autowired
	IMemberService memberSerivce;
	
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
		String check = oneDayPolicy.evaluateAttendTemp(initializedAttendance);
		initializedAttendance.setAttendanceStatus(check);
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
	
	@RequestMapping(value="/attendance/view/{attendanceId}", method = RequestMethod.GET)
	public @ResponseBody Attendance dataToJson(@PathVariable int attendanceId, Date sqlDate) {
		Attendance attendance = attendanceService.selectAttendanceById(attendanceId);
		return attendance;
	}
	
	@RequestMapping(value="/attendance/totalList/{studentId}")
	public String getPersonalAttendanceList(HttpSession session, Model model, @PathVariable String studentId) {
		//한 사람의 모든 출결 가져옴
		LinkedList<Attendance> personalList = (LinkedList<Attendance>) attendanceService.getPersonalAttendanceList(studentId);
		//시작 날짜 localDate로 변환
		LocalDate date = Consts.CLASS_START_DATE.toLocalDate();
		//주차별 출결 담을 list 선언
		LinkedList<LinkedList<Attendance>> totalList = new LinkedList<LinkedList<Attendance>>();
		//하나의 주차의 출결 담을 list 선언 
		LinkedList<Attendance> weekList = new LinkedList<Attendance>();
		//한 학생의 모든 list 꺼냄
		ServerTimeSupplier.setTime();
		LocalDate today = ServerTimeSupplier.getDate().toLocalDate();
		int count = 0;
		
		while(date.compareTo(today)<=0 && !(personalList.isEmpty())){		
			//숫자 요일을 구하기 위해 dayOfWeek 객체로 변환
			DayOfWeek dayOfWeek = date.getDayOfWeek();	 	
			//숫자 요일 구하기
	        int dayOfWeekNumber = dayOfWeek.getValue();

			if(dayOfWeekNumber != 6 && dayOfWeekNumber != 7) {
				count++;
				 while(weekList.size() < dayOfWeekNumber-1) {
			        	Attendance attendance = new Attendance();
			        	weekList.add(attendance);
			        	System.out.println("weekList size : " + weekList.size());
			        }
		        //attendance 정보가 있고 기준 날짜와 출결 날짜가 같으면
		        Attendance attendance = personalList.peek();
		        System.out.println("attendance: "+ attendance);
		        LocalDate attDate = attendance.getAttendanceDate().toLocalDate();
		        System.out.println("attDate: "+ attDate);
		        if(date.compareTo(attDate)==0) {
		        	attendance = personalList.poll();
		        	weekList.add(attendance);
		        	
		        	
		        } else {
		        	Attendance absAttendance = new Attendance();
		        		absAttendance.setAttendanceArriveTime(null);
		        		absAttendance.setAttendanceDepartTime(null);
		        		absAttendance.setAttendanceStatus("결근");
			        	weekList.add(absAttendance); 	
		        }									
			
			}
			
			//만약에 일요일이면 
			if(dayOfWeekNumber == 6) {
				//하나의 주 데이터가 모인 list를 총 list에 담음
				totalList.add(weekList);
				//하나의 주 데이터 담을 list 갱신
				weekList = new LinkedList<Attendance>();
			}
			//기준날짜를 더함
			date = date.plusDays(1);
		
		}
		
		if(weekList.size()>0) {
			totalList.add(weekList);
		}
		
		System.out.println("사이즈: "+ totalList.size());
		for(List<Attendance> list : totalList) {
			System.out.println();
			System.out.println("totalList: "+ list);
			System.out.println();
		}
		
		int attCount = attendanceService.getAttendanceCount(studentId, "출근");
		int absCount = attendanceService.getAttendanceCount(studentId, "결근");
		int vacationCount = attendanceService.getAttendanceCount(studentId, "공가");
		int leaveCount = attendanceService.getAttendanceCount(studentId, "조퇴");
		int lateCount = attendanceService.getAttendanceCount(studentId, "지각");
	
		System.out.println("count: "+count);
		if(count != attCount+absCount+vacationCount+leaveCount+lateCount) {
			absCount = (count - (attCount+absCount+vacationCount+leaveCount+lateCount)) + absCount;
		}

		model.addAttribute("totalList", totalList);
		model.addAttribute("member", memberSerivce.selectMember(studentId));
		model.addAttribute("attCount",attCount);
		model.addAttribute("absCount",absCount);
		model.addAttribute("vacationCount",vacationCount);
		model.addAttribute("leaveCount",leaveCount);
		model.addAttribute("lateCount",lateCount);
		return "/attendance/list";
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
		Member member = (Member) session.getAttribute("member");
		attendanceException.setMemberId(member.getMemberId());
		attendanceException.setMemberName(member.getMemberName());
		attendanceException.setAttendanceExceptionDate(ServerTimeSupplier.getDate());
		attendanceException.setAttendanceExceptionApplyDate(Timestamp.valueOf(date + " " + time + ":00"));
		
		if (attendanceException.getAttendanceId() > 0) {
			int attendanceExceptionId = attendanceService.applyException(attendanceException, attendanceExceptionFiles);
			return "redirect:/attendance/exception/" + attendanceExceptionId;
		}
		else {
			Attendance attendance = new Attendance();
			attendance.setMemberId(member.getMemberId());
			attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
			attendance.setAttendanceDate(Date.valueOf(date));
			attendance.setAttendanceArriveTime(Timestamp.valueOf(date + " " + time + ":00"));
			attendanceException.setAttendanceId(attendanceService.getMaxAttendanceId() + 1);
			int attendanceExceptionId = attendanceService.applyException(attendance, attendanceException, attendanceExceptionFiles);
			return "redirect:/attendance/exception/" + attendanceExceptionId;
		}
	}
	
	@GetMapping("/attendance/exception/{attendanceExceptionId}")
	public String getAttendance(Model model, @PathVariable int attendanceExceptionId) {
		attendanceService.getAttendance(attendanceExceptionId, model);
		return "attendance/view";
	}
	
	@PostMapping("/attendance/manage")
	public String manageException(HttpSession session, AttendanceException attendanceException, int attendanceExceptionId, Date attendanceExceptionDate) {
		Attendance attendance = new Attendance();
		attendance.setMemberId(attendanceException.getMemberId());
		attendance.setAttendanceDate(attendanceExceptionDate);
		attendance.setAttendanceStatus(attendanceException.getAttendanceExceptionStatus());
		attendanceService.manageAttendance(attendance, attendanceException);
		return "redirect:/attendance/exception/" + attendanceExceptionId;
	}
	
	@GetMapping("/attendance/exceptionlist")
	public String getExceptionList() {
		return "redirect:/attendance/exceptionlist/1";
	}
	
	@GetMapping("/attendance/exceptionlist/{pageNo}")
	public String getExceptionList(Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceExceptionService.getTotalAttendanceExceptionCount(), pageNo);
		model.addAttribute("attendanceExceptionList", attendanceExceptionService.getTotalAttendanceException(pager));
		model.addAttribute("pager", pager);
		return "attendance/exceptionlist";
	}
}
