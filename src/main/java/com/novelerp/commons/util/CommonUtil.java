package com.novelerp.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.appbase.util.AppBaseConstant;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class CommonUtil {

	private static final Logger LOG =  LoggerFactory.getLogger(CommonUtil.class);
	private CommonUtil(){
		
	}
	/**
	 * 
	 * @param object - String object to check against
	 * @return true if String is null or blank after trimming space, false otherwise
	 */
	public static boolean isStringEmpty(String object){
		boolean empty =false;
		if(object==null || object.trim().length() ==0)
			empty =true;
		return empty;
	}
	
	/**
	 * 
	 * @param list
	 * @return true if list is null or having size 0
	 */			
	public static boolean isListEmpty(List<?> list){
		boolean empty =false;
		if(list == null || list.isEmpty())
			empty=true;
		return empty;
	}
	

	/**
	 * 
	 * @param list
	 * @return true if list is null or having size 0
	 */			
	public static boolean isCollectionEmpty(Collection<?> collection){
		boolean empty =false;
		if(collection == null || collection.isEmpty())
			empty=true;
		return empty;
	}

	/**
	 * 
	 * @param number 
	 * @return -1 if number is less than zero, 0 if number is equal to zero and 1 if number is greater than zero
	 */
	public static int compareToZero(BigDecimal number){		
		BigDecimal inputNumber = number.setScale(2, RoundingMode.HALF_UP);
		return inputNumber.compareTo(new BigDecimal("0"));
	}

	/**
	 * covert string to number and then compare it with Zero.
	 * @param number 
	 * @return -1 if number is less than zero, 0 if number is equal to zero and 1 if number is greater than zero
	 */

	public static int compareToZero(String number){
		
		if(number == null)
			throw new NumberFormatException("Null value");
		BigDecimal inputNumber =null;
		
		try{
			inputNumber = new BigDecimal(number);	
		}catch(NumberFormatException e){
			
			throw new NumberFormatException("Invalid String ");
		}
			
		
		return compareToZero(inputNumber);
	}
	
	public static boolean isZeroOrLess(String number){
			int result =  compareToZero(number);
			return result==-1||result==0?true:false;
		
	}
	
	public static <T> T castObject(Object obj, Class<T> type){
		T t =null;
		
		try{
			t = type.cast(obj);
		}catch(ClassCastException e){
			LOG.error("Exception", e);
		}
		
		return t;
	}
	/**
	 * 
	 * @param number
	 * @return BigDecimal value of string, or 0 in case of error/ exception
	 */
	
	public static BigDecimal getBDFromString(String number){
		
		BigDecimal value = BigDecimal.valueOf(0.0);
		
		try{
		
			value = new BigDecimal(number);
		}catch(Exception e){
			LOG.error("Conversion ERROR: " ,e);
		}
		return value;
	}
	
	/**
	 * check if two Long objects are equal
	 * @param first
	 * @param second
	 * @return true if value of first is equal to second, false otherwise.
	 */
	
	public static boolean isEqual(Long first,  Long second){
		if(first==null && second == null){
			return true;
		}
		else if ((first==null && second !=null) || (first!=null && second ==null)){
			return false;
		}
		else if (first.compareTo(second)==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static String getReportFileName(HttpServletRequest request,String prefix){
		Long creationTime=System.currentTimeMillis();
		String fileName=prefix+creationTime+".pdf"; 
		HttpSession session=request.getSession(false);
		session.setAttribute(AppBaseConstant.GENERATED_FILE_NAME, fileName);
		session.setAttribute(AppBaseConstant.GENERATION_TIME, creationTime);
		return fileName;
	}
	
	public static void resetReportFileName(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		session.setAttribute(AppBaseConstant.GENERATED_FILE_NAME, null);
		session.setAttribute(AppBaseConstant.GENERATION_TIME, null);
	}
	public static String getSessionFileName(HttpServletRequest request,String prefix,String fileName){
		Long creationTime=System.currentTimeMillis();
		String[] fileParts=fileName.split(".sig");
		String sessionFileName=fileParts[0].toString();
		/*String sessioFileName=prefix+creationTime+"."+getFileExtension(fileName);;*/ 
		HttpSession session=request.getSession(false);
		session.setAttribute(AppBaseConstant.GENERATED_FILE_NAME, sessionFileName);
		session.setAttribute(AppBaseConstant.GENERATION_TIME, creationTime);
		return fileName;
	}
	
	public static String getObjectJson(Object obj) throws JsonProcessingException{
		 ObjectMapper objJson = new ObjectMapper();
    	 String json;
		try {
			json = objJson.writeValueAsString(obj);
			json=json.replaceAll("null", "\"\"");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		 return json;
	}
	
	public static byte[] getObjectByteArray(Object obj) throws JsonProcessingException{
		 ObjectMapper objJson = new ObjectMapper();
   	 String json;
		try {
			json = objJson.writeValueAsString(obj);
			json=json.replaceAll("null", "\"\"");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		 return json.getBytes();
	}
	
	public static boolean isSetEmpty(Set<?> set){
		boolean empty =false;
		if(set == null || set.isEmpty())
			empty=true;
		return empty;
	}
}

