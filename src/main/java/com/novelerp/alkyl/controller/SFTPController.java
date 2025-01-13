package com.novelerp.alkyl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPController {
	
	private static String userName = "ftpuser";
	private static String passWord = "va&gR@_fbqR9&MGX";
	private static String sftpHost = "202.143.99.102";
	private static Integer sftpPort = 990;
	private static String path = "/SAP_INTEGRATION/VENDOR_DATA/TO_PROCESS/";
	
	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<>();
		try{
			
			JSch jsch = new JSch();
			Session session = jsch.getSession(userName,sftpHost,sftpPort);
			session.setPassword(passWord);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			map.put("channelSftp", channelSftp);
			map.put("session", session);
			File file = new File("D:\\Mayank Maheshwari.pdf");
			FileInputStream fis=null;
			try {
				if (channelSftp != null && session != null) {
					System.out.println("getting destination path....");
					if(path!=null){
						channelSftp.cd(path);
						//file = readByteArrayTo(new byte[111], "xyz");
						fis=new FileInputStream(file);
						channelSftp.put(fis, "xyz");
						System.out.println("file uploaded successfully....");
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}finally {
				System.out.println(file);
				try {
					if(fis!=null){
					 fis.close();
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				closeSFTPConnection(channelSftp,session);			
			}
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static File writeByteArrayTo(byte[] media, String fileName,String path){
		File file = new File(path+fileName);
		FileOutputStream fOut = null;
		try{
			fOut =  new FileOutputStream(file);
			fOut.write(media);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			closeOutputStream(fOut);
		}
		return file;
	}
	
	public static File readByteArrayTo(byte[] media, String fileName){
		File file = new File(fileName);
		FileInputStream fIn = null;
		try{
			fIn =  new FileInputStream(file);
			fIn.read(media);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			closeIntputStream(fIn);
		}
		return file;
	}
	
	private static void closeSFTPConnection(ChannelSftp channelSftp,Session session){
		if (channelSftp != null) {
            channelSftp.exit();
        }
        if (session != null) {
            session.disconnect();
        }
	}
	
	private static void closeOutputStream(OutputStream out){
		if(out ==null){
			return;
		}
		try{
			out.flush();
			out.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void closeIntputStream(InputStream in){
		if(in ==null){
			return;
		}
		try{
			in.reset();
			in.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
