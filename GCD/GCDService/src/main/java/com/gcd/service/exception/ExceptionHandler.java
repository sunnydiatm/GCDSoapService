package com.gcd.service.exception;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;


import com.gcd.service.exception.GCDException;
import com.gcd.service.constants.GCDConstant;
import service.gcd.soap.gcdsoapservices.ProcessingException;
import service.gcd.soap.services.ProcessingFault;
import service.gcd.soap.services.GCDSoapServicesPort;
import service.gcd.soap.gcdsoapservices.Message;
import org.springframework.dao.DataAccessException;

public final class ExceptionHandler {
	 private static final Logger LOG = Logger.getLogger(ExceptionHandler.class.getName());
    
    private ExceptionHandler()
    {
        
    }
    
    public static GCDException generateGCDException(Exception ex, String code) {
    	GCDException exception = null;
        String errorCode = "";
        if(ex instanceof DataAccessException) {
            if(code != null && !code.equals("")) {
                errorCode = code;
            } else {
                errorCode = GCDConstant.error_CODE_DATAACCESS_ERROR_GENERIC;
            }
            exception =  new GCDException(code, GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.error_CODE_DATAACCESS_ERROR_DESC + ex.getMessage());
        } 
        return exception;
    }
    
    public static GCDException generateDBConnectionGCDException() {
    	GCDException exception = new GCDException(GCDConstant.error_CODE_DATAACCESS_ERROR_GENERIC,
												GCDConstant.system_DATABASE,
												GCDConstant.error_TYPE_TECHNICAL,
												GCDConstant.error_CODE_DATAACCESS_ERROR_DESC + GCDConstant.error_CODE_DATAACCESS_CONNECTION_ERROR_DESC);
        					return exception;
    }    
    

    public static ProcessingFault generateFaultMessage(GCDException ex) {
    	LOG.info("ExceptionHandler->generateFaultMessage GCDException type");
    	ProcessingException proException = new ProcessingException();
    	Message message = new Message();
    	String exceptionSystem = null;
    	String errorType = null;
    	
        message.setDescription(ex.getDescription());
        message.setType(ex.getCode());
        message.setComponentType(ex.getSystem());
        
        proException.getApplicationMessage().add(message);
        
        ProcessingFault exception = new ProcessingFault(ex.getDescription(), proException);
        
        return exception;
    }
    
    public static ProcessingFault generateFaultMessage(Exception ex) {
    	LOG.info("ExceptionHandler->generateFaultMessage() Exception type");
    	ProcessingException proException = new ProcessingException();
        Message message = new Message();
       
        message.setDescription(GCDConstant.error_CODE_SERVER_ERROR_DESC);
        message.setType(GCDConstant.error_TYPE_TECHNICAL);
        message.setComponentType(GCDConstant.system_SERVER);
    	
    	proException.getApplicationMessage().add(message);
    	
        ProcessingFault exception = new ProcessingFault(GCDConstant.error_CODE_SERVER_ERROR_DESC + ex.getMessage(), proException);
        
        return exception;
    }
    
    
    
}
