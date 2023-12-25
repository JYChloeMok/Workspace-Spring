package com.ch.spring.notice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ch.spring.common.model.vo.PageInfo;
import com.ch.spring.common.template.Pagination;
import com.ch.spring.notice.model.service.NoticeService;
import com.ch.spring.notice.model.vo.Notice;

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
	
	
	@GetMapping("noticeEnrollForm.no")
	public String noticeEnrollForm() {
		return "notice/NoticeEnrollForm";
	}
	
	@PostMapping("insert.no")
	public String insertNotice(Notice n, HttpSession session) {
		if(noticeService.insertNotice(n) > 0) {
			return "redirect:list.no";
		} else {
			session.setAttribute("errorMsg", "작성에 실패하였습니다.");
			return "common/errorPage";
		}
	}
	
	@GetMapping("detail.no")
	public ModelAndView selectNotice(@RequestParam(value="cPage", defaultValue="1") int cPage, int nno, ModelAndView mv) {
		Notice n = noticeService.selectNotice(nno);
		if(null != n) {
			mv.addObject("n", n)
			  .addObject(cPage)
			  .setViewName("notice/noticeDetailview");
		} else {
			mv.addObject("errorMsg", "상세 조회에 실패하였습니다.")
			  .setViewName("common/errorPage");
		}
		return mv;
	}
	
	@PostMapping("delete.no")
	public String deleteNotice(@RequestParam(value="cPage", defaultValue="1") int cPage, int nno, HttpSession session) {
		if(noticeService.deleteNotice(nno) > 0) {
			session.setAttribute("alertMsg", "삭제 성공!");
			return "redirect:list.no?cPage=" + cPage;
		} else {
			session.setAttribute("errorMsg", "상세 조회 실패...");
			return "common/errorPage";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
