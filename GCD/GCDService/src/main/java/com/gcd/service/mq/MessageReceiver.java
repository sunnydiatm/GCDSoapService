package com.gcd.service.mq;

import java.io.Serializable;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import com.gcd.service.model.GCD;
import com.gcd.service.constants.GCDConstant;

/**
 * This class contains all the logic for receiving message from the head of the queue.
 * @Operations : receiveMessage
 * @Developer: Singh, Sunny 
 */


public class MessageReceiver {
	private static final Logger LOG = Logger.getLogger(MessageReceiver.class.getName());
	
	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		 this.jmsTemplate = jmsTemplate;
	 }
	
	/**
	 * The following method receive Message from the head of the queue
	 * @Input -  N/A
	 * @Response -  gcd
	 * @Exception - this method returns JMSException exception 
	 */
	public GCD receiveMessage() throws JMSException {
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside receiveMessage : MessageReceiver ");
		}
		
		GCD gcd = (GCD) jmsTemplate.receiveAndConvert();
		
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Result Returned from Queue!!! : "+gcd.getFirstOperand()+ " & " + gcd.getSecondOperand());
		}
		 	    return gcd; 
	}
	
}