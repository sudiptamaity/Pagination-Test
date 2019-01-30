package com.lti.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.lti.training.day6.streams.Employee;
//import com.lti.training.day7.jdbc.DataAccessException;
import com.lti.training.model.Product;

public class ProductDao {

	public List<Product> fetchProducts(int from, int to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
			// this string contains the sql query to be fired whenever the user inputs the number of products he wants to see
			String sql = "select * from" 
					+ " "
					+ "(select p.*, rownum r from product13 p)" 
					+ " "
					+ "where r  between ? and ?";

			pstmt = conn.prepareStatement(sql); // preparing the query to be selected
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			rs = pstmt.executeQuery();// select statement

			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("pid"));
				product.setName(rs.getString("pname"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				products.add(product);
			}
			return products;
		}

		catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			}

			catch (Exception e) {
			}
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
