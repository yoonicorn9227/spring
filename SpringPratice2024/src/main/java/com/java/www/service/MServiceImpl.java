package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {
	@Autowired
	MemberMapper memberMapper;

	@Override // 1. 로그인 접속
	public MemberDto loginDo(MemberDto mdto) {
		System.out.println("MServiceImpl id : "+mdto.getId());
		System.out.println("MServiceImpl pw : "+mdto.getPw());
		MemberDto memberDto = memberMapper.loginDo(mdto);
		return memberDto;
	}// login(mdto)

}// MServiceImpl
