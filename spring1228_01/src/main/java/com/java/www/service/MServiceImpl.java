package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {

	@Autowired
	MemberMapper memberMapper;
	@Autowired
	HttpSession session;

	// 1.①로그인 접속
	@Override
	public MemberDto loginSelect(MemberDto mdto) {
		MemberDto memberDto = memberMapper.loginSelect(mdto);

		return memberDto;
	}// loginSelect(mdto)

	// 1.②로그인 접속(ajax)
	@Override
	public String loginCheck(MemberDto mdto) {
		MemberDto memberDto = memberMapper.loginCheck(mdto);
		String result = "";
		if (memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = "로그인성공";
		} else {
			result = "로그인실패";
		} // if-else

		return result;
	}// loginCheck(mdto)

	// 2. 아이디 중복체크(ajax)
	@Override
	public String idCheck(MemberDto mdto) {
		MemberDto memberDto = memberMapper.idCheck(mdto);
		String result = "";
		if (memberDto != null) {
			result = "사용불가";
		} else {
			result = "사용가능";
		} // if-else
		return result;
	}// idCheck(mdto)

}// MServiceImpl
