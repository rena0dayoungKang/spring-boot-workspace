package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.kosta.univ.dto.ProfessorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Professor {
	@Id
	private Integer profno;
	@Column
	private String name;
	@Column
	private String id;
	@Column
	private String position;
	@Column
	private Integer pay;
	@Column
	private Date hiredate;
	@Column
	private Integer bonus;
	@Column
	private Integer deptno;
	@Column
	private String email;
	@Column
	private String hpage;
	
	public ProfessorDto toDto() {
		return ProfessorDto.builder()
						   .profno(profno)
						   .name(name)
						   .id(id)
						   .position(position)
						   .pay(pay)
						   .hiredate(hiredate)
						   .bonus(bonus)
						   .deptno(deptno)
						   .email(email)
						   .hpage(hpage)
						   .build();
	}
	
}
