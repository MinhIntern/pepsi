package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

import com.poly.demo.dao.AccountDao;
import com.poly.demo.entity.Account; // Import Account entity
import com.poly.demo.entity.Category;

import java.util.List; // Import List

@Controller
public class admincontroller {
	@Autowired
	private AccountDao accountDao;

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/account/edit/")
	public String account_edit(Model model, @RequestParam(value = "id", required = false, defaultValue = "") String id,
			@ModelAttribute("accountItem") Account acc) {
		// Tìm kiếm tài khoản dựa trên id
		Account account = accountDao.findById(id).get();
		// Load lại danh sách tài khoản và trả về trang admin
		model.addAttribute("account", account);
		return "admin";
	}

	@GetMapping("/account/delete")
	public String account_delete(Model model, @RequestParam(value = "id", required= false,defaultValue = "")String id, @ModelAttribute("accountItem")Account acc) {
		Account account = accountDao.findById(id).get();
		account.setActivated(false);
		accountDao.save(account);
		 List<Account> accounts = accountDao.findAll();
		    model.addAttribute("accounts", accounts);
		    return "admin";
	}

	@ModelAttribute("accounts")
	public List<Account> acco() {
		return accountDao.findAll();
	}
}
