package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

public interface BService {

	
	//1.공지사항 게시판 리스트
	ArrayList<BoardDto> selectAll();

	//2.공지사항 게시글 1개가져오기 
	Map<String, Object> selectOne(int bno);

	//2-1.공지사항 게시글 하단 댓글 저장 & 댓글 1개 가져오기
	BCommentDto bCommentInsert(BCommentDto cdto);

	//2-2.공지사항 게시글 하단 댓글 삭제
	String BCommentDelete(int cno);

	//2-2.공지사항 게시글 하단 수정저장
	BCommentDto BCommentUpdate(BCommentDto cdto);

}//BService
