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
	@Autowired
	HttpSession session;

	@Override
	public int login(String id, String pw) {
		int result = 0;
		MemberDto mdto = memberMapper.login(id, pw);
		if (mdto != null) {
			session.setAttribute("session_id", mdto.getId());
			session.setAttribute("session_name", mdto.getName());
			result = 1;
		} // if
		return result;
	}// login(id,pw)

}// CLASS
