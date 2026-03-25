package com.esnbd.esnbdonline.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "account_details")
public class AccountHolderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountholder_id")
	private Integer holderId;
	
	private String name;
	private String dob;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private Address address;
	
	private String mailId;
	
	@OneToMany(mappedBy = "holderDetails" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)		
	private List<Account> account;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="creditcard_id")
	private CreditCard creditCard;
	
	public AccountHolderDetails(String name, String dob, Address address, String mailId, List<Account> account,
			User user) {
		super();
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.mailId = mailId;
		this.account = account;
		this.user = user;
	}	
}
