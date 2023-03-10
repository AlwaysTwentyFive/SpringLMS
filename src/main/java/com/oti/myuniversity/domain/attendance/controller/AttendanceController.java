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

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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

	// in com.oti.myuniversity.config.AppConfig.java
	@Autowired
	Attendance initializedAttendance;

	@Autowired
	Pager pager;

	@RequestMapping(value = "/attendance/attend", method = RequestMethod.POST)
	public String attend(HttpSession session) {
		initializedAttendance.setAttendanceStatus("??????");
		Member member = (Member) session.getAttribute("member");
		initializedAttendance.setMemberId(member.getMemberId());
		String check = oneDayPolicy.evaluateAttendTemp(initializedAttendance);
		initializedAttendance.setAttendanceStatus(check);
		attendanceService.insertAttendance(initializedAttendance);
		return "redirect:/home";
	}

	@RequestMapping(value = "/attendance/leave", method = RequestMethod.POST)
	public String leave(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		initializedAttendance.setMemberId(member.getMemberId());
		String check = oneDayPolicy.evaluateAttend(initializedAttendance);
		initializedAttendance.setAttendanceStatus(check);
		attendanceService.updateTimeStatus(initializedAttendance);
		return "redirect:/home";
	}

	@RequestMapping(value = "/attendance/list")
	public String getAttendanceList(Model model) {
		return getAttendanceList(model, 1);
	}

	@RequestMapping(value = "/attendance/list/{pageNo}")
	public String getAttendanceList(Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceService.getTotalAttendanceCount(), pageNo);
		model.addAttribute("attendanceList", attendanceService.getTotalAttendance(pager));
		model.addAttribute("pager", pager);
		return "attendance/totalList";
	}

	@RequestMapping(value = "/attendance/view/{attendanceId}", method = RequestMethod.POST)
	public @ResponseBody Attendance dataToJson(@PathVariable int attendanceId, @RequestParam("attendanceStatus") String status ,Date sqlDate, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		System.out.println(attendanceId);
		Attendance attendance = attendanceService.selectAttendanceById(attendanceId);
		
		if(attendance != null) {
			attendance = attendanceService.selectAttendanceById(attendanceId);
		} else {
			attendance = new Attendance();
			if(status.equals("??????")) {
				attendance.setAttendanceStatus("??????");			
			} else if (status.equals("??????")) {
				attendance.setAttendanceStatus("??????");	
			} else if (status.equals("??????")) {
				attendance.setAttendanceStatus("??????");	
			} else if (status.equals("??????")) {
				attendance.setAttendanceStatus("??????");	
			} else if (status.equals("??????")) {
				attendance.setAttendanceStatus("??????");	
			} else {
				attendance.setAttendanceStatus("??????");	
			}
		}
		
		attendance.setMemberType(member.getMemberType());
		
		
		return attendance;
	}

	@RequestMapping(value = "/attendance/checkException/{attendanceId}", method = RequestMethod.GET)
	public @ResponseBody String getExceptionByAttId(@PathVariable int attendanceId) {

		AttendanceException attendanceException = attendanceExceptionService
				.getAttendanceExceptionByAttendanceId(attendanceId);
		System.out.println(attendanceException);
		String result = "fail";
		if (attendanceException != null) {
			result = Integer.toString(attendanceException.getAttendanceExceptionId());
		}
		return result;

	}

	@RequestMapping(value = "/attendance/readException/{attendanceId}", method = RequestMethod.GET)
	public String getExceptionByAttId(@PathVariable int attendanceId, Model model) {

		AttendanceException attendanceException = attendanceExceptionService
				.getAttendanceExceptionByAttendanceId(attendanceId);
		System.out.println("attendanceExcetion: " + attendanceException);
		return getAttendanceException(model, attendanceException.getAttendanceExceptionId());

	}

	@RequestMapping(value = "/attendance/totalList/{studentId}")
	public String getPersonalAttendanceList(Model model, @PathVariable String studentId) {
		// ??? ????????? ?????? ?????? ?????????
		LinkedList<Attendance> personalList = (LinkedList<Attendance>) attendanceService
				.getPersonalAttendanceList(studentId);
		// ?????? ?????? localDate??? ??????
		LocalDate date = Consts.CLASS_START_DATE.toLocalDate();
		Date startDate = Consts.CLASS_START_DATE;
		// ????????? ?????? ?????? list ??????
		LinkedList<LinkedList<Attendance>> totalList = new LinkedList<LinkedList<Attendance>>();
		// ????????? ????????? ?????? ?????? list ??????
		LinkedList<Attendance> weekList = new LinkedList<Attendance>();
		// ??? ????????? ?????? list ??????
		ServerTimeSupplier.setTime();
		Date sqlToday = ServerTimeSupplier.getDate();
		LocalDate today = ServerTimeSupplier.getDate().toLocalDate();
		int count = 0;

		while (date.compareTo(today) <= 0 ) {
			// ?????? ????????? ????????? ?????? dayOfWeek ????????? ??????
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			// ?????? ?????? ?????????
			int dayOfWeekNumber = dayOfWeek.getValue();

			if (dayOfWeekNumber != 6 && dayOfWeekNumber != 7) {
				count++;
				while (weekList.size() < dayOfWeekNumber - 1) {
					Attendance attendance = new Attendance();
					weekList.add(attendance);
				}

				// attendance ????????? ?????? ?????? ????????? ?????? ????????? ?????????
				LocalDate attDate;
				Attendance attendance = null;
				if (!(personalList.isEmpty())) {
					attendance = personalList.peek();
					attDate = attendance.getAttendanceDate().toLocalDate();
				} else {
					attDate = date;
				}

				if (date.compareTo(attDate) == 0) {
					if (attendance != null) {
						attendance = personalList.poll();
					} else {
						attendance = new Attendance();
						Date absDate = Date.valueOf(date);
						attendance.setAttendanceDate(absDate);
						attendance.setAttendanceArriveTime(null);
						attendance.setAttendanceDepartTime(null);
						attendance.setAttendanceStatus("??????");
					}
					weekList.add(attendance);
				} else {
					Attendance absAttendance = new Attendance();
					Date absDate = Date.valueOf(date);
					absAttendance.setAttendanceDate(absDate);
					absAttendance.setAttendanceArriveTime(null);
					absAttendance.setAttendanceDepartTime(null);
					absAttendance.setAttendanceStatus("??????");
					weekList.add(absAttendance);
				}
			}
			// ????????? ???????????????
			if (dayOfWeekNumber == 6) {
				// ????????? ??? ???????????? ?????? list??? ??? list??? ??????
				totalList.add(weekList);
				// ????????? ??? ????????? ?????? list ??????
				weekList = new LinkedList<Attendance>();
			}
			// ??????????????? ??????
			date = date.plusDays(1);
			
		}

		if (weekList.size() > 0) {
			totalList.add(weekList);
		}
		for (List<Attendance> list : totalList) {
			System.out.println();
			System.out.println("totalList: " + list);
			System.out.println();
		}
		int attCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday, startDate);
		int absCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		int vacationCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		int leaveCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		int lateCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		int sickCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		int goOutCount = attendanceService.getAttendanceCount(studentId, "??????",sqlToday,startDate);
		
		if (count != attCount + absCount + vacationCount + leaveCount + lateCount + sickCount + goOutCount) {
			absCount = (count - (attCount + absCount + vacationCount + leaveCount + lateCount  + sickCount + goOutCount)) + absCount;
		}
		model.addAttribute("totalList", totalList);
		model.addAttribute("member", memberSerivce.selectMember(studentId));
		model.addAttribute("attCount", attCount);
		model.addAttribute("absCount", absCount);
		model.addAttribute("vacationCount", vacationCount);
		model.addAttribute("leaveCount", leaveCount);
		model.addAttribute("lateCount", lateCount);
		model.addAttribute("sickCount", sickCount);
		model.addAttribute("goOutCount", goOutCount);
		return "attendance/list";
	}

	@GetMapping("/attendance/write")
	public String applyException() {
		return "attendance/write";
	}

	@PostMapping("/attendance/write")
	public String applyException(HttpSession session, AttendanceException attendanceException, String date, String time,
			MultipartFile[] attendanceExceptionFiles) throws IOException {
		Member member = (Member) session.getAttribute("member");
		attendanceException.setMemberId(member.getMemberId());
		attendanceException.setMemberName(member.getMemberName());
		ServerTimeSupplier.setTime();
		attendanceException.setAttendanceExceptionDate(ServerTimeSupplier.getDate());
		attendanceException.setAttendanceExceptionApplyDate(Timestamp.valueOf(date + " " + time + ":00"));

		int attendanceExceptionId = attendanceService.applyException(attendanceException, attendanceExceptionFiles);
		return "redirect:/attendance/exception/" + attendanceExceptionId;
	}

	@PostMapping(value = "/attendance/update", produces = "application/text; charset=UTF-8")
	public @ResponseBody String updateStatus(@RequestParam("status") String status,
			@RequestParam("attendanceId") int attendanceId, @RequestParam("date") String date,
			@RequestParam("memberId") String memberId, HttpServletResponse response) {
		Attendance attendance = new Attendance();
		// database??? ????????? ????????? ?????????
		if (attendanceId == 0) {
			Date attDate = Date.valueOf(date);
			attendance.setAttendanceStatus(status);
			attendance.setAttendanceDate(attDate);
			attendance.setMemberId(memberId);
			
			attendanceService.insertAttendanceWithoutTime(attendance);

		} else {
			// update ??????
			attendance.setMemberId(memberId);
			Date attDate = Date.valueOf(date);
			attendance.setAttendanceDate(attDate);
			attendance.setAttendanceStatus(status);
			attendanceService.updateAttendanceStatus(attendance);
		}

		response.setCharacterEncoding("UTF-8");
		return status;
	}

	@GetMapping("/attendance/exception/{attendanceExceptionId}")
	public String getAttendanceException(Model model, @PathVariable int attendanceExceptionId) {
		attendanceService.getAttendance(attendanceExceptionId, model);
		return "attendance/view";
	}

	@PostMapping("/attendance/manage")
	public String manageException(AttendanceException attendanceException, int attendanceExceptionId, Date attendanceExceptionDate) {
		attendanceService.manageAttendance(attendanceException, attendanceExceptionDate);
		return "redirect:/attendance/exception/" + attendanceExceptionId;
	}

	@GetMapping("/attendance/exceptionlist")
	public String getExceptionList(Model model) {
		return getExceptionList(model, 1);
	}

	@GetMapping("/attendance/exceptionlist/{pageNo}")
	public String getExceptionList(Model model, @PathVariable int pageNo) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, attendanceExceptionService.getTotalAttendanceExceptionCount(),
				pageNo);
		model.addAttribute("attendanceExceptionList", attendanceExceptionService.getTotalAttendanceException(pager));
		model.addAttribute("pager", pager);
		return "attendance/exceptionlist";
	}
}
