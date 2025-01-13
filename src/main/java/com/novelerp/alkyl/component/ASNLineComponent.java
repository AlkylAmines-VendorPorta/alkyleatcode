package com.novelerp.alkyl.component;

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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.sap.document.sap.rfc.ses.ServiceObject;

@Component
public class ASNLineComponent {

	@Autowired
	private AdvanceShipmentNoticeService asnService;

	@Autowired
	private AdvanceShipmentNoticeLineService asnLineService;

	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private ValidationUtil validationUtil;

	public boolean unloadASN(AdvanceShipmentNoticeDto dto) {
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", dto.getAdvanceShipmentNoticeId()));

		return asnLineService.unloadASN(dto.getAsnLineList(), asn);
	}

	public boolean unloadASN105(AdvanceShipmentNoticeDto dto) throws Exception {
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", dto.getAdvanceShipmentNoticeId()));
		asn.setPostingDate(dto.getPostingDate());

		return asnLineService.unloadASN105(dto.getAsnLineList(), asn);
	}
	
	
	public boolean unloadASN101(AdvanceShipmentNoticeDto dto) throws Exception {
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId101",
				AbstractContextServiceImpl.getParamMap("asnId", dto.getAdvanceShipmentNoticeId()));
		asn.setPostingDate(dto.getPostingDate());
	
		return asnLineService.unloadASN101(dto.getAsnLineList(), asn);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public AdvanceShipmentNoticeLineDto getASNLine(Long asnLineId) {
		return asnLineService.findDto("getASNLineByASNLineId",
				AbstractContextServiceImpl.getParamMap("asnLineId", asnLineId));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public List<AdvanceShipmentNoticeLineDto> getASNLineByASNId(Long asnId) {
		return asnLineService.findDtos("getASNLinesByASNId", AbstractContextServiceImpl.getParamMap("asnId", asnId));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public List<AdvanceShipmentNoticeLineDto> getServiceLineByASNId(Long asnId) {
		return asnLineService.findDtos("getServiceLineByASNId", AbstractContextServiceImpl.getParamMap("asnId", asnId));
	}

	public boolean qcPassASN(AdvanceShipmentNoticeDto dto) {

		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", dto.getAdvanceShipmentNoticeId()));

		return asnLineService.qcPassASN(dto.getAsnLineList(), asn);
	}

	public boolean qcFailASN(Long asnId, String remark) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("asnId", asnId);
		List<AdvanceShipmentNoticeLineDto> lineDtos = asnLineService.findDtos("getASNLinesByASNId", params);
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		return asnLineService.qcFailASN(lineDtos, asn, remark);
	}

	public List<AdvanceShipmentNoticeLineDto> getASNLineForASNCreation(Long asnId) {
		List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineService.findDtos("getASNLinesByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		List<PurchaseOrderLineDto> poLineList = poLineService.findDtos("getPOLineForASNLine",
				AbstractContextServiceImpl.getParamMap("poId", asn.getPo().getPurchaseOrderId()));

		/*for (AdvanceShipmentNoticeLineDto asnLinedto : asnLineList) {
			// AdvanceShipmentNoticeLineDto asnLinestorage = new
			// AdvanceShipmentNoticeLineDto();

			System.out.println(asnLineList.get(0).getPoLine().getPurchaseOrder().getPurchaseOrderNumber());
			System.out.println(asnLineList.get(0).getPoLine().getCode());
			System.out.println(asnLineList.get(0).getPoLine().getPlant());
			// String
			// url="http://103.231.11.54:8000/sap/bc/yweb03_ws_17?sap-client=009&order=6500019280&matnr=702116&plant=1840";
			String url = "http://103.231.11.54:8000/sap/bc/yweb03_ws_17?sap-client=009&order="
					+ asnLinedto.getPoLine().getPurchaseOrder().getPurchaseOrderNumber() + "&matnr="
					+ asnLinedto.getPoLine().getCode() + "&plant=" + asnLinedto.getPoLine().getPlant();

			List<String> cntList = new ArrayList<>();
			cntList.add("");
//			URLConnection request = null;
//			try {
//				URL u = new URL(url);
//				request = u.openConnection();
//				request.connect();
//				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//				StringBuilder sb = new StringBuilder();
//				String line;
//				while ((line = br.readLine()) != null) {
//					sb.append(line + "\n");
//				}
//				System.out.println(sb);
//				br.close();
//				String[] dropdownelement = sb.toString().replaceAll("\"", "").trim().split(",");
//				cntList = Arrays.asList(dropdownelement);
//
//			} catch (MalformedURLException ex) {
//				ex.printStackTrace();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			} finally {
//				cntList.add("");
//				if (request != null) {
//					try {
//						((HttpURLConnection) request).disconnect();
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//			}

			Map<String, String> storageLoc = new HashMap<>();
			for (String temp : cntList) {
				storageLoc.put(temp.trim(), "");
			}
			asnLinedto.setStorageLocation(cntList.get(0));
		}*/

		if (!CommonUtil.isCollectionEmpty(poLineList)) {
			if (asnLineList == null) {
				asnLineList = new ArrayList<>();
			}
			for (PurchaseOrderLineDto poLine : poLineList) {
				AdvanceShipmentNoticeLineDto asnLine = new AdvanceShipmentNoticeLineDto();

				asnLine.setPoLine(poLine);
				asnLineList.add(asnLine);
			}
		}

		return asnLineList;
	}
	
	public List<AdvanceShipmentNoticeLineDto> getASNLineForASNPrint(Long asnId) {
		List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineService.findDtos("printASNByASNNOforSecurityNew",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
//		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
//				AbstractContextServiceImpl.getParamMap("asnId", asnId));
//		List<PurchaseOrderLineDto> poLineList = poLineService.findDtos("getPOLineForASNLine",
//				AbstractContextServiceImpl.getParamMap("poId", asn.getPo().getPurchaseOrderId()));

//		if (!CommonUtil.isCollectionEmpty(poLineList)) {
//			if (asnLineList == null) {
//				asnLineList = new ArrayList<>();
//			}
//			for (PurchaseOrderLineDto poLine : poLineList) {
//			AdvanceShipmentNoticeLineDto asnLine = new AdvanceShipmentNoticeLineDto();
//
//			asnLine.setPoLine(poLine);
//			asnLineList.add(asnLine);
//			}
//		}

		return asnLineList;
	}
	
	public List<AdvanceShipmentNoticeLineDto> getMainASNLineForASNCreation(Long asnId,String status) {
		
		
		
		List<AdvanceShipmentNoticeLineDto> asnLineList=new ArrayList<>();
		if(status.equals(AppBaseConstant.ASN_STATUS_103_Posted) || status.equals(AppBaseConstant.ASN_STATUS_GATE_OUT)) {
			 asnLineList = asnLineService.findDtos("getASNLinesByASNId",
					AbstractContextServiceImpl.getParamMap("asnId", asnId));
		}
		else {
		    asnLineList = asnLineService.findDtos("getASNLinesByASNIdMain",AbstractContextServiceImpl.getParamMap("asnId", asnId));
		}
		
		
		
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNIdforMain",AbstractContextServiceImpl.getParamMap("asnId", asnId));
		if(asn.getPo()!=null) {
		List<PurchaseOrderLineDto> poLineList = poLineService.findDtos("getPOLineForASNLineCommercial",AbstractContextServiceImpl.getParamMap("poId", asn.getPo().getPurchaseOrderId()));
		
		
		if (!CommonUtil.isCollectionEmpty(poLineList)) {
			if (asnLineList == null) {
				asnLineList = new ArrayList<>();
			}
//			for (PurchaseOrderLineDto poLine : poLineList) {
//				SecurityPOItemDto asnLine = new SecurityPOItemDto();
//
//				asnLine.setPoLine(poLine);
//				asnLineList.add(asnLine);
//			}
		}
		}
		return asnLineList;
	}


	public List<AdvanceShipmentNoticeLineDto> getServiceLineForASNCreation(Long asnId) {
		List<AdvanceShipmentNoticeLineDto> serviceLineList = asnLineService.findDtos("getServiceLineByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));

		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));

		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("poId", asn.getPo().getPurchaseOrderId());
		params.put("asnId", asnId);
		List<PurchaseOrderLineDto> serviceList = poLineService.findDtos("getServiceListByPOIdASNId", params);
		if (!CommonUtil.isCollectionEmpty(serviceList)) {
			if (serviceLineList == null) {
				serviceLineList = new ArrayList<>();
			}
			for (PurchaseOrderLineDto service : serviceList) {
				AdvanceShipmentNoticeLineDto asnLine = new AdvanceShipmentNoticeLineDto();
				asnLine.setPoLine(service);
				serviceLineList.add(asnLine);
			}
		}
		return serviceLineList;
	}
	
	public CustomResponseDto sendServiceApprovedDataToSAP(String json, Map<String, Object> param, AdvanceShipmentNoticeDto asn, Errors errors) {
	
		
		CustomResponseDto resp=new CustomResponseDto();
		
		String username=param.get("user").toString();
		String password=param.get("pass").toString();
	
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

   
     //   String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_65?sap-client=100&JSON="+json;
        String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_65?sap-client=100&JSON="+json;
		URLConnection request=null;     

	
		try {
//			 URL u = new URL(url);
			 URL u = new URL(url.replace(" ","%20"));
            
             
             request = u.openConnection();
       
				request.setRequestProperty("Accept", "application/json");
			     // snippet begins
			     request.setRequestProperty("Authorization",
			       "Basic " + Base64.getEncoder().encodeToString(
			         (username + ":" + password).getBytes()
			       )
			     );

            request.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            JSONObject obj = new JSONObject(sb.toString());
            String message = obj.getString("message");
			if(message.equals("Not updated Unsuccessfully")) {

				String error = obj.getString("ERROR");	
	//			String error = obj.getString("message");	
			//	resp.addObject("error", error);
			//	return new CustomResponseDto(false, error);
				validationUtil.reject(errors, error, error);
				
			}else {
				String doctype = obj.getString("Service sheet No");
				resp.addObject("doctype", doctype);
				asnService.updateByJpql(
						
						AbstractContextServiceImpl.getParamMap("sap103Id", doctype),

						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
				return new CustomResponseDto(true, "Updated successfully");
				
			}
//            String Message=obj.getString("message");
//            if(Message.equals("Updated successfully")) {
//            	// resp = new CustomResponseDto(true, "Updated successfully");
//            	 return new CustomResponseDto(true, "Updated successfully");
//            }else {
////            	 resp = new CustomResponseDto(false, "Updation Failed");
//            	 return new CustomResponseDto(false, "Updation Failed");
//            }


        } 
		catch (MalformedURLException ex) {
            ex.printStackTrace();
            return new CustomResponseDto(false, ex.getMessage());
        } 
		catch (IOException ex) {
			ex.printStackTrace();
			//	throw new RuntimeException("Error while writing file");
				return new CustomResponseDto(false, "Invalid SAP Login credentials");
						
		}
		finally {
            if (request != null) {
                try {
                	((HttpURLConnection) request).disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
	//	return true;
		//return false;
		return resp;
	}


}
