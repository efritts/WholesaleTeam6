package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
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
			rs = stmt.executeQuery("select * from Purchase_Order");

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

		
			stmt = conn.prepareStatement("select * from Purchase_Order where PO_num like ?");
			
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
		
	public boolean delete(int poNum) {
		String sql="DELETE FROM purchase_order WHERE po_num ='"+poNum+"'";

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
		
	
	
	public boolean update(int poNum, String string) {
		String sql = "UPDATE purchase_order SET po_num ='" + poNum + "' ,purchase_date= '" + string 
				+ "' WHERE po_num='" + poNum + "'";

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
		
	public boolean addRow(int poNum, String string) {
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			Date date = Date.valueOf(string);
			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO purchase_order VALUES (?, ?)");
			ps.setInt(1, poNum );
			ps.setDate(2, date);
			
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

	private PurchaseOrder convertRowToPurchaseOrder(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int poNum = rs.getInt("PO_num");
		Date date = rs.getDate("purchase_date");
		

	

		PurchaseOrder tempPO = new PurchaseOrder(poNum, date);

		return tempPO;
	}

}
