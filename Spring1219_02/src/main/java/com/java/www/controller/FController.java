package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FController {
	@GetMapping("/")
	public String index() {
		return "index";
	}// index()

}// FController
