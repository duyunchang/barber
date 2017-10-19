package com.geekcattle.util.ftp.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

public class FTPUtil {

    /**
     * 连接ftp服务器
     * @param url
     * @param port
     * @param username
     * @param password
     *
     * @return FtpClient
     * @throws FtpProtocolException
     * @throws IOException
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {
            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            //登陆
            ftp.login(username, password.toCharArray());
        	
        	 // 第二种方法  
        	 //ftp = FtpClient.create(url);  
        	// ftp.login(username, null, password);
            
            //设置为2进制
            ftp.setBinaryType();
            System.out.println("登录成功!!");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 切换目录
     * @param ftp
     * @param path
     * @throws IOException 
     * @throws FtpProtocolException 
     */
    public static void changeDirectory(FtpClient ftp, String path) throws FtpProtocolException, IOException   {
       
        	// 把远程系统上的目录切换到参数path所指定的目录
            ftp.changeDirectory(path); 
    }

    /**
     * 关闭ftp
     * @param ftp
     */
    public static void disconnectFTP(FtpClient ftp){
        try {
        	System.out.println("start disconnect!!");
            ftp.close();
            System.out.println("disconnect success!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param localFile
     * @param ftpFile
     * @param ftp
     */
    public static void upload(String localFile, String ftpFile, FtpClient ftp) {
    	System.out.println("start upload!!");
    	OutputStream os = null;
        FileInputStream fis = null;
        try {
        	// 将ftp文件加入输出流中。输出到ftp上
            //os = ftp.putFileStream(ftpFile);
        	 // 将远程文件加入输出流中  	
            os = (TelnetOutputStream) ftp.putFileStream(ftpFile, true);  
            
            File file = new File(localFile);

            // 创建一个缓冲区
            fis = new FileInputStream(file);
            //byte[] bytes = new byte[1024*5*2];//94922(ms)
            //byte[] bytes = new byte[1024*5];//87056(ms)
            byte[] bytes = new byte[1024*2];//76653(ms)
            //byte[] bytes = new byte[1024];//76592(ms)
            //byte[] bytes = new byte[1024/2];//83180(ms)
            int c;
            long allsize=file.length();
            long count=0;
            while((c = fis.read(bytes)) != -1){
            	
                os.write(bytes, 0, c);
                count++;
                System.out.println(c*count+";"+allsize);
            }
            System.out.println("upload success!!");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(os!=null) {
                    os.close();
                }
                if(fis!=null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件下载
     * @param localFile
     * @param ftpFile
     * @param ftp
     */
    public static void download(String localFile, String ftpFile, FtpClient ftp) {
    	System.out.println("start download!!");
    	InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 获取ftp上的文件
            is = ftp.getFileStream(ftpFile);
            File file = new File(localFile);
            byte[] bytes = new byte[1024];
            int i;
            fos = new FileOutputStream(file);
            while((i = is.read(bytes)) != -1){
                fos.write(bytes, 0, i);
            }
            System.out.println("download success!!");

        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) {
                    fos.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}