package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;

public class ProductDAO {

	private Connection conn;

	private String url, user, pass, driver;

	public ProductDAO() {
		super();

		url = Login.getInfo().get(0);
		user = Login.getInfo().get(1);
		pass = Login.getInfo().get(2);
		driver = Login.getInfo().get(3);

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Product> getAllProducts() {

		List<Product> customers = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from product");

			// set
			while (rs.next()) {
				Product tempProduct = convertRowToProduct(rs);
				customers.add(tempProduct);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;

	}
	
	public List<Product> getProductById(String id) throws SQLException{
		List<Product> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from product where PO_num like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Product tempProduct = convertRowToProduct(rs);
				list.add(tempProduct);
			}
			rs.close();
			stmt.close();
			return list;
			
		


	}
		
	

	private Product convertRowToProduct(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int prodId = rs.getInt("Prod_id");

		
		String name = rs.getString("Prod_name");
		int wholePrice = rs.getInt("whole_price");
		int retailPrice = rs.getInt("retail_price");


		Product tempProduct = new Product(prodId, name, wholePrice, retailPrice);

		return tempProduct;
	}

}
