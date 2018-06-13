package com.db.awmd.challenge.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.interfaces.IMoneyTransferService;
import com.db.awmd.challenge.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/accounts/transfer")
@Slf4j
public class MoneyTransferController {

  @Autowired
  private final AccountsService accountsService;
  
  @Autowired
  private final IMoneyTransferService moneyTransferService;

  @Autowired
  public MoneyTransferController(AccountsService accountsService, IMoneyTransferService moneyTransferService) {
    this.accountsService = accountsService;
    this.moneyTransferService = moneyTransferService;
  }

//  @RequestMapping(method = RequestMethod.GET, value = "/transfer")
  @GetMapping(path = "/{accountFromId}/{accountToId}/{transferAmount}")  
  public Account moneyTransfer(@PathVariable("accountFromId") String accountFromId, @PathVariable("accountToId") String accountToId, @PathVariable("transferAmount") BigDecimal transferAmount) {

	Account accountFrom  = this.accountsService.getAccount(accountFromId);
	Account accountTo  = this.accountsService.getAccount(accountToId);

	log.info("Transferring money from account for id {} ", accountFrom.getAccountId(), "to account ", accountTo.getAccountId());
    return this.moneyTransferService.transferAmount(accountFrom, accountTo, transferAmount);
  }
}
