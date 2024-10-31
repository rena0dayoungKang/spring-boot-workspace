package com.kosta.shop.dto;

import org.modelmapper.ModelMapper;

import com.kosta.shop.entity.Goods;

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
public class GoodsDto {
	private String gCode;
	private String gCategory;
	private String gName;
	private String gContent;
	private Integer gPrice;
	private String gImage;
	
	private String cartGcode;
	
	public Goods toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, Goods.class);
	}
}
