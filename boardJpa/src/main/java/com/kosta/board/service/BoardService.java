package com.kosta.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.util.PageInfo;

public interface BoardService {
	Integer boardWrite(BoardDto boardDto, MultipartFile imageFile, MultipartFile uploadFile) throws Exception;
	BoardDto boardDetail(Integer num) throws Exception;
	Integer checkHeart(String memberId, Integer boardNum) throws Exception;
	Integer boardModify(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception;
	List<BoardDto> boardList(PageInfo page) throws Exception;
	boolean toggleHeart(String id, Integer boardNum) throws Exception;
	void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
