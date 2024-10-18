package com.kosta.board.entitiy;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@Entity(name="file")
public class BFile {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String directory;
	@Column
	private String name;
	@Column
	private Long size;
	@Column
	private String contentType;
	@Column
	@CreationTimestamp
	private Date uploadDate;	
	
	@OneToOne(mappedBy = "imageFile", fetch = FetchType.LAZY)
	private Board boardImg;
	
	@OneToOne(mappedBy = "uploadFile", fetch = FetchType.LAZY)
	private Board boardUpload;
	
	
	
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer num;
//	@OneToOne(fetch = FetchType.LAZY)
//	private Board board1;
//	
//	@Column
//	private String directory;
//	
//	@Column
//	private String name;
//	@OneToOne(fetch = FetchType.LAZY)
//	private Board board2;
//	
//	@Column
//	private Long size;
//	@Column
//	private String contenttype;
//	@Column
//	private Date uploaddate;
}
