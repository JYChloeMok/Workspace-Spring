package com.ch.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ch.spring.member.model.dao.MemberDao;
import com.ch.spring.member.model.vo.CertVO;
import com.ch.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	// 빈등록, 자동 자원관리
	
	private final MemberDao memberDao;
	private final SqlSessionTemplate sqlSession;

	
	@Override
	public Member loginMember(Member m) {
		return memberDao.loginMember(sqlSession, m);
	}


	@Override
	public int insertMember(Member m) {
		return memberDao.insertMember(sqlSession, m);
	}


	@Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);
	}


	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public int ajaxIdCheck(String checkId) {
		return memberDao.ajaxIdCheck(sqlSession, checkId);
	}

	
	@Override
	public void insertSecret(CertVO certVo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean validate(CertVO certVo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
