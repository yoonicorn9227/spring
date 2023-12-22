package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {
	//로그인 체크
	int login(String id, String pw);

	//회원가입 - 아이디 중복 체크
	String idCheck(String id);

	String mInsert(MemberDto mdto);

}// Interface(MService)
