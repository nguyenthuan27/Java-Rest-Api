package com.java6.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java6.Entity.Category;

public interface CategoryDao extends JpaRepository<Category, String>{

}
