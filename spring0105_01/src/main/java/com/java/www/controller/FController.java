package com.java.www.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
public class FController {

	@Autowired
	BService bService;

	// 1. 메인페이지
	@GetMapping({ "/", "main" })
	public String main() {
		return "main";
	}// main

	// 2.글쓰기 페이지
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}// bwrite
		// 2.글쓰기 페이지

	@GetMapping("map")
	public String map() {
		return "map";
	}// map

	@PostMapping("screenInfo")
	@ResponseBody
	public String screenInfo(String movie) throws Exception {
		System.out.println("test data : " + movie);

		// --- 영화정보 api 가져오기
		String key = "5c94b83d302c89227b3dbb74aff43646"; // 개인 인증발급키
		//-------오늘날짜
		System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
		String today = sdf.format(System.currentTimeMillis());
		System.out.println("오늘날짜  : "+today);
		//------------------------
		// -----공공데이터 소스추가-----
		StringBuilder urlBuilder = new StringBuilder(
				"http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + key); /*key */
		urlBuilder.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=" /*날짜*/
				+ URLEncoder.encode("20240104", "UTF-8")); /* 응답메세지 형식 : REST방식의 URL호출 시 json값 추가(디폴트 응답메세지 형식은XML) */
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

		// -----
	}// screenInfo

	@PostMapping("uploadImage") // summernote에서 ajax이미지 전송
	@ResponseBody
	public String uploadImage(BoardDto bdto, @RequestPart MultipartFile file) throws Exception {
		String urlName = "";

		// 파일 서버로 전송하는 부분
		if (!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFName = time + "_" + oriFName; // 파일이름 변경
			String uploadUrl = "c:/upload/";
			File f = new File(uploadUrl + uploadFName); // 파일등록
			urlName = "/upload/" + uploadFName;
			file.transferTo(f); // 파일서버로 전송
		} // if

		return urlName;
	}// uploadImage

	@GetMapping("bview") // 3.게시글 1개 가져오기
	public String bview(@RequestParam(defaultValue = "1") int bno, Model model) {

		BoardDto bdto = bService.selectOne(bno);

		model.addAttribute("bdto", bdto);

		return "bview";
	}// bwrite(bno, model)

	@PostMapping("bwrite") // 2. 글쓰기 저장
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {

		System.out.println("FController bwrite btitle: " + bdto.getBtitle());

		// 파일 서버로 전송하는 부분
		if (!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFName = time + "_" + oriFName;

			String uploadUrl = "c:/upload/";
			File f = new File(uploadUrl + uploadFName);
			file.transferTo(f); // 파일서버로 전송
			bdto.setBfile(uploadFName);

		} else {
			bdto.setBfile("");
		} // if-else

		System.out.println("FController bwrite bfile : " + bdto.getBfile());

		// service연결
		bService.bwrite(bdto);

		// model
		model.addAttribute("result", "success-bwrite");
		return "result";
	}// bwrite

	@GetMapping("blist") // 1. 게시글 전체가져오기
	public String blist(Model model) {

		// service연결(1.게시글 전체가져오기)
		ArrayList<BoardDto> list = bService.selectAll();

		// model 저장후 전송
		model.addAttribute("list", list);
		return "blist";
	}// blist

}// FController
