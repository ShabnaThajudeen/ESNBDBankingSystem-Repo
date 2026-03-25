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
@DiscriminatorValue("Current")
public class CurrentAccount extends Account{	
	@Column(name = "balance")
	private Float accountBalance;
	
	@Column(name = "min_bal")
	private final Float MIN_BALANCE = AppConstants.CURRENT_ACCOUNT_MIN_BALANCE;	
	
	public CurrentAccount() {
		super();
		this.accountBalance = MIN_BALANCE;
	}

	public CurrentAccount(Long accountNo, String accountType, AccountHolderDetails holderDetails,
			Set<AccountTransactions> transactions) {
		super(accountNo, accountType, holderDetails, transactions);		
	}

	public CurrentAccount(String accountType, AccountHolderDetails holderDetails,
			Set<AccountTransactions> transactions) {
		super(accountType, holderDetails, transactions);		
	}	
	
	public void deposit(float amount) {
		this.accountBalance += amount;
	}
	
	public void withdraw(float amount) {		
		this.accountBalance -= amount;
	}		
}
