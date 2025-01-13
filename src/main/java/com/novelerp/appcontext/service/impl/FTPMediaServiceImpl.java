package com.novelerp.appcontext.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
@Service(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
public class FTPMediaServiceImpl  extends AbstractMediaServiceImpl implements MediaService{
	
	static Logger log=LoggerFactory.getLogger(SftpMediaServiceImpl.class);
	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;
	
	private SFTPUploadDto ftpCredentials;
	
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
		FTPClient ftpClient = getFTPConnection();
		String savedFilePath = null;
		if(!ftpClient.isConnected()){
			log.error("connection map is empty....");
			return null;
		}
		InputStream inputStream = null;
        OutputStream outputStream = null;
		try {
			if (ftpClient != null) {
				log.info("getting destination path....");
				savedFilePath = getPathByFileType(ftpClient);
				if(savedFilePath!=null){
					
					ftpClient.changeWorkingDirectory(savedFilePath);
					inputStream = new ByteArrayInputStream(media);
					if(!ftpClient.sendNoOp()){
						ftpClient = getFTPConnection();
					}
					/*outputStream = ftpClient.storeFileStream(fileName);
					byte[] bytesIn = new byte[4096];
					int read = 0;
					while ((read = inputStream.read(bytesIn)) != -1) {
						outputStream.write(bytesIn, 0, read);				
					}*/
					ftpClient.storeFile(fileName, inputStream);
					log.info("file uploaded successfully....");
					return savedFilePath;
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}finally {
			try {
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (Exception e) {
			    log.error(e.toString());
			}
			try {
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (Exception e) {
			    log.error(e.toString());
			}
			closeFTPConnection(ftpClient);			
		}		
		return savedFilePath;
	}
	
	@Override
	public boolean writeByteArrayTo(byte[] media,String savedFilePath,String fileName) {
		FTPClient ftpClient = getFTPConnection();
		if(!ftpClient.isConnected()){
			log.error("connection map is empty....");
			return false;
		}
		InputStream inputStream = null;
        OutputStream outputStream = null;
		try {
			if (ftpClient != null) {
				log.info("getting destination path....");
				if(savedFilePath!=null){
					
					ftpClient.changeWorkingDirectory(ftpCredentials.getPath()+savedFilePath);
					inputStream = new ByteArrayInputStream(media);
					if(!ftpClient.sendNoOp()){
						ftpClient = getFTPConnection();
					}
					/*outputStream = ftpClient.storeFileStream(fileName);
					byte[] bytesIn = new byte[4096];
					int read = 0;
					while ((read = inputStream.read(bytesIn)) != -1) {
						outputStream.write(bytesIn, 0, read);				
					}*/
					
					/*boolean completed = ftpClient.completePendingCommand();
					if (completed) {
						System.out.println("The second file is uploaded successfully.");
					};*/
					log.info("file uploaded successfully....");
					return ftpClient.storeFile(fileName, inputStream);
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}finally {
			try {
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (Exception e) {
			    log.error(e.toString());
			}
			try {
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (Exception e) {
			    log.error(e.toString());
			}
			closeFTPConnection(ftpClient);			
		}		
		return false;
	}
	
	
	@Override
	public SFTPUploadDto getCredentials(){
		SFTPUploadDto dto=new SFTPUploadDto();
		
		Map<String,Object> param=AbstractServiceImpl.getParamMap("referenceCode",AppBaseConstant.FTP_CONN_DETAILS);
		List<SystemConfiguratorDto> sysConfig=sysConfigService.findDtos("getSysConfigQueryForSFTP",param);
		if(CommonUtil.isCollectionEmpty(sysConfig)){
			log.error("ftp connection details not found");
			return dto;
		}
		for(SystemConfiguratorDto sysDto:sysConfig){
			if(AppBaseConstant.FTP_URL.equals(sysDto.getValue())){
				dto.setUrl(sysDto.getName());
			}else if(AppBaseConstant.FTP_USERNAME.equals(sysDto.getValue())){
				dto.setUsername(sysDto.getName());
			}else if(AppBaseConstant.FTP_PASSWORD.equals(sysDto.getValue())){
				dto.setPassword(sysDto.getName());
			}else if(AppBaseConstant.FTP_PORT.equals(sysDto.getValue())){
				dto.setPort(Integer.parseInt(sysDto.getName()));
			}else if(AppBaseConstant.FTP_PATH.equals(sysDto.getValue())){
				dto.setPath(sysDto.getName());
			}
		}
		return dto;
	}
	
	private void closeFTPConnection(FTPClient ftpClient){
		if (ftpClient != null) {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private String getPathByFileType(FTPClient ftpClient){
		
		RoleDto role=contextService.getDefaultRole();
		String path= ftpCredentials.getPath()+AppBaseConstant.FTP_DOCS;
		
		if(null!=path){
			log.info("creating path by attachment type....");
			path=createPath(path, ftpClient, "REGISTRATION");
		}
		if(null!=path){
			log.info("creating path by current date....");
			path=getPathForCurrentDate(path,ftpClient);
		}
		if(null!=path){
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_PARTNER_USER.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			    String partnerId=Long.toString(1l);
			    log.info("creating path by vendor id....");
			    path=createPath(path,ftpClient,partnerId);
			}else{
				log.info("creating path for others....");
				path=createPath(path,ftpClient,AppBaseConstant.OTHER_ATTACHMENT);
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
	public String getPathForCurrentDate(String path,FTPClient ftpClient){
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		if(null!=path){
		   path=createPath(path,ftpClient,year);
		}
		String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
		if(null!=path){
		   path=createPath(path,ftpClient,month);
		}
		String date=Integer.toString(Calendar.getInstance().get(Calendar.DATE));
		if(null!=path){
		   path=createPath(path,ftpClient,date);
		}
		return path;
	}
	public String createPath(String path,FTPClient ftpClient,String folder){
		if((null!=path && path!="") && (null!=folder && folder!="")){
			try{
				ftpClient.changeWorkingDirectory(path);
				boolean success = false;
				if (folder.length() > 0){
					success = ftpClient.changeWorkingDirectory(folder);
					if(success){
						path = path+folder+AppBaseConstant.SFTP_FILE_SEPERATOR;
					}else{
						ftpClient.makeDirectory(folder);
						ftpClient.changeWorkingDirectory(folder);
						path = path+folder+AppBaseConstant.SFTP_FILE_SEPERATOR;
						log.info("path created for directory "+folder);
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
		    FTPClient ftpClient = getFTPConnection();
			if(null != ftpClient){
		try {
			String physicalName = dto.getName() != null?dto.getName():dto.getFileName();			
			FTPFile file = ftpClient.mlistFile(dto.getPath()+physicalName);
			bis = new BufferedInputStream(ftpClient.retrieveFileStream(dto.getPath()+physicalName));
			     String mimeType = context.getMimeType(dto.getPath());
				  
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	
	              response.setContentType(mimeType);
	              response.addHeader("Content-Disposition", "attachment; filename=\"" + dto.getFileName()+"\"");
	              response.setContentLength((int) file.getSize());
	
	               os = response.getOutputStream();
	            
	               if(!"Y".equals(dto.getIsDocEncrypted()))
	               {
		              byte[] buffer = new byte[4096];
		              int b = -1;
		
		              while ((b = bis.read(buffer)) != -1) {
		                  os.write(buffer, 0, b);
		              }
	               }else{
	              
		              byte[] buffer=new byte[(int) file.getSize()];
		              bis.read(buffer);
		              buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
		              os.write(buffer);
	               }
		}catch(Exception e) {
			log.error(e.toString());
		}finally {
			try{
				bis.close();
			}catch (Exception e) {
				log.error("Exception while closing stream", e);
			}
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            closeFTPConnection(ftpClient);
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
		  FTPClient ftpClient = getFTPConnection();
		  if(null != ftpClient){
			  try {
				bis = new BufferedInputStream(ftpClient.retrieveFileStream(file));
				return IOUtils.toByteArray(bis);
			  }catch(Exception e) {
				log.error(e.toString());
			  }finally {
				try{
					bis.close();
		            closeFTPConnection(ftpClient);
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
	
	private FTPClient getFTPConnection(){
		FTPClient ftpClient = new FTPClient();
		try {
			ftpCredentials = getCredentials();
			
			ftpClient.connect(InetAddress.getByName(ftpCredentials.getUrl()));
			/*ftpClient.connect(InetAddress.getByName("192.168.20.57"));*/
			ftpClient.login(ftpCredentials.getUsername(), ftpCredentials.getPassword());
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			ftpClient.setConnectTimeout(1000*60*5);
			
			System.out.println(ftpClient.getBufferSize());
			ftpClient.setBufferSize(1024 * 1024);
			System.out.println(ftpClient.getBufferSize());
			
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return ftpClient;
    }
	
	@Override
	public String[] getFilesListAt(String path){
		FTPClient ftpClient = getFTPConnection();
		try {
			return ftpClient.listNames(ftpCredentials.getPath()+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new String[0];
	}
	
	@Override
	public boolean deleteFile(String obsoluteFilePath) {
		FTPClient ftpClient = getFTPConnection();
		boolean success = false;
		try {
			success = ftpClient.deleteFile(obsoluteFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}
}