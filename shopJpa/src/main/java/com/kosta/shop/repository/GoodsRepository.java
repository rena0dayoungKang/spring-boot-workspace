package com.kosta.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.shop.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, String> {

}
