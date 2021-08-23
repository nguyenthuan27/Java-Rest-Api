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

import com.java6.Dao.ProductDao;
import com.java6.Entity.Category;
import com.java6.Entity.Products;

@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class ProductRestController {
	@Autowired
	ProductDao dao;
	
	
	@GetMapping("rest/products")
	public ResponseEntity<List<Products>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@GetMapping("rest/products/{id}")
	public ResponseEntity<Products> getOne(@PathVariable("id") Integer id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id).get());
	}
	
	@PostMapping("/rest/products")
	public ResponseEntity<Products> post(@RequestBody Products product){
		System.out.println("DATA" + product);
		System.out.println("IMG" + product.getImage());
		dao.save(product);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("rest/products/{id}")
	public ResponseEntity<Products> put(@PathVariable("id") Integer id,@RequestBody Products category){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.save(category);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("rest/products/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
