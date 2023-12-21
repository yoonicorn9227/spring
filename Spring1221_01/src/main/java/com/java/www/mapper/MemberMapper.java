package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemBoardDto;

@Mapper
public interface MemberMapper {

	//1. 회원 정보 & 게시글 전체 리스트
	ArrayList<MemBoardDto> selectAll3();

}//InterFace(MemberMapper)
