package com.java.www.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BController {
	@Autowired
	BService bService;

	// 1.게시글 전체 가져오기
	@RequestMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();

		// model에 담아서 전송
		model.addAttribute("list", list);
		return "board/bList";
	}// bList

	// 2. 게시글 1개 가져오기
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);

		// model
		model.addAttribute("map", map);

		return "board/bView";
	}// bView

	// 3. 게시글 쓰기 - 게시글 쓰기 페이지
	@RequestMapping("bInsert")
	public String bInsert() {
		return "board/bInsert";
	}// bInsert

	// 3-1. 게시글 쓰기 - 게시글 쓰기저장
	@RequestMapping("doBInsert")
	public String doBInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		// MultipartFiles files - <input type ="file name="files"> 의 name를 가져옴.
		// ▼파일첨부의 파일이름
		String orgName = "";
		String newName = "";
		if (!files.isEmpty()) {// 파일을 첨부했으면
			orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			newName = String.format("%s_%s", orgName, time);
			String upload = "c:/upload/";// 파일업로드 위치
			File f = new File(upload + newName); // 파일 생성
			files.transferTo(f);
			bdto.setBfile(newName);
		} else {
			bdto.setBfile("");
		} // if-else파일첨부시)
		bService.doBInsert(bdto);
		return "board/doBInsert";
	}// doBInsert

	// 4. 게시글 1개 삭제하기
	@RequestMapping("bDelete")
	public String bDelete(@RequestParam(defaultValue = "1") int bno) {
		System.out.println("BController bDelete bno : " + bno);
		bService.bDelete(bno);

		return "board/bDelete";
	}// bDelete

	// 5. 게시글 1개 수정하기 - 게시글 수정페이지
	@RequestMapping("bUpdate")
	public String bUpdate(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);

		// Model 저장후 보내기
		model.addAttribute("map", map);
		return "board/bUpdate";
	}// bUpdate

	// 5-1. 게시글 1개 수정하기 - 게시글 수정저장
	@RequestMapping("doBUpdate")
	public String doBUpdate(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		// MultipartFiles files - <input type ="file name="files"> 의 name를 가져옴.
		// ▼파일첨부의 파일이름
		String orgName = "";
		String newName = "";
		if (!files.isEmpty()) {// 파일을 첨부했으면
			orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			newName = String.format("%s_%s", orgName, time); // newName = time+"_"+orgName;
			String upload = "c:/upload/";// 파일업로드 위치
			File f = new File(upload + newName); // 파일 생성
			files.transferTo(f); // 파일전송
			bdto.setBfile(newName); // bdto파일이름 저장
		} // if

		// DB연결 & 전송
		bService.doBUpdate(bdto); // 파일이 없으면 기존파일 이름 그대로 사용
		model.addAttribute("bdto", bdto);
		return "board/doBUpdate";
	}// doBUpdate(BoardDto bdto, @RequestPart MultipartFile files)

	//6. 게시글 답글 - 게시글 답글 페이지
	@RequestMapping("bReply")
	public String bReply(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController bReply bno : " + bno);
		Map<String, Object> map = bService.selectOne(bno);

		// model 데이터 저장후 보내기
		model.addAttribute("map", map);
		return "board/bReply";
	}// bReply

}// BController
