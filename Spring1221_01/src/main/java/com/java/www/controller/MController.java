package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemBoardDto;
import com.java.www.service.MService;

@Controller
public class MController {
	@Autowired
	MService mService;
	
	@RequestMapping("list3")
	public String list3(Model model) {
		ArrayList<MemBoardDto> list = mService.selectAll3();
		
		//model 저장후 전송
		model.addAttribute("list" , list);
		return "list3";
	}// list3()
}//MController
