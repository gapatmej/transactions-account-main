package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.AccountRepository;
import com.pichincha.backend.test.repository.TransactionRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final MessageSource messageSource;

	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository, MessageSource messageSource) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.messageSource = messageSource;
	}


	/**
	 * Returns a list of all transactions for a account with passed id.
	 *
	 * @param accountId id of the account
	 * @return list of transactions sorted by creation date descending - most recent first
	 */
	public List<TransactionDto> getTransactionsForAccount(Long accountId) {
		return transactionRepository.findAllByAccountIdOrderByCreationDateDesc(accountId).stream()
				.map(transaction -> new TransactionDto(transaction)).collect(Collectors.toList());
	}

	/**
	 * Creates a new transaction
	 *
	 * @param newTransactionDto data of new transaction
	 * @return id of the created transaction
	 * @throws IllegalArgumentException if there is no account for passed newTransactionDto.accountId
	 */
	public Long addTransaction(NewTransactionDto newTransactionDto) throws IllegalArgumentException {
		Optional<Account> account = accountRepository.findById(newTransactionDto.getAccountId());
		if( !account.isPresent() ){
			throw new IllegalArgumentException(messageSource.getMessage("account.not.found",null, Locale.ENGLISH));
		}
		Transaction transaction = new Transaction(newTransactionDto);
		transaction.setAccount(account.get());
		transaction = transactionRepository.save(transaction);
		return transaction.getId();
	}

}
