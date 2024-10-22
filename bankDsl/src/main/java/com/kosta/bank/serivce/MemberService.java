package com.kosta.bank.serivce;

import com.kosta.bank.dto.MemberDto;

public interface MemberService {
	void join(MemberDto memberDto) throws Exception;
	MemberDto login(String id,String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}
