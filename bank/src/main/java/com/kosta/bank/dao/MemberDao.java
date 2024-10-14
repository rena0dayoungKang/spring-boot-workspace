package com.kosta.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.bank.dto.Member;

@Mapper
@Repository
public interface MemberDao {
	void insertMember(Member member) throws Exception;
	Member selectMember(@Param("id") String id) throws Exception;
	void updateMember(Member member) throws Exception;
	
}
