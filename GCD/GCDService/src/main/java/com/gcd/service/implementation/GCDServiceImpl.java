package com.gcd.service.implementation;

import java.io.Serializable;

import java.util.logging.Logger;
import java.util.logging.Level;

import service.gcd.soap.gcdsoapservices.GcdSumResponse;
import service.gcd.soap.gcdsoapservices.GcdSumRequest;
import service.gcd.soap.gcdsoapservices.GcdRequest;
import service.gcd.soap.gcdsoapservices.GcdResponse;
import service.gcd.soap.gcdsoapservices.GcdListResponse;
import service.gcd.soap.gcdsoapservices.GcdListRequest;
import com.gcd.service.exception.GCDException;
import com.gcd.service.controller.GCDServiceController;
import com.gcd.service.exception.ExceptionHandler;
import service.gcd.soap.services.ProcessingFault;
import service.gcd.soap.services.GCDSoapServicesPort;
import com.gcd.service.config.ApplicationContextProvider;
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class contains all the logic for handling GCD application calls 
 * @Operations : gcd, gcdList, gcdSum
 * @Developer: Singh, Sunny
 */

@javax.jws.WebService(endpointInterface = "service.gcd.soap.services.GCDSoapServicesPort", 
wsdlLocation = "GCDSoapServices.wsdl", 
serviceName = "GCDSoapServices", 
targetNamespace = "http://com.gcd.service/services", 
portName = "GCDSoapServicesPort")
public class GCDServiceImpl implements GCDSoapServicesPort, Serializable {

	private static final long serialVersionUID = 3370367844977691546L;
	private static final Logger LOG = Logger.getLogger(GCDServiceImpl.class.getName());
	
	private GCDServiceController controller = null;

	public GCDServiceImpl() {
		controller = (GCDServiceController) ApplicationContextProvider.getApplicationContext().getBean("gcdController");
	}

	/**
   	 * The following operation searchByUser
   	 * @Input -  GcdRequest
   	 * @Response -  GcdResponse
   	 * @Exception -  this method returns exception inside service context 
   	 */
	public  GcdResponse gcd(GcdRequest parameters) throws ProcessingFault {
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside gcd : IMPL");
		} 
		GcdResponse response = null;
			
			try{
				
				if(null != parameters) {
				
					response = controller.gcd(parameters);
					if(null!= response ){
						response.setServiceContext(parameters.getServiceContext());
					}
				}
			} catch(GCDException ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	} catch(Exception ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	}    	
			
			if(LOG.isLoggable(Level.INFO)){
				LOG.info("GCD controller processed the request. Going to return response");
			} 
			
	    	return response;
	}
	/**
   	 * The following operation gcdList
   	 * @Input -  GcdListRequest
   	 * @Response -  GcdListResponse
   	 * @Exception -  this method returns exception inside service context 
   	 */
	public  GcdListResponse gcdList( GcdListRequest parameters) throws ProcessingFault {

		if(LOG.isLoggable(Level.INFO)){
			LOG.info("Inside gcdList : IMPL");
		} 
		GcdListResponse response = null;
			
			try{
				
				if(null != parameters ) {
				
					response = controller.gcdList(parameters);
					if(null!= response ){
						response.setServiceContext(parameters.getServiceContext());
					}
				}
			} catch(GCDException ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	} catch(Exception ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	}    	
			
			if(LOG.isLoggable(Level.INFO)){
				LOG.info("GCD controller processed the request. Going to return response");
			} 
		
		
		return response;
	}

	/**
   	 * The following operation gcdSum calculate the Sum of all GCD result inserted.
   	 * @Input -  gcdSumRequest
   	 * @Response -  gcdSumResponse
   	 * @Exception -  this method returns exception inside service context 
   	 */
	public GcdSumResponse gcdSum(GcdSumRequest parameters) 	throws ProcessingFault {
		
		if(LOG.isLoggable(Level.INFO)){
			LOG.info("Inside gcdSum : IMPL");
		} 
		GcdSumResponse response = null;
			
			try{
				
				if(null != parameters ) {
				
					response = controller.gcdSum(parameters);
					if(null!= response ){
						response.setServiceContext(parameters.getServiceContext());
					}
				}
			} catch(GCDException ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	} catch(Exception ex) {
	    		throw ExceptionHandler.generateFaultMessage(ex);
	    	}    	
			
			if(LOG.isLoggable(Level.INFO)){
				LOG.info("GCD controller processed the request. Going to return response");
			} 
			
	    	return response;
		
	}
	
	
}