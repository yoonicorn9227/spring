package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

@Mapper
public interface EmpMapper {


	//1.사원정보 전체 리스트
	ArrayList<EmpDto> selectAll();

	//2.사원,부서정보 전체 리스트
	ArrayList<EmpDeptDto> selectAll2();

	//3.회원정보,게시글 전체 리스트
	//ArrayList<MemBoardDto> selectAll3();

}//EmpMapper
