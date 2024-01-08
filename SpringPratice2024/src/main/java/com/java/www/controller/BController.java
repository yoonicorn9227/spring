package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BController {
	@Autowired
	BService bService;

	@GetMapping("bList")
	public String bList(Model model) {
		// service 연결
		ArrayList<BoardDto> list = bService.selectAll();
		
		// model 저장후 전송
		model.addAttribute("list", list);
		return "/board/bList";
	}// bList
	
	public String bView(BoardDto bdto, @RequestParam (defaultValue = "1") int bno) {
		//service 연결
		
		return "/board/bVeiw";
	}//bView
	

}// BController
