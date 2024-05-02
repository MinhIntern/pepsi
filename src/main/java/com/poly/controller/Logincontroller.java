package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.demo.dao.AccountDao;
import com.poly.demo.entity.Account;

@Controller
public class Logincontroller {
    
    @Autowired
    private AccountDao accountDao;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        Account account = accountDao.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            // Đăng nhập thành công, chuyển hướng đến trang chính
        	System.out.println("đăng nhập thành công!!!");
            return "redirect:/admin";
        } else {
            // Đăng nhập thất bại, hiển thị thông báo lỗi
            model.addAttribute("error", "Invalid username or password");
            System.out.println("đăng nhập thất bại!!!");
            return "login";
        }
    }
}
