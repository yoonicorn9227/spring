package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.BoardDto;

public interface BService {
	// 1. 게시글 전체 가져오기
	ArrayList<BoardDto> bList();

	// 2. 게시글 1개 가져오기
	BoardDto selectOne(int bno);

	//3. 게시글 파일 저장
	void bInsert(BoardDto bdto);

}// InterFace(BService)
