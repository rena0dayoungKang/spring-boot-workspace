package com.kosta.shop.dto;

import org.modelmapper.ModelMapper;

import com.kosta.shop.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
	
	private Integer num;
	private String userid;
	private String gCode;
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	private Integer orderinfoNum;
	
	public Order toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, Order.class);
	}
}
