package com.oti.myuniversity.domain.member.controller;

import static com.oti.myuniversity.common.Consts.DUMMY_PASSWORD;
import static com.oti.myuniversity.common.Consts.PAGES_PER_GROUP;
import static com.oti.myuniversity.common.Consts.ROWS_PER_PAGE;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.attendance.service.IAttendanceService;
import com.oti.myuniversity.domain.member.model.Member;
import com.oti.myuniversity.domain.member.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	IAttendanceService attendService;
	
	@Autowired
	Pager pager;
	
	private void resetSession(HttpSession session, Member member) {
		member.setMemberPassword(DUMMY_PASSWORD);
		session.setAttribute("member", member);
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(String memberId, String password, HttpSession session, Model model) {
		Member member = memberService.selectMember(memberId);
		
		if(member != null) {
			String dbPassword = member.getMemberPassword();
			if(dbPassword == null) {
				//비밀번호가 없음
				model.addAttribute("message", "비밀번호가 없습니다(?).");
			} else {
				//아이디 있음
				if(dbPassword.equals(password)) {
					//비밀번호 일치
					resetSession(session, member);

					return "redirect:/home";
				} else {
					//비밀번호 불일치
					model.addAttribute("message", "아이디 또는 비밀번호가 불일치 합니다.");
				}
			}
		} else {
			//유저가 없음
			model.addAttribute("message", "해당 아이디가 존재하지 않습니다.");		
		}

		return "member/login";
	}
	
	@GetMapping("/member/update")
	public String updateMember(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		
		if (member != null) {
			model.addAttribute("member", member);
		}
		else {
			session.invalidate();
			return "redirect:/login";
		}
		return "member/update";
	}
	
	@PostMapping("/member/update")
	public String updateMember(Member member, HttpSession session, Model model) {
		String password = member.getMemberPassword();
		String repassword = member.getRepassword();
		
		if (password != null && repassword != null) {
			if (password.equals(repassword)) {
				//update Member
				memberService.updateMember(member);
				
				//get member id from session 
				//to select updated member data
				String memberId = ((Member) session.getAttribute("member")).getMemberId();
				member = memberService.selectMember(memberId);
				
				//reset session with new member data
				resetSession(session, member);
				
				model.addAttribute("member", member);
				return "home";
			}
			else {
				System.out.println("!password.equals(repassword)");
				return "member/update";
			}
		}
		else {
			System.out.println("password == null || repassword == null");
			return "member/update";
		}
		
	}
	
	@RequestMapping(value="/member/list")
	public String getMemberList(Model model) {
		return getMemberList(1, model);
	}
	
	@RequestMapping(value="/member/list/{pageNo}")
	public String getMemberList(@PathVariable int pageNo, Model model) {
		pager.init(ROWS_PER_PAGE, PAGES_PER_GROUP, memberService.getTotalMemberCount(), pageNo);
		model.addAttribute("memberList", memberService.selectMemberList(pager));
		model.addAttribute("pager", pager);
		return "member/list";
	}
	
}