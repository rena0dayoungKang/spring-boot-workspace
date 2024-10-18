package com.kosta.univ.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kosta.univ.dto.DepartmentDto;

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
public class Department {
	@Id
	private Integer deptno;
	@Column
	private String dname;
	@Column
	private Integer part;
	@Column
	private String build;

	@OneToMany(mappedBy = "department1", fetch = FetchType.LAZY)
	private List<Student> studList1 = new ArrayList<>();
	
	@OneToMany(mappedBy = "department2", fetch = FetchType.LAZY)
	private List<Student> studList2 = new ArrayList<>();
	
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	private List<Professor> profList = new ArrayList<>();

	public DepartmentDto toDto() {
		return DepartmentDto.builder()
						    .deptno(deptno)
						    .dname(dname)
						    .part(part)
						    .build(build)
						    .build();
	}
}
