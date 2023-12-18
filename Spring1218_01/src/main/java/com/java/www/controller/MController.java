package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	@Autowired // IOC 컨테이너에 있는 객체 가져오기 (자동 객체선언)
	MService mService;
	@Autowired
	HttpSession session; // HttpSession session = request.getSession();

	@RequestMapping("login")
	public String login() {
		return "member/login";
	}// login();

	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}// logout();

	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("MController ID : " + mdto.getId());
		System.out.println("MController PW : " + mdto.getPw());

		// DB연결 - 리턴값 : DTO - DTO있는지 확인
		MemberDto memberDto = mService.loginSelect(mdto);// id,pw 태워서 보냄
		if (memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			System.out.println("MController id 있음 : " + memberDto.getId());
			result = 1;
		} else {
			System.out.println("MController  memberDto : null");

		} // if-else

		// result 1 : 성공. 0 :로그인 실패
		model.addAttribute("result", result);

		return "member/doLogin";
	}// login();
}// MController
