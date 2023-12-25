package com.ch.spring.notice.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ch.spring.common.model.vo.PageInfo;
import com.ch.spring.notice.model.dao.NoticeDao;
import com.ch.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	private final SqlSessionTemplate sqlSession;
	
	private final NoticeDao noticeDao;
	
	@Override
	public int selectListCount() {
		return noticeDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Notice> selectList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); // 시작하는 숫자
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit()); // 몇개씩 읽을지 limit
		return noticeDao.selectList(sqlSession, rowBounds);
	}

	@Override
	public int insertNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotice(Notice n) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
