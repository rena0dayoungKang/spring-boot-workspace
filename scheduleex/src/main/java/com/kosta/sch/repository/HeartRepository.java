package com.kosta.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.sch.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {

}
