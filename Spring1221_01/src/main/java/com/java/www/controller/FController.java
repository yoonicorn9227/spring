package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.service.EService;

@Controller
public class FController {
	@Autowired
	EService eService;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}// index()
	
	@RequestMapping("list")
	public String list(Model model) {
		ArrayList<EmpDto> list = eService.selectAll();
		
		//model 저장후 전송
		model.addAttribute("list" , list);
		return "list";
	}// list2()
	
	@RequestMapping("list2")
	public String list2(Model model) {
		ArrayList<EmpDeptDto> list = eService.selectAll2();
		
		//model 저장후 전송
		model.addAttribute("list" , list);
		return "list2";
	}// list2()
	
	//@RequestMapping("list3")
	//public String list3(Model model) {
		//ArrayList<MemBoardDto> list = eService.selectAll3();
		
		//model 저장후 전송
		//model.addAttribute("list" , list);
		//return "list3";
	//}// list3()
	

}// FController
