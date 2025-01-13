package com.novelerp.appbase.master.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.novelerp.appbase.master.dao.AttachmentDao;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.service.impl.MD5Security;
import com.novelerp.core.util.FileUtil;

@Service
public class AttachmentServiceImpl extends AbstractContextServiceImpl<Attachment, AttachmentDto> implements AttachmentService{

	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;

	private Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
//    @Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
    private ServletContext context;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

/*	@Autowired
	private AttachmentConverter attachmentConverter;*/
	
	@PostConstruct
	private void init() {
		super.init(AttachmentServiceImpl.class, attachmentDao, Attachment.class, AttachmentDto.class);
		/*setObjectConverter(attachmentConverter);*/
		setByPassProxy(true);
	}

	@Override
	/*@Transactional(propagation = Propagation.REQUIRED)*/
	public AttachmentDto addAttachment(MultipartFile multipartFile) {
		AttachmentDto attachment=new AttachmentDto();
		String fileName = multipartFile.getOriginalFilename();
		attachment.setFileExtension(getFileExtension(fileName));
		attachment.setFileName(fileName);
		long time = System.currentTimeMillis();
		String name =  time+fileName;
		attachment.setName(name);
		byte[] media = getMedia(multipartFile);
		String savedFilePath =  saveMedia(media, name);
		/*String savedFilePath=mediaService.save(media,name);*/
		if(savedFilePath==null){
		  attachment.setResponse(new ResponseDto(true,"Error in destination path creation"));
		  return attachment;
		}else{
		  attachment.setResponse(new ResponseDto(false,"Path created successfully"));
		}
		attachment.setPath(savedFilePath);
		return attachment;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AttachmentDto addProtectedAttachment(MultipartFile multipartFile) {
		
		/*BPartnerDto partner = contextService.getPartner();*/
		
		AttachmentDto attachment=new AttachmentDto();
		String fileName = multipartFile.getOriginalFilename();
		attachment.setFileExtension(getFileExtension(fileName));
		attachment.setFileName(fileName);
		long time = System.currentTimeMillis();
		String name =  time+fileName;
		attachment.setName(name);
		byte[] media = getMedia(multipartFile);
		String path =  saveMedia(media, name);
		attachment.setPath(path);
		return save(attachment);
	}

	
	private String getFileExtension(String fileName){
		int index = fileName.lastIndexOf(".");
		if(index<0){
			return "";
		}
		return  fileName.substring(index+1);
	}
	
	private byte[] getMedia(MultipartFile multipartFile){	
		byte []media=null;
		try{
			media =  multipartFile.getBytes();
		}catch (Exception e) {
			log.error("ERROR", e);
		}	
		return media;
	}
	
	public String saveMedia (byte[] media, String fileName){
		return mediaService.save(media, fileName);
	}
	
	@Override
	public String readFile(Long attachmentId, byte []data){
		  AttachmentDto attachment = findDto(attachmentId);
		  String filePath = attachment.getPath();
		  String fileName =  attachment.getFileName();
	      Path path = Paths.get(filePath+File.separator+fileName);
	      try{
	    	 data = Files.readAllBytes(path);
	      }catch (Exception e) {
			log.error("Error reading file", e);
	      }
	      return fileName;
	}

	@Override
	@Transactional
	public ResponseDto deleteAttachment(String name,String attachmentFieldName,Long attachmentId) {
		Map<String , Object> map=AbstractServiceImpl.getParamMap(attachmentFieldName, null);
		String attachmentColumn=attachmentFieldName.concat(".attachmentId");
	    updateByJpql(map, attachmentColumn, attachmentId, name);
	    boolean deleted=deleteById(attachmentId);
	    if(deleted)
	    {
	    	return new ResponseDto(false, "Record Deleted");
	    }
	    return new ResponseDto(true, "Error in Delete");
	}

	@Override
	public List<AttachmentDto> getAttachmentList(Map<String, Object> map, int pageNumber, int pageSize) {
		String queryString =attachmentDao.getAttachmentQuery();
		List<AttachmentDto> AttachmentEntity= findDtosByQuery(queryString,map, pageNumber, pageSize);
		return AttachmentEntity;
	}

	@Override
	public List<AttachmentDto> getAttachmentListDateWise(Map<String, Object> map, int pageNumber, int pageSize) {
		String queryString =attachmentDao.getAttachmentDateWiseQuery();
		List<AttachmentDto> AttachmentEntity= findDtosByQuery(queryString,map, pageNumber, pageSize);
		return AttachmentEntity;
	}
	
	@Override
	public File getFileFromAttachment(AttachmentDto attachment){
		attachment=findDto(attachment.getAttachmentId());
		
		String filePath=attachment.getPath();
		String filename= attachment.getName();
		/*String extension=signedAttachment.getFileExtension();*/
		File file= new File(filePath+filename);
		return file;
	}

	@Override
	/*@Transactional(propagation = Propagation.REQUIRED)*/
	public AttachmentDto addEncryptedAttachment(MultipartFile multipartFile, String flag) {
		BPartnerDto partner=contextService.getPartner();
		AttachmentDto attachment=new AttachmentDto();
		String fileName = multipartFile.getOriginalFilename();
		attachment.setFileExtension(getFileExtension(fileName));
		attachment.setFileName(fileName);
		long time = System.currentTimeMillis();
		String name =  time+fileName;
		attachment.setName(name);
		byte[] media = getMedia(multipartFile);
		String iv=IV_PREFIX+partner.getPanNumber();
		try{
			media=MD5Security.encrypt(media,SEC_KEY,iv);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		/*MultipartFile result = new MockMultipartFile(name, fileName,multipartFile.getContentType(), media);*/
		String path =  saveMedia(media, name);
		/*String path=mediaService.save(media, name);*/
		if(path==null){
			attachment.setResponse(new ResponseDto(true,"Error in destination path creation"));
			return attachment;
		}else{
			attachment.setResponse(new ResponseDto(false,"Path created successfully"));
		}
		attachment.setPath(path);
		if("Y".equals(flag)){
			attachment.setIsDocEncrypted(flag);
		}
		return attachment;
	}
	
	@Override
	public ResponseDto isValid(MultipartFile file){
		
		ResponseDto resp=new ResponseDto();
		
		if(file!=null){
			byte[] bytes=null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			 
			if(fileUtil.isPdf(bytes) || fileUtil.isJpeg(bytes) || fileUtil.isZip(bytes)  || "txt".equals(extension) ){// || fileUtil.isSigned(bytes)|| fileUtil.isRar(bytes)
				resp.setHasError(false);
				resp.setMessage("Valid File");
			}else{
				resp.setHasError(true);
				resp.setMessage("Only PDF, ZIP, TXT and JPEG files can be uploaded");
			}
		}else{
			resp.setHasError(true);
			resp.setMessage("No Document Uploaded");
		}
		
		return resp;
	}
	
	@Override
	public ResponseDto isImageValid(MultipartFile file){
		ResponseDto resp=new ResponseDto();
		if(file!=null){
			byte[] bytes=null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if( fileUtil.isJpeg(bytes)){
				resp.setHasError(false);
				resp.setMessage("Valid File");
			}else{
				resp.setHasError(true);
				resp.setMessage("Only *.PNG ,*.JPG AND *.JPEG files can be uploaded");
			}
		}else{
			resp.setHasError(true);
			resp.setMessage("No Document Uploaded");
		}
		
		return resp;
	}
	
	//for signed file upload ruchi
	@Override
	public ResponseDto isSignFile(MultipartFile file){
		ResponseDto resp=new ResponseDto();
		if(file!=null){
			byte[] bytes=null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if(fileUtil.isSigned(bytes)){
				resp.setHasError(false);
				resp.setMessage("Valid File");
			}else{
				resp.setHasError(true);
				resp.setMessage("Only Signed files can be uploaded");
			}
		}else{
			resp.setHasError(true);
			resp.setMessage("No Document Uploaded");
		}
		
		return resp;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateAttachmentDecryptionStatus(AttachmentDto att){
		Map<String, Object> propertyValueMap=new HashMap<>();
		propertyValueMap.put("isDocEncrypted", "Y");
		
		Map<String, Object> whereClauseParameters=new HashMap<>();
		whereClauseParameters.put("attachmentId", att.getAttachmentId());
		
		int updateCount=updateByJpql(propertyValueMap, whereClauseParameters);
		if(updateCount>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public void downloadTestFile(HttpServletRequest request,HttpServletResponse response) {
		String name="";
		String isSftpEnabled=sysConfiguratorService.getSystemPropertyConfigurator(AppBaseConstant.EAT_SFTP_ENABLED);
	    if("Y".equals(isSftpEnabled)){
	    	AttachmentDto attachment=new AttachmentDto();
	    	String path=sysConfiguratorService.getSystemPropertyConfigurator(AppBaseConstant.SFTP_PATH);
	    	attachment.setPath(path);
	    	attachment.setName(AppBaseConstant.DIGITAL_TEST_COPY);
	    	String fileName =CommonUtil.getReportFileName(request, "DTC_");
	    	attachment.setFileName(fileName);
	    	mediaService.downloadFile(attachment, request, response);
	    }else{
		 name= sysConfiguratorService.getAppDocDir();		 
		 name=name+AppBaseConstant.DIGITAL_TEST_COPY;
		 File file = new File(name);
         OutputStream os = null;
         FileInputStream fis = null;

		  if (file.exists()) {
			  try{
				  String mimeType = context.getMimeType(file.getPath());
				  
				  if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	
	              response.setContentType(mimeType);
	              String fileName =CommonUtil.getReportFileName(request, "DTC_");
	              /*StringBuilder headerContent =  new StringBuilder();
	      		  headerContent.append(fileName);*/
	              response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
	              response.setContentLength((int) file.length());
	
	              os = response.getOutputStream();
	              fis = new FileInputStream(file);
	              byte[] buffer = new byte[4096];
	              int b = -1;
	
	              while ((b = fis.read(buffer)) != -1) {
	                  os.write(buffer, 0, b);
	              }
			  }catch (Exception e) {
				  log.error("Exception", e);
			}finally {
				try{
					fis.close();
		            os.close();
				}catch (Exception e) {
					log.error("Exception while closing stream", e);
				}
			}
		  }
	    }
	}
	
}
