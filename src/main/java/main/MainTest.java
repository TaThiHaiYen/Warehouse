package main;

import java.io.File;

import dto.LogDTO;
import scp.ScpDownload;
import utils.Create_Drop_Table;
import utils.LoadFileExcel;
import utils.SelectField;
import utils.SendMail;
import utils.SettingStatus;
import utils.UpdateTable;
import utils.WriteLog;

public class MainTest {
	public static void main(String[] args) {
		SelectField selectField = new SelectField();
		
		try {
			//Download File
			ScpDownload download = new ScpDownload();
			download.downloadFile();
			//Ghi log sau khi download
			File folder = new File(selectField.selectField("pathFile", "config"));
			File[] listOfFiles = folder.listFiles();
			if (download.downloadFile().equals("success")) {
				for (File file : listOfFiles) {
					if (file.isFile()) {
						LogDTO log = new LogDTO();
						log.setLocalFileName(file.getName());
						log.setStatusDownload(SettingStatus.SUCCESS);
						log.setStatusStaging(SettingStatus.WAITING);
						log.setStatusDW(SettingStatus.WAITING);
						WriteLog.onWriteLog(log);
					}
				}
			}
			//Send mail sau khi download
//			if(selectField.selectField("statusDownload", "log").equalsIgnoreCase(SettingStatus.SUCCESS)) {
//				SendMail.sendMail("thaingocleduy@gmail.com", "Datawarehouse2020", "Update status Download Success");
//			} else {
//				SendMail.sendMail("thaingocleduy@gmail.com", "Datawarehouse2020", "Update status Download Fail");
//			}
//			
			//Tao bang students trong staging
			Create_Drop_Table create_Drop_Table = new Create_Drop_Table();
			create_Drop_Table.DropTable();
			create_Drop_Table.CreateTable();
			//Load data vao staging
			LoadFileExcel data = new LoadFileExcel();
			if (selectField.selectField("statusDownload", "log").equalsIgnoreCase(SettingStatus.SUCCESS)) {
				data.exportDataFromExcel();
				UpdateTable.updateTable("log", "statusStaging", SettingStatus.SUCCESS);
			} else {
				data.exportDataFromExcel();
				UpdateTable.updateTable("log", "statusStaging", SettingStatus.FAIL);
			}
			
			//Send mail sau khi load data vao staging
//			if(selectField.selectField("statusDownload", "log").equalsIgnoreCase(SettingStatus.SUCCESS)) {
//				SendMail.sendMail("thaingocleduy@gmail.com", "Datawarehouse2020", "Load data to Staging Success - Update status Staging Success");
//			} else {
//				SendMail.sendMail("thaingocleduy@gmail.com", "Datawarehouse2020", "Load data to Staging Fail - Update status Staging Fail");
//			}
//
//			System.out.println("success");
		} catch (Exception e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
}