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

import com.java6.Dao.AccountDao;
import com.java6.Entity.Account;


@CrossOrigin("*")
@RestController	
@RequestMapping("api/v1")
public class AccountController {
	@Autowired 
	AccountDao dao;
	
	@GetMapping("rest/accounts")
	public ResponseEntity<List<Account>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@GetMapping("rest/accounts/{username}")
	public ResponseEntity<Account> getOne(@PathVariable("username") String username){
		if (!dao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(username).get());
	}
	
	@PostMapping("rest/accounts")
	public ResponseEntity<Account> post(@RequestBody Account account){
		if (dao.existsById(account.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(account);
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("rest/accounts/{username}")
	public ResponseEntity<Account> put(@PathVariable("username") String username,@RequestBody Account account){
		if (!dao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		dao.save(account);
		return ResponseEntity.ok(account);
	}
	
	@DeleteMapping("rest/accounts/{username}")
	public ResponseEntity<Void> delete(@PathVariable("username") String username){
		if (!dao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(username);
		return ResponseEntity.ok().build();
	}
}
