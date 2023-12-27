package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	// 1.공지사항 게시판리스트
	ArrayList<BoardDto> selectAll();

	// 2.공지사항 게시글 1개 가져오기
	BoardDto selectOne(int bno);

	// 2.공지사항 하단댓글 모두가져오기
	ArrayList<BCommentDto> bCommentSelectAll(int bno);

	// 2-1.공지사항 하단댓글 1개 저장
	void bCommentInsert(BCommentDto cdto);

	// 2-1-1.공지사항 하단댓글 1개 가져오기
	BCommentDto bCommentSelectOne(int cno);

	// 2-2.공지사항 하단댓글 1개 삭제
	int bCommentDelete(int cno);

	// 2-3.공지사항 하단댓글 1개 수정 & 저장
	void BCommentUpdate(BCommentDto cdto);


}// BoardMapper
