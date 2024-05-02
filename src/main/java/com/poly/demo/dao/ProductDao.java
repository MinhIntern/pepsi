package com.poly.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.demo.entity.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{

}
