package com.java6;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.java6.Entity.Account;
import com.java6.Service.AccountService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityRestConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserService userServie;
	
//	@Autowired 
//	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JwtFilter jwtfilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServie);
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/authenticate").permitAll()
		.antMatchers("/api/v1/rest/accounts/**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/categories/**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/order**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/orderDetail/**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/products/**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/upload/**").hasAnyRole("DIRE", "STAF")
		.antMatchers("/api/v1/rest/roles/**").hasAuthority("ROLE_DIRE")
		.antMatchers("/api/v1/rest/rest/authorities/**").hasAuthority("ROLE_DIRE")
		.and().addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	
	
}
