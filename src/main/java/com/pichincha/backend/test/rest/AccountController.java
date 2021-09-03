package com.pichincha.backend.test.rest;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.rest.util.GsonUtils;
import com.pichincha.backend.test.rest.util.HeaderUtil;
import com.pichincha.backend.test.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractResource {

	private final AccountService aService;

	public AccountController(AccountService accountService) {
		super(AccountController.class);
		this.aService = accountService;
	}

	/**
	 * {@code GET /account} : get account by id.
	 *
	 * @param id the account id.
	 * @return the {@link AccountDto} with status {@code 200 (OK)} and with body account.
	 */
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto getAccount(@PathVariable Long id) {
		log.info("REST request to get AccountDto with the account id: {}", id);
		return aService.getAccount(id);
	}

	/**
	 * {@code GET / transactions} : get transactions by account id.
	 *
	 * @param accountId the account id.
	 * @return the {@link TransactionDto} with status {@code 200 (OK)} and with body transactions.
	 */
	@GetMapping(value = "/{accountId}/transactions")
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionDto> getTransactionsForAccount(@PathVariable Long accountId) {
		log.info("REST request to get a List of TransactionDto with the account id: {}", accountId);
		return aService.getTransactionsForAccount(accountId);
	}

	/**
	 * {@code POST  /new transaction}  : Creates a new transaction by account id.
	 * <p>
	 * Creates a new transaction by the account id
	 * The account id needs to be create.
	 *
	 * @param newTransactionDto the user to create.
	 * @param accountId the account id of the transaction.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the transaction id, or with status {@code 500 (Internal Server)}
	 * if the account not exist.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping(value = "/{accountId}/transactions")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity addTransaction(@PathVariable Long accountId, @RequestBody NewTransactionDto newTransactionDto) throws URISyntaxException {
		log.info("REST request to add a Transaction with the data: {}", GsonUtils.entityToJson(newTransactionDto));
		newTransactionDto.setAccountId(accountId);
		Long transactionId = aService.addTransaction(newTransactionDto);

		return ResponseEntity.created(new URI(String.format("/%s/transactions/",accountId)))
				.headers(HeaderUtil.createEntityCreationAlert("transaction", transactionId))
				.body(transactionId);
	}
}
