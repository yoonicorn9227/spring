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

	@Override //1.게시글 전체가져오기
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		
		return list;
	}//selectAll

	@Override //2. 글쓰기 저장
	public void bwrite(BoardDto bdto) {
		boardMapper.bwrite(bdto);
	}//bwrite(bdto)

	@Override //3.게시글 1개가져오기
	public BoardDto selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		
		return bdto;
	}
	
	
	
}// BServiceImpl
