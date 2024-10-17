package com.kosta.baseball.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
//	@Column
//	private Integer teamNum;
	
	//일대다 관계 중 player가 다수  Many:Player One:Team
	@ManyToOne(fetch=FetchType.EAGER) //EAGER : 즉시로딩
	@JoinColumn(name="teamNum")	//player테이블에 teamNum이 생긴다
	private Team team;  //team 이라는 변수명과 Team테이블에서 mapped By가 같아야함
	/*일대다 관계가 많다면 (테이블이 많다면), @ManyToOne 관계를 많이 달아놓으면 성능저하*/
	
	public PlayerDto toDto() {
		return PlayerDto.builder().num(num).name(name).backNum(backNum)
								  .teamNum(team.getNum())
								  .teamName(team.getName())
								  .teamLoc(team.getLoc())
								  .build();
	}
	
	//Entity 클래스는 테이블 create용
	//주로 데이터베이스 작업(삽입, 수정, 삭제, 조회)을 수행
}
