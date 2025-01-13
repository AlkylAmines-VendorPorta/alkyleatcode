package com.novelerp.alkyl.controller;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.novelerp.alkyl.component.ASNLineComponent;
import com.novelerp.alkyl.component.POComponent;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class ASNLineController {

	@Autowired
	private AdvanceShipmentNoticeLineService asnLineService;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
//	@Autowired
//	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private ASNLineComponent asnLineComponent;
	
	@Autowired
	private AttachmentService attService;
	
	@Autowired
	@Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
//	@Autowired
//	private AttachmentService attachmentService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	
	@Autowired
	private POLineController poLineController;
	

	
	
		@PostMapping(value="/getASNLines/{asnId}")
		public @ResponseBody CustomResponseDto getASNLines(@PathVariable("asnId") Long asnId){
	
		List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNCreation(asnId);
		List<AdvanceShipmentNoticeLineDto> serviceLineList = asnLineComponent.getServiceLineForASNCreation(asnId);
	
		
		
	//	AdvanceShipmentNoticeLineDto obj = new AdvanceShipmentNoticeLineDto();
		
	//	System.out.println(asnLineList.get(0).getPoLine().getPurchaseOrder().getPurchaseOrderNumber());
	  // String url="http://103.231.11.53:8000/sap/bc/yweb03_ws_17?sap-client=009&matnr="+materialCode+"&plant="+plant+"";
	// String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_17?sap-client=009&order=6500019280&matnr=702116&plant=1840";
			/*if(!CommonUtil.isCollectionEmpty(asnLineList)){
			AdvanceShipmentNoticeLineDto asnLine =asnLineList.get(0);
			if(asnLine.getPoLine()!=null){
				storageLoc = refListService.getReferenceListMap(asnLine.getPoLine().getPlant());
			}
		}*/
		Map<String, String> storageLoc = null;
		if(!CommonUtil.isCollectionEmpty(asnLineList)){
			AdvanceShipmentNoticeLineDto asnLine =asnLineList.get(0);
			if(asnLine.getPoLine()!=null){
				storageLoc = refListService.getReferenceListMap(asnLine.getPoLine().getPlant());
			}
		}
		
		
		
       String pono=asnLineList.get(0).getPoLine().getPurchaseOrder().getPurchaseOrderNumber();
		
		List<AdvanceShipmentNoticeLineDto> validateSSN = validateSSN(pono,asnId,serviceLineList);
		//List<AdvanceShipmentNoticeLineDto> validateASNContractPo = validateASNContractPo(pono,asnId,asnLineList);
		
		
		
		//Map<String, String> costList = refListService.getReferenceListMap(CoreReferenceConstants.COST_CENTER);
		
		// Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
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
		
		
		//String plant=poLineList.get(0).getPlant();
        
        String plant=asnLineList.get(0).getPoLine().getPlant();
	
//		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_20?sap-client=009&PLANT="+plant;
	//String url=	"https://172.18.2.28:44300/sap/bc/yweb03_ws_20?sap-client=100&order=&plant=0001";
	 
	//	String order=poLineList.get(0).getPurchaseOrder().getPurchaseOrderNumber();
        String order=asnLineList.get(0).getPoLine().getPurchaseOrder().getPurchaseOrderNumber();
//		 String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_20?sap-client=100&order="+order+"&plant="+plant;
		 String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_20?sap-client=100&order="+order+"&plant="+plant;
//		String url="http://103.231.11.53:8000/sap/bc/yweb03_ws_20?sap-client=009&order="+order+"&plant="+plant ;
	//	String url="http://103.231.11.53:8000/sap/bc/yweb03_ws_20?sap-client=009&order=6700007629&plant=1840";
		System.out.println(url);
		List<String> costcenterList = new ArrayList<>();
		URLConnection request=null;   
		try {
	            URL u = new URL(url);
	            request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            System.out.println(sb);
	            br.close();
	            String[] dropdownelement = sb.toString().replaceAll("\"","").trim().split(",");
	         
	            
	            costcenterList = Arrays.asList(dropdownelement);
                
	           // return sb.toString();


	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally {
	            if (request != null) {
	                try {
	                	((HttpURLConnection) request).disconnect();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
		Map<String, String> costList = new HashMap<>();
		for(String temp : costcenterList) {
			//costList.put(temp.split(":-")[0].trim(), temp.trim());
			costList.put(temp.split("-")[0].trim(), temp.trim());
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", AppBaseConstant.SSNVERSION);
		SystemConfiguratorDto sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
		String role = contextService.getDefaultRole().getValue();
		
		
		//CustomResponseDto resp = new CustomResponseDto("asnLineList",asnLineList);
		//resp.addObject("serviceLineList", serviceLineList);
		CustomResponseDto resp = new CustomResponseDto("asnLineList",asnLineList);
		resp.addObject("serviceLineList", validateSSN);
		//resp.addObject("serviceLineList", serviceLineList);
		resp.addObject("storageLocation", storageLoc);
		resp.addObject("costCenter", costList);
		resp.addObject("SSNVersion", sysConfig.getName());
		resp.addObject("role", role);
		resp.addObject("user", contextService.getUser());
		resp.setSuccess(true);
		resp.setMessage("Record Saved");
		return resp;
	}
		
		
@PostMapping(value="/getMainASNLines/{asnId}/{status}")
		public @ResponseBody CustomResponseDto getSecurityASNLines(@PathVariable("asnId") Long asnId,@PathVariable("status") String status){
			List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getMainASNLineForASNCreation(asnId,status);
			
			CustomResponseDto resp = new CustomResponseDto("asnLineList",asnLineList);
			return resp;
		}
	
	@PostMapping(value="/getASNLinesBllling/{asnId}/{attachmentId}")
	public @ResponseBody CustomResponseDto getASNLinesBllling(@PathVariable("asnId") Long asnId,@PathVariable(name="attachmentId",required=false) Long attachmentId){
		List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNCreation(asnId);
		List<AdvanceShipmentNoticeLineDto> serviceLineList = asnLineComponent.getServiceLineForASNCreation(asnId);
		
		Map<String, String> storageLoc = null;
		if(!CommonUtil.isCollectionEmpty(asnLineList)){
			AdvanceShipmentNoticeLineDto asnLine =asnLineList.get(0);
			if(asnLine.getPoLine()!=null){
				storageLoc = refListService.getReferenceListMap(asnLine.getPoLine().getPlant());
			}
		}
		
		AttachmentDto att = attService.findDto(attachmentId);
		byte[] bytes=mediaService.getBISFromAttachment(att);
		
		CustomResponseDto resp = new CustomResponseDto("asnLineList",asnLineList);
		resp.addObject("serviceLineList", serviceLineList);
		resp.addObject("storageLocation", storageLoc);
		resp.addObject("invoice", bytes);
		resp.setSuccess(true);
		resp.setMessage("Record Saved");
		return resp;
	}
	
	@PostMapping(value="/getServiceSheetLines/{poLineId}")
	public @ResponseBody CustomResponseDto getServiceSheetLines(@PathVariable("poLineId") Long poLineId){
		List<AdvanceShipmentNoticeLineDto> serviceLineList = new ArrayList<>();
		
		List<PurchaseOrderLineDto> servicesList =  poLineService.findDtos("getServicesByPOLineId", AbstractContextServiceImpl.getParamMap("poLineId", poLineId));
		if(!CommonUtil.isCollectionEmpty(servicesList)){
			for(PurchaseOrderLineDto service : servicesList){
				AdvanceShipmentNoticeLineDto serviceLine = new AdvanceShipmentNoticeLineDto();
				serviceLine.setPoLine(service);
				serviceLineList.add(serviceLine);
			}
		}
		CustomResponseDto resp = new CustomResponseDto("serviceLineList",serviceLineList);
		resp.setSuccess(true);
		resp.setMessage("Record Saved");
		return resp;
	}
	
	@PostMapping(value="/getServicesByASNLineId/{asnLineId}/{poLineId}")
	public @ResponseBody CustomResponseDto getServicesByASNLineId(@PathVariable("asnLineId") Long asnLineId,@PathVariable("poLineId") Long poLineId){
		List<AdvanceShipmentNoticeLineDto> serviceLineList = asnLineService.findDtos("getServiceLineByASNLineId", 
				AbstractContextServiceImpl.getParamMap("asnLineId", asnLineId));
		Map<String, Object> map = AbstractContextServiceImpl.getParamMap("poLineId", poLineId);
		map.put("asnLineId", asnLineId);
		List<PurchaseOrderLineDto> servicesList =  poLineService.findDtos("getServiceForASNLine", map);
		if(!CommonUtil.isCollectionEmpty(servicesList)){
			if(serviceLineList==null){
				serviceLineList = new ArrayList<>();
			}
			for(PurchaseOrderLineDto service : servicesList){
				AdvanceShipmentNoticeLineDto serviceLine = new AdvanceShipmentNoticeLineDto();
				serviceLine.setPoLine(service);
				serviceLineList.add(serviceLine);
			}
		}
		CustomResponseDto resp = new CustomResponseDto("serviceLineList",serviceLineList);
		resp.setSuccess(true);
		resp.setMessage("Record Saved");
		return resp;
	}
	
	@PostMapping(value="/saveASNLine")
	public @ResponseBody CustomResponseDto saveASNLine(@RequestBody AdvanceShipmentNoticeLineDto asnLine){
		if(asnLine.getAdvanceShipmentNoticeLineId()!=null){
			asnLineService.updateDto(asnLine);
		}else{
			asnLineService.save(asnLine);
		}
		return new CustomResponseDto("asnLine",asnLine);
	}	
	
	@PostMapping(value="/saveASNLineList")
	public @ResponseBody CustomResponseDto saveASNLineList(@RequestBody AdvanceShipmentNoticeDto asn){
		if(asn==null){
			return new CustomResponseDto("asnLine",new ArrayList<>());
		}else{
			List<AdvanceShipmentNoticeLineDto> asnLines = asnLineService.save(asn.getAsnLineList(),asn);
			return new CustomResponseDto("asnLine",asnLines);
		}
	}
	@PostMapping(value="/getStorageLocFromSAP/{orderNo}/{matcode}/{plant}")
	public @ResponseBody CustomResponseDto getStorageLocFromSAP(@PathVariable("orderNo") String orderNo,@PathVariable("matcode") String matcode,@PathVariable("plant") String plant){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
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
		//String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_17?sap-client=009&order="+orderNo+"&matnr="+matcode+"&plant="+plant+"";
	//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_17?sap-client=100&order="+orderNo+"&matnr="+matcode+"&plant="+plant+"";

        String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_17?sap-client=100&order="+orderNo+"&matnr="+matcode+"&plant="+plant+"";
      //  String url="https://172.18.2.29:44300/sap/bc/yweb03_ws_17?sap-client=100&order="+orderNo+"&matnr="+matcode+"&plant="+plant+"";
		//String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_17?sap-client=009&order="+asnLineList.get(0).getPoLine().getPurchaseOrder().getPurchaseOrderNumber()+"&matnr="+asnLineList.get(0).getPoLine().getCode()+"&plant="+asnLineList.get(0).getPoLine().getPlant();
		
		   List<String> cntList = new ArrayList<>();
			URLConnection request=null;   
			try {
		            URL u = new URL(url);
		            request =  u.openConnection();

		            request.connect();


		            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		            StringBuilder sb = new StringBuilder();
		            String line;
		            while ((line = br.readLine()) != null) {
		                sb.append(line + "\n");
		            }
		            System.out.println(sb);
		            br.close();
		            String[] dropdownelement = sb.toString().replaceAll("\"", "").trim().split(",");
		            cntList = Arrays.asList(dropdownelement);
		            
		        } catch (MalformedURLException ex) {
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }finally {
		            if (request != null) {
		                try {
		                	((HttpURLConnection) request).disconnect();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
			
			
			Map<String, String> storageLoc = new HashMap<>();
			for(String temp : cntList) {
				storageLoc.put(temp.split("-")[0].trim(), temp.trim());
			}
			return new CustomResponseDto("storageLocation",storageLoc);
	}
	
	private List<AdvanceShipmentNoticeLineDto> validateSSN(String PONO, Long asnId,
			List<AdvanceShipmentNoticeLineDto> serviceLineList) {
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
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


		String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;
    //    String url ="https://172.18.2.29:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;

		
		System.out.println(url);
		ResponseDto resp = new ResponseDto();
		URLConnection request = null;
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

			JSONObject obj = new JSONObject(sb.toString());
			String Message = obj.getString("message");

			if (Message.equals("PO not yet released")) {

				resp.setMessage("PO not yet released ");
				resp.setHasError(true);

			} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
				resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
				resp.setHasError(true);
			}

			else {

				//JSONArray headerArray = obj.getJSONArray("HEADER");
				
				JSONObject headerArray = obj.getJSONObject("HEADER");

				for (int i = 0; i < headerArray.length(); i++) {
					//JSONObject currentObj = headerArray.getJSONObject(i);
					String contractPo = headerArray.getString("contractPo");
					//JSONArray poLimits = currentObj.getJSONArray("POLINELIST");

					//JSONObject poLimitsobject = poLimits.getJSONObject(0);

					if (contractPo.equals("Y")) {
						JSONArray poLineListArray = headerArray.getJSONArray("poLineList");
						{

							for (int k = 0; k < poLineListArray.length(); k++) {

								JSONObject poobject = poLineListArray.getJSONObject(k);
								{
									Double balancelimit = poobject.getDouble("balanceLimit");
									for (AdvanceShipmentNoticeLineDto contractpo : serviceLineList) {

										contractpo.getPoLine().getPurchaseOrder().setContractPo(contractPo);

										contractpo.getPoLine().getPurchaseOrder().setBalanceLimit(balancelimit);
									}
								}
							}
						}
					} else {
						for (int j = 0; j < headerArray.length(); j++) {
							JSONArray poLineListArray = headerArray.getJSONArray("poLineList");

							{

								for (int k = 0; k < poLineListArray.length(); k++) {

									JSONObject poobject = poLineListArray.getJSONObject(k);

									String poLineitemno = poobject.getString("lineItemNumber");

									// String asnLineNo = poline.getLineItemNumber();

									Double balanceQUANTITY1 = 0.0;
									try {

										JSONArray serviceLineListArray = poobject.getJSONArray("serviceList");

										{
											for (int l = 0; l < serviceLineListArray.length(); l++) {
												JSONObject serviceobject = serviceLineListArray.getJSONObject(l);
												String serviceLineitemno = serviceobject.getString("lineItemNumber");
												String serviceNAME = serviceobject.getString("name");
												balanceQUANTITY1 = serviceobject.getDouble("balanceQuantity1");
												String code = serviceobject.getString("code");
												// serviceLine.getPoLine().getName().equalsIgnoreCase(serviceNAME)

												if (serviceLineList.get(0).getPoLine()
														.getPurchaseOrderLineId() != null) {
													{
														for (AdvanceShipmentNoticeLineDto serviceLine : serviceLineList) {

															String poline = serviceLine.getPoLine().getParentPOLine()
																	.getLineItemNumber();
															if (serviceLine.getPoLine().getCode().equals(code)
																	&& serviceLine.getPoLine().getLineItemNumber()
																			.equals(serviceLineitemno)
																	&& poLineitemno.equals(poline)) {
																serviceLine.getPoLine()
																		.setBalanceQuantity1(balanceQUANTITY1);
															}
														}
													}
												}
											}
										}
									} catch (Exception e) {

									}

								}
							}
						}

					}
				}
			}

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
		return serviceLineList;
	}
	
	
	
	@PostMapping(value = "/getASNLineReport")
	public @ResponseBody CustomResponseDto getASNLineReport(@RequestBody ASNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeLineDto> asnLinereportlist = asnLineService.getASNLineReportbyFilter(dto);
		
		resp.addObject("asnLinereportlist", asnLinereportlist);
		
		return resp;
		// return new CustomResponseDto("AsnReportList", asnreport);
	}
	
	@PostMapping(value = "/getASNLineReportWithoutPO")
	public @ResponseBody CustomResponseDto getASNLineReportWithoutPO(@RequestBody ASNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeLineDto> asnLinereportlistWithoutPO = asnLineService.getASNLineReportwithoutPObyFilter(dto);
		
		resp.addObject("asnLinereportlistWithoutPO", asnLinereportlistWithoutPO);
		
		return resp;
		// return new CustomResponseDto("AsnReportList", asnreport);
	}
	
	@PostMapping(value = "/getSSNLineReport")
	public @ResponseBody CustomResponseDto getSSNLineReport(@RequestBody SSNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeLineDto> ssnLinereportlist = asnLineService.getSSNLineReportbyFilter(dto);
		
		resp.addObject("ssnLinereportlist", ssnLinereportlist);
		
		return resp;
		// return new CustomResponseDto("AsnReportList", asnreport);
	}
		
	}



	


	
	


	
		
	
	


