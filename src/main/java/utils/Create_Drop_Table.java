package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.MyConnection;

public class Create_Drop_Table {
	SelectField selectField = new SelectField();
	PreparedStatement ps_drop = null;
	PreparedStatement ps_create = null;
	
	public void DropTable() {
		Connection connection = MyConnection.getMyConnection("datawarehousedb");
		try {
			String sql = "DROP TABLE IF EXISTS " +  selectField.selectField("tableName", "config");	
			ps_drop = connection.prepareStatement(sql);
			ps_drop.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CreateTable() {
		Connection connection = MyConnection.getMyConnection("datawarehousedb");
		try {
			String sql = "CREATE TABLE " + selectField.selectField("tableName", "config") + " (STT INT NOT NULL auto_increment, MSSV INT NOT NULL, ho VARCHAR(255), ten VARCHAR(255), dOB date, lop VARCHAR(255), tenLop VARCHAR(255), sdt INT, email VARCHAR(255), quequan VARCHAR(255), ghichu TEXT, PRIMARY KEY (STT));";
			ps_create = connection.prepareStatement(sql);
			ps_create.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
