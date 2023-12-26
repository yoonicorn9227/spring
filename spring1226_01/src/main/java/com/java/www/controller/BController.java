package com.java.www.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class BController {
	@Autowired
	BService bService;
	@Autowired
	HttpSession session;

	// 1.공지사항 리스트
	@GetMapping("notice")
	public String notice(@RequestParam(defaultValue = "1") int page, Model model) {
		// page가지고 와야함

		// Service 연결 - list
		ArrayList<BoardDto> list = bService.selectAll();

		// Model 저장후 전송
		model.addAttribute("list", list);

		return "customer/notice";
	}// notice

	// 2.공지사항 게시글 1개
	@GetMapping("notice_view")
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model) {
		// Service 연결 - Map
		Map<String, Object> map = bService.selectOne(bno);

		// Model 저장 후 전송
		model.addAttribute("map", map);

		return "customer/notice_view";
	}// notice_view

	@PostMapping("BCommentInsert")
	@ResponseBody // ajax활용시 // 데이터 전송
	public BCommentDto notice_view(BCommentDto cdto) {
		System.out.println("BController BCommentInsert cpw : " + cdto.getCpw());
		System.out.println("BController BCommentInsert ccontent : " + cdto.getCcontent());
		System.out.println("BController BCommentInsert bno : " + cdto.getBno());
		//id 추가
		cdto.setId((String) session.getAttribute("session_id"));


		// DB에 저장된 댓글 1개 가져오기 - cno, cdate가 포함되어 있음
		BCommentDto bCommentDto = bService.BCommentInsert(cdto);
		
		return bCommentDto;
	}// notice_view

}// BController
