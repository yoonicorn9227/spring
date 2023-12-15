package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
public class FController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}// index()

//	---- Board 관련 ----
	@RequestMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}// bInsert()

	@RequestMapping("bView")
	public String bView(BoardDto bdto, MemberDto mdto, Model model) {
		System.out.println("FCtrl bdto id : " + bdto.getId());
		System.out.println("FCtrl mdto id : " + mdto.getId());
		model.addAttribute("bdto", bdto);
		return "bView";
	}// bView()

	@RequestMapping("bUpdate")
	public String bUpdate(BoardDto bdto, Model model) {
		model.addAttribute("bdto", bdto);
		return "bUpdate"; //@RequestMapping("bUpdate") return "bUpdate" bUpdate.jsp 요청 
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}// login

	@RequestMapping("mLogin")
	public String doLogin(MemberDto mdto, Model model) {
		model.addAttribute("mdto", mdto);
		return "mLogin";
	}// doLogin

}// FController
