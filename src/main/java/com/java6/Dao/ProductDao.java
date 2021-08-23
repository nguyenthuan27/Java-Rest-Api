package com.java6.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java6.Entity.Products;

public interface ProductDao extends JpaRepository<Products, Integer>{

}
