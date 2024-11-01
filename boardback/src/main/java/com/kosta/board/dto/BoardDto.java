package com.kosta.board.dto;

import java.sql.Date;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	private Integer num;
	private String subject;
	private String content;
	private String fileNums;
	private Integer viewCount;
	private Date createDate;

	private String writer;	
	private String profileImage;
	private String nickname;
	
	public Board toEntity() {
		Board board = Board.builder()
					.num(num)
					.subject(subject)
					.content(content)
					.viewCount(viewCount)
					.createDate(createDate)
					.member(Member.builder().id(writer).build())
					.build();
		
		return board;
	}
	
	
}
