package com.novelerp.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	private static final String DEFAULT_FORMAT = "yyyyMMdd hh:mm:ss";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DEFAUT_MAIL_FORMAT = "dd-MM-yyyy";

	private DateUtil() {

	}

	public static Date getParsedDate(String dateString, String format) {
		Date parsedDate = null;
		String dateFormat = format;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		/*
		 * if("Y".equals(Configuration.getInstance().getProperty(Constants.
		 * ENABLE_TIMEZONE_UTC))) sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 */
		try {
			parsedDate = sdf.parse(dateString);
		} catch (Exception e) {
			log.error("Error", e);
		}
		return parsedDate;
	}

	public static String covertDateFormat(String dateString, String currentFormat, String format) {
		Date parsedDate = null;
		String dateFormat = format;
		String outputDateString = null;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		SimpleDateFormat curDateFormat = new SimpleDateFormat(currentFormat);
		/*
		 * if("Y".equals(Configuration.getInstance().getProperty(Constants.
		 * ENABLE_TIMEZONE_UTC))){
		 * curDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); }
		 */
		try {
			parsedDate = curDateFormat.parse(dateString);
			outputDateString = sdf.format(parsedDate);
		} catch (Exception e) {
			log.error("Error", e);
		}

		return outputDateString;
	}

	// added by sravan
	public static String getTimeStampToString(Timestamp timeStamp) {

		String strDate = "";
		if (timeStamp != null)

			try {
				SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.s");// dd/MM/yyyy
				String strDate1 = sdfDate.format(timeStamp);

				strDate = strDate1;
			} catch (Exception e) {
				log.error("Error", e);
			}

		return strDate;
	}

	public static String getDateString(Date date, String format) {
		if(date==null){
			date = new Date();
		}
		String parsedDate = null;
		String dateFormat = format;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			parsedDate = new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			log.error("Error", e);
		}

		return parsedDate;
	}

	public static void main(String d[]) {
		DateUtil date =  new DateUtil();
		System.out.println(date.getDateString(new Date(), DEFAULT_DATE_FORMAT));;
	}
	private Long createRandomInteger(int aStart, long aEnd){
		Random random = new Random();
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    long range = aEnd - (long)aStart + 1;
	    long fraction = (long)(range * random.nextDouble());
	    long randomNumber =  fraction + (long)aStart;    
	    return randomNumber;

	  }
	  public static Date getDateWithoutTime(Date date)
	  {
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	cal.set(Calendar.HOUR_OF_DAY, 0);
	    	cal.set(Calendar.MINUTE, 0);
	    	cal.set(Calendar.SECOND, 0);
	    	cal.set(Calendar.MILLISECOND, 0);
	    	date = cal.getTime();
	    	return date; 
	  }
	
	 public static int getYearsDiff(Date date, Date validTo){
		  Calendar a= Calendar.getInstance();
		    a.setTime(date);
		    Calendar b = Calendar.getInstance();
		    b.setTime(validTo);
      int diff = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);
      if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
          diff++;
      }
      return diff;
    }
	  /*public static int getRenewalYearDifference(Date date, Date validTo) {
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(date);
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(validTo);
	    int diffInYears=0;
	    int years = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	    if(date.compareTo(validTo)<=0)
	    {
	    	diffInYears=diffInYears+1;
	    }else if(date.compareTo(validTo)>0 && (years==0 || years==1))
	    {
	    	diffInYears=diffInYears+2;		    	
	    }else if(date.compareTo(validTo)>0 && years>0){
	    	cal2.add(Calendar.YEAR, -1);
	    	diffInYears = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	    }
	return diffInYears;
}*/
	  /*public static int getYearsForValidTo(Date date,Date validTo){
		    Calendar cal1 = Calendar.getInstance();
		    cal1.setTime(date);
		    Calendar cal2 = Calendar.getInstance();
		    cal2.setTime(validTo);
		    int diffInYears=0;
		    int years = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		    if(date.compareTo(validTo)<=0 || (date.compareTo(validTo)>0 && (years==0 || years==1)))
		    {
		    	diffInYears=diffInYears+1;
		    }else if(date.compareTo(validTo)>0 && years>0){
		    	cal2.add(Calendar.YEAR, -1);
		    	diffInYears = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		    }
		    return diffInYears;
	 }*/
	
	/*public static void main1(String[] a){
			Date d1=null;
			Date d2=null;
			Date d3=null;
			Date d4=null;
			Date d5=null;
			Date d6=null;
	    	 try {
	    		d5=new SimpleDateFormat("dd-MM-yyyy").parse("31-07-2018 12:00:00.000000000 AM");
				d1=new SimpleDateFormat("dd-MM-yyyy").parse("30-07-2018 12:00:00.000000000 AM");
				d2=new SimpleDateFormat("dd-MM-yyyy").parse("01-08-2018 12:00:00.000000000 AM");
				d3=new SimpleDateFormat("dd-MM-yyyy").parse("20-07-2020 12:00:00.000000000 AM");
				d4=new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2018 12:00:00.000000000 AM");
				d6=new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2019 12:00:00.000000000 AM");
			}catch (ParseException e){
				e.printStackTrace();
			}
	    	 System.out.println("d1-"+DateUtil.getYearsDiff(d1, d5));
	    	 System.out.println("d2-"+DateUtil.getYearsDiff(d2, d5));
	    	 System.out.println("d3-"+DateUtil.getYearsDiff(d3, d5));
	    	 System.out.println("d4-"+DateUtil.getYearsDiff(d6, d4));
	}*/
	 
	 public static String getYear(Date date){
		 return new SimpleDateFormat("YYYY").format(new Date());
	 }


	 
 }
