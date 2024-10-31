package com.kosta.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;

import com.kosta.shop.dto.GoodsDto;

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
public class Goods {
	
	@Id
	private String gCode;
	
	@OneToOne(mappedBy = "goods", fetch = FetchType.LAZY)
	private Cart cartGcode;
	
	@Column
	private String gCategory;
	@Column
	private String gName;
	@Column
	private String gContent;
	@Column
	private Integer gPrice;
	@Column
	private String gImage;
	
	public GoodsDto toDto() {
		return GoodsDto.builder()
					   .gCode(gCode)
					   .gCategory(gCategory)
					   .gName(gName)
					   .gContent(gContent)
					   .gPrice(gPrice)
					   .gImage(gImage)
					   .cartGcode(this.cartGcode != null ? this.cartGcode.getNum().toString():null)
					   .build();
	}
	
	
	
}
