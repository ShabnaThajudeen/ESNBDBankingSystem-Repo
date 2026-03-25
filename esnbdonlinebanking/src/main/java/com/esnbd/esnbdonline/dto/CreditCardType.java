package com.esnbd.esnbdonline.dto;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="creditcard_type")
public enum CreditCardType {
	VISA {
        @Override
        public String generateNumber()
        {
        	String no = "4";
        	
        	Random rnd1 = new Random();
        	int counter1 =0;
        	while(counter1 <= 5){
        	int generate1  = rnd1.nextInt(6); 
        	no = no + Integer.toString(generate1);
        	counter1++;
        	}
        	
        	Random rnd2 = new Random();
        	int counter2 =0;
        	while(counter2 <= 8){
        	int generate2 = rnd2.nextInt(9); 
        	no = no + Integer.toString(generate2);
        	counter2++;
        	}
           return no;
        }
    },
	MASTER_CARD {
		@Override
		public String generateNumber() {
            String no = "5";
        	
        	Random rnd1 = new Random();
        	int counter1 =0;
        	while(counter1 <= 5){
        	int generate1  = rnd1.nextInt(6); 
        	no = no + Integer.toString(generate1);
        	counter1++;
        	}
        	
        	Random rnd2 = new Random();
        	int counter2 =0;
        	while(counter2 <= 8){
        	int generate2 = rnd2.nextInt(9); 
        	no = no + Integer.toString(generate2);
        	counter2++;
        	}
           return no;
		}
	},
	AMERICAN_EXPRESS {
		@Override
		public String generateNumber() {
            String no = "37";
        	
        	Random rnd1 = new Random();
        	int counter1 =0;
        	while(counter1 <= 5){
        	int generate1  = rnd1.nextInt(6); 
        	no = no + Integer.toString(generate1);
        	counter1++;
        	}
        	
        	Random rnd2 = new Random();
        	int counter2 =0;
        	while(counter2 <= 6){
        	int generate2 = rnd2.nextInt(9); 
        	no = no + Integer.toString(generate2);
        	counter2++;
        	}
           return no;
		}
	},
	DISCOVER {
		@Override
		public String generateNumber() {
            String no = "6";
        	
            Random rnd1 = new Random();
        	int counter1 =0;
        	while(counter1 <= 5){
        	int generate1  = rnd1.nextInt(6); 
        	no = no + Integer.toString(generate1);
        	counter1++;
        	}
        	
        	Random rnd2 = new Random();
        	int counter2 =0;
        	while(counter2 <= 8){
        	int generate2 = rnd2.nextInt(9); 
        	no = no + Integer.toString(generate2);
        	counter2++;
        	}
           return no;
		}
	};	
	public abstract String generateNumber();
}
