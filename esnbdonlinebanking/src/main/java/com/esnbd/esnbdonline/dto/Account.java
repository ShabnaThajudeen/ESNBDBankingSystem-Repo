package com.esnbd.esnbdonline.dto;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	@SequenceGenerator(name = "my_sequence",sequenceName="my_sequence", initialValue = 1000000008, allocationSize = 20)
	@Column(name = "account_no")
	private Long accountNo;		
	
	@Column(name = "type")
	private String accountType;		
	
	@ManyToOne
	@JoinColumn(name="holder_id")
	private AccountHolderDetails holderDetails;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<AccountTransactions> transactions;	
	
	public Account(String accountType, AccountHolderDetails holderDetails, Set<AccountTransactions> transactions) {
		super();
		this.accountType = accountType;
		this.holderDetails = holderDetails;
		this.transactions = transactions;
	}	

	//------abstract methods
	public abstract void deposit(float amount);
	public abstract void withdraw(float amount);		
}
