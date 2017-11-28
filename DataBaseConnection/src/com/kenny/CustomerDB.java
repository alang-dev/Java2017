package com.kenny;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class CustomerDB {
	
	public static List<Customer> getAll() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM customer ORDER BY id";
		Connection connection = DBUtil.getConnection();
		
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getInt(1));
			customer.setFullname(rs.getString(2));
			customer.setAddress(rs.getString(3));
			customer.setSalary(rs.getDouble(4));
			
			customers.add(customer);
		}
		
		return customers;
	}
}
