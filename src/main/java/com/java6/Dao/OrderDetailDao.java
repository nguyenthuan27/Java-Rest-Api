package com.java6.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java6.Entity.OrderDetails;
import com.java6.Entity.myproduct;

public interface OrderDetailDao extends JpaRepository<OrderDetails, Integer>{

	
		
	
	@Query("select DISTINCT new myproduct(sum(p.quantity),p.products.Name,sum(p.price),p.orders.createDate,p.statusdeli) from OrderDetails p "
			+ " Where p.orders.account.username LIKE?1   GROUP BY  p.products.Name,p.orders.createDate,p.orders.account.username,p.statusdeli")
	List<myproduct> findMyProduct(String username);
}
