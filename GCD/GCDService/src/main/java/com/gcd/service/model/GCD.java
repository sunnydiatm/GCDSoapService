package com.gcd.service.model;
import java.io.Serializable;

public class GCD implements Serializable {
	private static final long serialVersionUID = 3370367844977691546L;
	private int firstOperand;
	private int secondOperand;
		
	public int getFirstOperand(){
		return firstOperand;
	}
		
	public void setFirstOperand(int firstOperand){
		this.firstOperand = firstOperand;
	}
	
	public int getSecondOperand(){
		return secondOperand;
	}
	public void setSecondOperand(int secondOperand){
		this.secondOperand = secondOperand;
	}
		
	public GCD() {
		
	}

}
