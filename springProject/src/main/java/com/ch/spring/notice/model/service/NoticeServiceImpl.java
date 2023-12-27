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
	public int insertNotice(Notice n) {
		return noticeDao.insertNotice(sqlSession, n);
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return noticeDao.selectNotice(sqlSession, noticeNo);
	}

	/**
	 * 공지사항은 Status컬럼 없으므로 그냥 삭제(DELETE)
	 */
	@Override
	public int deleteNotice(int noticeNo) {
		return noticeDao.deleteNotice(sqlSession, noticeNo);
	}

	@Override
	public int updateNotice(Notice n) {
		return noticeDao.updateNotice(sqlSession, n);
	}

	
	
}
