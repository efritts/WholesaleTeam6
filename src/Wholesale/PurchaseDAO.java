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
		
	public boolean delete(int poNum, int prodId) {
		String sql="DELETE FROM purchase WHERE PO_num ='"+poNum+ "' AND Prod_id='" + prodId +"'";

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
		
	
	
	public boolean update(int poNum, int prodId, int quantity) {
		String sql = "UPDATE purchase SET po_num ='" + poNum + "' ,prod_id= '" + prodId
				+ "',p_quantity='" + quantity +"' WHERE po_num='" + poNum + "' AND prod_id = '" +prodId+"'";

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
		
	public boolean addRow(int poNum, int prodId, int quantity) {
		
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO purchase VALUES (?, ?, ?)");
			ps.setInt(1, poNum );
			ps.setInt(2, prodId);
			ps.setInt(3, quantity);
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
	

	private Purchase convertRowToPurchase(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int poNum = rs.getInt("PO_num");
		int prodId = rs.getInt("Prod_id");
		int quantity = rs.getInt("P_quantity");

	

		Purchase tempPurchase = new Purchase(poNum, prodId, quantity);

		return tempPurchase;
	}

}
