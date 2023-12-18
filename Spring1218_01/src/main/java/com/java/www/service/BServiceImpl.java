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

	//1. 게시글 전체 가졍괴
	@Override
	public ArrayList<BoardDto> bList() {
		ArrayList<BoardDto> list = boardMapper.bList();
		return list;
	}// bList()

	//2. 게시글 1개 가져오기
	@Override
	public BoardDto selectOne(int bno) {
		BoardDto boardDto = boardMapper.selectOne(bno);
		return boardDto;
	}

	@Override //3/ 게시글 파일 저장
	public void bInsert(BoardDto bdto) {
		boardMapper.bInsert(bdto);
	}//bInsert()

}// CLASS(BServiceImpl)
