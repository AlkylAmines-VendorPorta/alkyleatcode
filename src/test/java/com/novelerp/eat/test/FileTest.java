/**
 * @author Ankush
 */
package com.novelerp.eat.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String leftPadded = StringUtils.leftPad("" + 1111, 7, "0");
		System.out.println(leftPadded);
		
	}
	
	
	/*private static void checkFileExtension(){
	MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile("tb_1530718797819.pdf (1).sig", new FileInputStream(new File("C:\\Users\\Lenovo\\Downloads\\tb_1530718797819.pdf (1).sig")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkPdf(multipartFile);	
		
	}*/
	
	private static boolean checkPdf(MultipartFile multipartFile){
		try{
			
			byte[] bytes=multipartFile.getBytes();
			if(0x30==bytes[0] && -126==bytes[1] ){
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*private static void checkLastModified(){
		Path file=Paths.get("C:\\Users\\Lenovo\\Downloads\\cb_1530722218958.pdf");
		System.out.println(file.lastModified());
		System.out.println(System.currentTimeMillis() +" "+ (new Date()).getTime());
		try{
			BasicFileAttributes attr=Files.readAttributes(file,BasicFileAttributes.class);
			System.out.println(attr.creationTime().toMillis());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/

}
