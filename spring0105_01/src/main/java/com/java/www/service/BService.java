package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.BoardDto;

public interface BService {

	//1.게시글 전체가져오기
	ArrayList<BoardDto> selectAll();

	//1.글쓰기 저장
	void bwrite(BoardDto bdto);

	BoardDto selectOne(int bno);

}//BService
