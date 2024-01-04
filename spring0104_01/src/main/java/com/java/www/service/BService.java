package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.BoardDto;

public interface BService {

	//1.전체 게시글 가져오기
	ArrayList<BoardDto> blist();

	
	//2. 게시글 저장
	void write(BoardDto bdto);

	//3. 게시글 1개 가져오기
	BoardDto selectOne(int bno);

}// BService
