package com.gcd.service.controller;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.jms.JMSException;
import java.sql.SQLException;
import service.gcd.soap.gcdsoapservices.GcdSumResponse;
import service.gcd.soap.gcdsoapservices.GcdSumRequest;
import service.gcd.soap.gcdsoapservices.GcdRequest;
import service.gcd.soap.gcdsoapservices.GcdResponse;
import service.gcd.soap.gcdsoapservices.GcdListResponse;
import service.gcd.soap.gcdsoapservices.GcdListRequest;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;
import com.gcd.service.exception.GCDException;
import com.gcd.service.mq.MessageReceiver;
import com.gcd.service.model.GCD;
import com.gcd.service.dao.IGCDServiceDAO;
import com.gcd.service.util.DAOUtils;
import com.gcd.service.constants.GCDConstant;
/**
 * This class contains all the logic for handling Database calls and Queue. It does extra processing once result is opened from the calls.
 * @Operations : gcd, gcdList, gcdSum
 * @Developer: Singh, Sunny
 */

public class GCDServiceController {
	 private static final Logger LOG = Logger.getLogger(GCDServiceController.class.getName());
	 
	 private MessageReceiver messageReceiver;
	 
	 public MessageReceiver getMessageReceiver(){
		 return this.messageReceiver;
	 }
	 public void setMessageReceiver(MessageReceiver messageReceiver ){
		 this.messageReceiver=messageReceiver;
	 }
	 
	 private IGCDServiceDAO dao;
		
	    public void setDao(IGCDServiceDAO dao) {
	    	this.dao=dao;
	    }	

	    public IGCDServiceDAO getDao() {
	    	return this.dao;
	    }
	 
	 /**
		 * The following operation gcd fetch first value from queue and then calculate
		 *  the GCD of the two numbers and stores the value to the DB
		 * @Input - GcdRequest
		 * @Response - GcdResponse
		 * @Exception - this method returns GCDException exception 
		 */
	 public GcdResponse gcd(GcdRequest parameters)  throws GCDException {
		 	if(LOG.isLoggable(Level.INFO)){
		 		LOG.info(" Inside gcd  : Controller");
		 	}
	    	long inTime = System.currentTimeMillis();
	    	
	    	GcdResponse response = null;
	    	String result = null;
	    	GCD gcd = null;
	    	 	
	    	if(messageReceiver!=null){
				try{
					if(LOG.isLoggable(Level.FINER)){
			    		LOG.finer(" gcd : Controller : Calling message queue now ");
			    	}
					
					gcd = messageReceiver.receiveMessage();
					
					if(LOG.isLoggable(Level.FINER)){
				 		LOG.finer(" gcd  : Controller : Calling message queue was successful ");
				 	}
					
					int gcdResult = DAOUtils.calcualteGCD(gcd.getFirstOperand(), gcd.getSecondOperand());
					
					if(dao!=null){
						if(LOG.isLoggable(Level.FINER)){
				    		LOG.finer(" gcd : Controller : Calling DB now ");
				    	}
						result = dao.insertGCD(gcd, gcdResult);
						
						if(LOG.isLoggable(Level.FINER)){
					 		LOG.finer(" gcd  : Controller : Calling DB was successful ");
					 	}
						response.setResponse(gcdResult);
					}else {
						LOG.severe(" gcd : Controller -> Exception occured -> DB object is null ");
						throw new GCDException(GCDConstant.error_CODE_DATAACCESS_ERROR_GENERIC,GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.error_CODE_DATAACCESS_CONNECTION_ERROR_DESC);
					}
				}catch(JMSException ex){
					LOG.severe(" gcd : Controller -> JMSException ocurred -> Reason : "+ex.getMessage());
					throw new GCDException(GCDConstant.JMS_EXCEPTION_CODE,GCDConstant.system_JMS_QUEUE, GCDConstant.error_TYPE_TECHNICAL, ex.getMessage());
				}
				
			}else {
				LOG.severe(" gcd : Controller -> Exception occured -> Message sender object is null ");
				throw new GCDException(GCDConstant.JMS_EXCEPTION_CODE,GCDConstant.system_JMS_QUEUE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.error_JMS_CONNECTION_ERROR_DESC);
			}
	    	
	    	long outTime = System.currentTimeMillis();
	    	if(LOG.isLoggable(Level.FINER)){
	    		LOG.finer("Exiting gcd method :: Controller : Total time taken= "+(outTime - inTime)+" Mili seconds");
	    	}
	   			return response;
	    }
	 /**
		 * The following operation gcdList list the GCD value from the database.
		 * @Input - GcdListRequest
		 * @Response - GcdListResponse
		 * @Exception -  this method returns GCDException exception 
		 */
	 public GcdListResponse gcdList(GcdListRequest parameters)  throws GCDException {  
		 	if(LOG.isLoggable(Level.INFO)){
		 		LOG.info("Inside gcdList : Controller");
		 	}
	    	long inTime = System.currentTimeMillis();
	    	GcdListResponse response = null;
	    	 
	    	response = dao.gcdList(parameters);
	    	
	    	long outTime = System.currentTimeMillis();
	    	if(LOG.isLoggable(Level.FINER)){
	    		LOG.finer("Exiting gcdList method :: Controller : Total time taken= "+(outTime - inTime)+" Mili seconds");
	    	}
	   			return response;
	    }
	 /**
		 * The following operation gcdSum calculate the sum of total GCD results 
		 * @Input - GcdSumRequest
		 * @Response - GcdSumResponse
		 * @Exception - this method returns GCDException exception
		 */
	 public GcdSumResponse gcdSum(GcdSumRequest parameters)  throws GCDException { 
		 	if(LOG.isLoggable(Level.INFO)){
		 		LOG.info("Inside gcdSum : Controller");
		 	}
	    	long inTime = System.currentTimeMillis();
	    	GcdSumResponse response = null;
	    	
	    	 response = dao.gcdSum(parameters);
	    	
	    	long outTime = System.currentTimeMillis();
	    	if(LOG.isLoggable(Level.FINER)){
	    		LOG.finer("Exiting gcdSum :: Controller : Total time taken= "+(outTime - inTime)+" Mili seconds");
	    	}
	   			return response;
	    }
}
