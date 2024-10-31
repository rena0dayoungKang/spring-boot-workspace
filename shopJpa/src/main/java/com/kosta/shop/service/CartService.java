package com.kosta.shop.service;

import java.util.List;

import com.kosta.shop.dto.CartDto;
import com.kosta.shop.dto.OrderDto;
import com.kosta.shop.dto.OrderInfoDto;

public interface CartService {
	void addCart(CartDto cartDto) throws Exception;
	List<CartDto> cartList(String userid) throws Exception;
	List<CartDto> orderAllConfirm(List<Integer> list) throws Exception;
	CartDto cartRetrieve(Integer num) throws Exception;
	void cartDeleteAll(List<Integer> list) throws Exception;
	void cartDelete(Integer num) throws Exception;
	void cartUpdate(Integer num, Integer gAmount) throws Exception; 
	void orderDone(OrderInfoDto orderInfo, OrderDto orderDto, Integer orderNum) throws Exception;
	List<OrderDto> orderAllDone(OrderInfoDto orderInfoDto, List<Integer> nums) throws Exception;
	
}
