package com.kosta.shop.service;

import com.kosta.shop.dto.MemberDto;

public interface MemberService {
	MemberDto login(String userid, String passwd) throws Exception;
	void signUp(MemberDto memberDto) throws Exception;
	boolean idCheck(String userid) throws Exception;

}
