package com.poly.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.demo.dao.ProductDao;
import com.poly.demo.entity.Product;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductDao proddao;
	public Map<Integer, Product> map = new HashMap<>();

	@Override
	public void add(Integer id) {
		Product pro = map.get(id);
		if (pro == null) {
			Product prod = null;
			prod = new Product();
			prod.setId(id);
			prod.setName(proddao.findById(id).get().getName());
			prod.setPrice(proddao.findById(id).get().getPrice());
			prod.setQuantity(1);
			map.put(id, prod);
		} else {
			pro.setQuantity(pro.getQuantity() + 1);
			map.put(id, pro);
		}
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		map.remove(id);

	}

	@Override
	public Product update(Integer id, int qty) {
		// TODO Auto-generated method stub
		Product i = map.get(id);
		if (i != null) {
			i.setQuantity(i.getQuantity() + qty);
			map.put(id, i);
			return i;
		}
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		map.clear();

	}

	@Override
	public Collection<Product> getProducts() {
		Collection<Product> colProduct = new ArrayList<>();
		for (Entry<Integer, Product> entry : map.entrySet()) {
			colProduct.add(entry.getValue());
		}
		return colProduct;
	}

	@Override
	public int getCount() {
		int count = 0;
		for (Entry<Integer, Product> entry : map.entrySet()) {
			count += 1;
		}
		return count;
	}

	@Override
	public double getAmount() {
		double total = 0;
		for (Entry<Integer, Product> entry : map.entrySet()) {
			total += entry.getValue().getQuantity() * entry.getValue().getPrice();
		}
		return total;
	}

	
	@Autowired
	private ProductDao productDao;
	private void reduceProductQuantity(Integer productId, int quantity) {
	    Product product = productDao.findById(productId).orElse(null);
	    if (product != null) {
	        int remainingQuantity = product.getQuantity() - quantity;
	        if (remainingQuantity >= 0) {
	            product.setQuantity(remainingQuantity);
	            productDao.save(product);
	        } else {
	          
	        }
	    }
	}

	
	@Override
	public void pay() {
		for (Entry<Integer, Product> entry : map.entrySet()) {
			Integer productId = entry.getKey();
			Product product = entry.getValue();
			reduceProductQuantity(productId, product.getQuantity());
		}
		clear();
	}

}
