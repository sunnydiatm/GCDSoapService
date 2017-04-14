package com.gcd.service.constants;

public final class GCDConstant {
	

	public static String error_TYPE_TECHNICAL = "TECHNICAL";
	public static String error_TYPE_BUSINESS = "BUSINESS";
	public static String system_SERVER = "SERVER";
	public static String system_JMS_QUEUE = "ACTIVEMQ";
	public static String system_DATABASE = "DATABASE";
	
	public static String SQL_EXCEPTION_CODE = "1001";
	public static String SQL_EXCEPTION_DESC = "SQL EXCEPTION";
	public static String ERROR_IN_RESULTSET_CLOSE = "Error in Resultset close...";
	public static String ERROR_IN_PSTMT_CLOSE = "Error in Prepared Statement close...";
	public static String ERROR_IN_CONN_CLOSE = "Error in Connection close...";
	
	
	public static String JMS_EXCEPTION_CODE = "2001";
	public static String JMS_EXCEPTION_DESC = "JMS EXCEPTION";
	public static String error_JMS_CONNECTION_ERROR_DESC = "Null or invalid database connection";
	
	public static String error_CODE_DATAACCESS_ERROR_GENERIC = "DB-001";
	public static String error_CODE_DATAACCESS_ERROR_DESC = "Exception while calling database operation - ";
	public static String error_CODE_DATAACCESS_CONNECTION_ERROR_DESC = "Null or invalid database connection";
	public static String error_CODE_DATAACCESS_BUSINESS_ERROR_DESC = "Business exception while calling database operation - ";
	
	public static String error_CODE_WAS_ERROR_GENERIC = "WAS-001";
	public static String error_CODE_WAS_ERROR_GET_AML_INFO = "WAS-002";
	public static String error_CODE_WAS_ERROR_GET_IB_SCORES = "WAS-003" ;
	public static String error_CODE_WAS_ERROR_SEARCH_PROD_BY_CLV = "WAS-004";
	
	public static String error_CODE_SERVER_ERROR_DESC = "Technical exception while processing request - ";

	public static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	public static final String GCD_QUEUE = "GCD_QUEUE";
}
