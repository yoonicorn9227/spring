package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;

	@Override // 1. 게시글 전체 가져오기
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}// selectAll

	@Override // 2.게시글 1개 가져오기
	public Map<String, Object> selectOne(int bno) {

		BoardDto boardDto = boardMapper.selectOne(bno);
		BoardDto prevDto = boardMapper.selectPrev(bno);
		BoardDto nextDto = boardMapper.selectNext(bno);
		
		//조회수 1증가
		boardMapper.bhitUp(bno);
		
		
		// Map 담아서 보내기
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", boardDto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);

		return map;
	}// selectOne

	@Override
	public void doBInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		System.out.println("BServiceImpl bInsert result : " + result);
	}// doBInsert

	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		System.out.println("BServiceImpl bDelete result : " + result);
	}// bDelete

	@Override
	public void doBUpdate(BoardDto bdto) {
		int result = boardMapper.doBUpdate(bdto);
		System.out.println("BServiceImpl doBUpdate result : " + result);
	}// doBUpdate(BoardDto bdto)

	@Override
	public void doBReply(BoardDto bdto) {
		// 1st. 부모보다 큰 bstep은 1씩 증가
		boardMapper.bstepUp(bdto);

		// 2nd. 현재글은 부모의 bstep + 1 저장
		//bdto.setBstep(bdto.getBstep() + 1); // 또는 #{bstep}+1

		// 3rd. bindent는 부모의 1 더하기 | 현재글은 부모의 bindent+1저장
		//bdto.setBindent(bdto.getBindent() + 1); // 또는 #{bindent}+1

		// 4. bgroup은 부모와 같음

		int result = boardMapper.doBReply(bdto);
		System.out.println("BServiceImpl doBReply result : " + result);
	}// doBReply(bdto)

}// CLASS(BServiceImpl)
