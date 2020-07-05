package dto;

public class LogDTO {
	private int id;
	private String localFileName;
	private String statusDownload;
	private String statusStaging;
	private String statusDW;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}

	public String getStatusDownload() {
		return statusDownload;
	}

	public void setStatusDownload(String statusDownload) {
		this.statusDownload = statusDownload;
	}

	public String getStatusStaging() {
		return statusStaging;
	}

	public void setStatusStaging(String statusStaging) {
		this.statusStaging = statusStaging;
	}

	public String getStatusDW() {
		return statusDW;
	}

	public void setStatusDW(String statusDW) {
		this.statusDW = statusDW;
	}

}
