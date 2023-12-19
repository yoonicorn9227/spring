package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {
	// 1. 게시글 전체 가져오기
	ArrayList<BoardDto> selectAll();

	// 2. 게시글 1개 가져오기
	Map<String, Object> selectOne(int bno);

}// InterFace(BService)
