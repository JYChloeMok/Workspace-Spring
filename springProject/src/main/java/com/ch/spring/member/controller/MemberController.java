package com.ch.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch.spring.member.model.service.MemberService;
import com.ch.spring.member.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser != null && bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg", "로그인 실패")
			  .setViewName("common/errorPage");
		}
		return mv;
	}
	// 매개변수 m의 userId필드 : 사용자가 입력한 아이디
	// 매개변수 m의 userPwd필드 : 사용자가 입력한 비밀번호(평문)
	// ----- DB에 다녀온 후 -----
	// loginUser의 userId필드 : 조회된 아이디
	// loginUser의 userPwd필드 : 암호화되어 DB에 저장된 비밀번호
	
	// 암호문은 복호화 불가능
	
	
	
	
	
	
	
}
