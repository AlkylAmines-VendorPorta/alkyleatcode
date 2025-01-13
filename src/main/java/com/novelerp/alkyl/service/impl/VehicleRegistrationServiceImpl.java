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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.controller.OutwardReadDto;
import com.novelerp.alkyl.dao.VehicleRegistrationDao;
import com.novelerp.alkyl.dao.impl.VehicleRegistrationDaoImpl;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.entity.VehicleRegistration;
import com.novelerp.alkyl.service.VehicleRegistrationService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service

public class VehicleRegistrationServiceImpl extends AbstractContextServiceImpl<VehicleRegistration, VehicleRegistrationDto> implements VehicleRegistrationService{

	@Autowired
	private VehicleRegistrationDao vehicleRegistrationDao;
	
	
	@Autowired
	@Lazy
	private VehicleRegistrationService vehicleRegistrationService;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(VehicleRegistrationServiceImpl.class, vehicleRegistrationDao, VehicleRegistration.class, VehicleRegistrationDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveVehicleRegistration(VehicleRegistrationDto vehicleRegistrationDto) {
		
		String reqNo=vehicleRegistrationDao.getNewReqNo();
		vehicleRegistrationDto.setRequestNo(reqNo);
		vehicleRegistrationDto.setStatus(AppBaseConstant.STATUSCREATED);
		save(vehicleRegistrationDto);
		
	}
	
	@Override
	public List<VehicleRegistrationDto> getOutwardReportbyFilter(OutwardReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
	//	System.out.println(dto.toString());
		//ASNReadDto getdto = getdto(dto);
		List<String> param = new ArrayList<>();
		if(dto.getStatus().equals("CREATED")) {
			param.add(AppBaseConstant.STATUSCREATED);
			params.put("status", param);
			
		}
		
		if(dto.getStatus().equals("VEHICLE REPORTED")) {
			param.add(AppBaseConstant.STATUSREPOTED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("VEHICLE GATE IN")) {
			param.add(AppBaseConstant.STATUSVEHICLEGATEIN);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("VEHICLE GATE OUT")) {
			param.add(AppBaseConstant.STATUSVEHICLEGATEOUT);
			params.put("status", param);
		}
		
		
		
		String query = vehicleRegistrationDao.getOutwardReportlist(dto);
		List<VehicleRegistrationDto> outwardList=findDtosByQuery(query, params);
		return outwardList;
	}
	private Map<String,Object> getParameterMap(OutwardReadDto dto){
		System.out.println(dto.toString());
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getSalesOrderNoFrom()!=null && dto.getSalesOrderNoTo()!=null){
			params.put("salesOrderNoFrom", dto.getSalesOrderNoFrom());
			params.put("salesOrderNoTo", dto.getSalesOrderNoTo());
   		}
   		if(dto.getSalesOrderNoFrom()!=null && dto.getSalesOrderNoTo()==null){
   			params.put("salesOrderNoFrom", dto.getSalesOrderNoFrom());
   		}
   		if(dto.getSalesOrderNoFrom()==null && dto.getSalesOrderNoTo()!=null){
   			params.put("salesOrderNoTo", dto.getSalesOrderNoTo());
   		}
   		if(dto.getRequestNoFrom()!=null && dto.getRequestNoTo()!=null){
			params.put("requestNoFrom", dto.getRequestNoFrom());
			params.put("requestNoTo", dto.getRequestNoTo());
   		}
   		if(dto.getRequestNoFrom()!=null && dto.getRequestNoTo()==null){
   			params.put("requestNoFrom", dto.getRequestNoFrom());
   		}
   		if(dto.getRequestNoFrom()==null && dto.getRequestNoTo()!=null){
   			params.put("requestNoTo", dto.getRequestNoTo());
   		}
   		if(dto.getRequestDateFrom()!=null && dto.getRequestDateTo()!=null){
   			//params.put("fromDate", dto.getAsnDateFrom());
			//params.put("toDate", dto.getAsnDateTo());
   			params.put("requestDateFrom", dto.getStartAsnDateFrom());
   			dto.setRequestDateFrom(dto.getStartAsnDateFrom());
   			params.put("requestDateTo", dto.getLastAsnDateTo());
   			dto.setRequestDateTo(dto.getLastAsnDateTo());
   		}
   		if(dto.getRequestDateFrom()!=null && dto.getRequestDateTo()==null){
   			params.put("requestDateFrom", dto.getStartAsnDateFrom());
			params.put("requestDateTo", dto.getLastAsnDateFrom());
			dto.setRequestDateFrom(dto.getStartAsnDateFrom());
			dto.setRequestDateTo(dto.getLastAsnDateFrom());
   		}
   		
   		if(dto.getRequestDateFrom()==null && dto.getRequestDateTo()!=null){
   			params.put("requestDateFrom", dto.getStartAsnDateTo());
			params.put("requestDateTo", dto.getLastAsnDateTo());
			dto.setRequestDateFrom(dto.getStartAsnDateTo());
			dto.setRequestDateTo(dto.getLastAsnDateTo());
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

   		if(dto.getFreightScope()!=null && !dto.getFreightScope().equals("")){
   			params.put("freightScope",dto.getFreightScope());
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}

		return params;
	}
	
//	@Async
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public CompletableFuture<Object> getVehicleRegistration(List<VehicleRegistrationDto> olddtoList) {
//		
//		CompletableFuture<Object> completableFuture = new CompletableFuture<>();
//		
//		CompletableFuture.runAsync(() -> {
//			
//		
//		for(VehicleRegistrationDto olddto:olddtoList) {
//			
//			String saleorderno=olddto.getSaleOrderNo();
//			
//			
//			
//			if(saleorderno!=null) {
//			// Create a trust manager that does not validate certificate chains
//	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
//	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//	                    return null;
//	                }
//	                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//	                }
//	                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//	                }
//	            }
//	        };
//	 
//	        // Install the all-trusting trust manager
//	        SSLContext sc = null;
//			try {
//				sc = SSLContext.getInstance("SSL");
//			} catch (NoSuchAlgorithmException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	        try {
//				sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			} catch (KeyManagementException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//	 
//	        // Create all-trusting host name verifier
//	        HostnameVerifier allHostsValid = new HostnameVerifier() {
//	            public boolean verify(String hostname, SSLSession session) {
//	                return true;
//	            }
//	        };
//	 
//	        // Install the all-trusting host verifier
//	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//			//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
//			
//			//String url="https://172.18.2.33:44300/sap/bc/zgate_web?sap-client=110&SALEORDERNO="+saleOrder;
//	        String url="https://172.18.2.36:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleorderno;
//			//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
//			URLConnection request=null;     
//			try {
//			
//		            URL u = new URL(url);
//		             request =  u.openConnection();
//
//		            request.connect();
//		            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		            StringBuilder sb = new StringBuilder();
//		            String line;
//		            while ((line = br.readLine()) != null) {
//		                sb.append(line + "\n");
//		            }
//		         //   System.out.println(sb);
//					br.close();
//				//	System.out.println(saleorderno);
//		      
//		            ObjectMapper objJson = new ObjectMapper();
//		            objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		            
//		            
//	                VehicleRegistrationDto newdtoList = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
//	                
//	              String Message= newdtoList.getMessage();
//	              if(Message!=null) {
//	              if(Message.equals("Invalid SalesOrder No.")) {
//	            	           
//	              }
//	              }
//	              
//	              if ((!Objects.equals(olddto.getRequiredOn(), newdtoList.getRequiredOn())) || (!Objects.equals(olddto.getPlant(), newdtoList.getPlant())) || (!Objects.equals(olddto.getShipToParty(), newdtoList.getShipToParty())) || (!Objects.equals(olddto.getDestination(), newdtoList.getDestination()) || (!Objects.equals(olddto.getTrasnporter(), newdtoList.getTrasnporter()))))
//	                {  
//	                Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("requiredOn", newdtoList.getRequiredOn());
//	                parameter.put("plant", newdtoList.getPlant());
//	    			parameter.put("shipToParty", newdtoList.getShipToParty());
//	                parameter.put("destination", newdtoList.getDestination());
//	                parameter.put("trasnporter", newdtoList.getTrasnporter());
//	                
//	    		 int vehicleRegistrationresult = vehicleRegistrationService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("vehicleRegistationId", olddto.getVehicleRegistationId()));	
//	    			 completableFuture.complete(vehicleRegistrationresult);
//	                }
//	              
//	          //     resp.addObject("registrationList",olddtoList);    
//		        } catch (MalformedURLException ex) {
//		            ex.printStackTrace();
//		            completableFuture.completeExceptionally(new RuntimeException("Calculation failed!"));
//		        } catch (IOException ex) {
//		            ex.printStackTrace();
//		        }finally {
//		            if (request != null) {
//		                try {
//		                	((HttpURLConnection) request).disconnect();
//		                } catch (Exception ex) {
//		                    ex.printStackTrace();
//		                }
//		            }
//		        }
//			
//			}
//			
//			}
//		
//		
//		}
//	
//		);
//		 
//		 return completableFuture;
//	//	 completableFuture.
////		Map<String, Object> params = new HashMap<String, Object>();
////	
////		//	return null;
////		List<VehicleRegistrationDto> updateddtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistration", params);
////		return CompletableFuture.completedFuture(updateddtoList);
//		
//		
//	
//	}
}
