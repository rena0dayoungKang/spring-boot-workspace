package com.kosta.board.entitiy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class BoardLike {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memId")
	private Member member;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BoardNum")
	private Board board;

	@Override
	public String toString() {
		return "BoardLike [num=" + num + ", memId=" + member.getId() + ", boardNum=" + board.getNum() + "]";
	}
	
	

}
