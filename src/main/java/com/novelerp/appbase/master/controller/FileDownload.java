package com.novelerp.appbase.master.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.SearchPObySAPDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

@Controller
@RequestMapping("/rest")
public class FileDownload {

	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;

	@Autowired
	private AttachmentService attachmentService;
	
	private Logger log =  LoggerFactory.getLogger(FileDownload.class); 
	
    @Autowired
    /*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
    private MediaService mediaService;
    
	@RequestMapping(value="/downloadAttachment",method = RequestMethod.POST)
	public @ResponseBody String doDownload(HttpServletRequest req,HttpSession session,HttpServletResponse resp) {//,@PathVariable("attachmentId") Long id
		AttachmentDto attachment=null;
		try {
			
			//attachment=attachmentService.findOne(id);
			//resp.setContentType(mimeType);
			attachment=(AttachmentDto)session.getAttribute("Attachment");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + attachment.getFileName()+"\"");
			resp.getOutputStream().write(attachment.getMedia());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return "Success";
	}
	
/*	   @GetMapping("/download/{attachmentId}")
	   public ResponseEntity<ByteArrayResource> download(@PathVariable ("attachmentId") Long attachmentId) throws IOException {
		  byte[] data = null;
		  AttachmentDto attachment =  attachmentService.findDto(attachmentId);
		  String filePath = attachment.getPath();
		  String fileName =  attachment.getFileName();

		  String fileName= attachmentService.readFile(attachmentId, data);
	      ByteArrayResource resource = new ByteArrayResource(data);
	      return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                  "attachment;filename=" + fileName)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(data.length)
	            .body(resource);
	   }
*/
	   @GetMapping("/download/{attachmentId}")
	   public void download(@PathVariable ("attachmentId") Long attachmentId,HttpServletRequest request, HttpServletResponse response) {
		  AttachmentDto attachment =  attachmentService.findDto(attachmentId);
		  if(attachment!=null){
			  mediaService.downloadFile(attachment,request,response);
		  }else{
			  log.error("Attachment record not found....");
		  }  
	   }
	   
	   @RequestMapping(value="/getFile/{attachmentId}", method=RequestMethod.POST)
	   public @ResponseBody CustomResponseDto getFile(@PathVariable ("attachmentId") Long attachmentId) {
		   CustomResponseDto resp =new CustomResponseDto();
		   AttachmentDto attachment =  attachmentService.findDto(attachmentId);
		   byte[] file=null;
			  if(attachment!=null){
				  file=  mediaService.getByteArrayByFile(attachment.getName());
				  resp.addObject("RESULT", true);
				  resp.addObject("ImageFile", file);
			  }else{
				  resp.addObject("RESULT", false);
				  resp.addObject("MESSAGE", "IMAGE NOT FOUND");
			  }  
			  return resp;
	   }
	   
	   @GetMapping("/downloadProtected/{attachmentId}")
	   public void downloadProtected(@PathVariable ("attachmentId") Long attachmentId
			   ,HttpServletRequest request, HttpServletResponse response) {
		  AttachmentDto attachment =  attachmentService.findDto(attachmentId);
		  if(attachment!=null){
			  mediaService.downloadFile(attachment,request,response);
		  }else{
			  log.error("Attachment record not found....");
		  }
		 /* String filePath = attachment.getPath();
		  String fileName =  attachment.getFileName();
		  String name =  attachment.getName();
		  BPartnerDto partner =contextService.getPartner();
		  String physicalName = name != null?name:fileName;
		  
		  File file = new File(filePath+File.separator+physicalName);*/
		  
		 /* OutputStream os = null;
          FileInputStream fis = null;

		  if (file.exists()) {
			  try{
				  String mimeType = context.getMimeType(file.getPath());
				  
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	
	              response.setContentType(mimeType);
	              response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
	              response.setContentLength((int) file.length());
	
	               os = response.getOutputStream();
	               fis = new FileInputStream(file);
	              byte[] buffer = new byte[4096];
	              int b = -1;
	              while ((b = fis.read(buffer)) != -1) {
	            	  buffer=
	                  os.write(buffer, 0, b);
	              }
	               
	              byte[] buffer=new byte[(int) file.length()];
	              fis.read(buffer);
	              buffer=MD5Security.decryptDoc(buffer, SEC_KEY, IV_PREFIX+partner.getPanNumber());
	              os.write(buffer);
	              
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
		  }*/	
		  
	   }   
	   @GetMapping("/downloadWithTimeStamp/{attachmentId}")
	   public void downloadWithTimeStamp(@PathVariable ("attachmentId") Long attachmentId,HttpServletRequest request, HttpServletResponse response) {
		  AttachmentDto attachment =  attachmentService.findDto(attachmentId);
		  if(attachment!=null){
			  CommonUtil.resetReportFileName(request);
			  CommonUtil.getSessionFileName(request,"SIGNED_",attachment.getFileName());
			 /* attachment.setIsTimestampDoc("Y");
			  attachment.setSessionFileName(fileName);*/
			  mediaService.downloadFile(attachment,request,response);
		  }else{
			  log.error("Attachment record not found....");
		  }
	   }
	   
	   @GetMapping("/downloadManual/{type}")
	   public void download(@PathVariable ("type") String type,HttpServletRequest request, HttpServletResponse response) {
		   AttachmentDto attachment = new AttachmentDto();
		   if(type.equals("Vendor")){
			   attachment.setName("Alkyl Vendor Manual.pdf");
			   attachment.setFileName("Alkyl Vendor Manual.pdf");
			   attachment.setPath("/PRD/DOCS/UserManual/");
			   attachment.setIsDocEncrypted("N");
		   }else{
			   attachment.setName("Alkyl User Manual.pdf");
			   attachment.setFileName("Alkyl User Manual.pdf");
			   attachment.setPath("/PRD/DOCS/UserManual/");
			   attachment.setIsDocEncrypted("N");
		   }
		 
		  if(attachment!=null){
			  mediaService.downloadFile(attachment,request,response);
		  }else{
			  log.error("Attachment record not found....");
		  }  
	   }
	  
	   
	   
@GetMapping(value = "/downloadPO/{pono}")
		public  @ResponseBody AttachmentDto downloadPO(@PathVariable("pono") String pono,HttpServletRequest httprequest,HttpServletResponse response) {
			
			
			
//			Map<String, Object> resp=SearchPO(pono);
			
			String home = System.getProperty("user.home");
			
			 TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	             public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                 return null;
	             }
	             public void checkClientTrusted(X509Certificate[] certs, String authType) {
	             }
	             public void checkServerTrusted(X509Certificate[] certs, String authType) {
	             }
	         }
	     };
	     

	     // Install the all-trusting trust manager
	     SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("SSL");
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     try {
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} catch (KeyManagementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	     // Create all-trusting host name verifier
	     HostnameVerifier allHostsValid = new HostnameVerifier() {
	         public boolean verify(String hostname, SSLSession session) {
	             return true;
	         }
	     };

	     // Install the all-trusting host verifier
	     HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
		//String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_22?sap-client=110&pono=3100000001&pono1=3100000030";
	  //   String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_26?sap-client=110&PO="+pono; 
	     String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_26?sap-client=100&PO="+pono; 
	     // snippet ends
			System.out.println(url);
//			List<String> list = new ArrayList<>();
		
			URLConnection request = null;
			try {
				URL u = new URL(url);
				request = u.openConnection();
			    request.setRequestProperty("Accept", "application/pdf"); 
				request.connect();
				InputStream is = request.getInputStream();
				
				String filename = "PO-"+pono+".pdf";  
				FileOutputStream fileOutput = new FileOutputStream(home+"/Downloads/"+filename);
				/* use binary I/O to prevent line based operation messing with the encoding.*/
				byte[] buf = new byte[2048];
				int b_read = 0;
				while ( (b_read = is.read(buf)) > 0) {
				    fileOutput.write(buf, 0, b_read);
				}
				fileOutput.flush();
				//closed the output stream
				fileOutput.close();
				is.close();
				
				File file = new File(home+"/Downloads/" + filename);
				String name =filename;
				String originalFileName = file.getName();
				String contentType = "application/octet-stream";

				InputStream inputStream = new FileInputStream(file);
				byte[] bytes = StreamUtils.copyToByteArray(inputStream);

				MultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, bytes);
				AttachmentDto attachment = new AttachmentDto();	
				attachment=attachmentService.addAttachment(multipartFile);
				if(!attachment.getResponse().isHasError()){
					attachment= attachmentService.save(attachment);
				}
				
				return attachment;
			} catch (MalformedURLException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (request != null) {
					try {
						((HttpURLConnection) request).disconnect();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
			
			return null;
		}
}
