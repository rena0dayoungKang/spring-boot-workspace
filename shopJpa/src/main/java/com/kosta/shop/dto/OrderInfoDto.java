package com.kosta.shop.dto;

import java.sql.Date;

import org.modelmapper.ModelMapper;

import com.kosta.shop.entity.OrderInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfoDto {
	private Integer num;
	private String userid; //FK : Member의 userid 
	private String orderName;
	private String post;
	private String addr1;
	private String addr2;
	private String phone;
	private String payMethod;
	private Date orderDay;
	
	public OrderInfo toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, OrderInfo.class);
	}
}
