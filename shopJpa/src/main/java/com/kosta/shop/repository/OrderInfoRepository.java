package com.kosta.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.shop.entity.OrderInfo;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {

}
