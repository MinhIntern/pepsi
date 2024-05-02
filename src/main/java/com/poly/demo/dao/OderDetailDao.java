package com.poly.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.demo.entity.OrderDetail;

public interface OderDetailDao  extends JpaRepository<OrderDetail, Long>{

}
