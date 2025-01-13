package com.novelerp.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.novelerp.commons.util.CommonUtil;
import com.novelerp.report.dto.PartnerDto;
import com.novelerp.report.service.PartnerReportService;
import com.novelerp.report.service.ReportService;

@Controller
public class PartnerReportController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private PartnerReportService partnerReportService;
	

	@Autowired
    private ServletContext context;

	private static final String REPORT_NAME="RegistrationPDF";
	private Logger  log =  LoggerFactory.getLogger(PartnerReportController.class);

	@PostMapping(value="/partnerReport")
	public void partnerReport(@ModelAttribute("partner") PartnerDto partner, HttpServletResponse response, HttpServletRequest request){
		
		String reportDir = reportService.getBaseDir();
		
/*		Map<String, Object> params =  ProcessParamBuilder.paramBuilder(partner);*/
		/*Map<String, Object> params =  new HashMap<>();
		params.put("M_BPARTNER_ID", partner.getbPartnerId());
		params.put("Report_Dir", reportDir);
		response.setHeader("Content-Disposition", "attachment; filename=\"partner" + ".pdf\"");
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}*/
		String Filename =partnerReportService.generatePartnerReport(partner);
		 File file = new File(Filename);
	        OutputStream os = null;
	        FileInputStream fis = null;

			  if (file.exists()) {
				  try{
					  String mimeType = context.getMimeType(file.getPath());
					  
		              if (mimeType == null) {
		                  mimeType = "application/octet-stream";
		              }
		
		              response.setContentType(mimeType);
		              
		              String fileName =CommonUtil.getReportFileName(request, "partner_");
		              
		              StringBuilder headerContent =  new StringBuilder();
		      			headerContent.append(fileName);
		              response.addHeader("Content-Disposition", "attachment; filename=\"" + headerContent.toString()+"\"");
		              response.setContentLength((int) file.length());
		
		               os = response.getOutputStream();
		               fis = new FileInputStream(file);
		              byte[] buffer = new byte[4096];
		              int b = -1;
		
		              while ((b = fis.read(buffer)) != -1) {
		                  os.write(buffer, 0, b);
		              }
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
			  }

	}
}
