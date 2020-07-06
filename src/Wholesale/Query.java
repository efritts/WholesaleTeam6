package Wholesale;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;

public class Query {

	private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs; 

	private String url, user, pass, driver;


    //Thought to have a PreparedStatement where user is only allowed to make Select queries
    //That way we avoid having query builder unintentionally allowing users to delete or modify things
    //We can change it to whatever you guys think is better though
	public Query(String userQuery) {
		super();

		url = Login.getInfo().get(0);
		user = Login.getInfo().get(1);
		pass = Login.getInfo().get(2);
		driver = Login.getInfo().get(3);

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, pass);
            String qry = "select ?;"
            stm = conn.prepareStatement(qry);
            stm.setString(1, userQuery);

            //Still TODO: get resultset and populate the queryGUI with results

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}