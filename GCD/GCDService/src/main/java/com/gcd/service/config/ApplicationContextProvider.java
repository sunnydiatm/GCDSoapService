package com.gcd.service.config;

import org.springframework.beans.BeansException;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware{

	private static ApplicationContext applicationContext = null;
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public void setApplicationContext(ApplicationContext ctx)throws BeansException {
		this.applicationContext = ctx;	
	}

}
