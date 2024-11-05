package com.kosta.board.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.QBoard;
import com.kosta.board.entity.QBoardLike;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardDslRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	public Long findBoardCount() throws Exception {
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board.count())
							  .from(board)
							  .fetchOne();							  
	}
	
	public Long searchBoardCount(String type, String word) throws Exception {
		QBoard board = QBoard.board;
		Long cnt = 0L;
		if(type.equals("subject")) {
			cnt = jpaQueryFactory.select(board.count())
					  .from(board)
					  .where(board.subject.contains(word))
					  .fetchOne();
		} else if (type.equals("content")) {
			cnt = jpaQueryFactory.select(board.count())
					  .from(board)
					  .where(board.content.contains(word))
					  .fetchOne();
		} else if (type.equals("writer")) {
			cnt = jpaQueryFactory.select(board.count())
					  .from(board)
					  .where(board.member.nickname.contains(word))
					  .fetchOne();
		}
		return cnt;
	}
	
	public List<Board> findBoardListByPaging(PageRequest pageRequest) throws Exception {
		QBoard board = QBoard.board;
		//QMember member = QMember.member;
		return jpaQueryFactory.selectFrom(board)
					   .orderBy(board.num.desc())
					   .offset(pageRequest.getOffset())
					   .limit(pageRequest.getPageSize())
					   .fetch();
	}
	
	public List<Board> searchBoardListByPaging(PageRequest pageRequest, String type, String word) throws Exception {
		QBoard board = QBoard.board;
		List<Board> boardList = null;
		
		if(type.equals("subject")) {
			boardList = jpaQueryFactory.selectFrom(board)
										.where(board.subject.contains(word))
										.orderBy(board.num.desc())
									    .offset(pageRequest.getOffset())
									    .limit(pageRequest.getPageSize())
									    .fetch();	
			
		} else if (type.equals("content")) {
			boardList = jpaQueryFactory.selectFrom(board)
										.where(board.content.contains(word))
										.orderBy(board.num.desc())
									    .offset(pageRequest.getOffset())
									    .limit(pageRequest.getPageSize())
									    .fetch();
			
		} else if (type.equals("writer")) {
			boardList = jpaQueryFactory.selectFrom(board)
										.where(board.member.nickname.contains(word))
										.orderBy(board.num.desc())
									    .offset(pageRequest.getOffset())
									    .limit(pageRequest.getPageSize())
									    .fetch();		
			
		}
		return boardList;
	}
	
	public Integer findBoardLike(String memId, Integer boardNum) throws Exception {
		QBoardLike boardLike = QBoardLike.boardLike;
		return jpaQueryFactory.select(boardLike.num)
							  .from(boardLike)
							  .where(boardLike.memId.eq(memId).and(boardLike.boardNum.eq(boardNum)))
							  .fetchOne();
	}
	
	
	public void updateBoardViewCount(Integer boardNum, Integer viewCount) throws Exception {
		QBoard board = QBoard.board;
		jpaQueryFactory.update(board)
					   .set(board.viewCount, viewCount)
					   .where(board.num.eq(boardNum))
					   .execute();
	}
	
}
