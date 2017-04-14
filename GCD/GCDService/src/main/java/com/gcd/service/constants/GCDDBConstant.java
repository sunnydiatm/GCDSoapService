package com.gcd.service.constants;


public interface GCDDBConstant {
	
			
			String QUERY_INSERT_GCD_DETAILS =  " INSERT INTO sakila.GCD_SOAP_TABLE ( FIRST_OPERAND, SECOND_OPERAND, GCD_RESULT) VALUES (?, ?, ?) ";

			String QUERY_FETCH_GCD_RESULT = " SELECT GCD_RESULT FROM sakila.GCD_SOAP_TABLE ";

			//String QUERY_FETCH_GCD_SUM_RESULT = " SELECT GCD_RESULT FROM sakila.GCD_SOAP_TABLE " ;


}

