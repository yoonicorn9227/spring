package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	// 1. 게시글 전체 가져오기
	Map<String, Object> selectAll(int page);

	// 2. 게시글 1개 가져오기 이전글 다음글 가져오기
	Map<String, Object> selectOne(int bno);

	// 3. 게시글 1개 작성
	void doBInsert(BoardDto bdto);

	// 4. 게시글 1개 삭제
	void bDelete(int bno);

	// 5. 게시글 1개 수정
	void doBUpdate(BoardDto bdto);

	// 6. 게시글 답글 1개 저장
	void doBReply(BoardDto bdto);

}// InterFace(BService)
