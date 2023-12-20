package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {
	//1. 회원 로그인
	MemberDto doLogin(MemberDto mdto);


}//InterFace(MemberMapper)
