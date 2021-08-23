package com.java6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java6.Dao.AccountDao;
import com.java6.Entity.Account;
@Service
public class AccountService {
	@Autowired
	private AccountDao accountDAO;
	public List<Account> getAccountList(){
		return accountDAO.findAll();
	}
	public Account getAccountById(String username) {
		return accountDAO.findById(username).get();
	}
	public void saveUpdateAccount(Account account) {
		accountDAO.save(account);
	}
	public void deleteAccount(String username) {
		accountDAO.deleteById(username);
	}
}
