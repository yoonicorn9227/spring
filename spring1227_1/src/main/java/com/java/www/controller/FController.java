package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.IncomeDto;
import com.java.www.service.FService;

@Controller
public class FController {
	@Autowired
	FService fService;
	
	
	//1.메인페이지
	@RequestMapping("/")
	public String main() {
		return "layout/main";
	}// main

	@GetMapping("layout/chart")
	public String chart() {
		return "layout/chart";
	}// chart

	@PostMapping("layout/incomeSelect")
	@ResponseBody // ajax전송
	public ArrayList<IncomeDto> incomeSelect(String cyear) {
		System.out.println("FController incomeSelect cyear : " + cyear);
		
		
		//Service 연결 - 매출액 가져오기
		ArrayList<IncomeDto> list = fService.incomeSelect(cyear);
		
		return list;
	}// incomeSelect
	
	
}// FController
