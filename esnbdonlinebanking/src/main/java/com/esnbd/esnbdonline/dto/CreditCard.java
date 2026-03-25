package com.esnbd.esnbdonline.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import com.esnbd.esnbdonline.appconstants.AppConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

@Entity
@Table(name="credit_card")
public class CreditCard {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="creditcard_id")
	private Integer id;
	
	@Column(name="creditcard_no")
	private String creditCardNo;	
	
	@Column(name="name")
	private String name;
	
	@Column(name="valid_from")
	private String validFrom;
	
	@Column(name="valid_till")
	private String validThru;
	
	@Column(name="cvv")	
	private String cvv;
	
	@Column(name="monthly_limit")
	private static final Float MONTHLY_LIMIT = AppConstants.CREDITCARD_MONTHLY_LIMIT;
	
	@Column(name="balance_limit")
	private Float balanceLimit;	
	
	@Column(name="creditcard_type")
	private CreditCardType creditCardType;
	
	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
	private Set<CreditCardTransactions> transactions;
	
	public CreditCard() {
		super();		
		this.balanceLimit = MONTHLY_LIMIT;
	}
	
	@SuppressWarnings("deprecation")
	public boolean refreshCreditCardLimit() {
		Date date = Calendar.getInstance().getTime();		
		int presentDate = date.getDate();		
		if(presentDate == 1) {
			this.balanceLimit = MONTHLY_LIMIT;	
			return true;
		}	
		else {
			return false;
		}
	}	
	
	public void processTransaction(Float amount) {
		this.balanceLimit -= amount;			
	}
}
