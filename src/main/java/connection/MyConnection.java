package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	public static Connection getMyConnection(String database) {

		Connection myConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host = "jdbc:mysql://localhost:3306/" + database;
			String username = "root";
			String password = "";
			myConnection = DriverManager.getConnection(host, username, password);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return myConnection;

	}
}
