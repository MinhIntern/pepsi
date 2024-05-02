package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexcontroller {
	@RequestMapping("/index")
	public String test() {
		return "index";
	}
	
	
	@RequestMapping("/products")
	public String admin() {
		return "products";
	}
	
	
	
}
