package com.novelerp.core.util;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DBUtil {

	private Logger log =  LoggerFactory.getLogger(DBUtil.class);
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public Connection getDBConnection(){
		Connection connection =  null;
		try{
			connection = dataSource.getConnection();
		}catch (Exception e) {
			log.error("Error while DB Connection", e);
		}
		return connection;
	}
	
	public void close(Connection connection){
		if(connection == null){
			return;
		}
		try{
			connection.close();
		}catch (Exception e) {
			log.error("Error while DB Connection", e);
		}
	}

}
