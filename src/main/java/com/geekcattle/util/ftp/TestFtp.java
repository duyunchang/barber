package com.geekcattle.util.ftp;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;

import com.geekcattle.util.ftp.config.Config;
import com.geekcattle.util.ftp.entity.FtpParms;
import com.geekcattle.util.ftp.tools.*;

public class TestFtp {

	public static void main(String[] args) {
		long currentTimeMillis1 = System.currentTimeMillis();
		System.out.println("LocalIp=" + Tools.NetTools.hostAddress()); // read
																		// host
																	 	// ip

		FtpParms ftpParms = readConfig("F:/Java.zip", "/home/test/xx.zip");

		try {
			upload(ftpParms);

			// ftpParms = readConfig("F:/Temp/user1@20150925135953.rar",
			// "/tmp/users/user1@20150925135953.rar");

			// download(ftpParms);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long currentTimeMillis2 = System.currentTimeMillis();
			System.out.println((currentTimeMillis2 - currentTimeMillis1) + "(ms)");
		}

	}

	static void upload(FtpParms ftpParms) throws FtpProtocolException, IOException {
		// 连接ftp
		FtpClient ftp = FTPUtil.connectFTP(ftpParms.getHOST_IP(), ftpParms.getHOST_PORT(), ftpParms.getFTP_USERNAME(),
				ftpParms.getFTP_PASSWD());

		// 切换目录
		FTPUtil.changeDirectory(ftp, ftpParms.getFTP_PATH());

		FTPUtil.upload(ftpParms.getLocalPath(), ftpParms.getFtpPath(), ftp);

		FTPUtil.disconnectFTP(ftp);
	}

	static void download(FtpParms ftpParms) {
		// 连接ftp
		FtpClient ftp = FTPUtil.connectFTP(ftpParms.getHOST_IP(), ftpParms.getHOST_PORT(), ftpParms.getFTP_USERNAME(),
				ftpParms.getFTP_PASSWD());

		// 切换目录
		// FTPUtil.changeDirectory(ftp, path);

		FTPUtil.download(ftpParms.getLocalPath(), ftpParms.getFtpPath(), ftp);

		FTPUtil.disconnectFTP(ftp);
	}

	// static void listFiles(FtpParms ftpParms) {
	// FtpClient ftp = FTPUtil.connectFTP(ftpParms.getHOST_IP(),
	// ftpParms.getHOST_PORT(),
	// ftpParms.getFTP_USERNAME(),ftpParms.getFTP_PASSWD());
	// try {
	// System.out.println(ftp.listFiles(path));
	// } catch (FtpProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	private static FtpParms readConfig(String localPath, String ftpPath) {
		FtpParms ftpParms = new FtpParms(Config.FTP.HOST_IP, Config.FTP.HOST_PORT, Config.FTP.FTP_USERNAME,
				Config.FTP.FTP_PASSWD, Config.FTP.FTP_PATH);
		ftpParms.setLocalPath(localPath);
		ftpParms.setFTP_PATH(ftpPath);
		return ftpParms;
	}
}