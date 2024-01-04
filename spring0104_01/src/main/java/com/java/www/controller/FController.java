package com.java.www.controller;

import java.io.File;
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

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {

	@Autowired
	BService bService;
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String index() {
		return "index";
	}// index

	// 1. 전체
	@GetMapping("blist")
	public String blist(Model model) {

		// 전체 게시글 가져오기
		ArrayList<BoardDto> list = bService.blist();

		// model 저장 후 전송
		model.addAttribute("list", list);

		return "blist";
	}// blist

	@GetMapping("bwrite")
	public String bwrite() {

		return "bwrite";
	}// bwrite
	
	//3. 게시글 1개 가져오기
	@GetMapping("bview")
	public String bview(@RequestParam (defaultValue = "1") int bno, Model model) {
		BoardDto bdto = bService.selectOne(bno);
		
		model.addAttribute("bdto", bdto);
		
		
		return "bview";
	}// bview

	// 내용부분 - 이미지 추가시 호출
	@PostMapping("summernoteUpload")
	@ResponseBody
	public String summernoteUpload(@RequestPart MultipartFile file) throws Exception {
		String urlLink = "";

		if (!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time + "_" + oriFName;
			String fupload = "c:/upload/";

			// 파일 업로드 부분
			 File f = new File(fupload+upFName);
			 file.transferTo(f);

			// 파일저장위치
			urlLink = "/upload/" + upFName;
			System.out.println(" summernoteUpload 파일저장 위치 : "+urlLink);
		} // if
		return urlLink;
	}// bwrite

	@GetMapping("write")
	public String write() {

		return "write";
	}// write

	// submit버튼 클릭시 파일업로드
	@PostMapping("write")
	public String write(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {
		System.out.println("Controller bdto btitle : " + bdto.getBtitle());
		System.out.println("Controller bdto bcontent : " + bdto.getBcontent());
		if (!file.isEmpty()) {
			String oriFName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time + "_" + oriFName;
			String fupload = "c:/upload/";

			// 파일 업로드 부분
			 File f = new File(fupload+upFName);
			 file.transferTo(f);
			// bdto bfile 추가
			bdto.setBfile(upFName);

		} else {
			bdto.setBfile("");
		}
		
		// if
		System.out.println("파일첨부 이름 : " + bdto.getBfile());
		
		//service연결 - 파일저장
		bService.write(bdto);
		
		model.addAttribute("result", "write-s");
		return "doFboard";
	}// write

}// FController
