package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.mapper.EmpMapper;

@Service
public class EServiceImpl implements EService {
	@Autowired
	EmpMapper empMapper;

	@Override
	public ArrayList<EmpDto> selectAll() {
		ArrayList<EmpDto> list = empMapper.selectAll();
		return list;
	}// selectAll

	@Override
	public ArrayList<EmpDeptDto> selectAll2() {
		ArrayList<EmpDeptDto> list = empMapper.selectAll2();
		return list;
	}// selectAll2

	//@Override
	//public ArrayList<MemBoardDto> selectAll3() {
		//ArrayList<MemBoardDto> list = empMapper.selectAll3();
		//return list;
	//}//selectAll3

}// CLASS(EServiceImpl)
