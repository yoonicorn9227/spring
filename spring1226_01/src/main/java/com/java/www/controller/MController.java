package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	@Autowired
	MService mService;

	@Autowired
	HttpSession session;

	// 1.로그인 페이지
	@GetMapping("login")
	public String login() {
		return "member/login";
	}// login

	// ★1-1. <<ajax>>로 로그인 접속(ajax형태 : 데이터를 직접 보냄)
	@PostMapping("ajaxLogin")
	@ResponseBody // 데이터로 전송
	public String ajaxLogin(MemberDto mdto) {
				
				
		System.out.println("MController login id : " + mdto.getId());
		System.out.println("MController login pw : " + mdto.getPw());
		
		//Sevice연결
		int result = mService.login(mdto);
		System.out.println("MController ajaxResult : " + result);

		return result+"";
	}// ajaxLogin

	// ☆1-1. 로그인 접속(jsp형태 : 데이터를 Model에 태워서 jsp페이지로 보냄)
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("MController login id : " + mdto.getId());
		System.out.println("MController login pw : " + mdto.getPw());

		int result = mService.login(mdto);

		// Model
		model.addAttribute("result", result);

		return "member/doLogin";
	}// login(mdto)

	// 2.로그아웃
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}// logout

}// MController
