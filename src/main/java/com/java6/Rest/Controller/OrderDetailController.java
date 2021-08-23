package com.java6.Rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java6.Dao.OrderDetailDao;
import com.java6.Entity.Category;
import com.java6.Entity.OrderDetails;


@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class OrderDetailController {
	@Autowired
	private OrderDetailDao dao;
	
	@GetMapping("rest/orderDetail")
	public ResponseEntity<List<OrderDetails>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	@GetMapping("rest/orderDetail/{id}")
	public ResponseEntity<OrderDetails> getOne(@PathVariable("id") Integer id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id).get());
	}
	
	@PostMapping("/rest/orderDetail")
	public ResponseEntity<OrderDetails> post(@RequestBody OrderDetails orderDetail){
		
		dao.save(orderDetail);
		return ResponseEntity.ok(orderDetail);
	}
	
	@PutMapping("rest/orderDetail/{id}")
	public ResponseEntity<OrderDetails> put(@PathVariable("id") Integer id,@RequestBody OrderDetails orderDetail){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.save(orderDetail);
		return ResponseEntity.ok(orderDetail);
	}
	
	@DeleteMapping("rest/orderDetail/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
