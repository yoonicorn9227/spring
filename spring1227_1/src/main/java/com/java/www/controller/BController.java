package com.java.www.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// 2.게시글 1개보기
	@GetMapping("notice_view") // 1개 게시글 가져오기
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		System.out.println("BController notice_view bno : " + bno);

		// model 저장후 전송
		model.addAttribute("map", map);

		return "customer/notice_view";
	}// notice_view

	// 2-1. 댓글 1개저장
	@PostMapping("BCommentInsert")
	@ResponseBody
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		System.out.println("BController BCommentInsert bno : " + cdto.getBno());

		// Service연결 - 저장시간, cno 받아와야함 (selectKey)
		BCommentDto bCommentDto = bService.bCommentInsert(cdto);

		return bCommentDto;
	}// BcommentInsert

	// 2-2. 댓글 1개 삭제
	@PostMapping("BCommentDelete")
	@ResponseBody //ajax - 데이터 전송
	public String BCommentDelete(@RequestParam(defaultValue = "1") int cno) {
		System.out.println("BController BCommentDelete cno : " + cno);

		//Service연결 - 댓글삭제(cno)
		String result = bService.BCommentDelete(cno);
		return result;
	}// BCommentDelete

	// 2-3. 댓글 1개 수정저장
	@PostMapping("BCommentUpdate")
	@ResponseBody //ajax - 데이터 전송
	public BCommentDto BCommentUpdate(BCommentDto cdto) {
		System.out.println("BController BCommentUpdate cno : " + cdto.getCno());
		
		//Service연결 - 댓글수정 저장(cno)
		BCommentDto bCommentDto = bService.BCommentUpdate(cdto);
		return bCommentDto;
	}// BCommentUpdate
	
}// BController
