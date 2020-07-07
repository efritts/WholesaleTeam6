package Wholesale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Login {
	
	private static String url;
	private static String user;
	private static String pass;
	private static String driver;
	private static String driverLocation;
	private boolean connStatus;
	
	public Login(String url, String user, String pass, String driver, String driverLocation) {
		
		Login.url = url;
		Login.user = user;
		Login.pass = pass;
		Login.driver = driver;
		Login.driverLocation = driverLocation;
		
		getConnection();
	}
		
	public void getConnection() {	
		try {
				Class.forName(Login.driver);
				
				Connection conn = DriverManager.getConnection(url, user, pass);
				
				if(conn.isValid(2)) {
					connStatus = true;
				}
				else {
					connStatus = false;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public boolean getStatus() {
		return connStatus;
	}
	
	public static List<String> getInfo(){
		
		List<String> info = new ArrayList<String>();
		info.add(url);
		info.add(user);
		info.add(pass);
		info.add(driver);
		
		return info;
		
	}

}
