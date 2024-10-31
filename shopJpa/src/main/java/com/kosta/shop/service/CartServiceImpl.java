package com.kosta.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.shop.dto.CartDto;
import com.kosta.shop.dto.OrderDto;
import com.kosta.shop.dto.OrderInfoDto;

@Service
public class CartServiceImpl implements CartService {

	@Override
	public void addCart(CartDto cartDto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CartDto> cartList(String userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDto> orderAllConfirm(List<Integer> list) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartDto cartRetrieve(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cartDeleteAll(List<Integer> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cartDelete(Integer num) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cartUpdate(Integer num, Integer gAmount) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderDone(OrderInfoDto orderInfo, OrderDto orderDto, Integer orderNum) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderDto> orderAllDone(OrderInfoDto orderInfoDto, List<Integer> nums) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
