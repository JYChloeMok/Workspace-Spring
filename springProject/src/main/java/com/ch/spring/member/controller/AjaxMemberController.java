package com.ch.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AjaxMemberController {
	
	
	private final MemberService memberService;
	
	
	@ResponseBody // 그냥 String리턴하면 viewResolver에서 찾기때문에 body임을 명시
	@GetMapping("ajaxIdCheck.me")
	public String ajaxIdCheck(String checkId) {
		return memberService.ajaxIdCheck(checkId) > 0 ? "N" : "Y";
	}

}
