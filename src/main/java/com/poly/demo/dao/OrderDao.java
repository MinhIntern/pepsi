package com.poly.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.demo.entity.Order;

public interface OrderDao extends  JpaRepository<Order, Long> {

}
