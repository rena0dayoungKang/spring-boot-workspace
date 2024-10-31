package com.kosta.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.kosta.shop.dto.OrderDto;

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
@Table(name = "gOrder")
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String userid;
	@Column
	private String gCode;
	@Column
	private String gName;
	@Column
	private Integer gPrice;
	@Column
	private String gSize;
	@Column
	private String gColor;
	@Column
	private Integer gAmount;
	@Column
	private String gImage;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderinfoNum")
	private OrderInfo orderInfoNum;
	//private Integer orderinfoNum; //FK : orderInfo의 num
	
	public OrderDto toDto() {
		ModelMapper mapper = new ModelMapper();
		OrderDto orderDto = mapper.map(this, OrderDto.class);
		return orderDto;
	}
	
}
