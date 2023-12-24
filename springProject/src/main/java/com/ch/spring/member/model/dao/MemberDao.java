package com.ch.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ch.spring.member.model.vo.CertVO;
import com.ch.spring.member.model.vo.Member;

@Repository
public class MemberDao {

	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.deleteMember", userId);
	}
	
	
	
	public int ajaxIdCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.ajaxIdCheck", checkId);
	}


	public void insertSecret(SqlSessionTemplate sqlSession, CertVO certVo) {
		sqlSession.insert("memberMapper.insertSecret", certVo);
	}


	public int validate(SqlSessionTemplate sqlSession, CertVO certVo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
