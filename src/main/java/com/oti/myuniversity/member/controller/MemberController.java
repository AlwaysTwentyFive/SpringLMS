package com.oti.myuniversity.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oti.myuniversity.common.Pager;
import com.oti.myuniversity.member.model.Member;
import com.oti.myuniversity.member.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	Pager pager;
	
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
	
	@GetMapping("/member/update")
	public String updateMember(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		
		if (member != null) {
			model.addAttribute("member", member);
		}
		else {
			session.invalidate();
			return "redirect:login";
		}
		return "member/update";
	}
	
	@PostMapping("/member/update")
	public String updateMember(Member member, HttpSession session, Model model) {
		String password = member.getMemberPassword();
		String repassword = member.getRepassword();
		
		if (password != null && repassword != null) {
			if (password.equals(repassword)) {
				memberService.updateMember(member);
				member = (Member) session.getAttribute("member");
				String memberId = member.getMemberId();
				model.addAttribute("member", memberService.selectMember(memberId));
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
	
	@RequestMapping(value="/member/list/{page}")
	public String getMemberList(@PathVariable int page, Model model) {
		pager.init(10, 5, memberService.getTotalMemberCount(), page);
		model.addAttribute("memberList", memberService.selectMemberList(pager));
		model.addAttribute("pager", pager);
		return "member/list";
	}
	
}