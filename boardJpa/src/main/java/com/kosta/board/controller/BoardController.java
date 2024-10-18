package com.kosta.board.controller;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue="1") Integer page) { 
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<BoardDto> boardList = boardService.boardList(pageInfo);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "writeform";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDto boardDto, 
			@RequestPart(value="file", required=false) MultipartFile file, 
			@RequestPart(value="dfile", required=false) MultipartFile dfile, Model model)
	{	
		try {
			Integer boardNum = boardService.boardWrite(boardDto, file, dfile);
			return "redirect:boardDetail?num="+boardNum;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			BoardDto boardDto = boardService.boardDetail(num); 
			mav.addObject("board", boardDto);
			mav.setViewName("boarddetail");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@GetMapping("/image/{num}")
	public void imageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			FileInputStream fis = new FileInputStream(uploadPath+num);
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@GetMapping("/boardModify")
//	public ModelAndView boardModify(@RequestParam("num") Integer num) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			BoardDto board = boardService.boardDetail(num);
//			mav.addObject("board", board);
//			mav.setViewName("modifyform");
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.addObject("err", e.getMessage());
//			mav.setViewName("err");
//		} 
//		return mav;
//	}
//	
//	@PostMapping("/boardModify")
//	public String boardModify(BoardDto board, MultipartFile file, MultipartFile dfile, Model model) {
//		try {
//			Integer num = boardService.boardModify(board, file, dfile);
//			return "redirect:boardDetail?num="+num;
//		} catch (Exception e) {
//			model.addAttribute("err", e.getMessage());
//			return "err";
//		}	
//	}
	
	
//	@RequestMapping(value="/heart", method=RequestMethod.POST)
//	@ResponseBody 
//	public String heart(@RequestParam("num") Integer num) {
//		try {
//			boolean heart = boardService.toggleHeart(((MemberDto)session.getAttribute("member")).getId(), num);
//			return String.valueOf(heart);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "false";
//		}
//	}
	
	
//	@RequestMapping(value="/fileDown")
//	public void fileDown(@RequestParam("file") String filename, HttpServletResponse response) {
//		String path = "C:/kdy/upload/";
//		File file = new File(path, filename);
//		response.setContentType("application/download");
//		response.setContentLength((int)file.length());
//		response.setHeader("Content-disposition", "attachment;filename=\""+filename+"\"");
//		try {
//			OutputStream out = response.getOutputStream();
//			FileInputStream fis = new FileInputStream(file);
//			FileCopyUtils.copy(fis, out);
//			fis.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
