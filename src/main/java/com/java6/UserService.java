package com.java6;

import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.java6.Dao.AccountDao;
import com.java6.Dao.AuthoritiesDao;
import com.java6.Entity.Account;
import com.java6.Entity.Authority;


@Service
public class UserService implements UserDetailsService {
	@Autowired
	AccountDao account;
	
	@Autowired
	AuthoritiesDao authority;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> user = account.findById(username);
		List<Authority> anh = authority.findAllAcount(user.get());
		List<GrantedAuthority> authorities = anh.stream().map(a -> a.getRole().getId())
				.map(menuName -> new SimpleGrantedAuthority(menuName)).collect(Collectors.toList());
		User dl = new User(user.get().getUsername(), user.get().getPassword(), authorities);
		return dl;

	}
}

