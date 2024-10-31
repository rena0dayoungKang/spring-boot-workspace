package com.kosta.shop.dto;

import org.modelmapper.ModelMapper;

import com.kosta.shop.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
	
	private Integer num;
	private String userid; // FK: Member의 userid
	private String gCode;  // FK : Goods의 gCode
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	
	public Cart toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, Cart.class);
	}
}
