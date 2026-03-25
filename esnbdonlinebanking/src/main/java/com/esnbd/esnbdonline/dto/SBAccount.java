package com.esnbd.esnbdonline.dto;

import java.util.Set;

import com.esnbd.esnbdonline.appconstants.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity
@DiscriminatorValue("SB")
public class SBAccount extends Account{	
	@Column(name = "balance")
	private Float accountBalance;
	
	@Column(name = "min_bal")
	private final Float MIN_BALANCE = AppConstants.SAVINGS_ACCOUNT_MIN_BALANCE;		
	
	public SBAccount() {
		super();
		this.accountBalance = AppConstants.SAVINGS_ACCOUNT_MIN_BALANCE;		
	}
	
	public SBAccount(Long accountNo, String accountType, AccountHolderDetails holderDetails,
			Set<AccountTransactions> transactions) {
		super(accountNo, accountType, holderDetails, transactions);
		// TODO Auto-generated constructor stub
	}



	public SBAccount(String accountType, AccountHolderDetails holderDetails, Set<AccountTransactions> transactions) {
		super(accountType, holderDetails, transactions);
		// TODO Auto-generated constructor stub
	}
	
	public void deposit(float amount) {
		this.accountBalance += amount;
	}
	
	public void withdraw(float amount) {
		this.accountBalance -= amount;
	}		
}
