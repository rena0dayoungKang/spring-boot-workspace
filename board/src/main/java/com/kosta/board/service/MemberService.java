package com.kosta.board.service;

import com.kosta.board.dto.Member;

public interface MemberService {
	void join(Member member) throws Exception;
	Member login(String id, String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}
