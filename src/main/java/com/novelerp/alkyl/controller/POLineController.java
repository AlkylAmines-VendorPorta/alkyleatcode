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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class POLineController {

	@Autowired
	private PurchaseOrderLineService poLineService;
	@Autowired
	private ReferenceListService refListService;
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	@PostMapping(value="/getPOLines/{poId}")
	public @ResponseBody CustomResponseDto getPOLines(@PathVariable("poId") Long poId){
		List<PurchaseOrderLineDto> poLineList = poLineService.findDtos("getPOLineByPOId", AbstractContextServiceImpl.getParamMap("poId", poId));
		List<PurchaseOrderLineDto> serviceLineList = poLineService.findDtos("getServicesByPOId", AbstractContextServiceImpl.getParamMap("poId", poId));
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
		
		
		String plant=poLineList.get(0).getPlant();
	
//		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_20?sap-client=009&PLANT="+plant;
	//String url=	"https://172.18.2.28:44300/sap/bc/yweb03_ws_20?sap-client=100&order=&plant=0001";
	 
		String order=poLineList.get(0).getPurchaseOrder().getPurchaseOrderNumber();
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
		
		
		String pono=poLineList.get(0).getPurchaseOrder().getPurchaseOrderNumber();
		//String doctyp=poLineList.get(0).getPurchaseOrder().getDoctyp();
		//List<PurchaseOrderLineDto> validatePoSSN = validatePoSSN(pono,poId,doctyp,serviceLineList);
		List<PurchaseOrderLineDto> validatePoSSN = validatePoSSN(pono,poId,serviceLineList);
		
		//Map<String, String> ssnFundList = getssnFundsfromSAP();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", AppBaseConstant.SSNVERSION);
		SystemConfiguratorDto sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
		
		Map<String, String> ssnApproverList = refListService.getReferenceListMap(CoreReferenceConstants.SSN_APPROVER_HEAD_LIST);
		
		CustomResponseDto resp = new CustomResponseDto("poLineList", poLineList);
		resp.addObject("serviceLineList", validatePoSSN);
		resp.addObject("costCenter", costList);
		resp.addObject("SSNVersion", sysConfig.getName());
		resp.addObject("ssnApproverList", ssnApproverList);
		//resp.addObject("ssnFundList", ssnFundList);
		return resp;
	}
	
	@PostMapping(value="/getServicesListByPOLineId/{poLineId}")
	public @ResponseBody CustomResponseDto getServicesListByPOLineId(@PathVariable("poLineId") Long poLineId){
		List<PurchaseOrderLineDto> serviceList = poLineService.findDtos("getServicesByPOLineId", AbstractContextServiceImpl.getParamMap("poLineId", poLineId));
		return new CustomResponseDto("serviceList", serviceList);
	}
	
	
	private List<PurchaseOrderLineDto> validatePoSSN(String PONO, Long poId,List<PurchaseOrderLineDto> serviceLineList) {


		String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;
	//	String url ="https://172.18.2.29:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;
	
	
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
			} else {

				//JSONArray headerArray = obj.getJSONArray("HEADER");
				
				JSONObject headerArray = obj.getJSONObject("HEADER");
				
				ObjectMapper mapper = new ObjectMapper();

				for (int i = 0; i < headerArray.length(); i++) {
//					JSONObject currentObj = headerArray.getJSONObject(i);
					String contractPo = headerArray.getString("contractPo");
					

					if (contractPo.equals("Y")) {

						JSONArray poLineListArray = headerArray.getJSONArray("poLineList");
						{

							for (int k = 0; k < poLineListArray.length(); k++) {

								JSONObject poobject = poLineListArray.getJSONObject(k);
								{
									Double balancelimit = poobject.getDouble("balanceLimit");
									for (PurchaseOrderLineDto contractpo : serviceLineList) {

										contractpo.getPurchaseOrder().setContractPo(contractPo);
										contractpo.getPurchaseOrder().setBalanceLimit(balancelimit);

									}
								}
							}
						}
					} else {
						
						
//						JSONObject headerArray = obj.getJSONObject("HEADER");
						
//						ObjectMapper mapper = new ObjectMapper();
//						mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//						PurchaseOrderDto[] dto=mapper.readValue(headerArray.toString(), PurchaseOrderDto[].class);
//						System.out.println(dto);

//						 for (int i = 0; i < headerArray.length(); i++) {
//						 JSONObject currentObj = headerArray.getJSONObject(i);

						for (int j = 0; j < headerArray.length(); j++) {
							JSONArray poLineListArray = headerArray.getJSONArray("poLineList");
							{

								for (int k = 0; k < poLineListArray.length(); k++) {

									JSONObject poobject = poLineListArray.getJSONObject(k);

									String poLineitemno = poobject.getString("lineItemNumber");
//									String blocked=poobject.getString("blocked");

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

												if (serviceLineList.get(0).getPurchaseOrderLineId() != null) {

													{
														for (PurchaseOrderLineDto serviceLine : serviceLineList) {

															String poline = serviceLine.getParentPOLine()
																	.getLineItemNumber();

															if (serviceLine.getCode().equals(code)
																	&& serviceLine.getLineItemNumber()
																			.equals(serviceLineitemno)
																	&& poLineitemno.equals(poline)) {
																serviceLine.setBalanceQuantity1(balanceQUANTITY1);

															}
															
//															if(blocked.equals("B")) {
//																serviceLine.setIsActive("N");
//															}
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
	
	
	//@PostMapping(value="/getssnFundsfromSAP")
	public @ResponseBody Map<String, String> getssnFundsfromSAP(){
		CustomResponseDto resp = new CustomResponseDto();

		String fundurl="https://172.18.2.29:44300/sap/bc/yweb03_WS_49?sap-client=100";
		//	String fundurl="https://172.18.2.36:44300/sap/bc/yweb03_WS_49?sap-client=100";
				List<String> fundList = new ArrayList<>();
				URLConnection fundrequest=null;   
				try {
			            URL u = new URL(fundurl);
			            fundrequest =  u.openConnection();

			            fundrequest.connect();


			            BufferedReader br = new BufferedReader(new InputStreamReader(fundrequest.getInputStream()));
			            StringBuilder sb = new StringBuilder();
			            String line;
			            while ((line = br.readLine()) != null) {
			                sb.append(line + "\n");
			            }
			            System.out.println(sb);
			            br.close();
			            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
			         //   String[] dropdownelement = sb.toString().replaceAll("\"", "").split(";");
			            fundList = Arrays.asList(dropdownelement);
			            
			        } catch (MalformedURLException ex) {
			            ex.printStackTrace();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }finally {
			            if (fundrequest != null) {
			                try {
			                	((HttpURLConnection) fundrequest).disconnect();
			                } catch (Exception ex) {
			                    ex.printStackTrace();
			                }
			            }
			        }
				
				Map<String, String> ssnFundList = new HashMap<>();
				for(String temp : fundList) {
					ssnFundList.put(temp, temp.trim());
				//	ssnFundList.put(temp.split("-")[0].trim(), temp.trim());
				}

		
		return ssnFundList;
//				resp.addObject("ssnFundList", ssnFundList);
//                return resp;

	}

	
}