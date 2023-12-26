package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;

	// 1.공지사항 게시판 리스트
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}// selectAll(bdto)

	// 2.공지사항 게시글 1개 & 댓글 전체가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		System.out.println("BServiceImpl  SelectOne bno: " + bno);

		// 댓글 전체가져오기
		Map<String, Object> map = new HashMap<>();
		BoardDto bdto = boardMapper.selectOne(bno);
		ArrayList<BCommentDto> list = boardMapper.BCommentselectAll(bno);

		// map
		map.put("bdto", bdto);
		map.put("list", list);

		return map;
	}// selectOne(bno)

	// 2-1. 댓글 입력 및 & 저장된 댓글 1개 가져오기
	@Override
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		
		//댓글 1개저장 
		boardMapper.BCommentInsert(cdto);
		System.out.println("BServiceImpl BCommentInsert cno : "+cdto.getCno()); //selectKey에서 불러와서 입력(from boardMapper.xml)
		
		//저장된 댓글 1개 가져오기
		BCommentDto bCommentDto = boardMapper.BCommentselectOne(cdto.getCno());
		System.out.println("BServiceImpl bCommentDto ccontent : " +bCommentDto.getCcontent());
		return bCommentDto;
	}//BCommentInsert

}// BServiceImpl
