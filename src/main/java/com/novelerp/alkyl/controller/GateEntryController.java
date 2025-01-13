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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.poi.ss.formula.functions.Now;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.GetGSTINDetails;
import com.novelerp.alkyl.dto.CustomerDto;
import com.novelerp.alkyl.dto.GateEntryDto;

import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;

import com.novelerp.alkyl.dto.PrintGateEntryDetailDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SAPSalesOrderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.alkyl.dto.GateEntryLineDto;

import com.novelerp.alkyl.service.GateEntryLineService;
import com.novelerp.alkyl.service.GateEntryService;
import com.novelerp.alkyl.service.MaterialGateInListService;
import com.novelerp.alkyl.service.MaterialGateInService;
import com.novelerp.alkyl.service.PrintGateEntryDetailService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.dto.MaterialGateInListDto;

@Controller
@RequestMapping("/rest")
public class GateEntryController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GateEntryService gateEntryService;
	
	@Autowired
	private PrintGateEntryDetailService printGateEntryDetailsService;
	
	@Autowired
	private GateEntryLineService gateEntryLineService;
	
	@Autowired
	private MaterialGateInService materialGateInService;
	
	@Autowired
	private MaterialGateInListService materialGateInListService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@Autowired
	private GetGSTINDetails getGSTNDEtails;
	
	
	@PostMapping(value = "/saveGateEntry")
	public @ResponseBody CustomResponseDto saveGateEntry(@RequestBody GateEntryDto gateEntryDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			if(gateEntryDto.getPlant()!=null) {
			gateEntryService.save(gateEntryDto);
			resp.setSuccess(true);
			resp.setMessage("Request Created");
			
			}else {
				resp.setSuccess(false);
				resp.setMessage("Please Select Plant");
			}
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
			return resp;
		}

		return resp;
	}
	
	@PostMapping(value = "/updateGateEntry")
	public @ResponseBody CustomResponseDto updateGateEntry(@RequestBody GateEntryDto gateEntryDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			if(gateEntryDto.getPlant()!=null) {
			gateEntryService.saveGateEntry(gateEntryDto);
			resp.setSuccess(true);
			resp.setMessage("Request Updated");
		//	resp.setMessage("Request Updated and Approved Sucessfully");
			
			}else {
				resp.setSuccess(false);
				resp.setMessage("Please Select Plant");
			}
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
			return resp;
		}

		return resp;
	}
	
	
	@PostMapping(value = "/savePrintGateEntry")
	public @ResponseBody CustomResponseDto savePrintGateEntry(@RequestBody PrintGateEntryDetailDto printGateEntryDetailDto) {
	CustomResponseDto resp = new CustomResponseDto();
	try {
		printGateEntryDetailsService.save(printGateEntryDetailDto);
	} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		return resp;
		}
		resp.setSuccess(true);
		resp.setMessage("Request Created");

		return resp;
	}
	
	
	@PostMapping(value = "/getRGPPlant")
	public @ResponseBody CustomResponseDto getOnLoadMaster() {
		Map<String,String> plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		CustomResponseDto resp = new CustomResponseDto();
        resp.addObject("plantList",plantList);
        return resp;
	}
	@PostMapping(value = "/getAllGateEntry")
	public @ResponseBody CustomResponseDto getAllGateEntry() {
		CustomResponseDto resp = null;
	//	List<GateEntryDto> gateEntryListDto=gateEntryService.findDtos("getAllRecords", null);
		List<GateEntryDto> gateEntryListDto=gateEntryService.findDtos("getAllRecordsExceptClosed", null);
		resp = new CustomResponseDto("gateEntryList", gateEntryListDto);
		return resp;
	}
	
	@PostMapping(value = "/hodApproval/{id}")
	public @ResponseBody CustomResponseDto hodApproved(@PathVariable("id") Long gateEntryId) {
		User hod= new User();
		hod.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.HODAPPROVED);
		param.put("hod", hod);
		param.put("hodDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		
		AbstractContextServiceImpl.getParamMap("hod",hod);
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Approved Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving");
		}
		
	}
	
	@PostMapping(value = "/formNoPrint/{plant}/{form}")
	public @ResponseBody CustomResponseDto formNoPrint(@PathVariable("plant") String plant, @PathVariable("form") String form) {
		
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
        
    //    String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_32?sap-client=110&plant="+plant;
    //    String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_32?sap-client=100&plant="+plant;
        String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_32?sap-client=100&plant="+plant+"&form="+form;
        System.out.println(url);
		ResponseDto resp = new ResponseDto();
		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
			URL u = new URL(url.replace(" ","%20"));
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
			String formno = sb.toString().replaceAll("\"", "").trim();
		
			CustomResponseDto response = new CustomResponseDto("formno",formno);
			 return response;
			 
		}
		catch (MalformedURLException ex) {
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
		
		
		return null;
	}
	
	@PostMapping(value = "/hodReject/{id}/{remark}")
	public @ResponseBody CustomResponseDto hodApproved(@PathVariable("id") Long gateEntryId,
			@PathVariable("remark") String remark) {
		
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.HODREJECTED);
		param.put("hodRejectDesc", remark);
		int result = gateEntryService.updateByJpql(
				param,
				AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Rejected Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Cannot Reject");
		}

		
	}
	@PostMapping(value = "/commApproval/{id}")
	public @ResponseBody CustomResponseDto commApproval(@PathVariable("id") Long gateEntryId) {
		User commercial= new User();
		commercial.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.COMMERCIALAPPROVED);
		param.put("commercial", commercial);
		param.put("commercialDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Approved Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving");
		}
		
	}
	@PostMapping(value = "/commReject/{id}/{remark}")
	public @ResponseBody CustomResponseDto commReject(@PathVariable("id") Long gateEntryId,
			@PathVariable("remark") String remark) {
		
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.COMMERCIALREJECTED);
		param.put("commRejectDesc", remark);
		int result = gateEntryService.updateByJpql(
				param,
				AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Rejected Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Cannot Reject");
		}

		
	}
	@PostMapping(value = "/nrgpClosed/{id}")
	public @ResponseBody CustomResponseDto securityGateOut(@PathVariable("id") Long gateEntryId) {
		User closedBy= new User();
		closedBy.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.GATEENTRYCLOSED);
		param.put("closedBy", closedBy);
		param.put("closedDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "GateOut & Closed");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Closing");
		}
		
	}
	@PostMapping(value = "/getGateEntryLineByGateEntryId/{gateEntryId}")
	public @ResponseBody CustomResponseDto getGateEntryLineByGateEntryId(@PathVariable("gateEntryId") Long gateEntryId) {
		Map<String, Object> params= new HashMap<>();
		params.put("gateEntryId", gateEntryId);
		return new CustomResponseDto("gateEntryLineDto", gateEntryLineService.findDtos("getGateEntryLineByGateEntryId", params));
		
	}
	
	@PostMapping(value = "/getGateEntryLineByGateEntryIdTest/{gateEntryId}")
	public @ResponseBody CustomResponseDto getGateEntryLineByGateEntryIdTest(@PathVariable("gateEntryId") Long gateEntryId) {
		Map<String, Object> params= new HashMap<>();
		params.put("gateEntryId", gateEntryId);
		CustomResponseDto resp = new CustomResponseDto();
		
		List<GateEntryLineDto> gateEntryLineDtoTest=gateEntryLineService.findDtos("getGateEntryLineByGateEntryIdTest", params);
		resp = new CustomResponseDto("gateEntryLineDtoTest", gateEntryLineDtoTest);
		return resp;
	}
	
	@PostMapping(value = "/rgpGateIn")
	public @ResponseBody CustomResponseDto rgpGateIn(@RequestBody GateEntryDto gateEntryDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			gateEntryLineService.updateGateIn(gateEntryDto);
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
			return resp;
		}
		resp.setSuccess(true);
		resp.setMessage("Gate In Success");
		return resp;
	}

	
	@PostMapping(value = "/rgpGateout/{id}")
	public @ResponseBody CustomResponseDto rgpGateout(@PathVariable("id") Long gateEntryId) {
		User gateOut= new User();
		gateOut.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SECURITYGATEOUT);
		param.put("gateOut", gateOut);
		param.put("gateOutDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "GateOut & Closed");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Closing");
		}
		
	}
	
	@PostMapping(value = "/functionalApproval/{id}")
	public @ResponseBody CustomResponseDto functionalApproval(@PathVariable("id") Long gateEntryId) {
		User fh= new User();
		fh.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.FHAPPROVED);
		param.put("fh", fh);
		param.put("fhDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Approved Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving");
		}
		
	}
	@PostMapping(value = "/functionalReject/{id}/{remark}")
	public @ResponseBody CustomResponseDto functionalReject(@PathVariable("id") Long gateEntryId,
			@PathVariable("remark") String remark) {
		
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.FHREJECTED);
		param.put("rejectDesc", remark);
		int result = gateEntryService.updateByJpql(
				param,
				AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Rejected Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Cannot Reject");
		}

		
	}
	
	
	@PostMapping(value = "/plantheadApproval/{id}")
	public @ResponseBody CustomResponseDto plantheadApproval(@PathVariable("id") Long gateEntryId) {
		User planthead= new User();
		planthead.setUserId(contextService.getUser().getUserId());
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.PLANTHEADAPPROVED);
		param.put("planthead", planthead);
		param.put("plantheadDate", new Date());
		int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Approved Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving");
		}
		
	}
	
	@PostMapping(value = "/plantheadReject/{id}/{remark}")
	public @ResponseBody CustomResponseDto plantheadReject(@PathVariable("id") Long gateEntryId,
			@PathVariable("remark") String remark) {
		
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.PLANTHEADREJECTED);
		param.put("rejectDesc", remark);
		int result = gateEntryService.updateByJpql(
				param,
				AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Rejected Successfully");
			return resp;
		} else {
			return new CustomResponseDto(false, "Cannot Reject");
		}

		
	}
	
	@PostMapping(value = "/getAllMaterialGAteIn")
	public @ResponseBody CustomResponseDto getAllMaterialGAteIn() {
		CustomResponseDto resp = null;
	//	List<MaterialGateInDto> dto=materialGateInService.findDtos("getAllRecords", null);
		List<MaterialGateInDto> dto=materialGateInService.findDtos("getAllRecordsExceptClosed", null);
		resp = new CustomResponseDto("materialGateInLIst", dto);
		return resp;
	}
	
	@PostMapping(value = "/getMaterialLineId/{Id}")
	public @ResponseBody CustomResponseDto getMaterialLineId(@PathVariable("Id") Long Id) {
		Map<String, Object> params= new HashMap<>();
		params.put("materialGateIn", Id);
		return new CustomResponseDto("materialLine", materialGateInListService.findDtos("getMaterialGateInLIst", params));
	}
	
	@PostMapping(value = "/materialCheck")
	public @ResponseBody CustomResponseDto saveMaterialGateEntry(@RequestBody MaterialGateInDto materialGateInDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			materialGateInService.updateRejectQty(materialGateInDto);
			materialGateInService.UpdateMaterialGateIn(materialGateInDto);
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
			return resp;
		}
		resp.setSuccess(true);
		resp.setMessage("Submit Successfully");
		return resp;
	}
	@PostMapping(value = "/closeMaterialGateIn")
	public @ResponseBody CustomResponseDto closeMaterialGateIn(@RequestBody MaterialGateInDto materialGateInDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			materialGateInService.closeMaterialGateIn(materialGateInDto);
			
			for(MaterialGateInListDto lineDto:materialGateInDto.getMaterialGateInList()) {
				materialGateInService.closedGateEntryUpdate(lineDto);
			}
			
			materialGateInService.closedMaterialGateInUpdate(materialGateInDto);
			
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
			return resp;
		}
		resp.setSuccess(true);
		resp.setMessage("Closed Successfully");
		return resp;
	}
	@PostMapping(value = "/getGSTNDetails/{Id}")
	public @ResponseBody CustomResponseDto getGSTNDetails(@PathVariable("Id") String Id) {
		CustomResponseDto resp = null;
		boolean message= getGSTNDEtails.getAuth(Id);
		resp = new CustomResponseDto("materialGateInLIst", message);
		return resp;
	}
	
	@PostMapping(value = "/getReqStatus")
	public @ResponseBody CustomResponseDto getOnLOadMaster() {
		Map<String,String> statusList =refListService.getReferenceListMap(CoreReferenceConstants.REQ_STATUS);
		CustomResponseDto resp = new CustomResponseDto();
        resp.addObject("statusList",statusList);
        return resp;
	}
	
	@PostMapping(value = "/getGateEntryByFilter")
	public @ResponseBody CustomResponseDto getGateEntryByFilter(@RequestBody GateEntryReadDto dto) {
		String role=null;
		CustomResponseDto resp = new CustomResponseDto();
	//	List<GateEntryDto> gateEntryListDto=gateEntryService.getGateEntryByFilter(dto);
		List<GateEntryLineDto> gateEntryLineListDto=gateEntryLineService.getGateEntryByFilter(dto);
		resp.addObject("gateEntryListDto", gateEntryLineListDto);

		return resp;
	}
	
	
	@PostMapping(value = "/getGateEntryMaterialByFilter")
	public @ResponseBody CustomResponseDto getGateEntryMaterialByFilter(@RequestBody GateEntryReadDto dto) {
		String role=null;
		CustomResponseDto resp = new CustomResponseDto();
		List<MaterialGateInListDto> materialgateEntryLineListDto=materialGateInListService.getGateEntryMaterialByFilter(dto);
		
		resp.addObject("materialgateEntryLineListDto", materialgateEntryLineListDto);
		

		return resp;
	}
	
	@PostMapping(value = "/getRGPReportByFilter")
	public @ResponseBody CustomResponseDto getRGPReportByFilter(@RequestBody GateEntryReadDto dto) {
		String role=null;
		CustomResponseDto resp = new CustomResponseDto();

		List<GateEntryLineDto> gateEntryLineListDto=gateEntryLineService.getGateEntryRGPByFilter(dto);
		resp.addObject("gateEntryListDto", gateEntryLineListDto);
		
//		List<MaterialGateInListDto> materialgateEntryLineListDto=materialGateInListService.getGateEntryMaterialByFilter(dto);
//		
//		
//	
//		resp.addObject("materialgateEntryLineListDto", materialgateEntryLineListDto);
		

		return resp;
	}
	
	
	@PostMapping(value = "/getVendorDataSAP/{vendorCode}")
	public @ResponseBody CustomResponseDto getVendorDataSAP(@PathVariable("vendorCode") String vendorCode) {
		CustomResponseDto resp = new CustomResponseDto();
		
		//Map<String, Object> params = new HashMap<String, Object>();
		
	//	List<String> param = new ArrayList<>();

	//	params.put("outboundDeliverNo", outboundDeliverNo);
				
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
					
					String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_29?sap-client=100&vendor="+vendorCode;
					System.out.println(url);

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
						System.out.println(obj);
						
						String Message = obj.getString("message");

						if (Message.equals("Vendor Not found in SAP..")) {

							resp.setMessage("Vendor Not found in SAP..");
							resp.setSuccess(false);

						}
						else {
						String vendorName = obj.getString("name");
						String street=obj.getString("street");
						String City=obj.getString("City");
						String Postcode=obj.getString("Postcode");
						
						String vendorAddress=street+","+ City+","+Postcode;
						
						Map<String, String> VendorData = new HashMap<>();
						VendorData.put("vendorName", vendorName);
						VendorData.put("vendorAddress", vendorAddress);
						resp.addObject("VendorData", VendorData);
						resp.setSuccess(true);
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
		

		
		return resp;

	}
	
	
	@PostMapping(value = "/getTransporterDataSAP")
	public @ResponseBody CustomResponseDto getTransporterDataSAP() {
		CustomResponseDto resp = new CustomResponseDto();
		
		//Map<String, Object> params = new HashMap<String, Object>();
		
	//	List<String> param = new ArrayList<>();

	//	params.put("outboundDeliverNo", outboundDeliverNo);
				
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
					
					String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_40?sap-client=100";
					System.out.println(url);
					
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
						
						JSONArray array=new JSONArray(sb.toString());
						List<String> transporterData = new ArrayList<>();
						for(int n = 0; n < array.length(); ++n)
						{
							
						    JSONObject obj = array.getJSONObject(n);
							String vendorName = obj.getString("name");
							String City=obj.getString("City");
							transporterData.add(vendorName);
							
							
						}
						resp.addObject("TransporterData", transporterData);

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


		
		return resp;

	}
	
	
	@PostMapping(value = "/cancelGateEntryRequest/{id}/{remarks}")
	public @ResponseBody CustomResponseDto cancelRGPRequest(@PathVariable("id") Long gateEntryId,
			@PathVariable("remarks") String remarks) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.GATEENTRYCANCELED);
		param.put("cancelDesc", remarks);
		int result = gateEntryService.updateByJpql(param,
		AbstractContextServiceImpl.getParamMap("gateEntryId", gateEntryId));
	if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Request Canceled.");
			resp.addObject("status", AppBaseConstant.GATEENTRYCANCELED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Canceling RGP Request");
		}

	}

}
