package com.kosta.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.GoodsDto;
import com.kosta.shop.service.GoodsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final GoodsService goodsService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/main")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		try {
			List<GoodsDto> goodsDto = goodsService.goodsList();
			mav.addObject("goods", goodsDto); 
			//<c:forEach var="item" items="${goods}">
			mav.setViewName("shopMain");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품목록 조회");			
			mav.addObject("message", "상품목록 조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

}
