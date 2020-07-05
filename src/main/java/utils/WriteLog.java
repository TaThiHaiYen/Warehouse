package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.MyConnection;
import dto.LogDTO;

public class WriteLog {
	public static void onWriteLog(LogDTO logDTO) throws Exception {
		// connection database
		Connection connection = MyConnection.getMyConnection("datasourse");

		// insert log
		String sql = "INSERT INTO log(localFileName, statusDownload, statusStaging, statusDW) VALUES (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.setString(1, logDTO.getLocalFileName());
			ps.setString(2, logDTO.getStatusDownload());
			ps.setString(3, logDTO.getStatusStaging());
			ps.setString(4, logDTO.getStatusDW());
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
