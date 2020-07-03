package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;

public class PurchaseDAO {

	private Connection conn;

	private String url, user, pass, driver;

	public PurchaseDAO() {
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

	public List<Purchase> getAllPurchases() {

		List<Purchase> purchases = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from purchase");

			// set
			while (rs.next()) {
				Purchase tempPurchase = convertRowToPurchase(rs);
				purchases.add(tempPurchase);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchases;

	}
	
	public List<Purchase> getPurchaseById(String id) throws SQLException{
		List<Purchase> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from purchase where PO_num like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Purchase tempPurchase = convertRowToPurchase(rs);
				list.add(tempPurchase);
			}
			rs.close();
			stmt.close();
			return list;
			
		


	}
		
	

	private Purchase convertRowToPurchase(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int poNum = rs.getInt("PO_num");
		int prodId = rs.getInt("Prod_id");
		int quantity = rs.getInt("P_quantity");

	

		Purchase tempPurchase = new Purchase(poNum, prodId, quantity);

		return tempPurchase;
	}

}
