package com.kosta.univ.dto;

import java.sql.Date;

import com.kosta.univ.entity.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorDto {
	private Integer profno;
	private String name;
	private String id;
	private String position;
	private Integer pay;
	private Date hiredate;
	private Integer bonus;
	private String email;
	private String hpage;
	
	private Integer deptno;
	private String dname; //extra
	
	public Professor toEntity() {
		return Professor.builder()
						.profno(profno)
						.name(name)
						.id(id)
						.position(position)
						.pay(pay)
						.hiredate(hiredate)
						.bonus(bonus)
						.email(email)
						.hpage(hpage)
						.deptno(deptno)
						.build();
	}
	
}
