package com.pichincha.backend.test.dto;

import com.pichincha.backend.test.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TransactionDto {

	private final Long id;

	private final String comment;

	private final String type;

	private final LocalDateTime creationDate;

	public TransactionDto(Transaction transaction) {
		this.id = transaction.getId();
		this.comment = transaction.getComment();
		this.type = transaction.getType();
		this.creationDate = transaction.getCreationDate();
	}
}
