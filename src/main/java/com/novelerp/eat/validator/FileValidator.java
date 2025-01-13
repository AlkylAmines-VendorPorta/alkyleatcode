/**
 * @author Ankush
 */
package com.novelerp.eat.validator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.ValidationUtil;

@Component
public class FileValidator {

	/* (non-Javadoc)
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	
	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AppPropertyUtil appPropertyUtil;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
    @Autowired
    /*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
    Logger log=LoggerFactory.getLogger(FileValidator.class);
	public void validate(Object object,HttpServletRequest request, Errors errors) {
		if(!object.getClass().isAssignableFrom(AttachmentDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		/*String isTimeStampValidation=appPropertyUtil.getProperty("timestamp.validation");*/
		String isTimeStampValidation=sysConfiguratorService.getSystemPropertyConfigurator("timestamp.validation");

		if(!"Y".equals(isTimeStampValidation)){
			return;
		}
		AttachmentDto attachment=(AttachmentDto) object;
		attachment=attachmentService.findDto(attachment.getAttachmentId());
		String fileName=attachment.getFileName();
		String[] fileParts=fileName.split(".sig");
		fileName=fileParts[0].toString();
		Long generationTime=null;
		HttpSession session=request.getSession(false);
		
		String fileNameSession=(String) session.getAttribute(AppBaseConstant.GENERATED_FILE_NAME);
		Long generationTimeSession=(Long) session.getAttribute(AppBaseConstant.GENERATION_TIME);
		try{
			generationTime=getFileGenerationTime(attachment,errors);		
			if(generationTimeSession!=null && fileNameSession!=null){
				Long actualDifference=generationTime-generationTimeSession;
				if(fileName.equals(fileNameSession)){
					Long allowedDiff=Long.parseLong(AppBaseConstant.GENERATION_DIFFERENCE);
					Long longTime=(new Date()).getTime();
					Long timePassedFromGeneration=longTime-generationTimeSession;
					if(allowedDiff<actualDifference || allowedDiff<timePassedFromGeneration){
						validatorUtil.reject(errors, "invalid.file", "File is to be uploaded within 5 mins of generation. "
								+ "Kindly regenerate the file and upload its signed file");
					}
				}else{
					validatorUtil.reject(errors, "invalid.file", "Invalid File, Please check the file you have uploaded");
				}
			}else{
				validatorUtil.reject(errors, "invalid.file", "Please Generate Document First And Then Upload Its Signed Copy");
			}
			
		}catch(Exception ex){
			validatorUtil.reject(errors,"invalid.file",ex.getMessage());
			 log.error(ex.toString());
		}
		
	}
	private Long getFileGenerationTime(AttachmentDto attachment,Errors errors){
	   Long generationTime=null;
	   try{
		String isSftpEnabled=sysConfiguratorService.getSystemPropertyConfigurator(AppBaseConstant.EAT_SFTP_ENABLED);
		if("Y".equals(isSftpEnabled)){
            Map<String,Object> map=mediaService.getSFTPConnection();
            ChannelSftp channelSftp=(ChannelSftp) map.get("channelSftp");
            SftpATTRS attrs =channelSftp.stat(attachment.getPath()+AppBaseConstant.SFTP_FILE_SEPERATOR+attachment.getName());
			generationTime=(long) attrs.getMTime();
			}else{
				File file=new File(attachment.getPath()+attachment.getName());
				Path path=file.toPath();
				BasicFileAttributes attr=Files.readAttributes(path, BasicFileAttributes.class);
				generationTime=attr.creationTime().toMillis();
			}
	   }catch(Exception e){
		   validatorUtil.reject(errors,"invalid.file",e.getMessage());
		   log.error(e.toString());
	   }
	   return generationTime;
	}
	
}
