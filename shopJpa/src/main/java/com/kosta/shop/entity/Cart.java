package com.kosta.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;

import com.kosta.shop.dto.CartDto;

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
public class Cart {
	
	@Id
	private Integer num;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private Member member; 
	//private String userid; // FK: Member의 userid
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gCode")
	private Goods goods;
	//private String gCode;  // FK : Goods의 gCode
	
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
	
	public CartDto toDto() {
		ModelMapper mapper = new ModelMapper();
		CartDto cartDto = mapper.map(this, CartDto.class);
		return cartDto;
	}
}
