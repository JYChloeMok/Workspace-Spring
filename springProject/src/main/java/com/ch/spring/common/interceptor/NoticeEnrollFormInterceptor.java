package com.ch.spring.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ch.spring.member.model.vo.Member;

public class NoticeEnrollFormInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object preHandle) throws IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null && "admin".equals(loginUser.getUserId())) {
			return true;
		} else {
			session.setAttribute("alertMsg", "작성자 외에는 사용할 수 없습니다");
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
	}
	
}
