package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.MyConnection;

public class CopyData {
	public void copyData() {
		SelectField selectField = new SelectField();
		Connection connection = null;
		PreparedStatement ps_drop = null;
		PreparedStatement ps = null;

		try {
			connection = MyConnection.getMyConnection("copieddatafromdwdb");
			
			String table_drop = "DROP TABLE IF EXISTS " +  selectField.selectField("tableName", "config");
			ps_drop = connection.prepareStatement(table_drop);
			ps_drop.execute();
			
			String sql_createtable = "CREATE TABLE " + selectField.selectField("tableName", "Config") + " (STT INT NOT NULL auto_increment, MSSV INT NOT NULL, ho VARCHAR(255), ten VARCHAR(255), dOB date, lop VARCHAR(8), tenLop VARCHAR(255), sdt INT, email VARCHAR(255), quequan VARCHAR(255), ghichu TEXT, PRIMARY KEY (STT));";
			 ps = connection.prepareStatement(sql_createtable);
			ps.execute();
			
			String sql = "INSERT INTO students SELECT * FROM datawarehousedb.students;";
			PreparedStatement pst = connection.prepareStatement(sql);


			int rows = pst.executeUpdate();
			if (rows == 0) {
				System.out.println("Don't add any row!");
			} else {
				System.out.println(rows + " row(s)affected.");
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
