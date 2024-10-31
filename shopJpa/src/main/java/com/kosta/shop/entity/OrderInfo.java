package com.kosta.shop.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.modelmapper.ModelMapper;

import com.kosta.shop.dto.OrderInfoDto;

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
@DynamicInsert
@Entity
public class OrderInfo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@OneToOne(mappedBy = "orderInfoNum", fetch = FetchType.LAZY)
	private Order orderNum;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private Member memId;
	//private String userid; //FK : Member의 userid 
		
	@Column
	private String orderName;
	@Column
	private String post;
	@Column
	private String addr1;
	@Column
	private String addr2;
	@Column
	private String phone;
	@Column
	private String payMethod;
	@Column
	@CreationTimestamp
	private Date orderDay;
	
	public OrderInfoDto toDto() {
		ModelMapper mapper = new ModelMapper();
		OrderInfoDto orderInfoDto = mapper.map(this, OrderInfoDto.class);
		return orderInfoDto;
	}
}
