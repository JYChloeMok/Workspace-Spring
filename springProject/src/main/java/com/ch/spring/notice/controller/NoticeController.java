package com.ch.spring.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ch.spring.common.model.vo.PageInfo;
import com.ch.spring.common.template.Pagination;
import com.ch.spring.notice.model.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	@GetMapping("list.no")
	public ModelAndView selectList(@RequestParam(value="cPage", defaultValue="1") int currentPage, ModelAndView mv) {
		PageInfo pi = Pagination.getPageInfo(noticeService.selectListCount(), currentPage, 5, 5);
		mv.addObject("list", noticeService.selectList(pi))
		  .addObject("pi", pi)
		  .setViewName("notice/NoticeListView");
		return mv;
	}
	
	
}
