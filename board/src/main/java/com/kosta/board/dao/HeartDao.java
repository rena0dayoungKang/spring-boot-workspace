package com.kosta.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HeartDao {
	
	void insertHeart(@Param("memId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
	Integer selectHeart(@Param("memId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
	void deleteHeart(@Param("memId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
	//heart.xml에 있는 #{} 의 변수명과 @Param("") 변수명 일치해야 한다 

}
