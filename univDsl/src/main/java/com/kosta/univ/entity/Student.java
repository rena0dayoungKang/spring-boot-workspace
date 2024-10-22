package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.kosta.univ.dto.StudentDto;

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
public class Student {
	@Id
	private Integer studno;
	@Column
	private String name;
	@Column
	private String id;
	@Column
	private Integer grade;
	@Column
	private String jumin;
	@Column
	private Date birthday;
	@Column
	private String tel;
	@Column
	private Integer height;
	@Column
	private Integer weight;
	@Column
	private Integer deptno1;
	@Column
	private Integer deptno2;
	@Column
	private Integer profno;
	
	public StudentDto toDto() {
		return StudentDto.builder()
						 .studno(studno)
						 .name(name)
						 .id(id)
						 .grade(grade)
						 .jumin(jumin)
						 .birthday(birthday)
						 .tel(tel)
						 .height(height)
						 .weight(weight)
						 .deptno1(deptno1)
						 .deptno2(deptno2)
						 .profno(profno)
						 .build();
	}
		
}
