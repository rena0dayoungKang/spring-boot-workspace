package com.kosta.board.service;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.MemberDto;

public interface MemberService {
	void join(MemberDto memberDto, MultipartFile profile) throws Exception;
	MemberDto login(String id, String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}
