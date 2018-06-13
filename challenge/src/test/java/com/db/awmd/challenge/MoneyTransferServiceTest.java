package com.db.awmd.challenge;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.service.MoneyTransferService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyTransferServiceTest {

  @Autowired
  private MoneyTransferService moneyTransferService;

  @Test
  public void transferFromAccountWithMoneyAmount() throws Exception {
	Account accountFrom = new Account("Id-123", new BigDecimal(1000));
	Account accountTo = new Account("Id-124", new BigDecimal(100));
	BigDecimal accountToBalance = accountTo.getBalance();
	
	// The variable below is use to add the balance to AccountTo has plus the Amount being transfer
	// This is use in the assertTrue
	BigDecimal newAccountToBalance = accountToBalance.add(new BigDecimal(500));
		
	Account accountTransferred = this.moneyTransferService.transferAmount(accountFrom, accountTo, new BigDecimal(500));
	assertTrue("Money was transferred ",(accountTransferred.getBalance().compareTo(newAccountToBalance) == 0));
  }

  @Test
  public void transferFromAccountWithZeroBalance() throws Exception {
	Account accountFrom = new Account("Id-123", BigDecimal.ZERO);
	Account accountTo = new Account("Id-124", new BigDecimal(100));
	  
	BigDecimal accountToBalance = accountTo.getBalance();
	Account accountTransferred = this.moneyTransferService.transferAmount(accountFrom, accountTo, BigDecimal.ZERO);
	
	assertTrue("Money was transferred ",(accountTransferred.getBalance().compareTo(accountToBalance) < 0));
  }

  @Test
  public void transferFromAccountWithLessBalance() throws Exception {
	Account accountFrom = new Account("Id-123", new BigDecimal(100));
	Account accountTo = new Account("Id-124", new BigDecimal(100));
	  
	BigDecimal accountToBalance = accountTo.getBalance();

	Account accountTransferred = this.moneyTransferService.transferAmount(accountFrom, accountTo, new BigDecimal(100));
	
	assertTrue("Money was transferred ",(accountTransferred.getBalance().compareTo(accountToBalance) > 0));
  }
}
