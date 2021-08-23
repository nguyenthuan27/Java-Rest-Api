package com.java6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java6.Dao.ProductDao;
import com.java6.Entity.Products;


@Service
public class ProductService {
	@Autowired
	private ProductDao productDAO;
	public List<Products> getAccountList(){
		return productDAO.findAll();
	}
	public Products geProductById(Integer id) {
		return productDAO.findById(id).get();
	}
	public void saveUpdateProduct(Products products) {
		productDAO.save(products);
	}
	public void deleteProduct(Integer id) {
		productDAO.deleteById(id);
	}
}
