package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.dto.MemberDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final HttpSession session;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value="type", required = false) String type,
			@RequestParam(value="word", required = false) String word) {
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<BoardDto> boardList = boardService.boardList(pageInfo, type, word);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("type", type);
			mav.addObject("word", word);
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
	public String boardWrite(BoardDto boardDto, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "dfile", required = false) MultipartFile dfile, Model model) {
		try {
			Integer boardNum = boardService.boardWrite(boardDto, file, dfile);
			return "redirect:boardDetail?num=" + boardNum;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}

	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("num") Integer num) {
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		ModelAndView mav = new ModelAndView();
		try {
			BoardDto boardDto = boardService.boardDetail(num);
			mav.addObject("board", boardDto);
			if (memberDto != null) {
				mav.addObject("heart", boardService.checkHeart(memberDto.getId(), num));
			}
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
			FileInputStream fis = new FileInputStream(uploadPath + num);
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/boardModify/{num}")
	public ModelAndView boardModify(@PathVariable Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("board", boardService.boardDetail(num));
			mav.setViewName("modifyform");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}

	@PostMapping("/boardModify")
	public String boardModify(BoardDto boardDto, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "dfile", required = false) MultipartFile dfile, Model model) {
		try {
			boardService.boardModify(boardDto, file, dfile);
			return "redirect:boardDetail?num=" + boardDto.getNum();
		} catch (Exception e) {
			e.printStackTrace();
			return "err";
		}
	}

	@PostMapping("/heart")
	@ResponseBody
	public ResponseEntity<String> heart(@RequestParam("num") Integer boardNum) {
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			boolean check = boardService.toggleHeart(memberDto.getId(), boardNum);
			return new ResponseEntity<String>(String.valueOf(check), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/fileDown")
	public void fileDown(@RequestParam("file") String filename, HttpServletResponse response) {
		try {
			FileInputStream fis = new FileInputStream(new File(uploadPath, filename));

			// 파일 형식 얻어옴
			String mimeType = "application/octet-stream"; // octet-stream : 8비트로 된 일련의 데이터를 뜻함. 지정되지 않은 파일 타입을 의미
			
			response.setContentType(mimeType);
			String encoding = new String(filename.getBytes("utf-8"), "8859_1"); // 한글 파일명 깨짐 방지
			response.setHeader("content-Disposition", "attachment; filename= " + encoding);

			FileCopyUtils.copy(fis, response.getOutputStream());
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
