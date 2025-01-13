package com.novelerp.alkyl.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.dao.AdvancePaymentDao;
import com.novelerp.alkyl.dao.AdvanceShipmentNoticeDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PaymentReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.AdvancePayment;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;
import com.novelerp.alkyl.service.AdvancePaymentService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;

@Service
public class AdvancePaymentServiceImpl extends AbstractContextServiceImpl<AdvancePayment, AdvancePaymentDto> implements AdvancePaymentService {
	
	@Autowired
	private AdvancePaymentDao advancePaymentDao;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private BPartnerComponent partnerComponent;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private AdvancePaymentService advanceService;
	
	@PostConstruct
	public void init(){
		
		super.init(AdvancePaymentService.class, advancePaymentDao, AdvancePayment.class, AdvancePaymentDto.class);
	setByPassProxy(true);
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public  List<AdvancePaymentDto> saveadvancepayment(List<AdvancePaymentDto> advance) {
	
		List<AdvancePaymentDto> userEntities = new ArrayList<>();
		for (AdvancePaymentDto user : advance) {
			AdvancePaymentDto oldDto = findDto("getpaymentbydocNumber", AbstractContextServiceImpl.getParamMap("docNumber", user.getDocumentNumber()));
	       
	        if(oldDto!=null){
	    		
	        	//oldDto=userEntities.add(updateDto(user));
	        	oldDto = getNewDtoFromOldDto(oldDto,user);
				oldDto = super.updateDto(oldDto);
				userEntities.add(oldDto);
	        	 
			//	advance = super.save(advance);
			}else{
				user.setIsMailSent("Y");
				user.setStatus(AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
				oldDto = super.save(user);
				userEntities.add(oldDto);
			//	advance = super.updateDto(advance);

			//return advance;
		}

	    }
				return userEntities;
	}
	
	private AdvancePaymentDto getNewDtoFromOldDto(AdvancePaymentDto oldDto,AdvancePaymentDto newDto){
		oldDto.setInterestRate(newDto.getInterestRate());
		oldDto.setInterestAmount(newDto.getInterestAmount());
		oldDto.setGrossAmount(newDto.getGrossAmount());
		oldDto.setNextPaymentDate(newDto.getNextPaymentDate());
		oldDto.setIgstAmount(newDto.getIgstAmount());
		oldDto.setCgstAmount(newDto.getCgstAmount());
		oldDto.setSgstAmount(newDto.getSgstAmount());
		oldDto.setStatus(AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
		oldDto.setGapinDays(newDto.getGapinDays());
//		oldDto.setApprovedBy(newDto.getApprovedBy());
//		oldDto.setApprovedDate(newDto.getApprovedDate());
		return oldDto;
	}



		
		@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
		public void sendMailforApproval(List<AdvancePaymentDto> advancePayment) {
			
			BPartnerDto partner=contextService.getPartner();
			
	
			//MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ADVANCE_PAYMENT_REQUEST_TEMPLATE);
			//if (mailTemplate != null) {
				StringBuilder html = new StringBuilder();
				
				double  totalInvoiceAmount=0.0;
				double invoiceAmount=0.0;
				String newTotalInvoiceAmount=null;
				
				 com.ibm.icu.text.NumberFormat format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "in"));
		          format.setMinimumFractionDigits(2);
		          format.setMaximumFractionDigits(2);
				
				html.append("<html>" +
				           "<body>" +
				           "<table border=1>" +
				           "<tr>" +
				           "<th>Supplier</th>" +
				           "<th>Document Number</th>" +
				           "<th>Reference</th>" +
				           "<th>Amt.in loc.cur.</th>" +
                           "<th>Invoice Date</th>" +
                           "<th>Due Date</th>" +
				           "</tr>");
				for(AdvancePaymentDto pay:advancePayment) {
					
					// Date basedate=pay.getBaselinePaymentDate();
					 Date basedate=pay.getInvoiceDate();
					 Date actualdate=pay.getActualPaymentDate();
				      // String pattern = "yyyy/MM/dd";
				        String pattern = "dd/MM/yyyy";
				         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				         String baseLinedate = simpleDateFormat.format(basedate);
				         String actualPaymentdate = simpleDateFormat.format(actualdate);
				        
				         invoiceAmount=pay.getAmountInLC();
				         
				         totalInvoiceAmount=totalInvoiceAmount+invoiceAmount;
//						 newTotalInvoiceAmount=new DecimalFormat("0.00").format(totalInvoiceAmount);
				         newTotalInvoiceAmount=format.format(totalInvoiceAmount);
				
				
					 html.append("<tr><td>");
					 html.append(pay.getVendorCode());
					 html.append("</td><td>");
					 html.append(pay.getDocumentNumber());
					 html.append("</td><td>");
					 html.append(pay.getReference());	
					 html.append("</td><td>");
					 html.append(format.format(pay.getAmountInLC()));
					 html.append("</td><td>");
					 html.append(baseLinedate);
					 html.append("</td><td>");
					 html.append(actualPaymentdate);
					 html.append("</td></tr>");

				}
				
				 html.append("<tr><th colspan=3>Total");
				 
				 html.append("</th><td>");
				 html.append(newTotalInvoiceAmount);
				 html.append("</tr>");
				
				html.append("</table>" +
				           "</body>" +
				           "</html>");
				
				String htmltable = html.toString();
				
				MailDto mailDto = new MailDto();
				

				mailDto.setSubject("Advance Payment Approval Request - "+partner.getName());

				mailDto.setMailContent("<p>Hello Sir/Madam,</p><br><p>Advance Payment approval required by Vendor "+partner.getName() + " for below Documents:<br><br>"+htmltable +"<br><br>Thanks,<br>Alkyl Team");
				
				List<String> recipientList = new ArrayList<>();
			
			    recipientList.add("vacct4@alkylamines.com");
				recipientList.add("ssatle@alkylamines.com");
			//	recipientList.add("saphelpdesk@alkylamines.com");
				mailDto.setRecipientList(recipientList);
				List<String> CcList = new ArrayList<>();
				//CcList.add(enquiry.getEnquiry().getCreatedBy().getEmail());
			
				mailDto.setCcList(CcList);
				//mailDto.getRecipientList().add(userComponent.getEmailByBpartnerId(enquiry.getPartner().getbPartnerId()));
				mailService.sendEmail(mailDto,true);

			
		}
		
		
		
		@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
		public void sendProcessedDatatoSAP(List<AdvancePaymentDto> advancePayment) {
			
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
			

	         ObjectMapper mapper = new ObjectMapper();
	       //  List<String> jsonlist = new ArrayList<>();
	         Map<String, Object> jsonlist = new HashMap<String, Object>();

	         
	         JSONArray newjsonlist=new JSONArray();
	        
	         for(AdvancePaymentDto newList:advancePayment) {      	 
	         jsonlist.put("documentNumber", newList.getDocumentNumber());
	         jsonlist.put("status", newList.getStatus());
	        
	         newjsonlist.put(jsonlist);
	        // newjsonlist.add(jsonlist);
	         
	         }

				try {
					
                    Long partnerId=partnerComponent.getLoggedInUser().getPartner().getbPartnerId();
                    
                //    BPartnerDto partner=contextService.getPartner();
					
					BPartnerDto partner = partnerService.findDto("getPartnerByPartnerId",
							AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
					
					
					String vendorSapCode=partner.getVendorSapCode();

					String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_55?sap-client=100&vendor=" + vendorSapCode +"&JSON="+newjsonlist + "&Level=Level2" ;
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
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			return;

	
						
		}
		
		
		
		@Override
		public List<AdvancePaymentDto> getPaymentReportbyFilter(PaymentReadDto dto) {
			Map<String, Object> params=getParameterMap(dto);
			List<String> param = new ArrayList<>();
			
				if(dto.getStatus().equals("IN Progress")) {
					param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
					params.put("status", param);
					
				}
				
				if(dto.getStatus().equals("Approved")) {
					param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);
					params.put("status", param);
					
				}
				
				if(dto.getStatus().equals("Rejected")) {
					param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
					params.put("status", param);
					
				}
			
			
			String query=advancePaymentDao.getPaymentReportlist(dto);
			List<AdvancePaymentDto> paymentList=findDtosByQuery(query, params);
			return paymentList;
		}
		private Map<String,Object> getParameterMap(PaymentReadDto dto){
			System.out.println(dto.toString());
			Map<String, Object> params = new HashMap<String, Object>();
			
	   		if(dto.getVendor()!=null && !dto.getVendor().equals("")){
	   			params.put("vendor",dto.getVendor());
	   		}
	   		
	   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
	   			params.put("status",dto.getStatus());
	   		}
	   		   		
			return params;
		}

		
		@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
		public CustomResponseDto sendApprovedMailtoVendor(List<AdvancePaymentDto> advancePayment) {
			CustomResponseDto resp=new CustomResponseDto();
			try {
				
				StringBuilder html = new StringBuilder();
				html.append("<html>" +
				           "<body>" +
				           "<table border=1>" +
				           "<tr>" +
				           "<th>Document Number</th>" +
				           "<th>Reference</th>" +
				           "<th>Invoice Date</th>" +
                           "<th>Invoice Net Amount</th>" +
                           "<th>Payment date</th>" +
                           "<th>interest amount</th>" +
                           "<th>CGST amount</th>" +
                           "<th>SGST amount</th>" +
                           "<th>IGST amount</th>" +
                           "<th>Net Payable</th>" +
				           "</tr>");
			 double grossAmount=0.0;
	         double totalGrossAmount=0.0;
	         double interestAmount=0.0;
	         double totalInterestAmount=0.0;
	         double cgstAmount=0.0;
	         double sgstAmount=0.0;
	         double igstAmount=0.0;
	         double totalGstAmount=0.0;
	         double invoiceAmount=0.0;
	      //   double totalInvoiceAmount=0.0;
	         String gstAmount=null;
	         String newTotalGrossAmount=null;
	         String newTotalInterestAmount=null;
	        
	     //    String newTotalInvoiceAmount=null;
	         double netPayable=0.0;
	         com.ibm.icu.text.NumberFormat format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "in"));
	          format.setMinimumFractionDigits(2);
	          format.setMaximumFractionDigits(2);
	          
	         for(AdvancePaymentDto newadvance:advancePayment) {
	        	 
	        	 
	         
	       	  grossAmount=newadvance.getGrossAmount();
			  interestAmount=newadvance.getInterestAmount();
			  cgstAmount=newadvance.getCgstAmount();
			  sgstAmount=newadvance.getSgstAmount();
			  igstAmount=newadvance.getIgstAmount();
			  invoiceAmount=newadvance.getAmountInLC();
			  netPayable=newadvance.getNetPayableAmount();
			  
//			  totalInvoiceAmount=totalInvoiceAmount+invoiceAmount;
//			  newTotalInvoiceAmount=new DecimalFormat("0.00").format(totalInvoiceAmount);
			  
			  totalGrossAmount= totalGrossAmount + grossAmount;
			//  newTotalGrossAmount=new DecimalFormat("0.00").format(totalGrossAmount);
			  newTotalGrossAmount=format.format(totalGrossAmount);
			  
			  totalInterestAmount=totalInterestAmount+interestAmount;
//			  newTotalInterestAmount=new DecimalFormat("0.00").format(totalInterestAmount);
			  newTotalInterestAmount=format.format(totalInterestAmount);
			  
			  
                if(igstAmount!=0.00) {
                	totalGstAmount=totalGstAmount+igstAmount;
                	//gstAmount=new DecimalFormat("0.00").format(totalGstAmount);
                	gstAmount=format.format(totalGstAmount);
                }else {
                	totalGstAmount=totalGstAmount+cgstAmount+sgstAmount;
                	//gstAmount=new DecimalFormat("0.00").format(totalGstAmount);
                	gstAmount=format.format(totalGstAmount);
                }
                
                Date nextpaydate=newadvance.getNextPaymentDate();
                Date invoiceDate=newadvance.getInvoiceDate();
			      // String pattern = "yyyy/MM/dd";
			        String pattern = "dd/MM/yyyy";
			         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			         String nextPaymentdate = simpleDateFormat.format(nextpaydate);
			         String finalinvoiceDate = simpleDateFormat.format(invoiceDate);
                
                
           	 html.append("<tr><td>");
			 html.append(newadvance.getDocumentNumber());
			 html.append("</td><td>");
			 html.append(newadvance.getReference());	
			 html.append("</td><td>");
			 html.append(finalinvoiceDate);
			 html.append("</td><td>");
			 html.append(format.format(invoiceAmount));
			 html.append("</td><td>");
			 html.append(nextPaymentdate);
			 html.append("</td><td>");
			 html.append(format.format(interestAmount));
			 html.append("</td><td>");
			 html.append(format.format(cgstAmount));
			 html.append("</td><td>");
			 html.append(format.format(sgstAmount));
			 html.append("</td><td>");
			 html.append(format.format(igstAmount));
			 html.append("</td><td>");
			 html.append(format.format(netPayable));
			 html.append("</td></tr>");
	        	 
	         }
	         
	         html.append("</table>" +
			           "</body>" +
			           "</html>");
			
			String htmltable = html.toString();

	         Map<String, Object> params = new HashMap<String, Object>();
	 		 params.put("vendorCode", advancePayment.get(0).getVendorCode());		
	 		 AdvancePaymentDto dto=advanceService.findDto("getPaymentByVendorCode", params);
	 		
				MailDto mailDto = new MailDto();
				mailDto.setSubject("Required Credit Note for Pre-Payment");
			    
//                Date nextpaydate=advancePayment.get(0).getNextPaymentDate();
//			        String pattern = "dd/MM/yyyy";
//			         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//			         String nextPaymentdate = simpleDateFormat.format(nextpaydate);

//				mailDto.setMailContent("<p>Hello Team,</p><br><p>Enclosed please find working for prepayment dt "+nextPaymentdate + "</p><p>Kindly provide credit note Basic Rs. "+advancePayment.get(0).getInterestAmount() + " + " + Rate +" % " +" GST.</p><p>Enclosed please find Working for the same</p><br><p>Regards,<br>ALKYL AMINES CHEMICALS LTD.</p>");
//			         mailDto.setMailContent("<p>Hello Team,</p><br><p>Your Pre-Payment Request has been approved for Invoice Amount - "+newTotalInvoiceAmount + " and will be paid net of pre-payment discount on "+nextPaymentdate + "</p><p>Kindly provide credit note of Rs. Basic "+newTotalInterestAmount + " + " + gstAmount +" GST. Total "+newTotalGrossAmount+" for pre-payment discount</p><br><p>Regards,<br>ALKYL AMINES CHEMICALS LTD.</p>");
			         
			    mailDto.setMailContent("<p>Hello Team,</p><br><p>Your Pre-Payment Request has been approved for below documents "+htmltable +"</p><p>Kindly provide credit note of Rs. Basic "+newTotalInterestAmount + " + " + gstAmount +" GST. Total "+ newTotalGrossAmount+" for pre-payment discount</p><br><p>Regards,<br>ALKYL AMINES CHEMICALS LTD.</p>");
				List<String> recipientList = new ArrayList<>();
			
				recipientList.add(dto.getCreatedBy().getEmail());
		
			//	recipientList.add("saphelpdesk@alkylamines.com");
				
				mailDto.setRecipientList(recipientList);
				List<String> CcList = new ArrayList<>();
				CcList.add("vacct4@alkylamines.com");
				CcList.add("ssatle@alkylamines.com");	 		
				mailDto.setCcList(CcList);
				
				mailService.sendEmail(mailDto,true);
				resp=new CustomResponseDto(true,"Mail Sucessfully Sent");

			}catch(Exception e) {
				e.printStackTrace();
				resp=new CustomResponseDto(false,"Mail Sending Failed");
			}
			return resp;
		}
		
		
		
		@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
		public void sendApprovedMailtoVendorold(List<AdvancePaymentDto> advancePayment) {
		//	BPartnerDto partner=contextService.getPartner();
			
	
			//MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ADVANCE_PAYMENT_REQUEST_TEMPLATE);
			//if (mailTemplate != null) {
				
				MailDto mailDto = new MailDto();
				

				mailDto.setSubject("Required Credit Note for Pre-Payment");
				Double taxratesgst=advancePayment.get(0).getSgst();
                Double taxratecgst=advancePayment.get(0).getCgst();
                Double taxrateigst=advancePayment.get(0).getIgst();
                Double GrossAmt=advancePayment.get(0).getGrossAmount();
                Double Rate=0.0;
                if(taxrateigst!=0.00) {
                	Rate=Rate+taxratesgst;
                }else {
                	Rate=Rate+(taxratecgst)+(taxratesgst);
                }
                
                
                Date nextpaydate=advancePayment.get(0).getNextPaymentDate();
			      // String pattern = "yyyy/MM/dd";
			        String pattern = "dd/MM/yyyy";
			         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			         String nextPaymentdate = simpleDateFormat.format(nextpaydate);

//				mailDto.setMailContent("<p>Hello Team,</p><br><p>Enclosed please find working for prepayment dt "+nextPaymentdate + "</p><p>Kindly provide credit note Basic Rs. "+advancePayment.get(0).getInterestAmount() + " + " + Rate +" % " +" GST.</p><p>Enclosed please find Working for the same</p><br><p>Regards,<br>ALKYL AMINES CHEMICALS LTD.</p>");
			         mailDto.setMailContent("<p>Hello Team,</p><br><p>Your Pre-Payment Request has been approved for Amount - "+advancePayment.get(0).getInterestAmount() + " and will be paid on "+nextPaymentdate + "</p><p>Kindly provide credit note of Rs. Basic "+advancePayment.get(0).getInterestAmount() + " + " + Rate +" % " +" GST. Total "+GrossAmt+" for pre-payment discount</p><br><p>Regards,<br>ALKYL AMINES CHEMICALS LTD.</p>");
			         
				List<String> recipientList = new ArrayList<>();
			
//				recipientList.add(advancePayment.get(0).getCreatedBy().getEmail());
				recipientList.add("saphelpdesk@alkylamines.com");
				
				mailDto.setRecipientList(recipientList);
				List<String> CcList = new ArrayList<>();
		//		CcList.add("vacct2@alkylamines.com");
			//	CcList.add("vacct4@alkylamines.com");			
				mailDto.setCcList(CcList);
				
				mailService.sendEmail(mailDto,true);

			
		}

	

}

			
	






