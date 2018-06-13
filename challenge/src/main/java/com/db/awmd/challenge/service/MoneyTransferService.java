package com.db.awmd.challenge.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.interfaces.IMoneyTransferService;

@Service
public class MoneyTransferService implements IMoneyTransferService {


	@Override
	public Account transferAmount(Account accountFrom, Account accountTo, BigDecimal transferAmount) {
		EmailNotificationService emailNotificationService;
		if (((accountFrom.getBalance().compareTo(transferAmount)) == 1) || ((accountFrom.getBalance().compareTo(transferAmount)) == 0)) {
			emailNotificationService = new EmailNotificationService();
			BigDecimal newAccountToBalance = accountTo.getBalance().add(transferAmount);
			accountTo.setBalance(newAccountToBalance);
			emailNotificationService.notifyAboutTransfer(accountFrom,"Transferred : " + transferAmount + " from Account Number" + accountFrom.getAccountId() + 
					" to Account Number " + accountTo.getAccountId());	
			emailNotificationService.notifyAboutTransfer(accountTo, "Account number " + accountFrom.getAccountId() + " transferred " + transferAmount + " to Account Number" + accountTo.getAccountId());
		} else {
			emailNotificationService = new EmailNotificationService();
			emailNotificationService.notifyAboutTransfer(accountFrom, accountFrom.getAccountId() + " has insufficient funds to transfer to " + accountTo.getAccountId());			
		}
		return accountTo;
	}
}
