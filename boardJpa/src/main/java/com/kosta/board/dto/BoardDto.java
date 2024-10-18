package com.kosta.board.dto;

import java.sql.Date;

import com.kosta.board.entitiy.BFile;
import com.kosta.board.entitiy.Board;
import com.kosta.board.entitiy.Member;

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
	private Date createDate;
	private Integer viewCount;

	private Integer imgFileNum;
	private Integer uploadFileNum;
	private String uploadFileName;
	
	//글 자체에 글쓴이의 아이디, 닉네임, 이미지가 따라다닐수도있어서 추가함
	private String writer;
	private String nickName;
	private byte[] profileImage;
	
	public Board toEntity() {
		Board board = Board.builder()
						   .num(num)
						   .subject(subject)
						   .content(content)
						   .createDate(createDate)
						   .viewCount(viewCount)
						   .member(Member.builder()
								   		 .id(writer)
								   		 .nickname(nickName)
								   		 .profileImage(profileImage)
								   		 .build())
						   .build();
		if(imgFileNum != null) {
			board.setImageFile(BFile.builder().num(imgFileNum).build());
		}
		if(uploadFileNum != null) {
			board.setUploadFile(BFile.builder().num(uploadFileNum).name(uploadFileName).build());
		}
		return board;
	}
	
}
