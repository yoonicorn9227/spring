package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

public interface BService {

	
	//1.공지사항 게시판 리스트
	ArrayList<BoardDto> selectAll();

	//2.공지사항 게시판 게시글 1개보기
	Map<String, Object> selectOne(int bno);

	BCommentDto BCommentInsert(BCommentDto cdto);

}//BService
