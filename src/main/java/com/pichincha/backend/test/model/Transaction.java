package com.pichincha.backend.test.model;

import com.pichincha.backend.test.dto.NewTransactionDto;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	private String comment;

	private String type;

	private LocalDateTime creationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id", nullable = false)
	private Account account;

	public Transaction() {
	}

	public Transaction(NewTransactionDto newTransactionDto) {
		this.comment = newTransactionDto.getComment();
		this.type = newTransactionDto.getType();
		this.creationDate = LocalDateTime.now();
	}
}
