package com.kosta.board.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@ColumnDefault("0")
	private Integer viewCount;
	@Column
	@CreationTimestamp
	private Date createDate; 
	
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BFile> fileList = new ArrayList<>();
	
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
		
		if(fileList != null && fileList.size() > 0) {
			boardDto.setFileNums(fileList.stream()
										   .map(f -> f.getNum()+"")
										   .collect(Collectors.joining(","))); 
											//번호를가지고 , 로 연결해서 사용
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
