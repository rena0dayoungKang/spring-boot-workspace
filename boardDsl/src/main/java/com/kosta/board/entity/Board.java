package com.kosta.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.kosta.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String subject;
	@Column
	private String content;
	
//	@Column
//	private String writer;
//	@Column
//	private Integer imgFileNum;
//	@Column
//	private Integer uploadFileNum;
	
	@Column
	private Integer viewCount;
	@Column
	@CreationTimestamp
	private Date createDate; 
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imgFileNum")
	private BFile imageFile;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploadFileNum")
	private BFile uploadFile;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private Member member;
	
	public BoardDto toDto() {
		BoardDto boardDto = BoardDto.builder()
									.num(num)
									.subject(subject)
									.content(content)
									.viewCount(viewCount)
									.createDate(createDate)
									.writer(member.getId())
									.nickname(member.getNickname())
									.build();
		
		if(imageFile != null) {
			boardDto.setImgFileNum(imageFile.getNum());
		}
		if(uploadFile != null) {
			boardDto.setUploadFileNum(uploadFile.getNum());
			boardDto.setUploadFileName(uploadFile.getName());
		}
		if(member.getProfileImage() != null) {
			try {
				boardDto.setProfileImage(new String(Base64.encodeBase64(member.getProfileImage()),"UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return boardDto;
	}	
	
}
