package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("login")
	public String login() {
		return "/member/login";
	}// login

	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		int result = 0;

		// service연결
		MemberDto memberDto = mService.loginDo(mdto);
		if (memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		} // if

		System.out.println("MController result : " + result);

		// model
		model.addAttribute("result", result);

		return "/member/doLogin";
	}// login(mdto,model)

}// MController
