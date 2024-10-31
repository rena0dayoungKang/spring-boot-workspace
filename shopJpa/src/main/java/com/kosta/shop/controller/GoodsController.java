package com.kosta.shop.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.CartDto;
import com.kosta.shop.dto.GoodsDto;
import com.kosta.shop.dto.MemberDto;
import com.kosta.shop.dto.OrderDto;
import com.kosta.shop.dto.OrderInfoDto;
import com.kosta.shop.service.CartService;
import com.kosta.shop.service.GoodsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {

	private final GoodsService goodsService;
	private final CartService cartService;
	@Autowired
	private HttpSession session;

	@GetMapping("/goodsRetrieve")
	public ModelAndView goodsRetrieve(@RequestParam("gCode") String gCode,
			@RequestParam(name = "cart", required = false, defaultValue = "N") String cart) {
		ModelAndView mav = new ModelAndView();
		try {
			GoodsDto goodsDto = goodsService.goodsRetrieve(gCode);
			mav.addObject("item", goodsDto);
			mav.addObject("cart", cart);
			// <input type="hidden" name="gImage" value="${item.gImage}">
			mav.setViewName("goodsRetrieve");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품 상세 조회");
			mav.addObject("message", "상품 상세 조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	@GetMapping("/orderConfirm")
	// gImage=top1&gCode=T1&gName=앨리스+데님+탑&gPrice=12100 --> Goods dto에 있음
		// &gSize=L&gColor=navy&gAmount=1 --> 내가 선택한 값
	public ModelAndView orderConfirm(GoodsDto goodsDto, @RequestParam("gSize") String gSize,
			@RequestParam("gColor") String gColor, @RequestParam("gAmount") Integer gAmount) {
		ModelAndView mav = new ModelAndView("orderConfirm");
		// gDTO :상품정보
		mav.addObject("gDTO", goodsDto);
		// gSize
		mav.addObject("gSize", gSize);
		// gColor
		mav.addObject("gColor", gColor);
		// gAmount
		mav.addObject("gAmount", gAmount);
		return mav;
	}

	@GetMapping("/addCart")
	// addCart?
	// gImage=top10&gCode=T10&gName=홀+포켓+보이+프렌드+셔츠&gPrice=27800
	// &gSize=L&gColor=navy&gAmount=1
	public ModelAndView addCart(CartDto cartDto) {
		ModelAndView mav = new ModelAndView();
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("user");
			cartDto.setUserid(memberDto.getUserid());
			cartService.addCart(cartDto);
			mav.setViewName("redirect:goodsRetrieve?gCode=" + cartDto.getGCode() + "&cart=Y");
			// 상품을 장바구니에 담고나서 다시 상세보기 화면으로 redirect
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "장바구니 담기"); // addObject : key와 value를 담아 보낼 수 있는 메서드
			mav.addObject("message", "장바구니 담기 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	@GetMapping("/cartList")
	@ModelAttribute("cartList")
	// 많이 쓰는 방법. (Model : 데이터)
	// 내가 응답으로 주는 애가 cartList라는 모델에 담아준다
	// setViewName 대신에 <c:if test="${empty cartList}"> 에서의 cartList 이름과 같아야함
	public List<CartDto> cartList() {
		List<CartDto> cartList = null;
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("user");
			cartList = cartService.cartList(memberDto.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	// 장바구니에서 선택삭제 기능
	// CartDelAll?cartAmount=1&cartAmount=1&cartAmount=1&check=15&cartAmount=1
	@GetMapping("/CartDelAll")
	public String cartDelAll(@RequestParam("check") Integer[] num) {
		try {
			cartService.cartDeleteAll(Arrays.asList(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cartList";
	}

	// 장바구니에서 하나씩 삭제버튼
	@GetMapping("/cartDelete")
	@ResponseBody
	public void cartDelete(@RequestParam("num") Integer num) {
		try {
			cartService.cartDelete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 장바구니에서 수량 수정
	@GetMapping("/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam("num") Integer num, @RequestParam("gAmount") Integer gAmount) {
		try {
			cartService.cartUpdate(num, gAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// cartOrderConfirm?num=15
	// 장바구니에서 바로 주문버튼 클릭
	@GetMapping("/cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			CartDto cartDto = cartService.cartRetrieve(num);
			mav.addObject("cDTO", cartDto); // <c:if test="${not empty cDTO}">
			mav.setViewName("orderConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "주문확인");
			mav.addObject("message", "주문확인 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	// cartOrderAllConfirm?allCheck=on&check=15&cartAmount=1&check=16&cartAmount=6&check=17&cartAmount=1
	// 장바구니에서 선택 주문
	@GetMapping("/cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer[] num) {
		ModelAndView mav = new ModelAndView();
		try {
			List<CartDto> cartDtoList = cartService.orderAllConfirm(Arrays.asList(num));
			mav.addObject("cartList", cartDtoList);
			mav.setViewName("orderAllConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "주문확인");
			mav.addObject("message", "주문확인 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	// orderDone
	@GetMapping("/cartOrderDone")
	public ModelAndView cartOrderDone(@ModelAttribute OrderInfoDto orderInfoDto, @ModelAttribute OrderDto orderDto,
			@RequestParam(name = "orderNum", required = false) Integer orderNum) {
		ModelAndView mav = new ModelAndView();
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("user");
			orderInfoDto.setUserid(memberDto.getUserid());
			orderDto.setUserid(memberDto.getUserid());
			cartService.orderDone(orderInfoDto, orderDto, orderNum);
			mav.setViewName("orderDone");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품결제");
			mav.addObject("message", "상품결제 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	
	// orderAllDone
	@GetMapping("/cartOrderAllDone")
	public ModelAndView cartOrderAllDone(@RequestParam("num") Integer[] nums, @ModelAttribute OrderInfoDto orderInfoDto) {
		ModelAndView mav = new ModelAndView();
		try {
			MemberDto memberDto = (MemberDto)session.getAttribute("user");
			orderInfoDto.setUserid(memberDto.getUserid());
			List<OrderDto> orderList = cartService.orderAllDone(orderInfoDto, Arrays.asList(nums));
			mav.addObject("orderAllDone", orderList);
			mav.setViewName("orderAllDone");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품결제");
			mav.addObject("message", "상품결제 실패");
			mav.setViewName("memberResult");
		}		
		return mav;
	}
}
