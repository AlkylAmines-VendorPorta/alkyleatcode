package com.novelerp.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

@Component
public class FileUtil {
	
	private Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * Return the mime type of the file
	 * @param file
	 * @return
	 */
	public String getType(File file){
		
		Tika tika = new Tika();
		String type = null;
		try{
			type = tika.detect(file);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return type;
	}
	
	/**
	 * Check if file is valid.
	 * @param file
	 * @return true if file exists and its size is greater than zero byte, false otherwise.
	 */
	public boolean isValidFile(File file){
		boolean isValid =  false;
		try{
			double bytes =0;
			if(file.exists()){
				 bytes = file.length();
			}else{
				log.error("File Does not exist");
			}
			if(bytes>0){
				isValid = true;
			}else{
				log.error("File Empty");
			}
		}catch (Exception e) {
			log.error("Error", e);
		}
		return isValid;
	}
	
	public boolean isValidByteArray(byte[] byteArray){
		boolean isValid =  false;
		try{
			double bytes =0;
			if(null!=byteArray){
				 bytes = byteArray.length;
			}else{
				log.error("File Does not exist");
			}
			if(bytes>0){
				isValid = true;
			}else{
				log.error("File Empty");
			}
		}catch (Exception e) {
			log.error("Error", e);
		}
		return isValid;
	}
	
/*	public static void main(String[] args) throws Exception {
		File file = new File("D:\\eatApp\\docs\\163.pdf");
		FileUtil fileUtil = new FileUtil();
		
		System.out.println(fileUtil.getType(file));
	}*/
	
	/**
	 * Check if file is PDF
	 * @param file
	 * @return return true if PDF, false otherwise.
	 */
	public boolean isPdf(File file){
		boolean isPdf =  false;
		String fileType = getType(file);
		if(fileType.contains("pdf")){
			isPdf =true;
		}
		return isPdf;		
	}
	
	public boolean isPdf(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			return isPdf(bytes);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isPdf(byte[] bytes){
		if(0x25==bytes[0] && 0x50==bytes[1] && 0x44==bytes[2] && 0x46==bytes[3] ){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isJpeg(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			return isJpeg(bytes);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isJpeg(byte[] bytes){
		/*if(0xFF==bytes[0] && 0xD8==bytes[1] ){*/
		int i = 0;
		if((bytes [i] & 0xFF) == 0xFF && (bytes[i+1] & 0xFF) == 0xD8 && (bytes[i+2] & 0xFF) == 0xFF 
		           && (bytes[i+3] & 0xFF) == 0xE0) {
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isZip(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			return isZip(bytes);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isZip(byte[] bytes){
		if(0x50==bytes[0] && 0x4B==bytes[1] ){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isRar(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			return isZip(bytes);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isRar(byte[] bytes){
		if(0x52==bytes[0] && 0x61==bytes[1] && 0x72==bytes[2] && 0x21==bytes[3] ){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isSigned(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			return isZip(bytes);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isSigned(byte[] bytes){
		if(0x30==bytes[0] ){
			return true;
		}else{
			return false;
		}	
	}
	
	public byte[] getBytesFromFile(File file) {
		if(file!=null && file.exists()){
			byte[] bytes=new byte[(int) file.length()];
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				fis.read(bytes);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			return bytes;
		}else{
			return null;
		}
		
	}
	
	public boolean writeBytesToFile(File file,byte[] bytes) throws IOException{
		if(file!=null && file.exists()){
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				log.error(e.getMessage());
				return false;
			} catch (IOException e) {
				log.error(e.getMessage());
				return false;
			}finally{
				fos.close();
			}
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean cleanFile(String filePath){
		PdfReader reader =null;
		PdfStamper stamper =null;
		try {
			reader = new PdfReader(filePath);
			int n = reader.getNumberOfPages();
	        List<Integer> selectPages=new ArrayList<Integer>();
	        for (int i = 0; i < n; ) {
	        	int blankPageSize=reader.getPageContent(++i).length;
	        	if(blankPageSize>30){
	        		selectPages.add(i);
	        	}
	        }
	        reader.selectPages(selectPages);
	        stamper = new PdfStamper(reader, new FileOutputStream(filePath));
	        for (int i = 0; i < n; ) {
        		stamper.getOverContent(++i);
	        }
		} catch (Exception e) {
			log.error("Error", e);
			return false;
		}finally{
			try {
			    stamper.close();
		        reader.close();	    	
	       } catch (Exception e2) {
	    	   log.error("Error", e2);
	    	   return false;
	       }
		}
		return true;
	}
	
	public PdfReader cleanFile(PdfReader reader){
		int n = reader.getNumberOfPages();
		 List<Integer> selectPages=new ArrayList<Integer>();
	        for (int i = 0; i < n; ) {
	        	int blankPageSize;
				try {
					blankPageSize = reader.getPageContent(++i).length;
					if(blankPageSize>30){
		        		selectPages.add(i);
		        	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        reader.selectPages(selectPages);
			return reader;
	}
	
	public boolean delete(String path){
		if(null==path){
			return false;
		}else{
			File file=new File(path);
			if(file.exists()){
				return file.delete();
			}else{
				return false;
			}
		}
	}
	
	public File moveFile(File file, String path, String fileName){
		FileOutputStream fOut = null;
		File outFile=new File(path+fileName);
		try{
			fOut =  new FileOutputStream(outFile);
			fOut.write(getBytesFromFile(file));
		}catch (Exception e) {
			log.error("Exception", e);
			return null;
		}finally {
			closeOutputStream(fOut);
		}
		return outFile;
	}
	
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

	
}
