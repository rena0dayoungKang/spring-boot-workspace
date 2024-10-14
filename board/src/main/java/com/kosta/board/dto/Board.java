package com.kosta.board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private Integer num;
	private String subject;
	private String content;
	private String writer;
	private String filename;
	private Date create_date;
	private Integer view_cnt;
	private String dfilename;
	
	
}
