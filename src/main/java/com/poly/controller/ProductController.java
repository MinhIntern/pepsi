package com.poly.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.poly.demo.dao.CategoryDao;
import com.poly.demo.dao.ProductDao;
import com.poly.demo.dao.CategoryDao;
import com.poly.demo.entity.Category;
import com.poly.demo.entity.Product;
import com.poly.demo.service.ShoppingCartServiceImpl;

@Controller
public class ProductController {

	@Autowired
	CategoryDao categdao;

	@Autowired
	ProductDao proddao;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@RequestMapping("/product")
	public String product() {
		return "products";
	}

	@GetMapping("/product/buy/")
	public String product(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer id) {
		cartService.add(id);
		return "redirect:/product";
	}

	@RequestMapping("/product/buy/cart/reset")
	public String reset() {
		cartService.clear();
		return "redirect:/product";
	}
	
	@RequestMapping("/product/buy/cart/pay")
	public String pay() {
		cartService.pay();
		return "redirect:/product";
	}
	

	@ModelAttribute("category")
	public List<Category> category() {
		return categdao.findAll();
	}

	@ModelAttribute("cart")
	public Collection<Product> cart() {
		System.out.println(cartService.getProducts().size());
		return cartService.getProducts();
	}

	@ModelAttribute("total")
	public double total() {
		return cartService.getAmount();
	}

	@ModelAttribute("count")
	public int count() {
		return cartService.getCount();
	}
}
