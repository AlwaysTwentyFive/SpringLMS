package com.oti.myuniversity.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		String queryString = request.getQueryString();
		String method = request.getMethod();
		if (queryString == null) {
			queryString = "";
		}
		System.out.println("========================================");
		System.out.println("[Request] " + url + queryString + " (" + method + ")");
		Map<String, String[]> map = request.getParameterMap();
		if (map != null) {
			Set<String> keyset = map.keySet();
			for (String key : keyset) {
				System.out.println("========================================");
				System.out.println("key:" + key);
				String[] values = map.get(key);
				for (String value : values) {
					System.out.println("value:" + value);
				}
			}
			System.out.println("========================================");
		}
		else {
			System.out.println("ParamterMap doesn't exist");
		}
		System.out.println("========================================");
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
