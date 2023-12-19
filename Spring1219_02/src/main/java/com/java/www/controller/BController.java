package com.java.www.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BController {
	@Autowired
	BService bService;

	// 1. 게시글 전체 가져오기
	@RequestMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();

		// model저장하고 보내기
		model.addAttribute("list", list);
		return "board/bList";
	}// bList

	// 2. 게시글 1개 가져오기
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);

		//model 데이터 저장후 보내기
		model.addAttribute("map",map);
		return "board/bView";
	}// bView

}
// BController
