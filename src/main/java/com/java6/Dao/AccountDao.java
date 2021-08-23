package com.java6.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java6.Entity.Account;

public interface AccountDao extends JpaRepository<Account,String>{

}
