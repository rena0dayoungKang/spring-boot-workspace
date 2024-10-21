package com.kosta.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.board.service.BoardService;

@SpringBootTest
class BoardJpaApplicationTests {

	@Autowired
	private BoardService boardService;
	
	
	@Test
	void checkHeartTest() throws Exception {
		Integer boardLikeNum = boardService.checkHeart("kosta", 1);
		System.out.println("------------->"+boardLikeNum);
	}

}
