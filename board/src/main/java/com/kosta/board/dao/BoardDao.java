package com.kosta.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Board;

@Mapper
@Repository
public interface BoardDao {
	
	void insertBoard(Board board) throws Exception;
	Board selectBoard(@Param("num") Integer num) throws Exception;
	void updateBoard(Board board) throws Exception;
	void updateViewCnt(@Param("num") Integer num) throws Exception;
	Integer selectBoardCount() throws Exception;
	List<Board> selectBoardList(@Param("row") Integer row) throws Exception;

}
