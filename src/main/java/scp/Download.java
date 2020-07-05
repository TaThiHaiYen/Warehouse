
package scp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import connection.MyConnection;

public class Download {

	// lấy cofig để tải file trên nó
	static String host;
	static int port;
	static String user;
	static String pw;
	static String remotePath;
	static String localPath;
	static String dirLog;
	static String download;

	public static void getConfig(int id) throws SQLException {
		PreparedStatement pre = (PreparedStatement) MyConnection.getMyConnection("datasourse")
				.prepareStatement("SELECT * FROM datasourse.config where id=?;");
		pre.setInt(1, id);
		ResultSet tmp = pre.executeQuery();
		tmp.next();

		// using step1 dow file
		host = tmp.getString("host");
		user = tmp.getString("user");
		pw = tmp.getString("pw");
		remotePath = tmp.getString("remotePath");
		localPath = tmp.getString("localPath");
		port = Integer.parseInt(tmp.getString("port"));
		// dirLog

		System.out.println("Get config: complete!!!!");
	}
}