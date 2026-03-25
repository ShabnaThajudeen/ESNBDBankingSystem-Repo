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
@Table(name="transaction")
public class AccountTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_id_sequence")
	@SequenceGenerator(name="trans_id_sequence", sequenceName = "trans_id_sequence", initialValue = 1500000000, allocationSize = 20)
	@Column(name="transaction_id")
	private Integer transId;
	
	private String date;	
	private String description;
	
	@Column(name="cheque_no")
	private Integer chequeNo;
	
	private Float withdraw;	
	private Float deposit;
	
	@Column(name="balance")
	private Float accBalance;	
	
	@ManyToOne
	@JoinColumn(name="acc_no")
	private Account account;	

	public AccountTransactions(String date, String description, Integer chequeNo, Float withdraw, Float deposit,
			Float accBalance, Account account) {
		super();
		this.date = date;
		this.description = description;
		this.chequeNo = chequeNo;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.accBalance = accBalance;
		this.account = account;
	}	
}
