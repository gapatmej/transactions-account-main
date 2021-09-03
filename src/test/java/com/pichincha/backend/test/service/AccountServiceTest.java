package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.AccountRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;

	@Test
	public void shouldReturnCreatedAccount() {
		Account account = new Account();
		account.setNumber("Test number");
		account.setType("Test type");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		account.setCreationDate(creationDate);
		accountRepository.save(account);

		AccountDto accountDto = accountService.getAccount(account.getId());

		assertNotNull("Account shouldn't be null", accountDto);
		assertThat(accountDto.getType(), equalTo("Test type"));
		assertThat(accountDto.getNumber(), equalTo("Test number"));
		assertThat(accountDto.getCreationDate(), equalTo(creationDate));
	}

	@Test
	public void shouldReturnNullForNotExistingAccount() {
		AccountDto accountDto = accountService.getAccount(123L);
		assertNull(accountDto);
	}

}
