package com.novelerp.report.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.FileUtil;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.StandardCustomDocService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.report.service.ReportService;
import com.novelerp.report.service.TenderReportService;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class TenderReportServiceImpl implements TenderReportService {

	private Logger log = LoggerFactory.getLogger(TenderReportServiceImpl.class);
	
	@Autowired
	private AppPropertyUtil propertyUtil;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private StandardCustomDocService tenderDocService;
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	@Autowired
	private TAHDRService tahdrService;
		
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@Autowired
	private AppContext appContext;
	
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Override
	public String generateTenderReport(Long tahdrId){
		log.debug("Generating Tender Document");
		String tenderHeaderPath = generateHeader(tahdrId);
		
		TAHDRDto tahdr = tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		String tenderCode =  getTenderCode(tahdrId);
		try{
			PDFMergerUtility pdfMerger =  new PDFMergerUtility();
			addFileToPDF(tenderHeaderPath, pdfMerger);
			addTenderDocuments(tahdrId, pdfMerger);
			if(!AppBaseConstant.TENDER_TYPE_CODE_WORKS.equals(tahdr.getTahdrTypeCode())){
				addAnnexureB(tahdrId, pdfMerger);
				addDeliveryDetailPage(pdfMerger,tahdrId);
			}else if(AppBaseConstant.TENDER_TYPE_CODE_WORKS.equals(tahdr.getTahdrTypeCode())){
				addServiceList(tahdrId, pdfMerger);
			}
			
			generateItemDetail(tahdrId, pdfMerger);
			addRefMaterials(tahdrId, pdfMerger);
			addRefMaterialsParticulars(tahdrId, pdfMerger);
			addRequiredDocs(tahdrId, pdfMerger);
			String appTempDir =  sysConfiguratorService.getAppTempDir();
			String tenderName = tahdr.getTahdrCode();
			
			tenderName = tenderName.replace("/", "_");
			tenderName = tenderName.replace(" ", "_");
			Long version=null;
			if(tahdr!=null && tahdr.getTahdrDetail()!=null && !tahdr.getTahdrDetail().isEmpty()){
				version=tahdr.getTahdrDetail().iterator().next().getVersion();
			}
			if(CommonUtil.isEqual(version, null)){
				tenderName = tenderName+"_version_"+"1";
			}else{
				tenderName = tenderName+"_version_"+version;
			}
			Long timeStamp=System.currentTimeMillis();
			String mergerDestinationFile= appTempDir+tenderName+"_temp.pdf";
			pdfMerger.setDestinationFileName(mergerDestinationFile);
			pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
			String destinationFile = appTempDir+tenderName+"_"+timeStamp+".pdf";
			addTenderAndPageInfo(tenderCode, mergerDestinationFile, destinationFile);
			File file=new File(mergerDestinationFile);
			if(file.exists()){
				file.delete();
			}
			return tenderName+"_"+timeStamp+".pdf";
//			attachment =  addAttachment(tahdrId,appDocDir,tenderName+".pdf");
			
		}catch (Exception e) {
			log.error("Error", e);
		}

		return null;
	}

	private String getTenderCode(Long tahdrId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId);
		TAHDRDetailDto tahdrDetail = tahdrDetailService.findDto("QueryForTAHDRDetailByTahdrId", params);
		if(tahdrDetail ==null){
			return null;
		}
		Long version = tahdrDetail.getVersion();
		String tender =  tahdrDetail.getTahdr().getTahdrCode();
		if(version !=null && version>1){
			tender =  tender + " Version-" +version;
		}
		return tender;
	}
	/**
	 * Generate Header Report .. 
	 * @param tenderId
	 */
	private String generateHeader(Long tenderId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("tahdrId", new Long(tenderId));
		return reportService.generateReport("Tender", tenderHeaderParam, tempOutputDir, "pdf");
	}
	
	private void generateItemDetail(Long tahdrId, PDFMergerUtility pdfMerger) throws FileNotFoundException{
		
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId);
		List<TAHDRMaterialDto> tahdrMaterials = tahdrMaterialService.findDtos("getRefTahdrMaterialsQuery", params);
		addTahdrMaterials(tahdrMaterials,pdfMerger);
	}
	
	
	
	private void addTenderDocuments(Long tahdrId, PDFMergerUtility pdfMerger) throws FileNotFoundException{
		List<StandardCustomDocDto> docs = tenderDocService.getDocs(tahdrId);
		if(CommonUtil.isListEmpty(docs)){
			return;
		}
		
		for(StandardCustomDocDto doc : docs){
			addDoc(doc, pdfMerger);
		}
	}
	
	private void addDoc(StandardCustomDocDto doc, PDFMergerUtility pdfMerger) throws FileNotFoundException{
		if(!isValidDoc(doc)){
			return;
		}
		
		
		AttachmentDto attachment  = doc.getAttachment();
		String filePath = "";
		if(doc.getCode().startsWith("CM")){
			filePath=attachment.getPath()+attachment.getName();
		}else{
			filePath=attachment.getPath()+attachment.getFileName();
		}
		
		if(appContext.isSFTP()){
			addFileToPDFFromSFTP(filePath, pdfMerger);
			return;
		}

		addFileToPDF(filePath, pdfMerger);
	}
	
	private void addServiceList(Long tahdrId, PDFMergerUtility pdfMerger){
		String serviceListPath = generateServiceList(tahdrId);
		addFileToPDF(serviceListPath, pdfMerger);
	}
	
	private void addAnnexureB(Long tahdrId, PDFMergerUtility pdfMerger){
		String annexurebPath = generateAnnexureB(tahdrId);
		addFileToPDF(annexurebPath, pdfMerger);
	}
	
	private void addDeliveryDetailPage(PDFMergerUtility pdfMerger, Long tahdrId){
/*		String appDocDir =  propertyUtil.getAppDocDir();
		String docPath =  appDocDir +"delivery_detail_page.pdf";*/
		String docPath = generateDeliveryDetailPage(tahdrId);
		addFileToPDF(docPath, pdfMerger);
	}
	
	
	private String generateServiceList(Long tahdrId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_TAHDR_ID", tahdrId);
		return reportService.generateReport("SERVICE_LIST", tenderHeaderParam, tempOutputDir, "pdf");
	}
	
	private String generateAnnexureB(Long tahdrId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRID", tahdrId);
		return reportService.generateReport("PriceScheduler", tenderHeaderParam, tempOutputDir, "pdf");
	}
	
	private String generateDeliveryDetailPage(Long tahdrId){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("T_TAHDR_ID", tahdrId);
		return reportService.generateReport("DeliveryDetails_Tender", tenderHeaderParam, tempOutputDir, "pdf");
	}

	
	private boolean isValidDoc(StandardCustomDocDto doc){
		if(doc ==null){
			log.warn("Invalid document, cannot be processed.");
			return false;
		}
		AttachmentDto attachment  = doc.getAttachment();
		if(attachment ==null){
			log.warn("No attachment for doc id = " + doc.getStandardCustomDocId());
			return false;
		}
		String filePath = attachment.getPath();
		String fileName = attachment.getFileName();
		
		if(CommonUtil.isStringEmpty(filePath) || CommonUtil.isStringEmpty(fileName)){
			log.warn("File path or fileName is invalid for attachment id = " + attachment.getAttachmentId());
			return false;
		}
		
		return true;
	}
	
	private void addTahdrMaterials(List<TAHDRMaterialDto> tahdrMaterials, PDFMergerUtility pdfMerger) throws FileNotFoundException{		
		if(CommonUtil.isListEmpty(tahdrMaterials)){
			return;
		}
		for(TAHDRMaterialDto tahdrMaterial : tahdrMaterials){
			addTahdrMaterial(tahdrMaterial, pdfMerger);
		}
	}
	
	private void addTahdrMaterial(TAHDRMaterialDto tahdrMaterial, PDFMergerUtility pdfMerger) throws FileNotFoundException {		
		AttachmentDto attachment = getAttachment(tahdrMaterial);
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		String reportDir =  reportService.getBaseDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRMATERIALID", tahdrMaterial.getTahdrMaterialId());
		tenderHeaderParam.put(ContextConstant.REPORT_DIR_CODE, reportDir);		
		String outputPath = reportService.generateReport("MSEDCLMain", tenderHeaderParam, tempOutputDir, "pdf");
		File materialMainPage =  new File(outputPath);
		pdfMerger.addSource(materialMainPage);
		addVersionSpecs(attachment, pdfMerger);
		/*addItemParticulars(tahdrMaterial.getTahdrMaterialId(),pdfMerger);*/
	}
	
	private void addVersionSpecs(AttachmentDto attachment, PDFMergerUtility pdfMerger){
		if(attachment ==null){
			return;
		}
		String versionSpecs = attachment.getPath()+attachment.getName();
		addFileToPDF(versionSpecs, pdfMerger);
	}
	
	private void addItemParticulars(Long tahdrMaterialId, PDFMergerUtility pdfMerger){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRMATERIALID", tahdrMaterialId);
		String outputPath = reportService.generateReport("TechnicalParticulars", tenderHeaderParam, tempOutputDir, "pdf");
		addFileToPDF(outputPath, pdfMerger);		
	}
	
	private void addRefMaterials(Long tahdrId, PDFMergerUtility pdfMerger){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRID", tahdrId);
		String outputPath = reportService.generateReport("TechnicalSpecificationCont", tenderHeaderParam, tempOutputDir, "pdf");

		addFileToPDF(outputPath, pdfMerger);
	}
	
	private void addRefMaterialsParticulars(Long tahdrId, PDFMergerUtility pdfMerger){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		String reportDir =  reportService.getBaseDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRID", tahdrId);
		tenderHeaderParam.put(ContextConstant.REPORT_DIR_CODE, reportDir);
		String outputPath = reportService.generateReport("TechnicalParticularsRef", tenderHeaderParam, tempOutputDir, "pdf");
		addFileToPDF(outputPath, pdfMerger);
	}

	private AttachmentDto getAttachment(TAHDRMaterialDto tahdrMaterial){
		if(tahdrMaterial == null){
			return null;
		}
		MaterialVersionDto materialversion = tahdrMaterial.getMaterialVersion();
		if(materialversion == null){
			return null;
		}
		return materialversion.getVersionSpecification();
	}
	
	private void addFileToPDF(String filepath, PDFMergerUtility pdfMerger) {
		File file = new File(filepath);
		try {
			if (fileUtil.isValidFile(file) && fileUtil.isPdf(file)) {
				pdfMerger.addSource(file);
			}
		} catch (Exception e) {
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
	
	@Transactional(propagation=Propagation.REQUIRED)
	public AttachmentDto addAttachment(Long tahdrId, String fileName){
		BPartnerDto partner=contextService.getPartner();
		String filePath  =  sysConfiguratorService.getAppDocDir();
		
		if(CommonUtil.isStringEmpty(filePath)
				|| CommonUtil.isStringEmpty(fileName)){
			return null;
		}
		AttachmentDto attachment = tahdrDetailService.getTenderDoc(tahdrId);
		if(attachment == null){
			attachment = new AttachmentDto();
			attachment.setFileExtension("pdf");
			attachment.setPath(filePath);
			attachment.setName(fileName);
			attachment.setFileName(fileName);
			attachment.setPartner(partner);
			attachment = attachmentService.save(attachment);
		}
		return attachment;
	}
		
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AttachmentDto addAttachment(Long tahdrId, String fileName, String filePath){
		BPartnerDto partner=contextService.getPartner();
		
		if(CommonUtil.isStringEmpty(filePath)
				|| CommonUtil.isStringEmpty(fileName)){
			return null;
		}
		AttachmentDto attachment = tahdrDetailService.getTenderDoc(tahdrId);
		if(attachment == null){
			attachment = new AttachmentDto();
			attachment.setFileExtension("pdf");
			attachment.setPath(filePath);
			attachment.setName(fileName);
			attachment.setFileName(fileName);
			attachment.setPartner(partner);
			attachment = attachmentService.save(attachment);
		}else{
			attachment.setFileExtension("pdf");
			attachment.setPath(filePath);
			attachment.setName(fileName);
			attachment.setFileName(fileName);
			attachment=attachmentService.updateDto(attachment);
		}
		return attachment;
	}
	
	private void addTenderAndPageInfo(String tenderCode, String tenderFile, String destination){
		/*fileUtil.cleanFile(tenderFile);*/
		PdfReader reader =null;
		PdfStamper stamper =null;
		try{
			reader = new PdfReader(tenderFile);
	        int n = reader.getNumberOfPages();
	        reader = fileUtil.cleanFile(reader);
	        n = reader.getNumberOfPages();
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
	
	private void addRequiredDocs(Long tahdrId, PDFMergerUtility pdfMerger){
		String tempOutputDir= sysConfiguratorService.getAppTempDir();
		String reportDir =  reportService.getBaseDir();
		Map<String, Object> tenderHeaderParam = new HashMap<>();
		tenderHeaderParam.put("TAHDRID", tahdrId);
		tenderHeaderParam.put(ContextConstant.REPORT_DIR_CODE, reportDir);
		String outputPath = reportService.generateReport("TenderReqDoc", tenderHeaderParam, tempOutputDir, "pdf");
		addFileToPDF(outputPath, pdfMerger);
	}
	
/*	public static void main(String[] args) {
		try{
			File file1 = new File("D:\\eatApp\\docs\\FILE_ANNEXURE_Q_Version_1.pdf");
			File file2 = new File ("D:\\eatApp\\docs\\Form_CCF_aadhar.pdf");
			PDFMergerUtility pdfMerger =  new PDFMergerUtility();
			
			pdfMerger.addSource(file1);
			pdfMerger.addSource(file2);
			
			pdfMerger.setDestinationFileName("D:\\eatApp\\docs\\XYZ.pdf");
			pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
