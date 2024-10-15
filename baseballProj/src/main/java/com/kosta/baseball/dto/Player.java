package com.kosta.baseball.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	private Integer num;
	private String name;
	private Integer backNum;
	private Integer teamNum;
	private String teamName; //DTO는 컬럼명이랑 같을 필요 없다. 테이블의 컬럼 갯수보다 많으면 됨. 프론트로 받을 때 사용 
							 //사용자가 player등록할때 teamName을 포함해서 등록할 때 사용하는 용도임. 테이블에 insert할때는 teamName사용X
}
