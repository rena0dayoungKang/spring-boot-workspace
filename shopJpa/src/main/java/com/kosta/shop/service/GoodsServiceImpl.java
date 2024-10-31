package com.kosta.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dto.GoodsDto;
import com.kosta.shop.entity.Goods;
import com.kosta.shop.repository.GoodsRepository;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsRepository goodsRepository;

	@Override
	public List<GoodsDto> goodsList() throws Exception {
		
		List<Goods> goodsList = goodsRepository.findAll();
		
		List<GoodsDto> goodsDtoList = goodsList.stream()
											   .map(i -> i.toDto())
											   .collect(Collectors.toList());
		goodsDtoList.forEach(dto -> System.out.println("goods Dto : " + dto));
	
		return goodsDtoList;
	}

	@Override
	public GoodsDto goodsRetrieve(String gCode) throws Exception {
		return null;
	}

}
