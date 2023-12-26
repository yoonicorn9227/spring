package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//1.공지사항 게시판리스트
	ArrayList<BoardDto> selectAll();

	//2.공지사항 게시글 1개
	BoardDto selectOne(int bno);
	//2-1.  하단댓글 리스트
	ArrayList<BCommentDto> BCommentselectAll(int bno);

	//2-2.  하단댓글 저장(입력)
	void BCommentInsert(BCommentDto cdto);

	//2-2.  하단댓글 저장된 댓글 가져오기(입력)
	BCommentDto BCommentselectOne(int cno);

}//BoardMapper
