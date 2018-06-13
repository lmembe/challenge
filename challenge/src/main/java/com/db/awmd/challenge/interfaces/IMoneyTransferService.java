package com.db.awmd.challenge.interfaces;

import java.math.BigDecimal;

import com.db.awmd.challenge.domain.Account;

public interface IMoneyTransferService {
	
	Account transferAmount(Account accountFrom, Account accountTo, BigDecimal transferAmount);
}
