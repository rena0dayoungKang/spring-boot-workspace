package com.kosta.univ.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kosta.univ.dto.ProfessorDto;

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
//	@Column
//	private Integer deptno;
	@Column
	private String email;
	@Column
	private String hpage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptno")
	private Department department;
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	private List<Student> studentList = new ArrayList<>();

	@Override
	public String toString() {
		return "Professor [profno=" + profno + ", name=" + name + ", id=" + id + ", position=" + position + ", pay="
				+ pay + ", hiredate=" + hiredate + ", bonus=" + bonus + ", email=" + email + ", hpage=" + hpage
				+ ", department=" + department + ", studentList=" + studentList + "]";
	}
	
	
	public ProfessorDto toDto() {
		return ProfessorDto.builder()
						   .profno(profno)
						   .name(name)
						   .id(id)
						   .position(position)
						   .pay(pay)
						   .hiredate(hiredate)
						   .bonus(bonus)
						   .email(email)
						   .hpage(hpage)
						   .deptno(department.getDeptno())
						   .dname(department.getDname())
						   .build();
	}
	
}
