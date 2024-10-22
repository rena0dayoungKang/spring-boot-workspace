package com.kosta.baseball.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kosta.baseball.dto.PlayerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Player {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String name;
	@Column
	private Integer backNum;
	@Column
	private Integer teamNum;
	
	public PlayerDto toDto() {
		return PlayerDto.builder().num(num).name(name).backNum(backNum)
								  .teamNum(teamNum)
								  .build();
	}
}
