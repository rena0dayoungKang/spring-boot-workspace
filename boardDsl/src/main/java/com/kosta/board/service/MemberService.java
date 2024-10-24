package com.kosta.board.service;

import com.kosta.board.dto.MemberDto;

public interface MemberService {
	void join(MemberDto memberDto) throws Exception;
	MemberDto login(String id, String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}
