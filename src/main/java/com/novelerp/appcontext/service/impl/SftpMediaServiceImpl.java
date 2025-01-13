package com.novelerp.appcontext.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.SFTPUploadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.service.impl.MD5Security;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.FileUtil;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)
public class SftpMediaServiceImpl  extends AbstractMediaServiceImpl implements MediaService{
	static Logger log=LoggerFactory.getLogger(SftpMediaServiceImpl.class);
	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;
	
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;
	
	@Autowired
    private ServletContext context;
	@Autowired
	private SystemConfiguratorService sysConfigService;
	@Autowired
	private AppPropertyUtil propertyUtil;
	
	@Autowired
	private FileUtil fileUtil;
	
	
	@Override
	public String save(byte[] media,String fileName) {
		Map<String,Object> map=getSFTPConnection();
		String savedFilePath = null;
		if(map.isEmpty()){
			log.error("connection map is empty....");
			return null;
		}
		File file =null;
		ChannelSftp channelSftp=(ChannelSftp) map.get("channelSftp");
		Session session=(Session) map.get("session");
		FileInputStream fis=null;
		try {
			if (channelSftp != null && session != null) {
				log.info("getting destination path....");
				savedFilePath = getPathByFileType(channelSftp);
				if(savedFilePath!=null){
					channelSftp.cd(savedFilePath);
					file = writeByteArrayTo(media, fileName);
					fis=new FileInputStream(file);
					channelSftp.put(fis, fileName);
					log.info("file uploaded successfully....");
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}finally {
			delete(file);
			try {
				if(fis!=null){
				 fis.close();
				}
			} catch (Exception e) {
			    log.error(e.toString());
			}
			closeSFTPConnection(channelSftp,session);			
		}		
		return savedFilePath;
	}
	private SFTPUploadDto getSFTPCredential(){
		SFTPUploadDto dto=new SFTPUploadDto();
		/*String url=propertyUtil.getProperty(AppBaseConstant.SFTP_URL);
		String username=propertyUtil.getProperty(AppBaseConstant.SFTP_USERNAME);
		String password=propertyUtil.getProperty(AppBaseConstant.SFTP_PASSWORD);
		String port=propertyUtil.getProperty(AppBaseConstant.SFTP_PORT);
		dto.setUrl(url);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setPort(Integer.parseInt(port));*/
		Map<String,Object> param=AbstractServiceImpl.getParamMap("referenceCode",AppBaseConstant.SFTP_CONN_DETAILS);
		List<SystemConfiguratorDto> sysConfig=sysConfigService.findDtos("getSysConfigQueryForSFTP",param);
		if(CommonUtil.isCollectionEmpty(sysConfig)){
			log.error("sftp connection details not found");
			return dto;
		}
		for(SystemConfiguratorDto sysDto:sysConfig){
			if(AppBaseConstant.SFTP_URL.equals(sysDto.getValue())){
				dto.setUrl(sysDto.getName());
			}else if(AppBaseConstant.SFTP_USERNAME.equals(sysDto.getValue())){
				dto.setUsername(sysDto.getName());
			}else if(AppBaseConstant.SFTP_PASSWORD.equals(sysDto.getValue())){
				dto.setPassword(sysDto.getName());
			}else if(AppBaseConstant.SFTP_PORT.equals(sysDto.getValue())){
				dto.setPort(Integer.parseInt(sysDto.getName()));
			}
		}
		return dto;
	}
	@Override
	public Map<String,Object> getSFTPConnection(){
	    Map<String,Object> map=new HashMap<>();
		try{
			JSch jsch = new JSch();
			SFTPUploadDto dto=getSFTPCredential();
			Session session = jsch.getSession(dto.getUsername(),dto.getUrl(),dto.getPort());
			session.setPassword(dto.getPassword());

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			map.put("channelSftp", channelSftp);
			map.put("session", session);
			}catch (Exception e) {
				log.error(e.toString());
			}
		return map;
    }
	private void closeSFTPConnection(ChannelSftp channelSftp,Session session){
		if (channelSftp != null) {
            channelSftp.exit();
        }
        if (session != null) {
            session.disconnect();
        }
	}
	
	public String getPathByFileType(ChannelSftp channelSftp){
		
		HttpSession session= ContextServiceImpl.getCurrentSession();
		RoleDto role=contextService.getDefaultRole();
		SFTPUploadDto dto=(SFTPUploadDto) session.getAttribute("fileUpload");
		String path=sysConfigService.getPropertyConfigurator("eat.sftp.file.path.linux");
		path=validateSessionObject(dto,role,path);
		if(null!=path){
			log.info("creating path by attachment type....");
			path=createPath(path, channelSftp, dto.getAttachmentType());
		}
		if(null!=path){
			log.info("creating path by current date....");
			path=getPathForCurrentDate(path,channelSftp);
		}
		if(null!=path){
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_PARTNER_USER.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			    String partnerId=Long.toString(dto.getPartner().getbPartnerId());
			    log.info("creating path by vendor id....");
			    path=createPath(path,channelSftp,partnerId);
			}else{
				log.info("creating path for others....");
				path=createPath(path,channelSftp,AppBaseConstant.OTHER_ATTACHMENT);
			} 
		}
		return path;
	}
	private String validateSessionObject(SFTPUploadDto dto,RoleDto role,String path){
        if(path==null){
            log.error("base path is null found...");
 			return null;
        }else if(dto==null){
    	    log.error("sftp session object is null..");
			return null;
		}else if(dto.getAttachmentType()==null){
			 log.error("sftp attachment type is null..");
			return null;
		}else if(role==null || role.getValue()==null ){
			 log.error("session role details are null..");
			return null;
		}else if(dto.getPartner()==null || dto.getPartner().getbPartnerId()==null){
			log.error("session partner details are null..");
			return null;
		}
       return path;
	}
	public String getPathForCurrentDate(String path,ChannelSftp channelSftp){
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		if(null!=path){
		   path=createPath(path,channelSftp,year);
		}
		String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
		if(null!=path){
		   path=createPath(path,channelSftp,month);
		}
		String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		if(null!=path){
		   path=createPath(path,channelSftp,date);
		}
		return path;
	}
	public String createPath(String path,ChannelSftp channelSftp,String folder){
		if((null!=path && path!="") && (null!=folder && folder!="")){
			try{
				channelSftp.cd(path);
				if (folder.length() > 0){
					try {
						channelSftp.cd(folder);
						path = path+folder+AppBaseConstant.SFTP_FILE_SEPERATOR;
						log.info("path created for directory"+folder);
				     	}catch(SftpException e){
						try{
							channelSftp.mkdir(folder);
							channelSftp.cd(folder);
							path = path+folder+AppBaseConstant.SFTP_FILE_SEPERATOR;
							log.info("path created for directory"+folder);
						}catch(SftpException e1){
							log.error(e1.toString());
							return null;
						}
					}
				}
			}catch(Exception e){
				log.error(e.toString());
				return null;
			}
		}else{
			log.error("path or folder empty found");
		}
		return path;
	}
    
	@Override
	public void downloadFile(AttachmentDto dto,HttpServletRequest request,HttpServletResponse response) {
		 
		  BPartnerDto partner =contextService.getPartner();
		  OutputStream os = null;
		  BufferedInputStream bis=null;
		    Map<String,Object> map=getSFTPConnection();
			if(!map.isEmpty()){
			ChannelSftp channelSftp=(ChannelSftp) map.get("channelSftp");
			Session session=(Session) map.get("session");
		try {
			String physicalName = dto.getName() != null?dto.getName():dto.getFileName();			
			bis = new BufferedInputStream(channelSftp.get(dto.getPath()+physicalName));
			SftpATTRS attrs =channelSftp.stat(dto.getPath()+AppBaseConstant.SFTP_FILE_SEPERATOR+physicalName);
			     String mimeType = context.getMimeType(dto.getPath());
				  
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	
	              response.setContentType(mimeType);
	              response.addHeader("Content-Disposition", "attachment; filename=\"" + dto.getFileName()+"\"");
	              response.setContentLength((int)attrs.getSize());
	
	               os = response.getOutputStream();
	            
	               if(!"Y".equals(dto.getIsDocEncrypted()))
	               {
		              byte[] buffer = new byte[4096];
		              int b = -1;
		
		              while ((b = bis.read(buffer)) != -1) {
		                  os.write(buffer, 0, b);
		              }
	               }else{
	              
		              byte[] buffer=new byte[(int) attrs.getSize()];
		              bis.read(buffer);
		              buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
		              os.write(buffer);
	               }
		}catch(Exception e) {
			log.error(e.toString());
		}finally {
			try{
				bis.close();
	            os.close();
	            closeSFTPConnection(channelSftp,session);
			}catch (Exception e) {
				log.error("Exception while closing stream", e);
			}
		}
	   }else{
		   log.error("sftp connection map is empty...");
	   }
	}
	
	@Override
	public byte[] getBISFromAttachment(AttachmentDto dto) {
		 if(null!=dto){
			 String physicalName = dto.getName() != null?dto.getName():dto.getFileName();
			 return getBISFromAttachment(dto.getPath()+physicalName);
		 }else{
			   log.error("sftp connection map is empty...");
			   return null;
		 }
	}
	
	@Override
	public byte[] getBISFromAttachment(String file) {
		 
		  BufferedInputStream bis=null;
		  Map<String,Object> map=getSFTPConnection();
		  if(!map.isEmpty()){
			  ChannelSftp channelSftp=(ChannelSftp) map.get("channelSftp");
			  Session session=(Session) map.get("session");
			  try {
				/*String physicalName = dto.getName() != null?dto.getName():dto.getFileName();*/
				bis = new BufferedInputStream(channelSftp.get(file));
				return IOUtils.toByteArray(bis);
			  }catch(Exception e) {
				log.error(e.toString());
			  }finally {
				try{
					bis.close();
		            closeSFTPConnection(channelSftp,session);
				}catch (Exception e) {
					log.error("Exception while closing stream", e);
				}
			  }
	   }else{
		   log.error("sftp connection map is empty...");
		   return null;
	   }
		return null;
	}
	
	@Override
	public String save(File file,String fileName) {
		byte[] bytes=fileUtil.getBytesFromFile(file);
		return save(bytes, fileName);
	}
}