package com.kosta.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
