package com.poly.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.demo.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>{
    Account findByUsername(String username);
}
