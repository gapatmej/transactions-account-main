package com.pichincha.backend.test.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String number;

	@Column(length = 70)
	private String type;

	private LocalDateTime creationDate;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private Set<Transaction> transactions = new HashSet<>();

}
