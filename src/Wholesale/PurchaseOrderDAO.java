package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.sql.Connection;

import Wholesale.Login;

public class PurchaseOrderDAO {

	private Login login;

	private Connection conn;

	private String url, user, pass, driver;

	public PurchaseOrderDAO() {
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

	public List<PurchaseOrder> getAllPurchaseOrders() {

		List<PurchaseOrder> purchaseOrders = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from PurchaseOrder");

			// set
			while (rs.next()) {
				PurchaseOrder tempPurchaseOrder = convertRowToPurchaseOrder(rs);
				purchaseOrders.add(tempPurchaseOrder);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchaseOrders;

	}
	
	public List<PurchaseOrder> getPurchaseOrderById(String id) throws SQLException{
		List<PurchaseOrder> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from PurchaseOrder where PO_num like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				PurchaseOrder tempPurchaseOrder = convertRowToPurchaseOrder(rs);
				list.add(tempPurchaseOrder);
			}
			rs.close();
			stmt.close();
			return list;
			
		


	}
		
	

	private PurchaseOrder convertRowToPurchaseOrder(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int poNum = rs.getInt("PO_num");
		Date date = rs.getDate("purchase_date");
		

	

		PurchaseOrder tempPO = new PurchaseOrder(poNum, date);

		return tempPO;
	}

}
