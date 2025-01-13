package com.novelerp.appcontext.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.SFTPUploadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.service.impl.MD5Security;
import com.novelerp.core.util.FileUtil;
import com.novelerp.core.util.OSValidator;
@Service(AppBaseConstant.ABSTRACT_MEDIA_SERVICE_IMPL)
public class AbstractMediaServiceImpl implements MediaService{

	
	protected Logger log =  LoggerFactory.getLogger(AbstractMediaServiceImpl.class);

	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
    private ServletContext context;
	
	@Autowired
	private FileUtil fileUtil;
	
	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;
	@Override
	public String save(byte[] media, String fileName) {
		File file=writeByteArrayTo(media,fileName);
		if(file!=null && file.exists()){
			return file.getParent()+File.separator;
		}
		return null;
	}

	public File writeByteArrayTo(byte[] media, String fileName){
		String path = getFilePath();
		File file = new File(path+fileName);
		FileOutputStream fOut = null;
		try{
			fOut =  new FileOutputStream(file);
			fOut.write(media);
		}catch (Exception e) {
			log.error("Exception", e);
			return null;
		}finally {
			closeOutputStream(fOut);
		}
		return file;
	}
	public String getFilePath(){
		String filePath=null;		
		if(OSValidator.isWindows()){
			/*filePath = propertyReader.getProperty("eat.file.location.windows");*/
			filePath =sysConfiguratorService.getSystemPropertyConfigurator("eat.file.location.windows");

		}else if (OSValidator.isUnix()){
			/*filePath = propertyReader.getProperty("eat.file.location.linux");*/	
			filePath =sysConfiguratorService.getSystemPropertyConfigurator("eat.file.location.linux");

		}
		return filePath;
	}
	
	/**
	 * close Output stream
	 * @param out
	 */
	private void closeOutputStream(OutputStream out){
		if(out ==null){
			return;
		}
		try{
			out.flush();
			out.close();
		}catch (Exception e) {
			log.error("Exception", e);
		}
	}

	/**
	 * Close Input stream
	 * @param in
	 */
	public void closeInputStream(InputStream in){
		if(in ==null){
			return;
		}
		try{
			in.close();
		}catch (Exception e) {
			log.error("Exception", e);
		}
	}
	
	public boolean delete(File file){
		if(file == null){
			return false;
		}
		if(file.exists()){
			return file.delete();
		}
		return false;
	}

	@Override
	public void downloadFile(AttachmentDto attachment, HttpServletRequest request, HttpServletResponse response) {
		  String isDocEncrypt=attachment.getIsDocEncrypted();
		  String filePath = attachment.getPath();
		  String fileName =  attachment.getFileName();
		  String name =  attachment.getName();
		  BPartnerDto partner =contextService.getPartner();
		  String physicalName = name!=null?name:fileName;
		  
          File file=new File(filePath+File.separator+physicalName);
		  OutputStream os = null;
          FileInputStream fis = null;

		  if (file.exists()) {
			  try{
				  String mimeType = context.getMimeType(file.getPath());
				  
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	
	              response.setContentType(mimeType);
	              if("Y".equals(attachment.getIsTimestampDoc())){
	            	 String sessionFileName=attachment.getSessionFileName()==null?"":attachment.getSessionFileName(); 
	            	 response.addHeader("Content-Disposition", "attachment; filename=\"" + sessionFileName+"\"");
	              }else{
	            	 response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
	              }
	              
	              response.setContentLength((int) file.length());
	
	               os = response.getOutputStream();
	               fis = new FileInputStream(file);
	               if(!"Y".equals(isDocEncrypt))
	               {
	              byte[] buffer = new byte[4096];
	              int b = -1;
	
	              while ((b = fis.read(buffer)) != -1) {
	                  os.write(buffer, 0, b);
	              }
	               }else{
	              
	              byte[] buffer=new byte[(int) file.length()];
	              fis.read(buffer);
	              buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
	              os.write(buffer);
	               }
			  }catch (Exception e) {
				  log.error("Exception", e);
			}finally {
				try{
					fis.close();
		            os.close();
		            /*file.delete();*/
				}catch (Exception e) {
					log.error("Exception while closing stream", e);
				}
			}
		  }
	
		
	}

	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.MediaService#getBISFromAttachment(com.novelerp.appbase.master.dto.AttachmentDto)
	 */
	@Override
	public byte[] getBISFromAttachment(AttachmentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.MediaService#getBISFromAttachment(java.lang.String)
	 */
	@Override
	public byte[] getBISFromAttachment(String file) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.MediaService#save(java.io.File, java.lang.String)
	 */
	@Override
	public String save(File file, String fileName) {
		File fileNew=moveFile(file, fileName);
		if(fileNew!=null && fileNew.exists()){
			return fileNew.getParent()+File.separator;
		}
		return null;
	}

	public File moveFile(File file, String fileName){
		String path = getFilePath();
		FileOutputStream fOut = null;
		File outFile=new File(path+fileName);
		try{
			fOut =  new FileOutputStream(outFile);
			fOut.write(fileUtil.getBytesFromFile(file));
		}catch (Exception e) {
			log.error("Exception", e);
			return null;
		}finally {
			closeOutputStream(fOut);
		}
		return outFile;
	}
	
	public File moveFile(File file, String path, String fileName){
		FileOutputStream fOut = null;
		File outFile=new File(path+fileName);
		try{
			fOut =  new FileOutputStream(outFile);
			fOut.write(fileUtil.getBytesFromFile(file));
		}catch (Exception e) {
			log.error("Exception", e);
			return null;
		}finally {
			closeOutputStream(fOut);
		}
		return outFile;
	}

	@Override
	public Map<String, Object> getSFTPConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFTPUploadDto getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getByteArrayByFile(String file) {
		 BufferedInputStream bis=null;
			  try {
				  String path = getFilePath();
				  InputStream is = new FileInputStream(path+file);
				bis = new BufferedInputStream( is);
				return IOUtils.toByteArray(bis);
			  }catch(Exception e) {
				log.error(e.toString());
			  }finally {
				try{
					bis.close();
				}catch (Exception e) {
					log.error("Exception while closing stream", e);
				}
			  }
		return null;
	}

	@Override
	public boolean writeByteArrayTo(byte[] media, String path, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getFilesListAt(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFile(String ObsoluteFilePath) {
		// TODO Auto-generated method stub
		return false;
	}
}
