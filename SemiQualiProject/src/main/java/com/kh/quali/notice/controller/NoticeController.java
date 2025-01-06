package com.kh.quali.notice.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.member.model.vo.Member;
import com.kh.quali.notice.model.service.NoticeService;
import com.kh.quali.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("notices")
	public ModelAndView selectNoticeList() {
		
		Map<String, Object> map = noticeService.selectNoticeList();
		
		return mv.setViewNameAndData("notice/list", map);
	}
	
	@GetMapping("noticeInsert")
	public String insertForm() {
		return "notice/insert";
	}
	
	@PostMapping("notices")
	public ModelAndView insertNotice(Notice notice, Member memberId, HttpSession session) {
		
		noticeService.insertNotice(notice, memberId);
		
		session.setAttribute("alertMsg", "게시글 등록 성공");
		
		return mv.setViewNameAndData("redirect:/notices", null);
	}

	@GetMapping("notice/{id}")
	public ModelAndView selectNoticeNo(@PathVariable(name="id") int id) {
				
		Map<String, Object> responseData = noticeService.selectNoticeId(id);
		
		return mv.setViewNameAndData("notice/detail", responseData);
	}
	
	@PostMapping("notice/notice-update")
	public ModelAndView updateForm(int noticeNo) {
		
		Map<String, Object> responseData = noticeService.selectNoticeId(noticeNo);
		
		
		return mv.setViewNameAndData("notice/update", responseData);
	}
	
	@PostMapping("notice/update")
	public ModelAndView updateNotice(Notice notice) {
		
		noticeService.updateNotice(notice);
		
		return mv.setViewNameAndData("redirect:/notice/" + notice.getNoticeNo(), null);
	}
	
	@PostMapping("notice/delete")
	public ModelAndView deleteNotice(int noticeNo, HttpSession session) {
		noticeService.deleteNotice(noticeNo);
		
		session.setAttribute("alertMsg", "게시글이 정상적으로 삭제되었습니다");
		
		return mv.setViewNameAndData("redirect:/notices", null);
	}
	
}
