package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.accountcreatingop.AccountTimeStampUpdater;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountMoneyDepositorService")
public class AccountMoneyDepositorService implements IAccountMoneyDepositor {

	private ILocalAccountRepository accountRepository;

	@Autowired
	public AccountMoneyDepositorService(ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account depositeMoney(Account account, AccountDepositRequest request) {

		float totalAmount = account.getAccountBalance() + Float.parseFloat(request.getAmount());
		account.setAccountBalance(totalAmount);
		account.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp());

		try {
			this.accountRepository.updateData(account);
			return account;
		} catch (Exception e) {
			return null;
		}
	}
}
