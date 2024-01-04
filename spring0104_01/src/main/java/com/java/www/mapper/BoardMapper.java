package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//1. 전체게시글 가져오기
	ArrayList<BoardDto> blist();

	//2. 게시글 저장
	void write(BoardDto bdto);

	BoardDto selectOne(int bno);

}// BoardMapper
