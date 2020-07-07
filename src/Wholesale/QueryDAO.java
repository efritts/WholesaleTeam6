package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSetMetaData;

import java.sql.Connection;

public class QueryDAO {

	private Connection conn;

	private String url, user, pass, driver;

	// Thought to have a PreparedStatement where user is only allowed to make Select
	// queries
	// That way we avoid having query builder unintentionally allowing users to
	// delete or modify things
	// We can change it to whatever you guys think is better though
	public QueryDAO(String userQuery) {
		super();

		url = Login.getInfo().get(0);
		user = Login.getInfo().get(1);
		pass = Login.getInfo().get(2);
		driver = Login.getInfo().get(3);

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, pass);

			// Still TODO: get resultset and populate the queryGUI with results

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List query(String sql) {
		Statement stmt;
		ResultSet rs;

		
		List result = new ArrayList<Object>();


		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount();
			
		    while(rs.next()){
		        Object[] currentRow = new Object[size];
		        for(int i = 0; i < size; i++){
//		        	currentRow[i] =  rs.getObject(i+1);
		        	if(rs.getObject(i + 1) == null) {
		        		currentRow[i] = "";
		        	}
		        	else {
		        		currentRow[i] = rs.getObject(i + 1);

		        	}
		        }
		        result.add(currentRow);
		    }

			// set

			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}