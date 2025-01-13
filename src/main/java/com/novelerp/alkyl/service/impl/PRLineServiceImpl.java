package com.novelerp.alkyl.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dao.PRLineDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.alkyl.dto.PRLineFromSapDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Service
public class PRLineServiceImpl extends AbstractContextServiceImpl<PRLine, PRLineDto> implements PRLineService {

	@Autowired
	private PRLineDao prLineDao;
	
	@Autowired
	private PRService prService;
	
	@Autowired
	private PRComponent prComponent;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@PostConstruct
	protected void init() {
		super.init(PRLineServiceImpl.class, prLineDao, PRLine.class, PRLineDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRLineDto> save(List<PRLineDto> prLines,PRDto pr,BPartnerDto partner) {
		if(CommonUtil.isCollectionEmpty(prLines) || pr == null){
			return new ArrayList<>();
		}
		
		for(PRLineDto prLine : prLines){
			
			List<PRLineDto> prServiceLines = prLine.getPrServiceLines();
			
			//PRLineDto prLineDto=prComponent.findPRLineByPR(pr.getPrNumber(),pr.getPrId(), prLine.getPrLineNumber());
			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("prId", prID);
			params.put("prNumber", pr.getPrNumber());
			params.put("lineNo", prLine.getPrLineNumber());
			PRLineDto prLineDto= findDto("getPRLineByPRANDLINE",params);
			prLine.setPrServiceLines(null);
			prLine.setPartner(partner);
			if(prLineDto==null){
				prLine.setPr(pr);
				super.save(prLine);
			}else{
				
				prLine=getNewPRLineFromOld(prLineDto, prLine);
				prLine.setPr(pr);
				prLine = super.updateDto(prLine);
				//super.updateDto(prLine);
			}
			prServiceLines=save(prServiceLines,pr,partner,prLine);
			prLine.setPrServiceLines(prServiceLines);
			//prLine.setPrServiceLines(save(prServiceLines,pr,partner,prLine));
		}
		
		return prLines;
	}
	
	private PRLineDto getNewPRLineFromOld(PRLineDto oldDto,PRLineDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setAccountAssignment(newDto.getAccountAssignment());
		oldDto.setAssignment(newDto.getAssignment());
		oldDto.setDeliverDate(newDto.getDeliverDate());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setHeaderText(newDto.getHeaderText());
		oldDto.setItemCategory(newDto.getItemCategory());
		oldDto.setItemText(newDto.getItemText());
		oldDto.setMatGrp(newDto.getMatGrp());
		oldDto.setMatGrpDesc(newDto.getMatGrpDesc());
		oldDto.setMaterialCode(newDto.getMaterialCode());
		oldDto.setMaterialDesc(newDto.getMaterialDesc());
		oldDto.setMaterialPOText(newDto.getMaterialPOText());
		oldDto.setPlant(newDto.getPlant());
		oldDto.setPrice(newDto.getPrice());
		oldDto.setPurchaseGroup(newDto.getPurchaseGroup());
		oldDto.setQuantity(newDto.getQuantity());
		oldDto.setRate(newDto.getRate());
		oldDto.setTrackingNo(newDto.getTrackingNo());
		oldDto.setUom(newDto.getUom());
		oldDto.setFixedVendorCode(newDto.getFixedVendorCode());
		oldDto.setDesireVendorCode(newDto.getDesireVendorCode());
		oldDto.setPlantDESC(newDto.getPlantDESC());
		return oldDto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRLineDto> save(List<PRLineDto> prServiceLines,PRDto pr,BPartnerDto partner,PRLineDto prLine) {
		List<PRLineDto> newServiceList = new ArrayList<>();
		if(CommonUtil.isCollectionEmpty(prServiceLines) || prLine==null || pr == null){
			return new ArrayList<>();
		}
		
		for(PRLineDto prServiceLine : prServiceLines){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("prNumber", pr.getPrNumber());
			//params.put("prLineNumber", prLine.getPrLineNumber());
			params.put("lineNumber", prServiceLine.getLineNumber());
			prServiceLine.setPr(pr);
			prServiceLine.setPartner(partner);
			prServiceLine.setParentPRLine(prLine);
			PRLineDto old = findDto("getServiceLineByPRandLineItemNumber", params);
			if(old==null){
				prServiceLine = super.save(prServiceLine);
			}else{
				old=getNewServiceLineFromOld(old, prServiceLine);
				prServiceLine=super.updateDto(old);
				
			}
			
			newServiceList.add(prServiceLine);
//			if(prServiceLine.getPrLineId()==null){
//				super.save(prServiceLine);
//			}else{
//				super.updateDto(prServiceLine);
//			}
		}
		
		//return prServiceLines;
		return newServiceList;
	}
	
	private PRLineDto getNewServiceLineFromOld(PRLineDto oldDto,PRLineDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setAccountAssignment(newDto.getAccountAssignment());
		oldDto.setAssignment(newDto.getAssignment());
		oldDto.setDeliverDate(newDto.getDeliverDate());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setHeaderText(newDto.getHeaderText());
		oldDto.setItemCategory(newDto.getItemCategory());
		oldDto.setItemText(newDto.getItemText());
		oldDto.setMatGrp(newDto.getMatGrp());
		oldDto.setMatGrpDesc(newDto.getMatGrpDesc());
		oldDto.setMaterialCode(newDto.getMaterialCode());
		oldDto.setMaterialDesc(newDto.getMaterialDesc());
		oldDto.setMaterialPOText(newDto.getMaterialPOText());
		oldDto.setPlant(newDto.getPlant());
		oldDto.setPrice(newDto.getPrice());
		oldDto.setPurchaseGroup(newDto.getPurchaseGroup());
		oldDto.setQuantity(newDto.getQuantity());
		oldDto.setRate(newDto.getRate());
		oldDto.setTrackingNo(newDto.getTrackingNo());
		oldDto.setUom(newDto.getUom());
		oldDto.setFixedVendorCode(newDto.getFixedVendorCode());
		oldDto.setDesireVendorCode(newDto.getDesireVendorCode());

		return oldDto;
	}


	@Override
	public List<PRLineDto> getPRLineListBYFilter(PRLineFilterDto prLineDto) {
		 List<PRLineDto> prLineList=new ArrayList<PRLineDto>();
		Map<String, Object> params=getParameterMap(prLineDto);
		
		List<String> param = new ArrayList<>();
		//Map<String, Object> params= new HashMap<>();			
		//String plant=userDetailsDto.getPlant();
//		param.add(AppBaseConstant.PR_STATUS_ACCEPTED);
//		
//		params.put("status", param);
		String query =prLineDao.getPRLineByFilterQuery(prLineDto);
		prLineList=findDtosByQuery(query, params);
		return prLineList;
	}
	private Map<String,Object> getParameterMap(PRLineFilterDto prLineDto){
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("isActive", "Y");
//		if(prLineDto.getPlant()!=null){
//			params.put("plant", prLineDto.getPlant());
//		}
//		if(prLineDto.getUserId()!=null){
//			params.put("userID", prLineDto.getUserId());
//		}
		
		if(prLineDto.getPrNoFrom()!=null && prLineDto.getPrNoTo()!=null){
   			
   			
   			params.put("prNoFrom", prLineDto.getPrNoFrom());
   			params.put("prNoTo", prLineDto.getPrNoTo());
   		}
   		if(prLineDto.getPrNoFrom()!=null && prLineDto.getPrNoTo()==null){
   			params.put("prNoFrom", prLineDto.getPrNoFrom());
   			
   		}
   		if(prLineDto.getPrNoFrom()==null && prLineDto.getPrNoTo()!=null){
   			params.put("prNoTo", prLineDto.getPrNoTo());
   			
   		}
		if(prLineDto.getPrDateFrom()!=null && prLineDto.getPrDateTo()!=null){
			params.put("prDateFrom", prLineDto.getStartprDateFrom());
			prLineDto.setPrDateFrom(prLineDto.getStartprDateFrom());
   			params.put("prDateTo", prLineDto.getLastprDateTo());
   			prLineDto.setPrDateTo(prLineDto.getLastprDateTo());
//   			params.put("prDateFrom", prLineDto.getPrDateFrom());  			
//   			params.put("prDateTo", prLineDto.getPrDateTo());
//   			prLineDto.setPrDateTo((prLineDto.getLastprDateTo()));
//   			prLineDto.setPrDateFrom((prLineDto.getStartprDateFrom()));
   		}
   		if(prLineDto.getPrDateFrom()!=null && prLineDto.getPrDateTo()==null){
   			
   		
   			
   			params.put("prDateFrom", prLineDto.getStartprDateFrom());
			params.put("prDateTo", prLineDto.getLastprDateFrom());
			prLineDto.setPrDateFrom((prLineDto.getStartprDateFrom()));
			prLineDto.setPrDateTo(prLineDto.getLastprDateFrom());
   		}
   		
   		if(prLineDto.getPrDateFrom()==null && prLineDto.getPrDateTo()!=null){
   			
   			params.put("prDateFrom", prLineDto.getStartprDateTo());
			params.put("prDateTo", prLineDto.getLastprDateTo());
			prLineDto.setPrDateFrom(prLineDto.getStartprDateTo());
			prLineDto.setPrDateTo(prLineDto.getLastprDateTo());
   		}
   		
   		if(prLineDto.getPurchaseGroupFrom()!=null && prLineDto.getPurchaseGroupTo()!=null){
   		      params.put("purchaseGroupFrom", prLineDto.getPurchaseGroupFrom());
		      params.put("purchaseGroupTo", prLineDto.getPurchaseGroupTo());
	     }
   		
		if(prLineDto.getPurchaseGroupFrom()!=null && prLineDto.getPurchaseGroupTo()==null){
		      params.put("purchaseGroupFrom", prLineDto.getPurchaseGroupFrom());
	     }
		
		if(prLineDto.getPurchaseGroupFrom()==null && prLineDto.getPurchaseGroupTo()!=null){
		      params.put("purchaseGroupTo", prLineDto.getPurchaseGroupTo());
	     }
		
		if(prLineDto.getStatus()!=null){
			params.put("status", prLineDto.getStatus());
		     }
		
		if(prLineDto.getPlant()!=null){
			params.put("plant", prLineDto.getPlant());
		     }
		
		
   		
   		
		
		return params;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updatePRBuyerAssign(List<PRLineDto> prLineDto) {
     long result=0;
     
 	User pmapprovedBy= new User();
 	pmapprovedBy.setUserId(contextService.getUser().getUserId());

		
			for(PRLineDto prLine:prLineDto){
				long prID=0;
				HashMap<String, Object> prLineMap=new HashMap<>();
				prLineMap.put("deliverDate", prLine.getDeliverDate());
				prLineMap.put("requiredDate", prLine.getRequiredDate());
				//prLineMap.put("description", prLine.getDescription());
				prLineMap.put("buyer.userId", prLine.getBuyerId());
				result=updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", prLine.getPrLineId()));
				if(prID!=prLine.getPr().getPrId()){
				HashMap<String, Object> prMap=new HashMap<>();
				prMap.put("status", AppBaseConstant.PR_STATUS_BUYER_ASSIGNED);
				prMap.put("pmapprovedBy", pmapprovedBy);
				prMap.put("pmapprovedDate", new Date());
				result=prService.updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prLine.getPr().getPrId()));
				}
			}
			if(result>0){
				return true;
			}
			return false;
		}
	
	
	@PostMapping(value = "/getPRFromSAPAfterPOCreation")
	public @ResponseBody List<PRLineDto> getPRFromSAPAfterPOCreation(PRLineFilterDto prLineDto) {

		CustomResponseDto resp = new CustomResponseDto();
		String prNo=prLineDto.getPrNoFrom();
		String purchaseGroup=prLineDto.getPurchaseGroupFrom();
		String plant=prLineDto.getPlant();
		
		if(prNo==null) {
			prNo="";
		}
		
		String role = null;
		List<PRLineDto> prLineList = new ArrayList<>();
		List<PRLineDto> prLineListArray = new ArrayList<>();
		List<PRLineDto> prLineListFinal = new ArrayList<>();
		
									// Create a trust manager that does not validate certificate chains
					TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}

						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}
					} };

					// Install the all-trusting trust manager
					SSLContext sc = null;
					try {
						sc = SSLContext.getInstance("SSL");
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
					} catch (KeyManagementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

					// Create all-trusting host name verifier
					HostnameVerifier allHostsValid = new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};

					// Install the all-trusting host verifier
					HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
					
					
//					Long partnerId=partnerComponent.getLoggedInUser().getPartner().getbPartnerId();
//					
//					BPartnerDto partner = partnerService.findDto("getPartnerByPartnerId",
//							AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
					
//					if (contextService != null && contextService.getDefaultRole() != null
//							&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
//						role = contextService.getDefaultRole().getValue();
//					}
					
//					String vendorSapCode=partner.getVendorSapCode();
					JSONArray newjsonlist=new JSONArray();
					
				
				//	String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_52?sap-client=100&PR_NO="+prNo+"&PR_GRP="+purchaseGroup+"&PR_PLANT="+plant;
					String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_52?sap-client=100&PR_NO="+prNo+"&PR_GRP="+purchaseGroup+"&PR_PLANT="+plant;
				
					System.out.println(url);

					URLConnection request = null;
					
					Map<String, Object> params = new HashMap<String, Object>();
				//	params.put("prNo", prNo);
//					PRDto oldDto = prService.findDtos("getPRByPRNumber", params);
					
			//		PRDto oldDto=prService.findDto("getPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", prNo));
					
			//		Map<String, Object> params = new HashMap<String, Object>();
//					params.put("prId", prID);
				
					
					
					
					try {
						URL u = new URL(url);
						request = u.openConnection();
						request.connect();
						BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
						StringBuilder sb = new StringBuilder();
						String line;
						while ((line = br.readLine()) != null) {
							sb.append(line + "\n");
						}
						System.out.println(sb);
						br.close();

						//JSONObject obj = new JSONObject(sb.toString());
						JSONArray obj=new JSONArray(sb.toString());
						//System.out.println(headerArray);
						
					//	JSONObject obj = new JSONObject(sb.toString());
						System.out.println(obj);
						 
						 ObjectMapper mapper = new ObjectMapper();
							mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
							mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
							
							

						
							
						// PRLineFromSapDto[] dto = objJson.readValue(headerArray.toString(), PRLineFromSapDto[].class);
						 
							 PRDto[] dto = mapper.readValue(obj.toString(), PRDto[].class);
						 
							
							 String oldDto="";
						 for(PRDto dtos:dto){
							 
							 List<PRLineDto> prLineList1= dtos.getPrLines();
							 prLineList= addprToprlineFromSap(prLineList1,dtos);

//							  oldDto=dtos.getPrNumber();
//							 prLineList.addAll(dtos.getPrLines());
							 
							 prLineListArray.addAll(prLineList);
			             }
						 
						 PRLineDto prLineoldDto=null;
						 
                             for(PRLineDto prline:prLineListArray) {
							
							 
							 if(prNo!="") {
								 params.put("prNumber", prNo);
									params.put("lineNo", prline.getPrLineNumber());
									 prLineoldDto= findDto("getPRLineByPRANDLINE",params);
							 }
							 
							 if(purchaseGroup!=null) {
								params.put("prNumber", prline.getPr().getPrNumber()); 
								params.put("purchaseGroup", purchaseGroup);
								params.put("lineNo", prline.getPrLineNumber());
								prLineoldDto= findDto("getPRLineByPRANDLINEorBuyer",params);
							 }
							 
							 prline.setPr(prLineoldDto.getPr());
							 prline.setPrLineId(prLineoldDto.getPrLineId());
							 prline.setBuyer(prLineoldDto.getBuyer());
							 
							 prLineListFinal.add(prLineoldDto);
						 }
						 
//						 for(PRLineDto prline:prLineList) {
//							
//							 
//							 if(prNo!=null) {
//								 params.put("prNumber", prNo);
//									params.put("lineNo", prline.getPrLineNumber());
//									 prLineoldDto= findDto("getPRLineByPRANDLINE",params);
//							 }
//							 
//							 if(purchaseGroup!=null) {
//								 params.put("prNumber", oldDto); 
//								params.put("purchaseGroup", purchaseGroup);
//								params.put("lineNo", prline.getPrLineNumber());
//								prLineoldDto= findDto("getPRLineByPRANDLINEorBuyer",params);
//							 }
//							
//							 
//							 
//							 
//							 prline.setPr(prLineoldDto.getPr());
//							 prline.setPrLineId(prLineoldDto.getPrLineId());
//							 prline.setBuyer(prLineoldDto.getBuyer());
//						 }
						 
						 
						 resp.addObject("prlineList", prLineListFinal);
						 resp.addObject("role", role);
						

					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						if (request != null) {
							try {
								((HttpURLConnection) request).disconnect();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					}

				

			

		
		
		return prLineListFinal;

	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRLineDto> addprToprlineFromSap(List<PRLineDto> prLineList,PRDto pr){
		for(PRLineDto prlist: prLineList) {
			prlist.setPr(pr);
		}
		return prLineList;
		
	}


}
