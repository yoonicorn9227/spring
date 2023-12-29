package com.java.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.IncomeDto;
import com.java.www.service.FService;

@Controller
public class FController {
	@Autowired
	FService fService;

	// 1.메인페이지
	@RequestMapping("/")
	public String main() {
		return "layout/main";
	}// main

	@GetMapping("layout/chart")
	public String chart() {
		return "layout/chart";
	}// chart

	@PostMapping("layout/incomeSelect")
	@ResponseBody // ajax전송
	public ArrayList<IncomeDto> incomeSelect(String cyear) {
		System.out.println("FController incomeSelect cyear : " + cyear);

		// Service 연결 - 매출액 가져오기
		ArrayList<IncomeDto> list = fService.incomeSelect(cyear);

		return list;
	}// incomeSelect

	// ***********공공데이터
	@GetMapping("layout/publicData")
	public String publicData() {
		return "layout/publicData";
	}// publicData

	@GetMapping("layout/searchData")
	@ResponseBody // 데이터로 전송
	public String searchData(String txt) throws Exception {
		System.out.println("searchData txt : " + txt);
		String page = 1 + "";
		String serviceKey = "918RE13GA7OY7ZEmUzApgbOeAcQoZ%2FaHsXWcqPAKQ9YNNPj83KOstRMRIUrCFIAcm9qj2R6b7NFZjp%2FYsYzJLg%3D%3D";
		String result = "";

		if (txt == null || txt.equals("")) {
			// 사진 목록 메소드 호출 - 검색단어가 없을때
			result = galleryList(serviceKey, page);
		} else {
			// 사진 조회 메소드 호출 - 검색단어가 있을때
			result = gallerySearchList(txt, serviceKey, page);
		} // if-else(검색단어 有/無)

		return result;
	}// searchData

	// 사진조회 메소드
	public String gallerySearchList(String txt, String serviceKey, String page) throws Exception {
		// -----공공데이터 소스추가-----
		StringBuilder urlBuilder = new StringBuilder(
				"https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 목록 건수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
				+ URLEncoder.encode("ETC", "UTF-8")); /* OS 구분 : IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC(기타) */
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
				+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명(어플명) */
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "="
				+ URLEncoder.encode("A", "UTF-8")); /* 정렬구분 : A=촬영일, B=제목, C=수정일 */
		urlBuilder
				.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(txt, "UTF-8")); /* 검색어 */
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 응답메세지 형식 : REST방식의 URL호출 시 json값 추가(디폴트 응답메세지 형식은XML) */
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); // String 을 계속 더하면 String변수를 계속 새롭게 만듬.
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line); // json데이터를 sb에 1줄씩 저장
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		// -----공공데이터 소스추가-----
		return sb.toString();
	}// gallerySearchList(txt, serviceKey, page)

	// 사진목록 메소드
	public String galleryList(String serviceKey, String page) throws Exception {

		// -----공공데이터 소스추가-----
		StringBuilder urlBuilder = new StringBuilder(
				"https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 목록 건수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
				+ URLEncoder.encode("ETC", "UTF-8")); /* OS 구분 : IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC(기타) */
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
				+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명(어플명) */
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "="
				+ URLEncoder.encode("A", "UTF-8")); /* 정렬구분 : A=촬영일, B=제목, C=수정일 */
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 응답메세지 형식 : REST방식의 URL호출 시 json값 추가(디폴트 응답메세지 형식은XML) */
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); // String 을 계속 더하면 String변수를 계속 새롭게 만듬.
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line); // json데이터를 sb에 1줄씩 저장
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		// -----공공데이터 소스추가-----

		return sb.toString();
	}// galleryList()

	// *************영화정보데이터
	@GetMapping("layout/screenData")
	public String screenData() {
		return "layout/screenData";
	}// screenData

	@GetMapping("layout/searchScreen")
	@ResponseBody // 데이터를 전송
	public String searchScreen(String txt) throws Exception {

		String key = "5c94b83d302c89227b3dbb74aff43646"; // 개인 인증발급키

		// -----공공데이터 소스추가-----
		StringBuilder urlBuilder = new StringBuilder(
				"http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + key); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "="
				+ URLEncoder.encode("20231228", "UTF-8")); /* 응답메세지 형식 : REST방식의 URL호출 시 json값 추가(디폴트 응답메세지 형식은XML) */
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); // String 을 계속 더하면 String변수를 계속 새롭게 만듬.
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line); // json데이터를 sb에 1줄씩 저장
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		// -----공공데이터 소스추가-----

		return sb.toString();

	}

	// *************자전거 데이터
	@GetMapping("layout/bikeData")
	public String bikeData() {
		return "layout/bikeData";
	}// searchBike

	@GetMapping("layout/searchBike")
	@ResponseBody
	public String searchBike(String txt) throws Exception {
		System.out.println("searchData txt :" + txt);
		String key = "425a63435168666438316f67574b77";
		String bikeUrl = "http://openapi.seoul.go.kr:8088/"+key+"/json/bikeList/1/15/";

		// -----공공데이터 소스추가-----
		URL url = new URL(bikeUrl);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); // String 을 계속 더하면 String변수를 계속 새롭게 만듬.
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line); // json데이터를 sb에 1줄씩 저장
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		// -----공공데이터 소스추가-----

		return sb.toString();
	}// searchBike

}// FController
