package com.gcd.service.dao;


import java.sql.SQLException;

import com.gcd.service.exception.GCDException;

import service.gcd.soap.gcdsoapservices.GcdSumResponse;
import service.gcd.soap.gcdsoapservices.GcdSumRequest;
import service.gcd.soap.gcdsoapservices.GcdRequest;
import service.gcd.soap.gcdsoapservices.GcdResponse;
import service.gcd.soap.gcdsoapservices.GcdListResponse;
import service.gcd.soap.gcdsoapservices.GcdListRequest;

import com.gcd.service.model.GCD;


public interface IGCDServiceDAO {
	
	 String insertGCD(GCD gcd, int parameters)  throws GCDException ;
	 GcdListResponse gcdList(GcdListRequest parameters)  throws GCDException ;
	 GcdSumResponse gcdSum(GcdSumRequest parameters)  throws GCDException  ;
}
