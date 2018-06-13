package com.db.awmd.challenge;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.service.MoneyTransferService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MoneyTransferControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private MoneyTransferService moneyTransferService;
  
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void prepareMockMvc() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void moneyTransfer() throws Exception {
	Account accountFrom = new Account("Id-123", new BigDecimal(1000));
	Account accountTo = new Account("Id-124", new BigDecimal(100));
	BigDecimal transferAmount = new BigDecimal(1000);
	BigDecimal accountToBalance = accountTo.getBalance();
    BigDecimal newAccountToBalance = accountToBalance.add(transferAmount);
	this.mockMvc.perform(get("/v1/accounts/transfer").contentType(MediaType.APPLICATION_JSON)
      .content("{\"accountFromId\":\"Id-123\",\"accountToId\":\":Id-124}")).andExpect(status().isOk());
    
    Account accountTransferred = moneyTransferService.transferAmount(accountFrom, accountTo, transferAmount);
	assertTrue("Money was transferred ",(accountTransferred.getBalance().compareTo(newAccountToBalance) == 0));
  }
 
}
