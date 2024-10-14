package com.kosta.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;

@Mapper
@Repository
public interface FileDao {
	
	void insertFile(BFile bFile) throws Exception;
	void deleteFile(@Param("num") Integer num) throws Exception;

}
