package com.java6.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java6.Entity.Account;
import com.java6.Entity.Authority;

public interface AuthoritiesDao extends JpaRepository<Authority, Integer>{
	@Query("select p from Authority p where p.account=?1")
	List<Authority> findAllAcount(Account account);
}
