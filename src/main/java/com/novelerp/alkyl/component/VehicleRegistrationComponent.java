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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.SAPSalesOrderDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.service.VehicleRegistrationService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.MailService;

@Component
public class VehicleRegistrationComponent {
	
	@Autowired
	private MailTemplateService mailTemplateService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private VehicleRegistrationService vehicleRegistrationService;
	
	public void sendVehicleGateOutMail(Long regID) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("vehicleRegistationId", regID);
			VehicleRegistrationDto dto=vehicleRegistrationService.findDto("getVehicleRegById", params);
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.VEHICLE_GATEOUT_TEMPLATE);
			if (mailTemplate != null) {
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();
				map.put("@plant@",dto.getPlant());
				map.put("@gstin@", dto.getGstin());
				map.put("@custNo@", dto.getCustNo());
				map.put("@consignee@", dto.getConsignee());
				map.put("@destination@", dto.getDestination());
				map.put("@invNo@", dto.getInvoiceNo());
				map.put("@invDate@", dto.getInvoiceDate());
				map.put("@poNo@", dto.getPoNo());
				map.put("@material@", dto.getMaterialCode());
				map.put("@qty@", dto.getQty());
				map.put("@uom@", dto.getUom());
				map.put("@localTrans@", dto.getLocalTransporter());
				map.put("@onwTrans@", dto.getOnwardTransporter());
				map.put("@vehicleNo@", dto.getVehicleRegistrationNo());
				map.put("@lrNo@", dto.getLrNo());
				map.put("@invAmt@", dto.getInvoiceAmt());
				mailTemplate.setFileName(dto.getInvoiceNo()+".pdf");
				mailTemplate.setFilePath(dto.getDocPic().getPath());
				List<String> toEmailIds = new ArrayList<>();
				toEmailIds.add(dto.getMeCode().getEmail());
				toEmailIds.add(dto.getSoldToEmail());
				
				

				List<String> ccEmailIds = new ArrayList<>();
				/*ccEmailIds.add(reqBy.getEmail());*/
				 ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
				// if (commercial != null) {
				// ccEmailIds.add(commercial.getEmail());
				// }

				mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public void sendMainONGateOUT() {
		MailTemplateDto mailTemplate = mailTemplateService
				.getMailTemplateFromTemplateType(AppBaseConstant.VEHICLE_GATEOUT_TEMPLATE);
		mailTemplate.setFileName("RCM POS.pdf");
		mailTemplate.setFilePath("D:/RCM POS.pdf");
		if (mailTemplate != null) {
			// Map<String, Object> params =
			// AbstractContextServiceImpl.getParamMap("plant",
			// asnDto.getPo().getPoLineList().get(0).getPlant());
			// params.put("roleValue",
			// AppBaseConstant.ROLE_COMMERCIAL_ADMIN);
			// UserDto commercial =
			// userService.findDto("getUserByRoleAndPlant", params);
			Map<String, Object> map = new HashMap<>();
			String subject = mailTemplate.getSubject();
			List<String> toEmailIds = new ArrayList<>();
			toEmailIds.add("sanjeev.m@novelerp.com");
			

			List<String> ccEmailIds = new ArrayList<>();
			//ccEmailIds.add(reqBy.getEmail());
			 /*ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);*/
			// if (commercial != null) {
			// ccEmailIds.add(commercial.getEmail());
			// }

			mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
		}
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public boolean saveInvoiceMailDetail(VehicleRegistrationDto dto,Long regNo) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("transportEmail",dto.getTransportEmail());
		param.put("shipToEmail", dto.getShipToEmail());
		param.put("soldToEmail", dto.getSoldToEmail());
		param.put("gstin", dto.getGstin());
		param.put("consignee", dto.getConsignee());
		param.put("uom", dto.getUom());
		param.put("localTransporter",dto.getLocalTransporter());
		param.put("onwardTransporter", dto.getOnwardTransporter());
		param.put("invoiceAmt",dto.getInvoiceAmt() );
		param.put("poNo",dto.getPoNo());
		param.put("custNo",dto.getCustNo());
		param.put("lrNo",dto.getLrNo());
		
		int result = vehicleRegistrationService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regNo));
		if (result > 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	public CustomResponseDto sendInvoiceToSAP(Long regID) {
		 CustomResponseDto resp=new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleRegistationId", regID);
		VehicleRegistrationDto dto=vehicleRegistrationService.findDto("getVehicleRegById", params);
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
//		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_04?saleorderno="+dto.getSaleOrderNo()+"&invno="+dto.getInvoiceNo()+"&meemail="+dto.getMeCode().getEmail();
		
	//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_04?saleorderno="+dto.getSaleOrderNo()+"&invno="+dto.getInvoiceNo()+"&meemail="+dto.getMeCode().getEmail();

       String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_34?sap-client=100&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
     //   String url="https://172.18.2.29:44300/sap/bc/yweb03_ws_34?sap-client=100&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
    
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
            br.close();
            JSONObject obj = new JSONObject(sb.toString());
            String Message=obj.getString("SUCCESS");
            if(Message.equals("MAIL SENT SUCCESSFULLY")) {
            	 resp = new CustomResponseDto(true, "MAIL SENT SUCCESSFULLY");
            }else {
            	 resp = new CustomResponseDto(false, "Mail Sending Failed in SAP");
            }
           

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
		
		return resp;
	}
	
	public CustomResponseDto sendInvoiceAndGateOutDetailsToSAP(Long regID) throws ParseException {
		
		CustomResponseDto resp=new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleRegistationId", regID);
		VehicleRegistrationDto dto=vehicleRegistrationService.findDto("getVehicleRegById", params);
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
        
        
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
      String GateoutDate = dateFormat.format(dto.getGateOutdate()); 
   

        
     //   String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_48?sap-client=100&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
     //   String url="https://172.18.2.29:44300/sap/bc/yweb03_OUT_48?sap-client=100&ORDER="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo()+"&QTY="+dto.getQty()+"&OUTDATE="+GateoutDate+"&plant="+dto.getPlant()+"&FLAG=Y";
      String url="https://172.18.2.36:44300/sap/bc/yweb03_OUT_48?sap-client=100&ORDER="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo()+"&QTY="+dto.getQty()+"&OUTDATE="+GateoutDate+"&plant="+dto.getPlant()+"&FLAG=Y";
		URLConnection request=null;     
	
		try {
			// URL u = new URL(url);
			 URL u = new URL(url.replace(" ","%20"));
			 
             request =  u.openConnection();

            request.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            JSONObject obj = new JSONObject(sb.toString());
            String Message=obj.getString("message");
            if(Message.equals("invoice not found ,PLS CHECK ..")) {
            	 resp = new CustomResponseDto(false, "invoice not found ,PLS CHECK ..");
            }else {
            	 resp = new CustomResponseDto(true, "invoice found successfully  ..");
            }


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
		return resp;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<SAPSalesOrderDto> getReqNoFromSalesOrder(List<SAPSalesOrderDto> dto) {
		List<SAPSalesOrderDto> saleOrderList= new ArrayList<>();
		for(SAPSalesOrderDto saleDto:dto){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("saleOrderID", saleDto.getSaleOrdNo());
			VehicleRegistrationDto vehicleDto=vehicleRegistrationService.findDto("getVehicleRegBySaleOrder", params);
			if(null!=vehicleDto){
				saleDto.setRequestNo(vehicleDto.getRequestNo());
			}
			saleOrderList.add(saleDto);
		}
		return saleOrderList;
		
		
		
	}
	
	public CustomResponseDto checkInvoiceFromSAP(Long regID,String invoiceNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		CustomResponseDto resp=new CustomResponseDto();
		params.put("vehicleRegistationId", regID);
		VehicleRegistrationDto dto=vehicleRegistrationService.findDto("getVehicleRegById", params);
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
//		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_04?saleorderno="+dto.getSaleOrderNo()+"&invno="+dto.getInvoiceNo()+"&meemail="+dto.getMeCode().getEmail();
		
	//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_04?saleorderno="+dto.getSaleOrderNo()+"&invno="+dto.getInvoiceNo()+"&meemail="+dto.getMeCode().getEmail();
    //    String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_04?sap-client=110&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
      //  String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_34?sap-client=100&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
    //    String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_34?sap-client=100&SALEORDERNO="+dto.getSaleOrderNo()+"&INVNO="+dto.getInvoiceNo();
        
        String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_42?sap-client=100&pono="+dto.getPoNo()+"&INVNO="+invoiceNo;
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
            br.close();
            JSONObject obj = new JSONObject(sb.toString());
            String Message=obj.getString("message");
            if(Message.equals("invoice not found ,PLS CHECK ..")) {
            	 resp = new CustomResponseDto(false, "invoice not found ,PLS CHECK ..");
            }else {
            	 resp = new CustomResponseDto(true, "invoice Matches");
            }


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
		return resp;
	}



}
