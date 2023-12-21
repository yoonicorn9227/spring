package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemBoardDto;
import com.java.www.mapper.MemberMapper;

@Service
public class MserviceImpl implements MService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	public ArrayList<MemBoardDto> selectAll3() {
		ArrayList<MemBoardDto> list = memberMapper.selectAll3();
		return list;
	}// selectAll3()

}// CLASS(MserviceImpl)
