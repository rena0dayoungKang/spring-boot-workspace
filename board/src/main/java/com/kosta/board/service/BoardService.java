package com.kosta.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;


public interface BoardService {
	void boardWrite(Board board, MultipartFile file, MultipartFile dfile) throws Exception;
	Board boardDetail(Integer num) throws Exception;
	Integer checkHeart(String memberId, Integer boardNum) throws Exception;
	Integer boardModify(Board board, MultipartFile file, MultipartFile dfile) throws Exception;
	List<Board> boardList(PageInfo page) throws Exception;
	boolean toggleHeart(String id, Integer boardNum) throws Exception;
	void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception;	
	//void imageView(HttpServletRequest request, OutputStream out, String file) throws Exception;
}
