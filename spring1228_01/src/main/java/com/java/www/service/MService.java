package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {
	//1. ①로그인 접속(jsp)
	MemberDto loginSelect(MemberDto mdto);

	//1. ②로그인 접속(ajax)
	String loginCheck(MemberDto mdto);

	//2-1.회원가입 - 아이디 중복체크(ajax)
	String idCheck(MemberDto mdto);

}//MService
