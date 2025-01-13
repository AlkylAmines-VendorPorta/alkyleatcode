package com.novelerp.report.service;

import java.io.OutputStream;
import java.util.Map;

/**
 * Reporting Service for the application
 * @author Vivek Birdi
 *
 */
public interface ReportService {
	
	
	/**
	 * Generate Report
	 * @param reportName - name of report.
	 * @param params -  Report Parameters.
	 * @param output-  Output stream.
	 * @param outputFormat - format in which out is required
	 * @return
	 */
	public String generateReport(String reportName, Map<String, Object> params,OutputStream output, String outputFormat);
	
	/**
	 * Generate report and store in outputDir
	 * @param reportName
	 * @param params
	 * @param outputDir
	 * @param outputFormat
	 * @return absolute path of generated file
	 */
	public String generateReport(String reportName, Map<String, Object> params, String outputDir, String outputFormat);
	
	/**
	 * Get base report directory.
	 * @return Base directory of reports folder
	 */
	public String getBaseDir();
}
