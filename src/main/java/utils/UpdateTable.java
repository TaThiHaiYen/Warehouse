package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.MyConnection;
////update 1 trường trong database
public class UpdateTable {
	public static void updateTable(String table, String field, String value) throws Exception {
		// connection database
		Connection connection = MyConnection.getMyConnection("datasourse");
		// sql
		String sql = "UPDATE " + table + " SET " + field + "= ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.setString(1, value);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
