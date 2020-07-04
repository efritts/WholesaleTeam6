package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;

import Wholesale.Login;

public class InventoryDAO {

	private Login login;

	private Connection conn;

	private String url, user, pass, driver;

	public InventoryDAO() {
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

	public List<Inventory> getAllInventorys() {

		List<Inventory> inventorys = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Inventory");

			// set
			while (rs.next()) {
				Inventory tempInventory = convertRowToInventory(rs);
				inventorys.add(tempInventory);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventorys;

	}
	
	public List<Inventory> getInventoryById(String id) throws SQLException{
		List<Inventory> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from inventory where Sup_id like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Inventory tempInventory = convertRowToInventory(rs);
				list.add(tempInventory);
			}
			rs.close();
			stmt.close();
			return list;

	}
		
	public boolean delete(int supId) {
		String sql="DELETE FROM inventory WHERE sup_id ='"+supId+"'";

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
		
	
	
	public boolean update(int supId, int prodId, int quantity)  {
		String sql = "UPDATE inventory SET Sup_id ='" + supId + "' , prod_id= '" + prodId 
				+ "',inv_quantity='" + quantity + "' WHERE Sup_id='" + supId + "' AND Prod_id= '" + prodId + "'";

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
		
	public boolean addRow(int supId, int prodId, int quantity) {
		
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO INVENTORY VALUES (?, ?, ?)");
			ps.setInt(1, supId );
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
	


	private Inventory convertRowToInventory(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int supId = rs.getInt("Sup_id");
		int prodId = rs.getInt("Prod_id");
		int quantity = rs.getInt("Inv_quantity");
		


		Inventory tempInventory = new Inventory(supId, prodId, quantity);

		return tempInventory;
	}

}
