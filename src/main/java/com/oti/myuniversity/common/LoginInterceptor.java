package com.oti.myuniversity.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oti.myuniversity.domain.member.model.Member;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			String contextName = request.getContextPath();
			String url = request.getRequestURI().replaceFirst(contextName, "");
			String param = request.getQueryString();
			session.setAttribute("url", url);
			session.setAttribute("param", param);
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false;
		}
		else {
			System.out.println("[Session]: " + member);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
