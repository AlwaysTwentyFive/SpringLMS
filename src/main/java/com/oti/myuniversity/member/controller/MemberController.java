package com.oti.myuniversity.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oti.myuniversity.member.model.Member;
import com.oti.myuniversity.member.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
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
				//아이디가 없음
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				//아이디 있음
				if(dbPassword.equals(password)) {
					//비밀번호 일치
					member.setMemberPassword("1");
					session.setAttribute("member", member);
					return "home";
				} else {
					//비밀번호 불일치
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		} else {
			//유저가 없음
			model.addAttribute("message", "USER_NOT_FOUND");		
		}

		session.invalidate();
		return "member/login";
	}
	
}
