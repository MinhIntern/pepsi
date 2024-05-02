package com.poly.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.demo.entity.Category;
public interface CategoryDao extends JpaRepository<Category,String>{}


