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
import java.util.Base64;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dao.AnnexureDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.dto.QCFApproverSAPDto;
import com.novelerp.alkyl.entity.Annexure;
import com.novelerp.alkyl.service.AnnexureService;
import com.novelerp.alkyl.service.PraposedReasonService;
import com.novelerp.alkyl.service.WinnerSelectionService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.BidderService;
import com.sap.document.sap.rfc.functions.ZQCFAnnexureClient;

@Service
public class AnnexureServiceImpl extends AbstractContextServiceImpl<Annexure, AnnexureDto> implements AnnexureService {

	@Autowired
	private AnnexureDao annexureDao;

	@Autowired
	private PraposedReasonService praposedReasonService;

	@Autowired
	private WinnerSelectionService winnerSelectionService;

	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ZQCFAnnexureClient webclient;
	
	@Autowired
	private AnnexureService annexureService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(AnnexureServiceImpl.class, annexureDao, Annexure.class, AnnexureDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AnnexureDto saveAnnexure(AnnexureDto annexureDto) {
		if (annexureDto.getPraposedReasonSet().isEmpty()) {
			throw new RuntimeException("Praposed reason cannot be null");
		}
		
//		if (annexureDto.getPraposedReasonSet().get(0).getCode().isEmpty()) {
//			throw new RuntimeException("Praposed reason cannot be null");
//		}
		
		if (annexureDto.getWinnerSelectionSet().isEmpty()) {
			throw new RuntimeException("Praposed Quantity cannot be null");
		}
		if (annexureDto.getBidder().isEmpty()) {
			throw new RuntimeException("Bidder split value cannot be null");
		}
		List<PraposedReasonDto> praposedResponseList = annexureDto.getPraposedReasonSet();
		List<WinnerSelectionDto> winnerSelectionDtoResponse = annexureDto.getWinnerSelectionSet();
		List<BidderDto> bidderList = annexureDto.getBidder();
		annexureDto.setPraposedReasonSet(null);
		annexureDto.setWinnerSelectionSet(null);
		annexureDto.setBidder(null);
		if (null == annexureDto.getAnnexureId()) {
			annexureDto = super.save(annexureDto);
		} else {
			annexureDto = super.updateDto(annexureDto);
		}
		praposedResponseList = praposedReasonService.savePraposedReason(praposedResponseList, annexureDto);
		winnerSelectionDtoResponse = winnerSelectionService.saveWinnerSelection(winnerSelectionDtoResponse,
				annexureDto);
		bidderList = bidderService.updateTotalSplitValue(bidderList);
		annexureDto.setPraposedReasonSet(praposedResponseList);
		annexureDto.setWinnerSelectionSet(winnerSelectionDtoResponse);
		annexureDto.setBidder(bidderList);
		return annexureDto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto updateAnnexureApproval(Long annexureId) {
		// TODO Auto-generated method stub
		long result = 0;
		HashMap<String, Object> annexureMap = new HashMap<>();

		annexureMap.put("status", AppBaseConstant.ANNEXURE_APPROVE_STATUS);
		/*annexureMap.put("qcfFormat",attid);*/

		result = updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));

		if (result > 0) {
//			try {
//				webclient.postQCFAnnexureDataToSAP(annexureId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return new ResponseDto(false, "Record Updated");
		}

		return new ResponseDto(true, "Failed Updated");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto updateAnnexureReject(Long annexureId, String remarks) {
		// TODO Auto-generated method stub
		long result = 0;
		HashMap<String, Object> annexureMap = new HashMap<>();
		
		User rejectedby= new User();
		rejectedby.setUserId(contextService.getUser().getUserId());

		annexureMap.put("status", AppBaseConstant.ANNEXURE_REJECT_STATUS);
		annexureMap.put("remark", remarks);
		annexureMap.put("rejectedby", rejectedby);
		
	
		result = annexureService.updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));

		if (result > 0) {
			return new ResponseDto(false, "Record Updated");
		}

		return new ResponseDto(true, "Failed Updated");
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto updateFinalAnnexureReject(Long annexureId, String remarks) {
		// TODO Auto-generated method stub
		long result = 0;
		HashMap<String, Object> annexureMap = new HashMap<>();
		
//		User rejectedby= new User();
//		rejectedby.setUserId(contextService.getUser().getUserId());

		annexureMap.put("status", AppBaseConstant.ANNEXURE_REJECT_STATUS);
		annexureMap.put("remark", remarks);
//		annexureMap.put("rejectedby", rejectedby);
		
	
		result = annexureService.updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));

		if (result > 0) {
			return new ResponseDto(false, "Record Updated");
		}

		return new ResponseDto(true, "Failed Updated");
	}

	@Override
	public List<AnnexureDto> getPrForQcf(String role) {
		String where = null;
		if (AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)) {
			/*where =" CAST(A.totalGrossValue as int)<=100000";*/
			where ="A.totalGrossValue>='0'";
		}else if(AppBaseConstant.ROLE_GENERAL_MANAGER_ADMIN.equals(role)){
			where ="CAST(A.totalGrossValue as int)=100001 AND CAST(A.totalGrossValue as int)<=500000";
		}else if(AppBaseConstant.ROLE_EXECUTIVE_MANAGER_ADMIN.equals(role)){
			where ="A.totalGrossValue>='500000' AND A.totalGrossValue<='1500000'";
		}else if(AppBaseConstant.ROLE_CHAIR_MAN_ADMIN.equals(role)){
			where ="A.totalGrossValue>='1500000'";
		}
		return getDtoList(annexureDao.getPrForQcf(where));
	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public ResponseDto updateFinalApproval(Long annexureId) {
//		// TODO Auto-generated method stub
//				long result = 0;
//				HashMap<String, Object> annexureMap = new HashMap<>();
//
//				annexureMap.put("status", AppBaseConstant.ANNEXURE_FINAL_APPROVE_STATUS);
//				/*annexureMap.put("qcfFormat",attid);*/
//			//	annexureMap.put("isFinalApprovalAccepted", "Y");
//
//				result = updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));
//
//				if (result > 0) {
//					try {
//						
//						webclient.postQCFAnnexureDataToSAP(annexureId);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					return new ResponseDto(false, "Record Updated");
//				}
//
//				return new ResponseDto(true, "Failed Updated");
//	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto updateFinalApproval(Long annexureId) {
		// TODO Auto-generated method stub
				long result = 0;
				HashMap<String, Object> annexureMap = new HashMap<>();

				annexureMap.put("status", AppBaseConstant.ANNEXURE_FINAL_APPROVE_STATUS);
				result = updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));

				if (result > 0) {
					
//					try {
//						webclient.postQCFAnnexureDataToSAP(annexureId);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					

					return new ResponseDto(false, "Record Updated");
				}

				return new ResponseDto(true, "Failed Updated");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateQCFAnnexureDataToSAP(Long annexureId) {
		// TODO Auto-generated method stub
			
					
					try {
						webclient.postQCFAnnexureDataToSAP(annexureId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					

					
	}
	
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void SendMailonFinalApproval(Long annexureId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", annexureId);		
		AnnexureDto dto=annexureService.findDto("getAnnexureById", params);
	    String qcfno=dto.getEnquiry().getQcfNo();
	    String recepient=dto.getQcf_to_mailid();
	    
	    
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
		
//		String url="https://172.18.2.29:44300/sap/bc/yweb03_QCF_45?sap-client=100&qcfno="+qcfno;
		String url="https://172.18.2.36:44300/sap/bc/yweb03_QCF_45?sap-client=100&qcfno="+qcfno;
		URLConnection request=null;   
		try {
			
			
	            URL u = new URL(url);
		    //	URL u = new URL(url.replace("[","%5B").replace("]", "%5D").replace(" ","%20"));
		    	// u = new URL(url.replace("]","%5D"));
	            request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
	            ObjectMapper objJson = new ObjectMapper();
		        objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		        QCFApproverSAPDto[] maildto = objJson.readValue(sb.toString(), QCFApproverSAPDto[].class);
					 List<QCFApproverSAPDto> ccList= new ArrayList<QCFApproverSAPDto>();

						
						MailDto mailDto = new MailDto();
	
					 for(QCFApproverSAPDto dtos:maildto){
					//	 ccList.add(dtos);	
						 
					String group= dtos.getGroup();

					if(group.equals("G2")) {
					//	recipientList.add(dtos.getEmailAddress());						
		    			mailDto.setSubject("QCF Acceptance Mail - "+ qcfno);
		    			mailDto.setMailContent("<p>Hi,</p><br><p>The QCF "+qcfno+" has been by approved by: "+recepient+"<br><p>Regards,</p><br>Team Alkyl<p></p>");


		    			mailDto.setSingleRecipient((dtos.getEmailAddress()));
		    			mailService.sendEmail(mailDto,true);
					}			    		
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
		//return url;
	//	return null;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void SendMailonPMRejection(Long annexureId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", annexureId);		
		AnnexureDto dto=annexureService.findDto("getAnnexureById", params);
	    String qcfno=dto.getEnquiry().getQcfNo();
	  //  String recepient=dto.getQcf_to_mailid();
	  // String PRmailid =dto.getRejectedby().getEmail();
	   String PRmailid ="";
			   
			   if((!dto.getRejectedby().getName().equals("")) || (dto.getRejectedby().getName()!=null)){
				   PRmailid=dto.getRejectedby().getName();
			   }else if (dto.getRejectedby().getUserDetails()!=null) {
				   PRmailid=dto.getRejectedby().getUserDetails().getName();
			   }
	   MailDto mailDto = new MailDto();
	   
	   try {
			mailDto.setSubject("QCF First Level Rejection Mail - "+ qcfno);
			mailDto.setMailContent("<p>Hi,</p><br><p>The QCF "+qcfno+" has been by Rejected on First Level by: "+PRmailid+"<br><p>Regards,</p><br>Team Alkyl<p></p>");


			mailDto.setSingleRecipient((dto.getCreatedBy().getEmail()));
			mailService.sendEmail(mailDto,true);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	    
//		 // Create a trust manager that does not validate certificate chains
//	       TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
//	               public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//	                   return null;
//	               }
//	               public void checkClientTrusted(X509Certificate[] certs, String authType) {
//	               }
//	               public void checkServerTrusted(X509Certificate[] certs, String authType) {
//	               }
//	           }
//	       };
//
//	       // Install the all-trusting trust manager
//	       SSLContext sc = null;
//			try {
//				sc = SSLContext.getInstance("SSL");
//			} catch (NoSuchAlgorithmException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	       try {
//				sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			} catch (KeyManagementException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	       HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//	       // Create all-trusting host name verifier
//	       HostnameVerifier allHostsValid = new HostnameVerifier() {
//	           public boolean verify(String hostname, SSLSession session) {
//	               return true;
//	           }
//	       };
//
//	       // Install the all-trusting host verifier
//	       HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//		
//		String url="https://172.18.2.29:44300/sap/bc/yweb03_QCF_45?sap-client=100&qcfno="+qcfno;
//		 URLConnection request=null;   
//		try {
//			
//			
//	            URL u = new URL(url);
//		    //	URL u = new URL(url.replace("[","%5B").replace("]", "%5D").replace(" ","%20"));
//		    	// u = new URL(url.replace("]","%5D"));
//	            request =  u.openConnection();
//
//	            request.connect();
//
//
//	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//	            StringBuilder sb = new StringBuilder();
//	            String line;
//	            while ((line = br.readLine()) != null) {
//	                sb.append(line + "\n");
//	            }
//	            br.close();
//	            ObjectMapper objJson = new ObjectMapper();
//		        objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		        QCFApproverSAPDto[] maildto = objJson.readValue(sb.toString(), QCFApproverSAPDto[].class);
//			
//
//						
//				MailDto mailDto = new MailDto();
//	
//					 for(QCFApproverSAPDto dtos:maildto){
//					//	 ccList.add(dtos);	
//						 
//					String group= dtos.getGroup();
//
//					if(group.equals("G2")) {
//					//	recipientList.add(dtos.getEmailAddress());						
//		    			mailDto.setSubject("QCF Rejection Mail - "+ qcfno);
//		    			mailDto.setMailContent("<p>Hi,</p><br><p>The QCF "+qcfno+" has been by Rejected by: "+PRmailid+"<br><p>Regards,</p><br>Team Alkyl<p></p>");
//
//
//		    			mailDto.setSingleRecipient((dtos.getEmailAddress()));
//		    			mailService.sendEmail(mailDto,true);
//					}			    		
//		             }
//	           
//	        } catch (MalformedURLException ex) {
//	            ex.printStackTrace();
//	        } catch (IOException ex) {
//	            ex.printStackTrace();
//	        }finally {
//	            if (request != null) {
//	                try {
//	                	((HttpURLConnection) request).disconnect();
//	                } catch (Exception ex) {
//	                    ex.printStackTrace();
//	                }
//	            }
//	        }
		//return url;
	//	return null;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void SendMailonFinalRejection(Long annexureId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", annexureId);		
		AnnexureDto dto=annexureService.findDto("getAnnexureById", params);
	    String qcfno=dto.getEnquiry().getQcfNo();
	    String recepient=dto.getQcf_to_mailid();
	    
	    
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
		
//		String url="https://172.18.2.29:44300/sap/bc/yweb03_QCF_45?sap-client=100&qcfno="+qcfno;
	       String url="https://172.18.2.36:44300/sap/bc/yweb03_QCF_45?sap-client=100&qcfno="+qcfno;
		 URLConnection request=null;   
		try {
			
			
	            URL u = new URL(url);
		    //	URL u = new URL(url.replace("[","%5B").replace("]", "%5D").replace(" ","%20"));
		    	// u = new URL(url.replace("]","%5D"));
	            request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
	            ObjectMapper objJson = new ObjectMapper();
		        objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		        QCFApproverSAPDto[] maildto = objJson.readValue(sb.toString(), QCFApproverSAPDto[].class);
			

						
						MailDto mailDto = new MailDto();
	
					 for(QCFApproverSAPDto dtos:maildto){
					//	 ccList.add(dtos);	
						 
					String group= dtos.getGroup();

					if(group.equals("G2")) {
					//	recipientList.add(dtos.getEmailAddress());						
		    			mailDto.setSubject("QCF Second Level Rejection Mail - "+ qcfno);
		    			mailDto.setMailContent("<p>Hi,</p><br><p>The QCF "+qcfno+" has been by Rejected on Second Level by: "+recepient+"<br><p>Regards,</p><br>Team Alkyl<p></p>");


		    			mailDto.setSingleRecipient((dtos.getEmailAddress()));
		    			mailService.sendEmail(mailDto,true);
					     }			    		
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
		//return url;
	//	return null;
	}

	
	
}
