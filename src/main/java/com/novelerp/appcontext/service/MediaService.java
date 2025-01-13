package com.novelerp.appcontext.service;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPFile;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.SFTPUploadDto;

public interface MediaService {
	
	public String save(byte []media, String fileName);
	public void downloadFile(AttachmentDto dto,HttpServletRequest request,HttpServletResponse response);
	/**
	 * @param dto
	 * @return
	 */
	public byte[] getBISFromAttachment(AttachmentDto dto);
	/**
	 * @param file
	 * @return
	 */
	public byte[] getBISFromAttachment(String file);
	/**
	 * @param file
	 * @param fileName
	 * @return
	 */
	public String save(File file, String fileName);
	/**
	 * 
	 * 
	 * @return sftp connection map
	 */
	public Map<String,Object> getSFTPConnection();
	
	public File writeByteArrayTo(byte[] media, String fileName);
	
	public byte[] getByteArrayByFile(String file);
	
	public boolean writeByteArrayTo(byte[] media, String path ,String fileName);
	
	public String[] getFilesListAt(String path);
	
	public boolean deleteFile(String ObsoluteFilePath);
	public SFTPUploadDto getCredentials();
}
