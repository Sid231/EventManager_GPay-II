package com.dev.eventmanager.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.dev.eventmanager.beans.Customer;

public class CustomerDbUtil {

	private DataSource dataSource;

	public CustomerDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean insertIntoDB(Customer customer) {
		boolean isSuccess = false;
		
		//CONNECT TO THE DATABASE
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			
			//GET A CONNECTION
			conn = dataSource.getConnection();
			
			//CREATE SQL STATEMENT
			String sql = "insert into student"
					+ "(name,phone_number,email,amount)"
					+ "values (?,?,?,?)";
			
			//SET THE PARAM VALUES FOR STUDENT
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,customer.getCustomerName());
			stmt.setString(2,customer.getPhoneNumber());
			stmt.setString(3,customer.getEmailAddress());
			stmt.setLong(4,customer.getAmount());
			
			//EXECUTE THE QUERY
			stmt.execute();
			isSuccess = true;
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}finally{
			close(conn, stmt, null);
		}
				
		return isSuccess;
	}

	private void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if(conn != null) conn.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
