package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BController {

	@Autowired
	BService bService;

	@Autowired
	HttpSession session;

	// 1. 게시글 전체 가져오기
	@RequestMapping("bList")
	public String bList(Model model) {
		// 게시글 전체 가져오기
		ArrayList<BoardDto> list = bService.bList();

		// model에 데이터 담기
		model.addAttribute("list", list);

		return "board/bList";
	}// bList()

	// 2. 게시글 1개 가져오기
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController Bno : " + bno);
		// Service 연결 - 게시글 1개 가져오기
		BoardDto boardDto = bService.selectOne(bno);

		// model에 데이터 담기
		model.addAttribute("bdto", boardDto);
		return "board/bView";
	}// bView()

	// 3.글쓰기 페이지
	@RequestMapping("bInsert")
	public String bInsert() {
		// 파일이름 중복 방지 :UUID, long time = System.currentTimeMillis();
		// UUID uuid = UUID.randomUUID(); // UUID : 난수 발생함수
		// long time = System.currentTimeMillis(); //

		/*
		 * String fileName = "a.jpg"; System.out.println("uuid : " + uuid);
		 * System.out.println(uuid + "_" + fileName); System.out.println(time + "_" +
		 * fileName);
		 */

		return "board/bInsert";
	}// bInsert()

	// 3-1.게시글 저장
	@RequestMapping("doBInsert")
	public String doBInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		// ▼session에서 id 가져옴.
		bdto.setId((String) session.getAttribute("session_id"));

		if (!files.isEmpty()) {// 파일 첨부가 null이 아니면
			String oriFileName = files.getOriginalFilename(); // 원본 파일 이름을 가져옴.
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid + "_" + oriFileName;

			// 1.파일저장 위치
			String fileUpload = "C:/upload/";
			// 2.파일 첨부
			File f = new File(fileUpload + newFileName);
			// 3.try-catch 또는 throws Exception
			files.transferTo(f);

			// 4.파일이름 저장 /파일이 없을 때는 파일이름이 null로 저장
			bdto.setBfile(newFileName);
		} // if

		// 파일저장 : service연결 - bInsert
		bService.bInsert(bdto);

		return "board/doBInsert";
	}// doBInsert()

}// BController
