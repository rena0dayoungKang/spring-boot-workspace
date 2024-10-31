package com.kosta.shop.service;

import java.util.List;

import com.kosta.shop.dto.GoodsDto;

public interface GoodsService {
	List<GoodsDto> goodsList() throws Exception;
	GoodsDto goodsRetrieve(String gCode) throws Exception;
}
