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

import com.java6.Dao.CategoryDao;
import com.java6.Entity.Category;

@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class CategoryController {
	@Autowired 
	private CategoryDao dao;
	
	@GetMapping("rest/categories")
	public ResponseEntity<List<Category>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@GetMapping("rest/categories/{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") String id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id).get());
	}
	
	@PostMapping("/rest/categories")
	public ResponseEntity<Category> post(@RequestBody Category category){
		if (dao.existsById(category.getId())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(category);
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("rest/categories/{id}")
	public ResponseEntity<Category> put(@PathVariable("id") String id,@RequestBody Category category){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.save(category);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("rest/categories/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
