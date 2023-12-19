package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {
	//1.로그인
	MemberDto loginSelect(MemberDto mdto);

}//InterFace(MemberMapper)
