package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;

@Component
public class GRNComponent {
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
//	@Autowired
//	private AdvanceShipmentNoticeService asnService;
	
//	public Errors  fetchGRNNO(){
//		String[] listOfFileNames = mediaService.getFilesListAt(AppBaseConstant.FTP_GRN_PROCESSED_PATH);
//		Errors errors = new Errors();
//		if(null==listOfFileNames || listOfFileNames.length==0){
//			errors.addError("Empty", "No records found to process");
//			return errors;
//		}
//		for(String fileName : listOfFileNames){
//			byte[] byteOut = mediaService.getBISFromAttachment(fileName);
//			ObjectMapper objJson = new ObjectMapper();
//			try {
//				String json = new String(byteOut);
//				json=json.toLowerCase();
//				GRNFTPResponse dto = objJson.readValue(json.getBytes(), GRNFTPResponse.class);
//				//GRNFTPResponse dto = (GRNFTPResponse) objJson.readValue(byteOut, Object.class);
//				
//				if(dto == null){
//					errors.addError(fileName, "Empty GRN File");
//					continue;
//				}
//				if(CommonUtil.isStringEmpty(dto.getMat_doc())){
//					errors.addError(fileName, "Document Number empty.");
//					continue;
//				}
//				
//				if(dto.getMov_typ().equals("105")) {
//				Map<String, Object> param = AbstractContextServiceImpl.getParamMap("grnId", dto.getMat_doc());
//				param.put("grnYear", dto.getDoc_year());
//				
//				int result = asnService.updateByJpql(param
//						, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeNo", dto.getAsnno()));
//				if(result>0){
//					mediaService.deleteFile(fileName);
//				}
//				}else {
//					Map<String, Object> param = AbstractContextServiceImpl.getParamMap("sap103Id", dto.getMat_doc());
//					param.put("sap103Year", dto.getDoc_year());
//					
//					int result = asnService.updateByJpql(param
//							, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeNo", dto.getAsnno()));
////					if(result>0){
////						mediaService.deleteFile(fileName);
////					}
//					
//				}
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				errors.addError(fileName, e.getMessage());
//			} 
//		}
//		return errors;
//	}
}
