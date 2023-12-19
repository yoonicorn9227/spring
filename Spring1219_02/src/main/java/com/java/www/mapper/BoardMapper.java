package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//1.게시글 전체 가져오기
	ArrayList<BoardDto> selectAll();

	//2.게시글 1개 가져오기
	BoardDto selectOne(int bno);
	//2-1.게시글 1개 가져오기
	BoardDto selectPrev(int bno);
	//2-2.게시글 1개 가져오기
	BoardDto selectNext(int bno);

}//BoardMapper
