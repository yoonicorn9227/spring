package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

public interface EService {
	//1. 사원정보 리스트 전체
	ArrayList<EmpDto> selectAll();

	ArrayList<EmpDeptDto> selectAll2();

	//ArrayList<MemBoardDto> selectAll3();

}//InterFace(EService)
