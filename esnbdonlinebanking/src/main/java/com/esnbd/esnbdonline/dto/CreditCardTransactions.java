package com.esnbd.esnbdonline.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Creditcard_transaction")
public class CreditCardTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_trans_id_sequence")
	@SequenceGenerator(name="cc_trans_id_sequence", sequenceName = "cc_trans_id_sequence", initialValue = 1700000000, allocationSize = 20)
	@Column(name="transaction_id")
	private Integer transId;
	
	private String date;	
	private String description;	
	
	private Float debit;
	
	@Column(name="balance_limit")
	private Float balanceLimit;	
	
	@ManyToOne
	@JoinColumn(name="card_no")
	private CreditCard card;
}
