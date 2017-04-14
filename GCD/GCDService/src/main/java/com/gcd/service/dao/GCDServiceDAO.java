package com.gcd.service.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import service.gcd.soap.gcdsoapservices.GcdSumResponse;
import service.gcd.soap.gcdsoapservices.GcdSumRequest;
import service.gcd.soap.gcdsoapservices.GcdRequest;
import service.gcd.soap.gcdsoapservices.GcdResponse;
import service.gcd.soap.gcdsoapservices.GcdListResponse;
import service.gcd.soap.gcdsoapservices.GcdListRequest;
import service.gcd.soap.gcdsoapservices.GcdListAll;
import com.gcd.service.exception.ExceptionHandler;
import com.gcd.service.exception.GCDException;
import com.gcd.service.util.DAOUtils;
import com.gcd.service.constants.GCDDBConstant;
import com.gcd.service.constants.GCDConstant;
import com.gcd.service.model.GCD;

/**
 * This class contains all the logic for handling Database calls for GCD Applications
 * @Operations : insertGCD, gcdList, gcdSum
 * @Developer: Singh, Sunny 
 */

public class GCDServiceDAO implements IGCDServiceDAO {

	private static final Logger LOG = Logger.getLogger(GCDServiceDAO.class.getName());
	
	private JdbcTemplate jdbc;	
    public void setJdbc(JdbcTemplate jdbc)
    {
    	this.jdbc=jdbc;
    }
    
    private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    
    
    /**
	 * The following insert the GCD details into DB table
	 * @Input -  gcd, gcdResult
	 * @Response -  String
	 * @Exception - this method returns GCDException exception 
	 */
    
    @Override
    public String insertGCD(GCD gcd, int gcdResult)  throws GCDException {
    	if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside insertGCD : DAO");
		}
    	String response = null ;
    	Connection conn = null;
		PreparedStatement ps = null;

		String queryString = GCDDBConstant.QUERY_INSERT_GCD_DETAILS ;
			try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(queryString);
					ps.setInt(1, gcd.getFirstOperand());
					ps.setInt(2, gcd.getSecondOperand());
					ps.setInt(3, gcdResult);
					ps.executeUpdate();
					response ="Success";
				} catch (SQLException ex) {
					LOG.severe(" Inside gcdSum : DAO -> SQLException ocurred -> Reason : "+ex.getMessage());
					DAOUtils.throwSQLException(ex);
				} finally {
					if(LOG.isLoggable(Level.SEVERE)){
						LOG.info(" Inside gcdSum : DAO -> finally  block -> Closing the open conections ");
					}
					DAOUtils.close(ps);
					DAOUtils.close(conn);
				}
			
			if(LOG.isLoggable(Level.INFO)){
				LOG.info(" Exiting insertGCD : DAO");
			}
				return response;
    }
    
    /**
 	 * The following operation listing the GCD result
 	 * @Input -  GcdListRequest
 	 * @Response -  GcdListResponse
 	 * @Exception - this method returns GCDException exception 
 	 */
    
    
    @Override
    public GcdListResponse gcdList(GcdListRequest parameters)  throws GCDException {
    	if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside gcdList : DAO");
		} 
    	GcdListResponse response = null ;
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> gcdResultList = null;
		GcdListAll list = null;
		
		String queryString =  GCDDBConstant.QUERY_FETCH_GCD_RESULT ;
		
			try {
				
				conn = dataSource.getConnection();
				ps = conn.prepareStatement(queryString);
				rs = ps.executeQuery();
				
				while (rs.next()) {

					list.getDescription().add((Integer) rs.getObject("GCD_RESULT"));
					
				}
				response.setGcdListAll(list);
			
			}catch (SQLException ex) {
				LOG.severe(" Inside gcdSum : DAO -> SQLException ocurred -> Reason : "+ex.getMessage());
				DAOUtils.throwSQLException(ex);
			} finally {
				if(LOG.isLoggable(Level.SEVERE)){
					LOG.info(" Inside gcdSum : DAO -> finally  block -> Closing the open conections  ");
				}
				DAOUtils.close(rs);
				DAOUtils.close(ps);
				DAOUtils.close(conn);
			}
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Exiting gcdList : DAO");
		}
    		return response;
    }
    
    /**
 	 * The following operation sum of all the GCD result stored in DB table.
 	 * @Input -  GcdSumRequest
 	 * @Response -  GcdSumResponse
 	 * @Exception - this method returns GCDException exception 
 	 */
    
    @Override
    public GcdSumResponse gcdSum(GcdSumRequest parameters)  throws GCDException  {
    	if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside gcdSum : DAO");
		}
    	
    	GcdSumResponse response = null ;
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString =  GCDDBConstant.QUERY_FETCH_GCD_RESULT ;
		
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Inside gcdSum : DAO -> Query String  : "+queryString);
		}
		
		try {
				int result = 0;
				
				conn = dataSource.getConnection();
				ps = conn.prepareStatement(queryString);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					result = result + ((Integer) rs.getObject("GCD_RESULT"));;
				
				}
				if(LOG.isLoggable(Level.INFO)){
					LOG.info(" Inside gcdSum : DAO -> Total Sum of GCD : "+result);
				}
				response.setSum(result);
			}catch (SQLException ex) {
				LOG.severe(" Inside gcdSum : DAO -> SQLException ocurred -> Reason : "+ex.getMessage());
				DAOUtils.throwSQLException(ex);
			}finally {
				
				if(LOG.isLoggable(Level.SEVERE)){
					LOG.info(" Inside gcdSum : DAO -> finally  block -> Closing the open conections ");
				}
				DAOUtils.close(rs);
				DAOUtils.close(ps);
				DAOUtils.close(conn);
			}
		if(LOG.isLoggable(Level.INFO)){
			LOG.info(" Exiting gcdSum : DAO");
		}
    		return response;
    }
    
   
}
	

