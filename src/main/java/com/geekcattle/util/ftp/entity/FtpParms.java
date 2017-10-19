package com.geekcattle.util.ftp.entity;

public class FtpParms {

	private String HOST_IP;
	private int HOST_PORT;
	private String FTP_USERNAME;
	private String FTP_PASSWD;
	private String FTP_PATH ;
	
	private String localPath;//本地路径 path+name
	private String ftpPath;//ftp目标路径  path+name
	
	private String changeDirectory;  //path+name

	public FtpParms() {
		super();
	}

	public FtpParms(String hOST_IP, int hOST_PORT, String fTP_USERNAME, String fTP_PASSWD, String fTP_PATH) {
		super();
		HOST_IP = hOST_IP;
		HOST_PORT = hOST_PORT;
		FTP_USERNAME = fTP_USERNAME;
		FTP_PASSWD = fTP_PASSWD;
		FTP_PATH = fTP_PATH;
	}

	
	public String getChangeDirectory() {
		return changeDirectory;
	}

	public void setChangeDirectory(String changeDirectory) {
		this.changeDirectory = changeDirectory;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getHOST_IP() {
		return HOST_IP;
	}

	public void setHOST_IP(String hOST_IP) {
		HOST_IP = hOST_IP;
	}

	public int getHOST_PORT() {
		return HOST_PORT;
	}

	public void setHOST_PORT(int hOST_PORT) {
		HOST_PORT = hOST_PORT;
	}

	public String getFTP_USERNAME() {
		return FTP_USERNAME;
	}

	public void setFTP_USERNAME(String fTP_USERNAME) {
		FTP_USERNAME = fTP_USERNAME;
	}

	public String getFTP_PASSWD() {
		return FTP_PASSWD;
	}

	public void setFTP_PASSWD(String fTP_PASSWD) {
		FTP_PASSWD = fTP_PASSWD;
	}

	public String getFTP_PATH() {
		return FTP_PATH;
	}

	public void setFTP_PATH(String fTP_PATH) {
		FTP_PATH = fTP_PATH;
	}

	@Override
	public String toString() {
		return "FtpParms [HOST_IP=" + HOST_IP + ", HOST_PORT=" + HOST_PORT + ", FTP_USERNAME=" + FTP_USERNAME
				+ ", FTP_PASSWD=" + FTP_PASSWD + ", FTP_PATH=" + FTP_PATH + "]";
	}

}
