package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;


public class CustomerDAO {


	private Connection conn;

	private String url, user, pass, driver;

	public CustomerDAO() {
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

	public List<Customer> getAllCusomters() {

		List<Customer> customers = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from customer");

			// set
			while (rs.next()) {
				Customer tempCustomer = convertRowToCustomer(rs);
				customers.add(tempCustomer);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;

	}
	
	public List<Customer> getCustomerById(String id) throws SQLException{
		List<Customer> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from customer where Cust_id like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Customer tempCustomer = convertRowToCustomer(rs);
				list.add(tempCustomer);
			}
			rs.close();
			stmt.close();
			return list;

	}
	
	public boolean delete(int custId) {
		String sql="DELETE FROM customer WHERE Cust_id ='"+custId+"'";

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
		
	
	
	public boolean update(int custId, String first, String middle, String last, String address, String phone) {
		String sql = "UPDATE customer SET Cust_id ='" + custId + "' ,f_name= '" + first 
				+ "',m_name='" + middle + "', l_name='" + last + "', Cust_address= '" + address 
				+ "', Cust_phone='" + phone +"' WHERE Cust_id='" + custId + "'";

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
		
	public boolean addRow(int custId, String first, String middle, String last, String address, String phone) {
		
		boolean added = false;
		
		PreparedStatement ps = null;
		
	    try {
			conn.setAutoCommit(false);

			    
			// Let's write a tuple or two
			ps = conn.prepareStatement("INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, custId );
			ps.setString(2, first);
			ps.setString(3, middle);
			ps.setString(4, last);
			ps.setString(5,  address);
			ps.setString(6,  phone);
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
	

	private Customer convertRowToCustomer(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int id = rs.getInt("Cust_id");
		String fName = rs.getString("f_name");
		String mName = rs.getString("m_name");
		String lName = rs.getString("l_name");
		String address = rs.getString("Cust_address");

		String phone = rs.getString("Cust_phone");

		Customer tempEmployee = new Customer(id, fName, mName, lName, address, phone);

		return tempEmployee;
	}

}
