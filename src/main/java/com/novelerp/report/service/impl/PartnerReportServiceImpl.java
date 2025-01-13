package com.novelerp.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.FileUtil;
import com.novelerp.report.dto.PartnerDto;
import com.novelerp.report.service.PartnerReportService;
import com.novelerp.report.service.ReportService;
@Service
public class PartnerReportServiceImpl implements PartnerReportService {
	
	private Logger log = LoggerFactory.getLogger(PartnerReportServiceImpl.class);
	@Autowired
	private AppPropertyUtil propertyUtil;
	@Autowired
	private ReportService reportService;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private BPartnerService bpartnerService;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;


	@Override
	public String generatePartnerReport(PartnerDto partner) {
		log.info("Generating partner Document");
		log.info("Generating manufacturer Document");
		String partnerHeadrPath = generateManufactureReport(partner.getbPartnerId());
		log.info("Generating manufacturer Document finished");
		log.info("Generating trader Document");
		String traderPath = generateTraderReport(partner.getbPartnerId());
		log.info("Generating trader Document finished");
		BPartnerDto pdto =bpartnerService.findDto(partner.getbPartnerId());
		String Reg_Doc_Verification = null;
		if(pdto.getIsCEApproved() != null){
			log.info("Generating Physical Verification Document");
			Reg_Doc_Verification= generateRegDocVerification(partner.getbPartnerId());
			log.info("Generating Physical Verification Document");
		}
		
		try{
			log.info("Merge Generated Documents");
			PDFMergerUtility pdfMerger =  new PDFMergerUtility();
			addFileToPDF(partnerHeadrPath, pdfMerger);
			addFileToPDF(traderPath, pdfMerger);
			if(pdto.getIsCEApproved() != null){
			addFileToPDF(Reg_Doc_Verification, pdfMerger);
			}
			
			
			String appDocDir =  sysConfiguratorService.getAppDocDir();
			String partnerName =getPartnerName(partner.getbPartnerId());
			
			partnerName = partnerName.replace("/", "_");
			String mergerDestinationFile= appDocDir+partnerName+ "_temp"+".pdf";
			pdfMerger.setDestinationFileName(mergerDestinationFile);
			pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
			String destinationFile = appDocDir+partnerName+".pdf";
			log.info("Merge Generated Documents Finished");
			log.info("Add Header and Footer");
			addTenderAndPageInfo(partnerName, mergerDestinationFile, destinationFile);
			log.info("Add Header and Footer Finished");
			return appDocDir+partnerName+".pdf";
//			attachment =  addAttachment(tahdrId,appDocDir,tenderName+".pdf");
			
		}catch (Exception e) {
			log.error("Error", e);
		}

		return null;
	}
	private String generateManufactureReport(Long BPartnerID){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("M_BPARTNER_ID", new Long(BPartnerID));
		tenderHeaderParam.put("Report_Dir",reportService.getBaseDir());
		
		return reportService.generateReport("RegistrationPDF", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String generateTraderReport(Long BPartnerID){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("M_BPARTNER_ID", new Long(BPartnerID));
		return reportService.generateReport("MANUFACTURER_FACTORY_DETAILS", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String generateRegDocVerification(Long BPartnerID){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("M_BPARTNER_ID", new Long(BPartnerID));
		return reportService.generateReport("Reg_Doc_Verification", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String getPartnerName(Long PartnerId){
		
		BPartnerDto bPartner =bpartnerService.findDto(PartnerId);
		String bPartnerName =bPartner.getName();
		return bPartnerName;
	}
	private void addFileToPDF(String filepath, PDFMergerUtility pdfMerger){		
		File file = new File(filepath);
		try{
			if(fileUtil.isValidFile(file) && fileUtil.isPdf(file)){
				pdfMerger.addSource(file);
			}
		}catch (Exception e) {
			log.error("Error", e);
		}
	}
	private void addTenderAndPageInfo(String tenderCode, String tenderFile, String destination){
		PdfReader reader =null;
		PdfStamper stamper =null;
		try{
			reader = new PdfReader(tenderFile);
	        int n = reader.getNumberOfPages();
	        reader = fileUtil.cleanFile(reader);
	        n=reader.getNumberOfPages();
	        stamper = new PdfStamper(reader, new FileOutputStream(destination));
	        PdfContentByte pagecontent =null;
	        
	        for (int i = 0; i < n; ) {
	            pagecontent = stamper.getOverContent(++i);
	            
	            	addTenderCode(pagecontent, tenderCode);
	            
	            addPageNumber(pagecontent, i, n);
	        }
		}catch (Exception e) {
			log.error("Error", e);
		}finally {
		   try {
			    stamper.close();
		        reader.close();	    	
	       } catch (Exception e2) {
	    	   log.error("Error", e2);
	       }
		}
	}
	public void addPageNumber(PdfContentByte pagecontent, int page, int totalPages) throws Exception{
        pagecontent.beginText();
        pagecontent.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_BOLD, //Font name
                  BaseFont.CP1257, //Font encoding
                 BaseFont.EMBEDDED //Font embedded
                 )
                , 12); // set font and size
        pagecontent.setTextMatrix(270, 40); // set x and y co-ordinates
                                   //0, 800 will write text on TOP LEFT of pdf page
                                   //0, 0 will write text on BOTTOM LEFT of pdf page
        pagecontent.showText(String.format("page %s of %s", page, totalPages)); // add the text        
        pagecontent.endText();
	}
	public void addTenderCode(PdfContentByte pagecontent, String tenderCode) throws Exception{
        pagecontent.beginText();
        pagecontent.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_BOLD, //Font name
                  BaseFont.CP1257, //Font encoding
                 BaseFont.EMBEDDED //Font embedded
                 )
                , 12); // set font and size
        pagecontent.setTextMatrix(270, 820); // set x and y co-ordinates
                                   //0, 800 will write text on TOP LEFT of pdf page
                                   //0, 0 will write text on BOTTOM LEFT of pdf page
        pagecontent.showText(tenderCode); // add the text        
        pagecontent.endText();
	}
}
