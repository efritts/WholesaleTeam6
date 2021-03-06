package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;

public class SupplierDAO {

	private Connection conn;

	private String url, user, pass, driver;

	public SupplierDAO() {
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

	public List<Supplier> getAllSuppliers() {

		List<Supplier> suppliers = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Supplier");

			// set
			while (rs.next()) {
				Supplier tempSupplier = convertRowToSupplier(rs);
				suppliers.add(tempSupplier);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suppliers;

	}
	
	public List<Supplier> getSupplierById(String id) throws SQLException{
		List<Supplier> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from supplier where Sup_id like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Supplier tempSupplier = convertRowToSupplier(rs);
				list.add(tempSupplier);
			}
			rs.close();
			stmt.close();
			return list;
			
		


	}
		
	

	private Supplier convertRowToSupplier(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int id = rs.getInt("Sup_id");
		String name = rs.getString("Sup_name");
		String address = rs.getString("Sup_address");
		String phone = rs.getString("phone");


		Supplier tempSupplier = new Supplier(id, name, address, phone);

		return tempSupplier;
	}

	public boolean addRow(String supId, String name, String address, String phone) {
		
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO SUPPLIER VALUES (?, ?, ?, ?)");
			ps.setString(1, supId );
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
		
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

	public boolean update(String suppId, String name, String address, String phone) {
		String sql = "UPDATE supplier SET Sup_id ='" + suppId + "' ,Sup_name= '" + name 
				+ "',sup_address='" + address + phone +"' WHERE Sup_id='" + suppId + "'";

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

	public boolean delete(int suppId) {
		String sql="DELETE FROM supplier WHERE Sup_id ='"+suppId+"'";

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

}
