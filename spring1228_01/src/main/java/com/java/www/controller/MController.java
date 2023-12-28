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

	// 1. 로그인
	@GetMapping("login")
	public String login() {
		return "/member/login";
	}// login

	// 1-1. ①로그인 접속(jsp)
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		int result = 0;

		// ＠Service연결
		MemberDto memberDto = mService.loginSelect(mdto);

		if (memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		} // if
		System.out.println("MController loginSelect result : " + result);
		// Model 저장 후 전송
		model.addAttribute("result", result);

		return "/member/doLogin";
	}// login(mdto)

	// 1-1. ②로그인 접속(ajax)
	@PostMapping("loginCheck")
	@ResponseBody
	public String loginCheck(MemberDto mdto) {
		// 확인용
		System.out.println("MController loginCheck id : " + mdto.getId());
		System.out.println("MController loginCheck id : " + mdto.getPw());

		// ＠Service연결
		String result = mService.loginCheck(mdto);

		return result;
	}// loginCheck(mdto)

	// 2.로그아웃
	@GetMapping("logout")
	public String logout() {
		session.invalidate();

		return "/member/doLogout";
	}// logout

	// 3.회원가입 페이지
	@GetMapping("step03")
	public String step03() {
		return "/member/step03";
	}// step03(회원가입 페이지)

	@PostMapping("idCheck")
	@ResponseBody //ajax활용시
	public String idCheck(MemberDto mdto) {
		String result = mService.idCheck(mdto);
		System.out.println("MService idCheck result : " +result);
		return result;
	}// idCheck(mdto)

}// MController
