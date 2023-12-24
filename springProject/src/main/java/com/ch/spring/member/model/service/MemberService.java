package com.ch.spring.member.model.service;

import com.ch.spring.member.model.vo.CertVO;
import com.ch.spring.member.model.vo.Member;

public interface MemberService {
	

	// 로그인 회원가입 정보수정 회원탈퇴
	Member loginMember(Member m);
	
	int insertMember(Member m);
	
	int updateMember(Member m);
	
	int deleteMember(String userId);
		
	// 중복체크
	int ajaxIdCheck(String checkId);

	// 메일인증
	void insertSecret(CertVO certVo);
	
	boolean validate(CertVO certVo);

}
