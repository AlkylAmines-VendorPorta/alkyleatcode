package com.novelerp.alkyl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;

public class FTPController {

	private static String userName = "ftpuser";
	private static String passWord = "va&gR@_fbqR9&MGX";
	private static String sftpHost = "202.143.99.102";
	private static Integer sftpPort = 990;
	private static String path = "/SAP_INTEGRATION/VENDOR_DATA/TO_PROCESS/";
	
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		ftpClient=connectionCredentials(ftpClient);
		
		File firstLocalFile = new File("D:\\ccc.zip");
		 
        String firstRemoteFile = "xyz.zip";
        InputStream inputStream = null;
        OutputStream outputStream = null;
		try {
			
			inputStream = new FileInputStream(firstLocalFile);
			
			outputStream = ftpClient.storeFileStream(path+firstRemoteFile);
			byte[] bytesIn = new byte[4096];
			int read = 0;
			while ((read = inputStream.read(bytesIn)) != -1) {
				outputStream.write(bytesIn, 0, read);				
			}
			
			boolean completed = ftpClient.completePendingCommand();
			if (completed) {
				System.out.println("The second file is uploaded successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				 inputStream.close();
				 outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

        
	}

	private static FTPClient connectionCredentials(FTPClient ftpClient) {
		
		try {
			ftpClient.connect(InetAddress.getByName(sftpHost));
			ftpClient.login(userName, passWord);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return ftpClient;
	}
	
}
