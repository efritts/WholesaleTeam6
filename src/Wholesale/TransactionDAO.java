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

public class TransactionDAO {

	private Login login;

	private Connection conn;

	private String url, user, pass, driver;

	public TransactionDAO() {
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

	public List<Transaction> getAllTransactions() {

		List<Transaction> transactions = new ArrayList<>();
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from transactions");

			// set
			while (rs.next()) {
				Transaction tempTransaction = convertRowToTransaction(rs);
				transactions.add(tempTransaction);

			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;

	}
	
	public List<Transaction> getTransactionById(String id) throws SQLException{
		List<Transaction> list = new ArrayList<>();
		
		int tempId = Integer.parseInt(id);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		
			stmt = conn.prepareStatement("select * from transactions where Cust_id like ?");
			
			stmt.setInt(1, tempId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Transaction tempTransaction = convertRowToTransaction(rs);
				list.add(tempTransaction);
			}
			rs.close();
			stmt.close();
			return list;
			
		


	}
		
	

	private Transaction convertRowToTransaction(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int custId = rs.getInt("Cust_id");
		int supId = rs.getInt("Sup_id");
		int poNum = rs.getInt("PO_num");


		Transaction tempTransaction = new Transaction(custId, supId, poNum);

		return tempTransaction;
	}

}
