package com.java6.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java6.Entity.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer>{
}
