package com.gcd.service.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import com.gcd.service.constants.GCDConstant;
import com.gcd.service.exception.GCDException;

/**
 * This class contains all the helper utility methods for GCD DAO implementation
 * @Operations : throwSQLException, close, calcualteGCD
 * @Developer: Singh, Sunny
 */

public  class DAOUtils {

	private static final Logger LOG = Logger.getLogger(DAOUtils.class.getName());
	
	public static int calcualteGCD(int firstOperand, int secondOperand) {
		   if (secondOperand==0) {
			   
			   return firstOperand;
		   }
		   return calcualteGCD(secondOperand,firstOperand%secondOperand);
	}
	
	public static void  close(Connection conn) throws GCDException {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				throw new GCDException(GCDConstant.SQL_EXCEPTION_CODE,GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.ERROR_IN_CONN_CLOSE);
			}
		}
	}
	public static void close(ResultSet rs) throws GCDException {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				throw new GCDException(GCDConstant.SQL_EXCEPTION_CODE,GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.ERROR_IN_RESULTSET_CLOSE);
			}
		}
	}
	public static void close(PreparedStatement pstmt) throws GCDException {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				throw new GCDException(GCDConstant.SQL_EXCEPTION_CODE,GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.ERROR_IN_PSTMT_CLOSE);
			}
		}
	}
	
	public static void throwSQLException(SQLException ex) throws GCDException {
		throw new GCDException(GCDConstant.SQL_EXCEPTION_CODE,GCDConstant.system_DATABASE, GCDConstant.error_TYPE_TECHNICAL, GCDConstant.SQL_EXCEPTION_DESC);
	}
	
	
}
