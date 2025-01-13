package com.novelerp.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novelerp.commons.util.CommonUtil;
import com.novelerp.report.service.CommercialBidReportService;
import com.novelerp.report.service.ReportService;

@Controller
public class CommercialBidReportController {
	
	@Autowired
	private CommercialBidReportService commercialReportService;
	@Autowired
	private ReportService reportService;

	@Autowired
    private ServletContext context;
	
	private static final String REPORT_NAME="DeliveryDetails";
	private Logger  log =  LoggerFactory.getLogger(PartnerReportController.class);

	@RequestMapping(value="/commercialBidReport/{bidderId}", method={RequestMethod.GET, RequestMethod.POST})
	public void procurementCommBidReport(@PathVariable("bidderId") Long bidderId, HttpServletResponse response, HttpServletRequest request){
		
		String Filename =commercialReportService.generateCommercialReport(bidderId);
		/*Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"cb_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}*/
		
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
	              
	              String fileName =CommonUtil.getReportFileName(request, "cb_");
	              
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
		            file.delete();
				}catch (Exception e) {
					log.error("Exception while closing stream", e);
				}
			}
		  }
		  
	   

	}
	@RequestMapping(value="/worksCommercialBidReport/{bidderId}", method={RequestMethod.GET, RequestMethod.POST})
	public void worksCommBidReport(@PathVariable("bidderId") Long bidderId, HttpServletResponse response){	
		
		Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"cb_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport("Works_DeliveryDetails", params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}

	}
}
