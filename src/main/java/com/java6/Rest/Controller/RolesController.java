package com.java6.Rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.java6.Dao.RoleDao;
import com.java6.Entity.Role;

@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class RolesController {
	@Autowired 
	RoleDao dao;
	
	@GetMapping("rest/roles")
	public ResponseEntity<List<Role>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
}
