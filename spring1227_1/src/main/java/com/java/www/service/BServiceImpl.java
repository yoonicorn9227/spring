package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;
	@Autowired
	HttpSession session;

	// 1.공지사항 게시판 리스트
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}// selectAll(bdto)

	// 2. 공지사항 게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		// ①게시글 1개가져오기
		BoardDto bdto = boardMapper.selectOne(bno);
		System.out.println("BServiceImpl bno : " + bno);

		// ②하단댓글 모두 가져오기
		ArrayList<BCommentDto> bCommentlist = boardMapper.bCommentSelectAll(bno);

		// ①+②으로 map에 담아야 함
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("bCommentlist", bCommentlist);

		return map;
	}// selectOne(bno)

	// 2-1. 공지사항 게시글 - 하단댓글 저장
	@Override
	public BCommentDto bCommentInsert(BCommentDto cdto) {
		// id저장 : session_id를 cdto의 id에 저장
		cdto.setId((String) session.getAttribute("session_id")); // cdto에 새로 입력할때는 BCommentDto 선언전에
		
		// ①댓글 1개저장
		boardMapper.bCommentInsert(cdto); // 댓글form에서 입력한 내용 저장시킴
		System.out.println("bCommentInsert cno : " + cdto.getCno());
		System.out.println("bCommentInsert cdate : " + cdto.getCdate());

		// ②댓글 1개가져오기
		//BCommentDto bCommentDto = boardMapper.bCommentSelectOne(cdto.getCno()); //
		
		return cdto;
	}// bCommentInsert

	// 2-2. 공지사항 게시글 - 하단댓글 1개 삭제
	@Override
	public String BCommentDelete(int cno) {
		String result = "";
		int re = boardMapper.bCommentDelete(cno);

		return result + re;
	}// BCommentDelete(cno)

	// 2-3. 공지사항 게시글 - 하단댓글 수정 저장
	@Override
	public BCommentDto BCommentUpdate(BCommentDto cdto) {

		// session_id를 cdto의 id에 저장
		cdto.setId((String) session.getAttribute("session_id"));

		// 수정저장
		boardMapper.BCommentUpdate(cdto);

		// 댓글 1개가져오기
		BCommentDto bCommentDto = boardMapper.bCommentSelectOne(cdto.getCno());

		return bCommentDto;
	}// BCommentUpdate

}// BServiceImpl
