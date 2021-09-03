package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.AccountRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionService transactionService;

	@Test
	public void shouldAddTransaction() {
		Account account = createTestAccount();

		NewTransactionDto transaction = new NewTransactionDto();
		transaction.setAccountId(account.getId());
		transaction.setType("Type");
		transaction.setComment("Comment");
		Long transactionId = transactionService.addTransaction(transaction);

		assertThat("Transaction id shouldn't be null", transactionId, notNullValue());
	}

	private Account createTestAccount() {
		Account account = new Account();
		account.setNumber("Test Number");
		account.setType("Test type");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		account.setCreationDate(creationDate);
		accountRepository.save(account);
		return account;
	}

	@Test
	public void shouldReturnAddedTransaction() {
		Account account = createTestAccount();

		NewTransactionDto transaction = new NewTransactionDto();
		transaction.setAccountId(account.getId());
		transaction.setType("Type");
		transaction.setComment("Comment");

		transactionService.addTransaction(transaction);

		List<TransactionDto> transactions = transactionService.getTransactionsForAccount(account.getId());

		assertThat("There should be one transaction", transactions, hasSize(1));
		assertThat(transactions.get(0).getType(), Matchers.equalTo("Type"));
		assertThat(transactions.get(0).getComment(), Matchers.equalTo("Comment"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnIllegalArgumentException() {
		NewTransactionDto transaction = new NewTransactionDto();
		transaction.setAccountId(1L);
		transaction.setType("Type");
		transaction.setComment("Comment");
		transactionService.addTransaction(transaction);
	}

}
