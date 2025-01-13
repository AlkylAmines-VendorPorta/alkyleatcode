package com.novelerp.report.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.FileUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.report.service.CommercialBidReportService;
import com.novelerp.report.service.ReportService;
@Service
public class CommercialBidReportServiceImpl implements CommercialBidReportService{

	private Logger log = LoggerFactory.getLogger(CommercialBidReportServiceImpl.class);
	
	@Autowired
	private AppPropertyUtil propertyUtil;
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private BidderService bidderService;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	

	@Override
	public String generateCommercialReport(Long BidderId) {
		
		log.debug("Generating Commercial Document");
		String commercialHeadrPath = generateHeader(BidderId);
		String commercialReqDoc =generateComercialReqDocReport(BidderId);
		String tenderFeesReport =generateTenderFeesReport(BidderId);
		String emdFeesReport =generateEMDFeesReport(BidderId);
		String tenderDocsPath =getTenderDocsPath(BidderId);
		String tenderCode =getTenderCode(BidderId);
		tenderCode=tenderCode.replace("/", "_");
		try{
			PDFMergerUtility pdfMerger =  new PDFMergerUtility();
			addFileToPDF(commercialHeadrPath, pdfMerger);
			addFileToPDF(commercialReqDoc, pdfMerger);
			addFileToPDF(tenderFeesReport, pdfMerger);
			addFileToPDF(emdFeesReport, pdfMerger);
				
			String appTempDir=sysConfiguratorService.getAppTempDir();
			String tenderName =getTenderName(BidderId);
			
			tenderName = tenderName.replace("/", "_");
			tenderName=tenderName+System.currentTimeMillis();
			String mergerDestinationFile= appTempDir+tenderName+ "_temp"+".pdf";
			pdfMerger.setDestinationFileName(mergerDestinationFile);
			pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
			String destinationTempFile = appTempDir+tenderName+".pdf";
			addTenderAndPageInfo(tenderCode, mergerDestinationFile, destinationTempFile);
			fileUtil.delete(mergerDestinationFile);
			PDFMergerUtility pdfMergers =  new PDFMergerUtility();
			log.info(tenderDocsPath);	
			/*String destinationFile=appDocDir+tenderName+".pdf";*/
			addFileToPDF(destinationTempFile, pdfMergers);
			addFileToPDFFromSFTP(tenderDocsPath, pdfMergers);
			pdfMergers.setDestinationFileName(mergerDestinationFile);
			log.info("pdf_merged");
			pdfMergers.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
			fileUtil.delete(destinationTempFile);
			return mergerDestinationFile;
//			attachment =  addAttachment(tahdrId,appDocDir,tenderName+".pdf");
			
		}catch (Exception e) {
			log.error("Error", e);
		}

		return null;
	}
	
	private String generateHeader(Long BidderId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_BIDDER_ID", new Long(BidderId));
		tenderHeaderParam.put("SEC_KEY",ContextConstant.SEC_KEY );
		tenderHeaderParam.put("IV_PREFIX",ContextConstant.IV_PREFIX );
		return reportService.generateReport("DeliveryDetails", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String generateComercialReqDocReport(Long BidderId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_BIDDER_ID", new Long(BidderId));
		return reportService.generateReport("Commercial_Req_Doc", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String generateTenderFeesReport(Long BidderId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_BIDDER_ID", new Long(BidderId));
		return reportService.generateReport("Tender_Fees", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String generateEMDFeesReport(Long BidderId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_BIDDER_ID", new Long(BidderId));
		return reportService.generateReport("EMD_Fees", tenderHeaderParam, tempOutputDir, "pdf");
	}
	private String getTenderDocsPath(Long BidderId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bidderId", BidderId);
		BidderDto bidderDto = bidderService.findDto("getBidderByIdWithTahdrDetail", params);
		if(bidderDto != null 
			&& bidderDto.getTahdr()!=null
			&& !CommonUtil.isCollectionEmpty(bidderDto.getTahdr().getTahdrDetail())
			&& bidderDto.getTahdr().getTahdrDetail().iterator().next().getTenderDoc()!=null){
			String path =bidderDto.getTahdr().getTahdrDetail().iterator().next().getTenderDoc().getPath();
			if(path!=null){
				String filename =bidderDto.getTahdr().getTahdrDetail().iterator().next().getTenderDoc().getName();
				return path+filename;
			}else{
				return "";
			}
		}else{
			return "";
		}
		
	}
	private String getTenderName(Long BidderId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bidderId", BidderId);
		BidderDto bidderDto = bidderService.findDto("getBidderByIdWithTahdrDetail", params);
		String TenderName =bidderDto.getTahdr().getTahdrCode();
		return TenderName;
	}
	private String getTenderCode(Long BidderId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bidderId", BidderId);
		BidderDto bidderDto = bidderService.findDto("getBidderByIdWithTahdrDetail", params);
		
		Long version = bidderDto.getTahdr().getTahdrDetail().iterator().next().getVersion();
		String tender = bidderDto.getTahdr().getTahdrCode();
		if(version !=null && version>1){
			tender =  tender + " Amendmant-" +version;
		}
		return tender;
	}
	
	private String getTenderDocName(Long BidderId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bidderId", BidderId);
		BidderDto bidderDto = bidderService.findDto("getBidderByIdWithTahdrDetail", params);
		TAHDRDetailDto td=bidderDto.getTahdr().getTahdrDetail().iterator().next();
		AttachmentDto tenderDoc = td.getTenderDoc();		
		return tenderDoc.getName();
	}
	
	private void addFileToPDF(String filepath, PDFMergerUtility pdfMerger){		
		File file = new File(filepath);
		try{
			if(fileUtil.isValidFile(file) && fileUtil.isPdf(file)){
				log.error("File Valid");
				pdfMerger.addSource(file);
				log.error("Source added");
			}else{
				log.error("file not valid");
			}
		}catch (Exception e) {
			log.error("Error", e);
		}
	}
	
	private void addFileToPDFFromSFTP(String filepath, PDFMergerUtility pdfMerger){
		byte[] bytes=mediaService.getBISFromAttachment(filepath);
		addFileToPDF(bytes,pdfMerger);	
	}
	
	private void addFileToPDF(byte[] stream, PDFMergerUtility pdfMerger){
		ByteArrayInputStream bis =null;
		try{

			if(fileUtil.isValidByteArray(stream)){
				if(fileUtil.isPdf(stream)){
					bis =new ByteArrayInputStream(stream);
					pdfMerger.addSource(bis);
				}				
			}
		}catch (Exception e) {
			log.error("Error", e);
		}finally {
			try{
				bis.close();
			}catch (Exception e) {
				log.error("Error", e);
			}
		}
	}
	
	private void addTenderAndPageInfo(String tenderCode, String tenderFile, String destination){
		/*fileUtil.cleanFile(tenderFile);*/
		PdfReader reader =null;
		PdfStamper stamper =null;
		try{
			reader = new PdfReader(tenderFile);
	        int n = reader.getNumberOfPages();
	      
	        reader=fileUtil.cleanFile(reader);
	        
	        stamper = new PdfStamper(reader, new FileOutputStream(destination));
	        PdfContentByte pagecontent =null;
	        
	        for (int i = 0; i < n; ) {
	            pagecontent = stamper.getOverContent(++i);
	            if(i>1){
	            	addTenderCode(pagecontent, tenderCode);
	            }
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
	public void addTenderCode(PdfContentByte pagecontent, String tenderCode) throws Exception{
        pagecontent.beginText();
        pagecontent.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_BOLD, //Font name
                  BaseFont.CP1257, //Font encoding
                 BaseFont.EMBEDDED //Font embedded
                 )
                , 12); // set font and size
        /*pagecontent.setTextMatrix(270, 820); // set x and y co-ordinates
                                   //0, 800 will write text on TOP LEFT of pdf page
                                   //0, 0 will write text on BOTTOM LEFT of pdf page
        pagecontent.showText(tenderCode); // add the text        
*/        
        pagecontent.showTextAligned(PdfContentByte.ALIGN_CENTER, tenderCode, 300, 820, 0);
        pagecontent.endText();
	}
	
	public void addPageNumber(PdfContentByte pagecontent, int page, int totalPages) throws Exception{
        pagecontent.beginText();
        pagecontent.setFontAndSize(BaseFont.createFont
                (BaseFont.TIMES_BOLD, //Font name
                  BaseFont.CP1257, //Font encoding
                 BaseFont.EMBEDDED //Font embedded
                 )
                , 12); // set font and size
        /*pagecontent.setTextMatrix(270, 40); // set x and y co-ordinates
                                   //0, 800 will write text on TOP LEFT of pdf page
                                   //0, 0 will write text on BOTTOM LEFT of pdf page
        pagecontent.showText(String.format("page %s of %s", page, totalPages)); // add the text        
*/        
        pagecontent.showTextAligned(PdfContentByte.ALIGN_CENTER, String.format("Page %s of %s", page, totalPages), 300, 10, 0);
        pagecontent.endText();
	}
	
}

