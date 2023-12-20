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

		// Map
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
	}//doBUpdate(BoardDto bdto)

}// CLASS(BServiceImpl)
