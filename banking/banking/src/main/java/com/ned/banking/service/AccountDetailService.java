package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountDetailService")
public class AccountDetailService implements IAccountDetail {

	private ILocalAccountRepository accountRepository;

	@Autowired
	public AccountDetailService(ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account getAccountDetail(int id) {
		return (Account) this.accountRepository.getDataById(id);
	}
}
