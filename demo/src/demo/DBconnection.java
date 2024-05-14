package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBconnection {
	private String Driver;
	private String url;
	private String username;
	private String password;

	public Connection loadProperties() {
		ResourceBundle rsb = ResourceBundle.getBundle("DB");

		String Driver = rsb.getString("driver");
		String url = rsb.getString("url");
		String username = rsb.getString("username");
		String password = rsb.getString("password");
		System.out.println(Driver + " " + url + " " + username + " " + password);

		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			if (con != null)
				System.out.println("Connection Established");
			else
				System.out.println("not e");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}
}
