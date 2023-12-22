package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	MemberDto login(String id, String pw);
	//회원가입 - 아이디 중복체크
	MemberDto idCheck(String id);
	
	void mInsert(MemberDto mdto);

}//MemberMapper
