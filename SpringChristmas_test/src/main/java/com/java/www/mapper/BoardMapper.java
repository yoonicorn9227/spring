package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 1.게시글 전체 가져오기
	ArrayList<BoardDto> selectAll();

	// 2.게시글 1개 가져오기
	BoardDto selectOne(int bno);

	// 2-1.게시글 이전글
	BoardDto selectPrev(int bno);

	// 2-2.게시글 다음글
	BoardDto selectNext(int bno);

	// 3. 게시글 1개 저장
	int bInsert(BoardDto bdto);

	// 4. 게시글 삭제
	int bDelete(int bno);

	// 5. 게시글 1개 수정
	int doBUpdate(BoardDto bdto);

}// InterFace(BoardMapper)
