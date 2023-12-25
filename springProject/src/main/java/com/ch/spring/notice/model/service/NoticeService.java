package com.ch.spring.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ch.spring.common.model.vo.PageInfo;
import com.ch.spring.notice.model.vo.Notice;

public interface NoticeService {
	
	int selectListCount();
	
	ArrayList<Notice> selectList(PageInfo pi);
	
	int insertNotice(Notice n);
	
	Notice selectNotice(int noticeNo);
	
	int deleteNotice(int noticeNo);
	
	int updateNotice(Notice n);

	
}
