package com.java6.Rest.Controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java6.JWTUtility;
import com.java6.UserService;
import com.java6.Dao.AccountDao;
import com.java6.Dao.AuthoritiesDao;
import com.java6.Entity.JWTRequet;
import com.java6.Entity.JWTRespone;

@CrossOrigin("*")
@RestController
public class HomeRest {
	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@Autowired
	AccountDao account;
	
	@Autowired
	AuthoritiesDao authority;
	
	@GetMapping("/a")
	public String home() {
		return "Welcome to Daily Code Buffer!!";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<JWTRespone> authenticate(@RequestBody JWTRequet jwtRequest) throws Exception {
		// Xác thực từ username và password.
		System.out.println("vaof");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
		Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
		List<String> anh = new ArrayList<String>();
		anh.add("DIRE");
		anh.add("STAF");
		List<GrantedAuthority> authorities = anh.stream().map(a -> a)
				.map(menuName -> new SimpleGrantedAuthority(menuName)).collect(Collectors.toList());
		System.out.println("token" + role.contains(authorities.get(1)));
		if (role.contains(authorities.get(0)) == true || role.contains(authorities.get(1)) == true) {
			final String token = jwtUtility.generateToken(userDetails);
			JWTRespone dl = new JWTRespone();
			dl.setJwtToken(token);
			dl.setDIRE(role.contains(authorities.get(0)));
			dl.setSTAF(role.contains(authorities.get(1)));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("toke" + token);
			return ResponseEntity.ok(dl);
			
		} else {
			System.out.println("cavafd");
			return ResponseEntity.badRequest().build();
		}

	}
}
