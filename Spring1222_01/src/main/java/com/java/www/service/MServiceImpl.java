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

	@Override //아이디 중복체크
	public String idCheck(String id) {
		String result="사용불가";
		
		//DB접근
		MemberDto mdto = memberMapper.idCheck(id);
		if(mdto==null) result ="사용가능";
		return result;
	}//idCheck()

	@Override
	public String mInsert(MemberDto mdto) {
		memberMapper.mInsert(mdto);
		System.out.println("MServiceImpl mInsert mdto id : "+mdto.getId());
		//DB전송
		String result="가입완료";
		return result;
	}

}// CLASS
