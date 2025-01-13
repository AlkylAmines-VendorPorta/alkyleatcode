package com.sap.document.sap.rfc.functions;

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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.service.AnnexureService;
import com.novelerp.alkyl.service.WinnerSelectionService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.PriceBidService;
import com.sap.document.sap.rfc.grn.GrnFtpObject;
import com.sap.document.sap.rfc.grn.ZMMGRN;
import com.sap.document.sap.rfc.ses.YSERVICECOSTCENTER;

@RequestMapping("/rest")
@Component
public class ZQCFAnnexureClient {

	@Autowired
	private ObjectFactory objectFactory;
	
	@Autowired
	private AnnexureService annexureService;
	
	@Autowired
	private WinnerSelectionService winnerSelectionService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public boolean postQCFAnnexureDataToSAP(Long AnnexureId) throws Exception{
		
		ZQCFAnnexute_Type inputVal = getQCFAnnexureFromAnnexureDto(AnnexureId);
		
		
		try {
			if(null != inputVal ){
				String json = CommonUtil.getObjectJson(inputVal);
				boolean status= mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_QCF_TO_PROCESS_PATH, inputVal.getEnqno()+".txt");
//				if(status){
//					sendEnquiryConfirmation(inputVal.getEnqno()+".txt");
//				}
				return status;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return false;
	}
	
//	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
//	public boolean postQCFAnnexureDataToSAP(Long AnnexureId) throws Exception{
//		
//		ZQCFAnnexute_Type inputVal = getQCFAnnexureFromAnnexureDto(AnnexureId);
//		
//		
//		try {
//			if(null != inputVal ){
//				String json = CommonUtil.getObjectJson(inputVal);
//				
//				// Create a trust manager that does not validate certificate chains
//				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//						return null;
//					}
//
//					public void checkClientTrusted(X509Certificate[] certs, String authType) {
//					}
//
//					public void checkServerTrusted(X509Certificate[] certs, String authType) {
//					}
//				} };
//
//				// Install the all-trusting trust manager
//				SSLContext sc = null;
//				try {
//					sc = SSLContext.getInstance("SSL");
//				} catch (NoSuchAlgorithmException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					sc.init(null, trustAllCerts, new java.security.SecureRandom());
//				} catch (KeyManagementException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//				// Create all-trusting host name verifier
//				HostnameVerifier allHostsValid = new HostnameVerifier() {
//					public boolean verify(String hostname, SSLSession session) {
//						return true;
//					}
//				};
//
//				// Install the all-trusting host verifier
//				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//				
//				String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_61?sap-client=100&JSON="+json;
//				 URLConnection request=null;   
//				try {
//					URL u = new URL(url);
//					request = u.openConnection();
//					request.connect();
//					BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//					StringBuilder sb = new StringBuilder();
//					String line;
//					while ((line = br.readLine()) != null) {
//						sb.append(line + "\n");
//					}
//					System.out.println(sb);
//					br.close();
//		               
//			        } catch (MalformedURLException ex) {
//			            ex.printStackTrace();
//			        } catch (IOException ex) {
//			            ex.printStackTrace();
//			        }
//				finally {
//			            if (request != null) {
//			                try {
//			                	((HttpURLConnection) request).disconnect();
//			                } catch (Exception ex) {
//			                    ex.printStackTrace();
//			                }
//			            }
//			        }
//				
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		
//		return false;
//	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=true)
	private ZQCFAnnexute_Type getQCFAnnexureFromAnnexureDto(Long id){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", id);
		AnnexureDto dto=annexureService.findDto("getAnnexureById", params);
		ZQCFAnnexute_Type qcf = objectFactory.createZQCFAnnexute_Type();
		qcf.setEnqno(dto.getEnquiry().getEnqNo());
		qcf.setQcfno(dto.getEnquiry().getQcfNo());
		
		/*qcf.setEnqtype("ZMPR");*/
		//qcf.setEnqtype("ZMPR");
		qcf.setEnqdate(DateUtil.getDateString(dto.getEnquiry().getCreated(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setEnqdeadline(DateUtil.getDateString(dto.getEnquiry().getBidEndDate(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setPurchaseorg("1000");
		/*qcf.setPurchasinggroup("191");*/
		/*qcf.setCollectiveno("1551");*/
		qcf.setVendordata(getWinnerSelection(id));
		
	
		
		if(dto.getStatus().equals("POSTED")) {
			qcf.setStatus("Approved");
		}else {
			qcf.setStatus("Rejected");
		}
		/*qcf.setPrno(dto.getPr().getPrNumber());
		qcf.setRfqtype(dto.getPr().getDocType());
		qcf.setRfqdate(DateUtil.getDateString(dto.getPr().getCreated(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setRfqdate(DateUtil.getDateString(dto.getPr().getBidEndDate(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setEkorg("9000");
		qcf.setEkgrp(dto.getPr().getBuyer().getp);
		qcf.setPlant(dto.getPr().getPrLines().get(0).getPlant());
		qcf.setVendor(getWinnerSelection(id));*/
		return qcf;
	}
	
	
//	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=true)
//	public String sendQCFApproval(String recipientList,List<String> ccList,String qcfno) throws Exception{
//		ZQCFAnnexute_Type qcf = objectFactory.createZQCFAnnexute_Type();
//		qcf.setApprovedbyemailid(recipientList);
//		qcf.setQcfno(qcfno);
//		
////		for(String ccmailid:ccList) {
//			//qcf.setCclistemailid(ccmailid);
//			qcf.setCclistemailid(ccList);
//	//	}
//		
//		try {
//			String json = CommonUtil.getObjectJson(qcf);
//			return json;
//		} catch (Exception e) {
//			throw e;
//		}
//		//	return qcf;
//			
//			
//	}
	
	
	@RequestMapping(value="/getQCFAnnexureDetailsForSAP/{annexureId}" , method = RequestMethod.GET)
	 public @ResponseBody String getQCFAnnexureDetailsForSAP(@PathVariable("annexureId") Long annexureId) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", annexureId);
		AnnexureDto dto=annexureService.findDto("getAnnexureById", params);
		ZQCFAnnexute_Type qcf = objectFactory.createZQCFAnnexute_Type();
		qcf.setEnqno(dto.getEnquiry().getEnqNo());
		/*qcf.setEnqtype("ZMPR");*/
		qcf.setEnqdate(DateUtil.getDateString(dto.getEnquiry().getCreated(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setEnqdeadline(DateUtil.getDateString(dto.getEnquiry().getBidEndDate(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setPurchaseorg("9000");
		
		/*qcf.setPurchasinggroup("191");*/
		/*qcf.setCollectiveno("1551");*/
		qcf.setVendordata(getWinnerSelection(annexureId));
		//qcf.setApprovedbyemailid(dto.getApprovedby_email());
		qcf.setQcfno(dto.getEnquiry().getQcfNo());
		
		if(dto.getStatus().equals("POSTED")) {
			qcf.setStatus("Approved");
		}else {
			qcf.setStatus("Rejected");
		}
		
		
		/*qcf.setPrno(dto.getPr().getPrNumber());
		qcf.setRfqtype(dto.getPr().getDocType());
		qcf.setRfqdate(DateUtil.getDateString(dto.getPr().getCreated(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setRfqdate(DateUtil.getDateString(dto.getPr().getBidEndDate(),DateUtil.DEFAULT_DATE_FORMAT));
		qcf.setEkorg("9000");
		qcf.setEkgrp(dto.getPr().getBuyer().getp);
		qcf.setPlant(dto.getPr().getPrLines().get(0).getPlant());
		qcf.setVendor(getWinnerSelection(id));*/
		
		
	//	return qcf.toString();
		
		
		try {
			String json = CommonUtil.getObjectJson(qcf);
		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
		//	if(!result){
		//		throw new RuntimeException("Error while writing file");
		//	}
			return json;
		} catch (Exception e) {
			throw e;
		}
	
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=true)
	private List<ZQCFVENDOR_Type> getWinnerSelection(Long id){
		List<ZQCFVENDOR_Type> winnerList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("annexureId", id);
		List<WinnerSelectionDto> winnerSelection =winnerSelectionService.findDtos("getWinnerSelectionByAnnexureId", param);
		for(WinnerSelectionDto dto:winnerSelection){
			ZQCFVENDOR_Type winner = new ZQCFVENDOR_Type();
			winner.setVendorname(dto.getItemBid().getBidder().getPartner().getVendorSapCode());
			winner.setValiditydatefrom(DateUtil.getDateString(dto.getItemBid().getBidder().getValidityDateFrom(),DateUtil.DEFAULT_DATE_FORMAT));
			winner.setValiditydateto(DateUtil.getDateString(dto.getItemBid().getBidder().getValidityDateTo(),DateUtil.DEFAULT_DATE_FORMAT));
			winner.setIncoterms(dto.getItemBid().getBidder().getIncoTerms());
			winner.setIncodescription(dto.getItemBid().getBidder().getVendorIncoDescription());
			winner.setQuotationrefno(dto.getItemBid().getBidder().getVendorOfferNo());
			winner.setQuotationrefdate(DateUtil.getDateString(dto.getItemBid().getBidder().getVendorOfferDate(),DateUtil.DEFAULT_DATE_FORMAT));
			winner.setRfqline(getRFQLine(dto));
			winnerList.add(winner);
		}
		
		return winnerList;
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=true)
	private List<ZQCFRFQLINE_Type> getRFQLine(WinnerSelectionDto dto){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("itemBidId", dto.getItemBid().getItemBidId());
		PriceBidDto priceBid=priceBidService.findDto("getPriceBidByItemBid", params);
		List<ZQCFRFQLINE_Type> rfqLine = new ArrayList<>();
		ZQCFRFQLINE_Type rfq = new ZQCFRFQLINE_Type();
		rfq.setMaterial(dto.getItemBid().getPrLine().getMaterialCode());
		rfq.setRfqitem(dto.getItemBid().getLineNumber());
		rfq.setRfqqty(String.valueOf(dto.getAllocatedQty()));
		rfq.setUom(dto.getItemBid().getPrLine().getUom());
		rfq.setDeliverydate(DateUtil.getDateString(priceBid.getDeliveryDate(),DateUtil.DEFAULT_DATE_FORMAT));
		rfq.setPrno(dto.getItemBid().getPrLine().getPr().getPrNumber());
		rfq.setPritem(dto.getItemBid().getPrLine().getPrLineNumber());
		rfq.setPrdate(DateUtil.getDateString(dto.getItemBid().getPrLine().getPr().getDate(),DateUtil.DEFAULT_DATE_FORMAT));
		rfq.setFreighttype(priceBid.getFreightChargesType());
		rfq.setFreightvalue(String.valueOf(priceBid.getFreightChargeRate()));
		rfq.setPftype(priceBid.getPackingFwdChargeType());
		rfq.setPfvalue(String.valueOf(priceBid.getPackingFwdCharge()));
		rfq.setOthertype(priceBid.getOtherChargesType());
		rfq.setOthervalue(String.valueOf(priceBid.getOtherChargesRate()));
		rfq.setTaxcode(priceBid.getTaxRate());
//		rfq.setNetprice(String.valueOf(priceBid.getNetRate()));
		rfq.setNetprice(String.valueOf((priceBid.getNetRate()+ priceBid.getCompleteOther())/priceBid.getItemBid().getQuantity()));
//		rfq.setPurchasinggroup(dto.getItemBid().getPrLine().getBuyer().getUserName());
		rfq.setPurchasinggroup(dto.getItemBid().getPrLine().getBuyer().getPurchaseGroup());
		rfq.setEnqtype(dto.getItemBid().getPrLine().getPr().getDocType());
		rfqLine.add(rfq);
		return rfqLine;
	}
	
	public void sendEnquiryConfirmation(String fileName){
		String url="http://103.231.11.53:8000/sap/bc/yweb03_ws_14?sap-client=009&FileName="+fileName;
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
	}
	
	
	public CustomResponseDto sendQCFApprovalListToSAP(String recipientList,List<String> ccList,String qcfno){
		
//		String url="https://172.18.2.29:44300/sap/bc/yweb03_QCF_44?sap-client=100&recipientList="+recipientList+"&ccList="+ccList+"&qcfno="+qcfno;
		String url="https://172.18.2.36:44300/sap/bc/yweb03_QCF_44?sap-client=100&recipientList="+recipientList+"&ccList="+ccList+"&qcfno="+qcfno;
		 URLConnection request=null;   
		try {
			
			
	        //    URL u = new URL(url);
		    	URL u = new URL(url.replace("[","%5B").replace("]", "%5D").replace(" ","%20"));
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
		return null;
	}
	
	

}
