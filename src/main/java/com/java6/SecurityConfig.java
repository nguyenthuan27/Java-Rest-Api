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
@Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.getAccountById(username);
				String password = passwordEncoder.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream()
						.map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (Exception e) {
				// TODO: handle exception
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests()
		.antMatchers("/customer/**").authenticated()
		.antMatchers("/rest/authorities").hasAnyRole("DIRE","STAF")
		.anyRequest().permitAll()
		.and().formLogin()
		.loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/security/login/success",false)
		.failureUrl("/security/login/error")
		
		.and().rememberMe()
		.tokenValiditySeconds(864000)
		
		.and().exceptionHandling()
		.accessDeniedPage("/security/unauthoried")
		
		.and().logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/sucess");
		
//		http.cors().and().csrf().disable().authorizeRequests()
//		.antMatchers("/authenticate").permitAll()
//		.antMatchers("/api/v1/rest/accounts/**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/categories/**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/order**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/orderDetail/**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/products/**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/upload/**").hasAnyRole("DIRE", "STAF")
//		.antMatchers("/api/v1/rest/roles/**").hasAuthority("ROLE_DIRE")
//		.antMatchers("/api/v1/rest/rest/authorities/**").hasAuthority("ROLE_DIRE")
//		.and().addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder getpPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
