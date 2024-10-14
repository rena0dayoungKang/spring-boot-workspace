package com.kosta.board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BFile {
	private Integer num;
	private String directory;
	private String name;
	private Long size;
	private String contenttype;
	private Date uploaddate;

}
