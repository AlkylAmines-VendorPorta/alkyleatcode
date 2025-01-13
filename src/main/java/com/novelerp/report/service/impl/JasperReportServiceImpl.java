package com.novelerp.report.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.DBUtil;
import com.novelerp.core.util.OSValidator;
import com.novelerp.report.service.ReportService;

import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class JasperReportServiceImpl implements ReportService{

	private static final Logger log =  LoggerFactory.getLogger(JasperReportServiceImpl.class);

	@Autowired
	private AppPropertyUtil propertyUtil;
	
	@Autowired
	private DBUtil dbUtil;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	@Override
	public String generateReport(String reportName, Map<String, Object> params, OutputStream output, String outputFormat) {
		long timestamp= System.currentTimeMillis();
		String reportFile = reportName+".jasper";
		String filePath =  getBaseDir()+ reportFile;
		InputStream input = getInputStream(filePath); 
		fillReportData(input, params, output, outputFormat);
		String outputFile = reportName +timestamp;	
		return outputFile;
	}

		
	public void fillReportData(InputStream input,  Map<String, Object> params, OutputStream output, String outputFormat) {
		
		Connection connection = dbUtil.getDBConnection();
		try{
			JasperRunManager.runReportToPdfStream(input, output, params, connection);
		}catch (Exception e) {
			log.error("Error while generating Report", e);
		}finally {
			dbUtil.close(connection);
			closeInputStream(input);
		}
	}

	
	public InputStream getInputStream(String filePath){
		InputStream in = null;
		try{
			in = new FileInputStream(filePath);
		}catch (FileNotFoundException e) {
			log.error("Error while reading file", e);
		}
		return in;
	}
	
	public void closeInputStream(InputStream input){
		try{
			input.close();
		}catch (IOException e) {
			log.error("Error while closing", input);
		}
	}
	
	public void closeOutputStream(OutputStream output){
		try{
			output.close();
		}catch (IOException e) {
			log.error("Error while closing", output);
		}
	}

	public String getBaseDir(){
		String baseDir=null;		
		if(OSValidator.isWindows()){
			/*baseDir = propertyUtil.getProperty("eat.report.path.windows");*/
			baseDir =sysConfiguratorService.getPropertyConfigurator("eat.report.path.windows");
		}else if (OSValidator.isUnix()){
			/*baseDir = propertyUtil.getProperty("eat.report.path.linux");	*/	
			baseDir =sysConfiguratorService.getPropertyConfigurator("eat.report.path.linux");
		}
		return baseDir;
	}


	@Override
	public String generateReport(String reportName, Map<String, Object> params, String outputDir, String outputFormat) {
		StringBuilder filePathBuilder =  new StringBuilder();
		filePathBuilder.append(outputDir)
			.append(reportName)
			.append("_")
			.append(System.currentTimeMillis())
			 .append(".")
				.append(outputFormat);
		String outputFile = filePathBuilder.toString();
		OutputStream outputStream = null;
		try{
			outputStream =  new FileOutputStream(outputFile);
			generateReport(reportName, params, outputStream, outputFormat);
		}catch (Exception e) {
			log.error("Exception", e);
			return null;
		}finally {
			closeOutputStream(outputStream);
		}
		return outputFile;
	}
	
}
