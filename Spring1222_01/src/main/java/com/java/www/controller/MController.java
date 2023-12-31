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

	@GetMapping("login") // 1. 로그인 페이지 이동
	public String login() {
		return "member/login";
	}// login

	@GetMapping("logout") // 1. 로그아웃
	public String logout() {
		session.invalidate();
		return "member/doLogout";
	}// logout

	@PostMapping("login") // 1-1. 로그인 확인
	public String login(String id, String pw, Model model) {
		int result = mService.login(id, pw);
		// Model 저장후 doLogin.jsp보내기
		model.addAttribute("result", result);
		return "member/doLogin";
	}// login(id,pw)

	@GetMapping("join01")
	public String join01() {
		return "member/join01";
	}// join01

	@GetMapping("join02") // 회원가입 정보저장
	public String join02() {
		return "member/join02";
	}// join02

	@PostMapping("join02") // 회원가입 정보저장
	@ResponseBody
	public String join02(MemberDto mdto,String pw1, String f_tell, String m_tell, String l_tell) {
		String phone = f_tell +"-"+ m_tell +"-"+ l_tell;
		mdto.setPhone(phone);
		mdto.setPw(pw1);
		String result = mService.mInsert(mdto);
		return result;
	}// join02

	@PostMapping("idCheck") // ajax => @ResponseBody
	@ResponseBody
	public String idCheck(String id) {
		System.out.println("MController idCheck id : " + id);
		// db접근
		String result = mService.idCheck(id);
		return result;
	}// idCheck

	@GetMapping("join03")
	public String join03() {
		return "member/join03";
	}// join03
}// MController
