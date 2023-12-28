package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {
	//1. ①로그인 접속(jsp)
	MemberDto loginSelect(MemberDto mdto);

	//1. ②로그인 접속(ajax)
	MemberDto loginCheck(MemberDto mdto);

	//2. 회원가입 - 아이디 중복체크(ajax)
	MemberDto idCheck(MemberDto mdto);

}//MemberMapper
