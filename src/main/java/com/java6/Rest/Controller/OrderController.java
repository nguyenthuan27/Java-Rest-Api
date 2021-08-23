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

import com.java6.Dao.OrderDao;
import com.java6.Entity.Account;
import com.java6.Entity.Orders;

@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class OrderController {
	@Autowired
	private OrderDao adao;
	
	@GetMapping("rest/order")
	public ResponseEntity<List<Orders>> getAll(Model model){
		return ResponseEntity.ok(adao.findAll());
	}
	
	@GetMapping("rest/order/{id}")
	public ResponseEntity<Orders> getOne(@PathVariable("id") Integer id){
		if (!adao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(adao.findById(id).get());
	}
	

	
	@PostMapping("/rest/order")
	public ResponseEntity<Orders> post(@RequestBody Orders order) {

//			if (adao.existsById(order.getId())) {
//				return ResponseEntity.badRequest().build();
//			}
			System.out.println("DATA" + order);
	
			adao.save(order);
			return ResponseEntity.ok(order);
	}
	
	@PutMapping("rest/order/{id}")
	public ResponseEntity<Orders> put(@PathVariable("id") Integer id,@RequestBody Orders order){
		if (!adao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		adao.save(order);
		return ResponseEntity.ok(order);
	}
	
	@DeleteMapping("rest/order/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		if (!adao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		adao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
