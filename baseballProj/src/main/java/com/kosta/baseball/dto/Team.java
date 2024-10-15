package com.kosta.baseball.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //내가 원하는 형태의 값만 생성자로 만들어주는 것 -> 테스트코드 작성 시 유용
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	private Integer num;
	private String name;
	private String loc;
}
