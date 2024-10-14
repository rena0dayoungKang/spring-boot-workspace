package com.kosta.board.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommonsController {
	
	@GetMapping("/image/{filename}")
	public void imageView(@PathVariable String filename, HttpServletResponse response) {
		//System.out.println(filename); //확장자명 없이 파일 이름만 가져옴 
		//실무에서는 file이라는 테이블을 따로 관리하면서 file 번호를 board테이블에서 foreign키로 갖고 있게 한다. 
		//동일한 이름의 파일을 올려도 중복되지 않기 때문에 이점이 있음
		try {
			String path = "C:/kdy/uploadImage/";
			FileInputStream fis = new FileInputStream(path+filename);
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
