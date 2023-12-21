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
	public Map<String, Object> selectAll(int page) {
		//2nd. 하단 넘버링 - page, countPerpage :1pg당 게시글 갯수, startPage, endPage, maxPage
		int countPerPage = 10; // 1pg당 게시글 수
		int bottomPerNum = 10; // page 하단에 page 나열
		int countAll = boardMapper.selectCountAll(); // 게시글 총갯수
		int maxPage = (int) Math.ceil((double) countAll / countPerPage); // 게시글 총갯수 1045개/10개 = 104.5 =Math.Ceil=> 105.0
		int startPage = ((page - 1) / bottomPerNum) * bottomPerNum + 1; // ex) (5pg-1)10 = 4/10 =0.4 -int-> 0 | 0*10+1 = 1
		int endPage = (startPage + bottomPerNum) - 1;
		if (endPage > maxPage)endPage = maxPage; // ex) startPage 1 ~ endPage: 10 일때 endPage에 maxPage를 넣어서 1~5까지만 출력.
		int startRow = (page - 1) * countPerPage + 1; // 페이지 첫 게시글에 나타나는 게시글번호(bno) 1pg - 1 , 2pg - 11
		int endRow = startRow + countPerPage - 1; // 페이지 마지막 게시글에 나타나는 게시글번호(bno) 1pg -10, 2pg - 20
		
		// 1st.게시글 전체가져오기
		ArrayList<BoardDto> list = boardMapper.selectAll(startRow, endRow);
		
		// 데이터 전송 - list,page, maxPage, startPage, endPage 총 4개를 넘겨줘야함 by Using "Map"
		Map<String, Object> map = new HashMap<>();
		map.put("list",list);
		map.put("page",page);
		map.put("maxPage",maxPage);
		map.put("startPage",startPage);
		map.put("endPage",endPage);
		map.put("countAll",countAll);
		 
		return map;
	}// selectAll(int page)

	@Override // 2.게시글 1개 가져오기
	public Map<String, Object> selectOne(int bno) {

		BoardDto boardDto = boardMapper.selectOne(bno);
		BoardDto prevDto = boardMapper.selectPrev(bno);
		BoardDto nextDto = boardMapper.selectNext(bno);

		// 조회수 1증가
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
		// bdto.setBstep(bdto.getBstep() + 1); // 또는 #{bstep}+1

		// 3rd. bindent는 부모의 1 더하기 | 현재글은 부모의 bindent+1저장
		// bdto.setBindent(bdto.getBindent() + 1); // 또는 #{bindent}+1

		// 4. bgroup은 부모와 같음

		int result = boardMapper.doBReply(bdto);
		System.out.println("BServiceImpl doBReply result : " + result);
	}// doBReply(bdto)

}// CLASS(BServiceImpl)
