package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FController {

	@RequestMapping("/")
	public String main() {
		return "layout/main";
	}//main
	
}//FController
