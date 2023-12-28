package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.IncomeDto;

public interface FService {
	//1. 매출액 가져오기
	ArrayList<IncomeDto> incomeSelect(String cyear);

	
	
	
}//FService
