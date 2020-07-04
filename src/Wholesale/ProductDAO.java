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
		
	public boolean delete(int prodId) {
		String sql="DELETE FROM product WHERE Prod_id ='"+ prodId +"'";

        try
        {
           
            //STATEMENT
            Statement s=conn.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
		
	
	
	public boolean update(int prodId, String name, int wholePrice, int retailPrice) {
		String sql = "UPDATE product SET prod_id ='" + prodId + "' ,prod_name= '" + name 
				+ "',whole_price='" + wholePrice + "', retail_price='" + retailPrice 
				+ "' WHERE Prod_id='" + prodId + "'";

        try {


            //STATEMENT
            Statement s = conn.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}
		
	public boolean addRow(int prodId, String name, int wholePrice, int retailPrice) {
		
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO PRODUCT VALUES (?, ?, ?, ?)");
			ps.setInt(1, prodId );
			ps.setString(2, name);
			ps.setInt(3, wholePrice);
			ps.setInt(4, retailPrice);
			if (ps.executeUpdate() > 0) {
				added = true;
			}
			
			ps.close();

			// Have to do this to write changes to a DB
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return added;
		
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
