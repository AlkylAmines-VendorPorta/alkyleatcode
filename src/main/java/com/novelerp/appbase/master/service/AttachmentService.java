package com.novelerp.appbase.master.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface AttachmentService extends CommonService<Attachment, AttachmentDto>{

	public AttachmentDto addAttachment(MultipartFile multipartFile);
	public AttachmentDto addEncryptedAttachment(MultipartFile multipartFile,String Flag);
	public AttachmentDto addProtectedAttachment(MultipartFile multipartFile);
	
	/**
	 * get byte array of file with given attachment id
	 * @param attachmentId
	 * @return
	 */
	public String readFile(Long attachmentId, byte[] data);
	public ResponseDto deleteAttachment(String name,String attachmentFieldName,Long attachmentId);
	public List<AttachmentDto> getAttachmentList(Map<String,Object> map,int pageNumber, int pageSize);
	public List<AttachmentDto> getAttachmentListDateWise(Map<String,Object> map,int pageNumber, int pageSize);
	/**
	 * @param attachment
	 * @return
	 */
	public File getFileFromAttachment(AttachmentDto attachment);
	/**
	 * @param file
	 * @return
	 */
	public ResponseDto isValid(MultipartFile file);
	//for signed file upload ruchi
	public ResponseDto isSignFile(MultipartFile file);
	/**
	 * @param att
	 * @return
	 */
	public boolean updateAttachmentDecryptionStatus(AttachmentDto att);
	
	public void downloadTestFile(HttpServletRequest request,HttpServletResponse response);
	
	public ResponseDto isImageValid(MultipartFile file);
	
	}
