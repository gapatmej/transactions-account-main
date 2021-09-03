package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final TransactionService transactionService;

	public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
		this.accountRepository = accountRepository;
		this.transactionService = transactionService;
	}

	public AccountDto getAccount(Long id) {
		return accountRepository.findById(id)
			.map(account -> new AccountDto(account.getNumber(), account.getType(), account.getCreationDate()))
			.orElse(null);
	}

	/**
	 * Returns a list of all transactions for a account with passed id.
	 *
	 * @param accountId id of the account
	 * @return list of transactions sorted by creation date descending - most recent first
	 */
	public List<TransactionDto> getTransactionsForAccount(Long accountId) {
		return transactionService.getTransactionsForAccount(accountId);
	}

	/**
	 * Creates a new transaction
	 *
	 * @param newTransactionDto data of new transaction
	 * @return id of the created transaction
	 * @throws IllegalArgumentException if there is no account for passed newTransactionDto.accountId
	 */
	public Long addTransaction(NewTransactionDto newTransactionDto) throws IllegalArgumentException {
		return transactionService.addTransaction(newTransactionDto);
	}

}
