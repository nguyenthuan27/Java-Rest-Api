package com.java6.Rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java6.Dao.AuthoritiesDao;
import com.java6.Entity.Authority;
import com.java6.Entity.Category;


@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class AuthoritiesController {
	@Autowired 
	private AuthoritiesDao dao;
	
	@GetMapping("rest/authorities")
	public ResponseEntity<List<Authority>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@PostMapping("/rest/authorities")
	public ResponseEntity<Authority> post(@RequestBody Authority authority){
		if (dao.existsById(authority.getId())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(authority);
		return ResponseEntity.ok(authority);
	}
}
