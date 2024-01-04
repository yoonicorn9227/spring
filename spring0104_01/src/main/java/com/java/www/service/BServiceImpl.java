package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;
	
	
	//1. 전체게시글 가져오기
	@Override
	public ArrayList<BoardDto> blist() {
		
		ArrayList<BoardDto> list = boardMapper.blist();
		
		return list;
	}


	//2. 게시글 저장
	@Override
	public void write(BoardDto bdto) {

		//게시글 저장
		boardMapper.write(bdto);
		
	}//


	@Override
	public BoardDto selectOne(int bno) {
		
		BoardDto bdto = boardMapper.selectOne(bno);
		
		
		return bdto;
	}

}//BServiceImpl
