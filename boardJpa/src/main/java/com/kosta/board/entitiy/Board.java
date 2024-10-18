package com.kosta.board.entitiy;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.kosta.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert //@CreationTimestamp를 위함
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String subject;
	
	@Column(columnDefinition = "LONGTEXT")
	@Lob
	private String content;
	
	@Column
	@CreationTimestamp
	private Date createDate;
	
	@Column
	@ColumnDefault("0")
	private Integer viewCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private Member member;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imgFileName")
	private BFile imageFile;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploadFileName")
	private BFile uploadFile;
	
	@Override
	public String toString() {
		return "Board [num=" + num + ", subject=" + subject + ", content=" + content + ", createDate=" + createDate
				+ ", viewCount=" + viewCount + ", writer=" + member.getId() + "]";
	}
	
	
	public BoardDto toDto() {
		BoardDto boardDto = BoardDto.builder()
									.num(num)
									.subject(subject)
									.content(content)
									.createDate(createDate)
									.viewCount(viewCount)
									.writer(member.getId())
									.nickName(member.getNickname())
									.profileImage(member.getProfileImage())
									.build();
		if(imageFile != null) {
			boardDto.setImgFileNum(imageFile.getNum());
		}
		if(uploadFile != null) {
			boardDto.setUploadFileNum(uploadFile.getNum());
			boardDto.setUploadFileName(uploadFile.getName());
		}
		return boardDto;
	}



	



	

//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer num;
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
//	private List<Heart> heartList = new ArrayList<>();
//	
//	
//	@Column
//	private String subject;
//	@Column
//	private String content;
//	
//	//@Column
//	//private String writer;  //member 테이블의 id와 연결 
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "writer")
//	private Member member;	
//	
//	//@Column
//	//private String filename; //bfile 테이블의 name과 연결
//	@OneToOne(mappedBy = "board1",fetch = FetchType.LAZY)
//	@JoinColumn(name = "fileName")
//	private BFile bfile1;
//	
//	@Column
//	private Date create_date;
//	@Column
//	private Integer view_cnt;
//	
//	@OneToOne(mappedBy = "board2", fetch = FetchType.LAZY)
//	@JoinColumn(name = "dfilename")
//	private BFile bfile2; //bfile 테이블의 name과 연결 
	
	
	
}
