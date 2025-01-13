package com.novelerp.eat.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.OtpDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.LiveBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TempPriceBidService;

@Service
public class LiveBidServiceImpl implements LiveBidService{
	
	@Autowired
	private TempPriceBidService tempPriceBidService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TAHDRDetailService  tAHDRDetailService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRMaterialService tAHDRMaterialService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private MailService mailService;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	private synchronized CustomResponseDto submitNewBid(PriceBidDto newBid,BPartnerDto partner){
		CustomResponseDto response=new CustomResponseDto();
		if(Double.parseDouble(newBid.getExGroupPriceRate())>0){
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrMaterialId", newBid.getItemBid().getTahdrMaterial().getTahdrMaterialId());
			params.put("partnerId",partner.getbPartnerId());
			PriceBidDto priceBid=priceBidService.findDto("getPriceBid",params);
			Long tahdrId=newBid.getItemBid().getBidder().getTahdr().getTahdrId();
			ItemBidDto itemBid=new ItemBidDto();
			if(priceBid==null){
				if(newBid.getItemBid()!=null && newBid.getItemBid().getTahdrMaterial()!=null ){
					itemBid.setPriceBid(newBid);
					Map<String, Object> newParams= new HashMap<>();		
					newParams.put("tahdrId", tahdrId);
					newParams.put("partnerId",partner.getbPartnerId());
					BidderDto bidder=bidderService.findDto("getBidderByTahdrId", newParams);
					
					itemBid.setBidder(bidder);
					itemBid.setTahdrMaterial(newBid.getItemBid().getTahdrMaterial());
				}else{
					response.addObject("result", false);
					response.addObject("resultMessage","Something went wrong !");
				}
				priceBid=newBid;
			}else{
				itemBid=priceBid.getItemBid();
				itemBid.setPriceBid(priceBid);
			}
			priceBid.setExGroupPriceRate(newBid.getExGroupPriceRate());
			priceBid.setFddRateWithGST(newBid.getFddRateWithGST());
			priceBid.setPreviousFddAmountWithGST(newBid.getPreviousFddAmountWithGST());
			
		    Double fddRateWithGST=Double.parseDouble(newBid.getFddRateWithGST());
		    BigDecimal B_fddRateWithGST = new BigDecimal(newBid.getFddRateWithGST());
		    Map<String, Object> map= new HashMap<>();
		    map.put("tahdrMaterialId", newBid.getItemBid().getTahdrMaterial().getTahdrMaterialId());
		    map.put("tahdrId", tahdrId);
		    PriceBidDto lowestBid=priceBidService.findDto("getLowestPriceBidByTahdrMaterialIdAndTahdrId", map);
		    
		    BigDecimal minBidDifference=null;
		    BigDecimal selectedfddRateWithGST=null;
		    String typeCode="";
		    BigDecimal newBidAmount=null;
		    Date auctionEndDate=null;
		    boolean isMinBidDiffers=false;
		    if(lowestBid==null){
		    	TAHDRDetailDto tenderDetails=tAHDRDetailService.findDto("getQueryForActiveTAHDRDetail", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		    	 auctionEndDate=tenderDetails.getAuctionToDate();
		    	 BigDecimal estimatedCostInLakh = new BigDecimal("1.00");
		    	 BigDecimal offerdQty = new BigDecimal(newBid.getOfferedQuantity().toString());
		    	/* BigDecimal estimatedCostRate=tenderDetails.getEstimatedCost().multiply(estimatedCostInLakh).divide(offerdQty);*/
		    	 BigDecimal estimatedCostRate=tenderDetails.getEstimatedCost().multiply(estimatedCostInLakh);
		    	 /*selectedfddRateWithGST=Double.parseDouble(estimatedCostRate.toString());*/
		    	 typeCode=tenderDetails.getTahdr().getTahdrTypeCode();
		    	 /*newBidAmount=typeCode.equalsIgnoreCase("QFA")?(fddRateWithGST-selectedfddRateWithGST):(selectedfddRateWithGST-fddRateWithGST);*/
		    	 newBidAmount=typeCode.endsWith("FA")?(B_fddRateWithGST.subtract(estimatedCostRate)):(estimatedCostRate.subtract(B_fddRateWithGST));
		    	 minBidDifference=tenderDetails.getMinBidDifference();
		    	 isMinBidDiffers=newBidAmount.compareTo(minBidDifference)>-1?true:false;
		    }else{
		    	 minBidDifference=lowestBid.getItemBid().getBidder().getTahdr().getTahdrDetail().iterator().next().getMinBidDifference();
		    	/* selectedfddRateWithGST=Double.parseDouble(lowestBid.getFddRateWithGST());*/
		    	 BigDecimal estimatedCostRate= new BigDecimal(lowestBid.getFddRateWithGST());
		    	 typeCode=lowestBid.getItemBid().getBidder().getTahdr().getTahdrTypeCode();
		    	 /*newBidAmount=typeCode.endsWith("FA")?(fddRateWithGST-selectedfddRateWithGST):(selectedfddRateWithGST-fddRateWithGST);*/
		    	 newBidAmount=typeCode.endsWith("FA")?(B_fddRateWithGST.subtract(estimatedCostRate)):(estimatedCostRate.subtract(B_fddRateWithGST));
		    	 auctionEndDate=lowestBid.getItemBid().getBidder().getTahdr().getTahdrDetail().iterator().next().getAuctionToDate();
		    	 isMinBidDiffers=newBidAmount.compareTo(minBidDifference)>=0?true:false;
		    }
			Date currentDate=new Date();
			
			Long dateDiff=auctionEndDate.getTime()-currentDate.getTime();
			/*String extendedMinutes=appPropertyUtil.getProperty("eat.auto.extend.minutes");*/
			String extendedMinutes=sysConfiguratorService.getPropertyConfigurator("eat.auto.extend.minutes");

			/*String leftMinutes=appPropertyUtil.getProperty("eat.compare.minutes");*/
			String leftMinutes=sysConfiguratorService.getPropertyConfigurator("eat.compare.minutes");

			
			final int newEndDate =Integer.parseInt(extendedMinutes);
			final int leftTimeInMinutes =Integer.parseInt(leftMinutes);
			
			
			Date newExtendedDate=new Date(auctionEndDate.getTime()+ (60*newEndDate * 1000));
			boolean isExtendingAuction=dateDiff<=leftTimeInMinutes?true:false;
			TAHDRDetailDto updatedDetail=null;
			if(auctionEndDate.compareTo(currentDate)>0){
				if(isMinBidDiffers){
					itemBid=itemBidService.saveNewRevisedBid(itemBid,tahdrId);
					boolean isAuctionExtended=false;
					if(CommonUtil.isCollectionEmpty(itemBid.getResponse().getErrors())){
						if(typeCode.equalsIgnoreCase("QFA") || typeCode.equalsIgnoreCase("QRA")){
							if(typeCode.equalsIgnoreCase("QFA")){
								itemBidService.selectHighestQuickItemBidByTahdrId(tahdrId);
							}else{
								itemBidService.selectLowestQuickItemBidByTahdrId(tahdrId);
							}
						}else{
							if(typeCode.equalsIgnoreCase("FA")){
								itemBidService.selectHighestItemBidByTahdrId(tahdrId);
							}else{
								itemBidService.selectLowestItemBidByTahdrId(tahdrId);
							}
						}
						if(isExtendingAuction){
							   isAuctionExtended=tAHDRDetailService.setNewAuctionFromDate(tahdrId, newExtendedDate);
						}
						Map<String, Object> paramMap= new HashMap<>();		
						paramMap.put("tahdrId", tahdrId);
						updatedDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
						if(isAuctionExtended){
								response.addObject("result", true);
								response.addObject("isAuctionExtended", isAuctionExtended);
								response.addObject("resultMessage", itemBid.getResponse().getMessage()+" And Auction Time is Extended by "+newEndDate+" minutes !");
						}else{
							response.addObject("result", true);
							response.addObject("resultMessage", itemBid.getResponse().getMessage());
						}	
						response.addObject("remainingTime", calculateRemainingTime(updatedDetail.getAuctionToDate()));
					}else{
						response.addObject("result", false);
						response.addObject("resultMessage", "Bid not Saved !");
					}
				}else{
					response.addObject("result", false);
					response.addObject("resultMessage", "Minimum Bid Difference Should be Fulfilled !");
				}
			}else{
				response.addObject("result", false);
				response.addObject("resultMessage", "Auction has ended !");
			}
			/*Map<String, Object> paramMap= new HashMap<>();		
			paramMap.put("tahdrId", tahdrId);
			updatedDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
			response.addObject("newDetail", updatedDetail);*/
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage","New Bid Cannot be less than ZERO !");
		}
		response.addObject("serverTime", new Date());
		return response;
	}

	private OtpDto generatOtp(String tahdrName,Long tahdrId){
		OtpDto otp =new OtpDto();
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		otp.setOtp(Integer.toString(n));
		otp.setAuctionId(tahdrId);
		
		UserDetailsDto details=contextService.getUserDetails();
		String mobileNo=details.getMobileNo();
		otp.setMobileNo("XXXXXXXX"+mobileNo.substring(8, 10));
		boolean isMailed=sendOtp(Integer.toString(n),tahdrName,details);
		 if(isMailed){
			 otp.setIsOtpSent("Y");
		 }else{
			 otp.setIsOtpSent("N");
		 }
		Date newCurrentDate=new Date();
		otp.setOtpGenerationDate(newCurrentDate);
		newCurrentDate = DateUtils.addMinutes(newCurrentDate, 10); 
		otp.setOtpExpiryDate(newCurrentDate);
		return otp;
	}
	
	private boolean sendOtp(String otp,String tahdrName,UserDetailsDto details){
		Map<String, String> smsParam = new HashMap<String, String>();
		String otpString=otp+"( for Auction: "+tahdrName+" )";
		smsParam.put("F1", otpString.replaceAll(" ", "%20"));
		if(details.getCreatedBy() != null ){
			UserDto user=contextService.getUser();
			String sub="Live Bid OTP ";
			String message="<p>Hi</p><br><h1><p>The Otp for Auction: <b>"+tahdrName+"</b> is <b>"+otp+"</b>.This OTP is Valid for 10 MINUTES. </h1><p>"
					+ " <br><h1><p> Regards,<br>Mahavitran State Electricity Board</p></h1>";
			sendMailBidder(user.getEmail(), sub, message);
			
		 return	smsService.sendSMS(details.getMobileNo(), AppBaseConstant.SMS_TEMPLATE_LIVE_BID_OTP, smsParam);
			
		}
		return false;
		
	}
	
	private boolean sendMailBidder(String email,String sub,String message){
		if(!"".equals(email)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setSingleRecipient(email);
			mailService.sendSingleEmailWithResult(mailDto,true);
			return true;
		}
		return false;
	}
	
	private CustomResponseDto validateOtp(OtpDto sessionOtp,OtpDto submittedOtp){
		CustomResponseDto response=new CustomResponseDto();
		if(sessionOtp!=null){
			String otpSubmitted= sessionOtp.getIsOtpSent().equals("Y")?submittedOtp.getOtp():"";
			Long submittedOtpAuctionId= submittedOtp.getAuctionId()==null?0:submittedOtp.getAuctionId();
			Long sessionOtpAuctionId= sessionOtp.getAuctionId()==null?0:sessionOtp.getAuctionId();
			if(CommonUtil.isEqual(submittedOtpAuctionId, sessionOtpAuctionId)){
				if(otpSubmitted.equals(sessionOtp.getOtp())){
					boolean result=otpExpiry(sessionOtp);
					if(!result){
						sessionOtp.setHasRegistered("Y");
						response.addObject("OTP", sessionOtp);
						response.addObject("result", true);
						response.addObject("resultMessage", "Success!");
					}else{
						response.addObject("result", false);
						response.addObject("resultMessage", "OTP Time Expired!");
					}
				}else{
					response.addObject("result", false);
					response.addObject("resultMessage", "Otp Does Not Matched!");
				}
			    }else{
			    	response.addObject("result", false);
					response.addObject("resultMessage", "Otp is of different Auction !");
				}
			}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Session expired!");
		}
		return response;
	}
	
	private boolean otpExpiry(OtpDto sessionOtp){
		Date currentDate=new Date();
		Long currentTime=currentDate.getTime();
		
		Date expiredOtpDate=sessionOtp.getOtpExpiryDate();
		Long expiredOtpTime=expiredOtpDate.getTime();
		
		/*String extendedMinutes=appPropertyUtil.getProperty("eat.compare.otp.expiryminutes");*/
		String extendedMinutes=sysConfiguratorService.getPropertyConfigurator("eat.compare.otp.expiryminutes");

		final int expiryminutes =Integer.parseInt(extendedMinutes);
		
		boolean isOTPExpired=expiredOtpTime-currentTime<=expiryminutes?false:true;
		return isOTPExpired;
	}

	@Override
	public CustomResponseDto getMyAuctionForLiveBid(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		if(partner!=null){
			Map<String, Object> params= new HashMap<>();		
		    params.put("tahdrId", tahdrId);
		    List<TAHDRDto> tahdrList=tAHDRService.findDtos("getOnGoingAuctionListByTahdrId", params);
			if(!CommonUtil.isCollectionEmpty(tahdrList)){
				 response.addObject("tahdrList", tahdrList);
				 response.addObject("result", true); 
				 response.addObject("autoRefreshTimer", AppBaseConstant.AUTO_REFRESH_TIMER); 
				  
			}else{
				response.addObject("resultMessage", "No Auction Found !");
				 response.addObject("result", false); 
			}
		}else{
			response.addObject("resultMessage", "SESSION WAS KILLED !");
			 response.addObject("result", false); 
		}
		return response;
	}

	@Override
	public CustomResponseDto getLiveAuctions(String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		if(partner!=null){
			Map<String, Object> params= new HashMap<>();		
		    params.put("typeCode", typeCode);
		    params.put("partnerId", partner.getbPartnerId());
		    List<TAHDRDto> tahdrList=null;
		    if(typeCode.equalsIgnoreCase(ContextConstant.TAHDR_TYPE_QUICK_FORWARD_AUCTION) || 
		    		typeCode.equalsIgnoreCase(ContextConstant.TAHDR_TYPE_QUICK_REVERSE_AUCTION)){
		    	 tahdrList=tAHDRService.findDtos("getOnGoingQuickAuctionListByTypeCode", params);
		    }else{
		    	tahdrList=tAHDRService.findDtos("getOnGoingAuctionListByTypeCode", params);
		    }
			if(!CommonUtil.isCollectionEmpty(tahdrList)){
				 response.addObject("tahdrList", tahdrList);
				 response.addObject("result", true); 
				 response.addObject("autoRefreshTimer", AppBaseConstant.AUTO_REFRESH_TIMER); 
				 response.addObject("serverTime", new Date());
			}else{
				response.addObject("resultMessage", "No Auction Found !");
				 response.addObject("result", false); 
			}
		}else{
			response.addObject("resultMessage", "SESSION WAS KILLED !");
			 response.addObject("result", false); 
		}
		return response;
	}

	@Override
	public CustomResponseDto getOverAllRank(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
		if(tahdrId!=null && partner!=null){
			String typeCode=tender.getTahdrTypeCode();
			int overAllRank=priceBidService.getOverAllRankBy(tahdrId, partner.getbPartnerId(),typeCode);
			response.addObject("overAllRank", overAllRank);
			response.addObject("result", true);
		}else{
			response.addObject("result", false);
		}
		return response; 
	}

	@Override
	public CustomResponseDto getTAHDRMaterialList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
		if(partner!=null){
			String typeCode=tender.getTahdrTypeCode();
			Map<String, Object> map= new HashMap<>();
			map.put("tahdrId", tahdrId);
			map.put("partnerId", partner.getbPartnerId());
			
			List<ItemBidDto> itemBidList=itemBidService.findDtos("getItemListByLiveAuction", map);
			
				if(!CommonUtil.isCollectionEmpty(itemBidList)){
					
					ItemBidDto itmBid=itemBidList.get(0);
					TAHDRMaterialDto tahdrMaterial=itmBid.getTahdrMaterial()==null?null:itmBid.getTahdrMaterial();
					if(tahdrMaterial!=null){
						Map<String, Object> params= new HashMap<>();		
						 params.put("tahdrMaterialId", tahdrMaterial.getTahdrMaterialId());
						 params.put("tahdrId", tahdrId);
						 
						 List<PriceBidDto> basePriceBidList=tempPriceBidService.findDtos("getTempPriceBidListByTahdrMaterialId", params);
						 if(!CommonUtil.isCollectionEmpty(basePriceBidList)){
							 response.addObject("basePriceBid", basePriceBidList.get(0)); 
						 }else{
							 response.addObject("basePriceBid", null);  
						 }
						 
						 int rank=priceBidService.getRankByMaterial(tahdrId, tahdrMaterial.getTahdrMaterialId(), partner.getbPartnerId(),typeCode);
						 response.addObject("rank", rank);
						 
						PriceBidDto lowestBid=priceBidService.findDto("getLowestPriceBidByTahdrMaterialIdAndTahdrId", params);
						response.addObject("lowestBid", lowestBid);
						 
						params.put("partnerId", partner.getbPartnerId());
						PriceBidDto partnerLatestBid=priceBidService.findDto("getPartnerLatestBidByTahdrMaterialIdAndTahdrId", params);
						response.addObject("partnerLatestBid", partnerLatestBid);
						 
						response.addObject("result", true); 
						
						Map<String, Object> paramMap= new HashMap<>();		
						paramMap.put("tahdrId", tahdrId);
						TAHDRDetailDto tenderDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
						response.addObject("remainingTime", calculateRemainingTime(tenderDetail.getAuctionToDate()));
						
					}else{
						response.addObject("resultMessage", "Material is not present !"); 
					    response.addObject("result", false); 	
					}
					response.addObject("responseList", itemBidList);
					
			}else{
					 response.addObject("result", false); 
					 response.addObject("resultMessage", "No Price Bid Found!"); 
				 }
		}else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}
	
	@Override
	public CustomResponseDto getQuickTAHDRMaterialList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
		if(partner!=null){
			String typeCode=tender.getTahdrTypeCode();
			Map<String, Object> map= new HashMap<>();
			map.put("tahdrId", tahdrId);
			
			List<TAHDRMaterialDto> tenderMaterialList=tAHDRMaterialService.findDtos("getQueryTahdrMaterialListByTahdrId", map);
			
				if(!CommonUtil.isCollectionEmpty(tenderMaterialList)){
					TAHDRMaterialDto tahdrMaterial=tenderMaterialList.get(0);
					Map<String, Object> params= new HashMap<>();		
					params.put("tahdrMaterialId", tahdrMaterial.getTahdrMaterialId());
					params.put("tahdrId", tahdrId);
						 
					List<PriceBidDto> basePriceBidList=tempPriceBidService.findDtos("getTempPriceBidListByTahdrMaterialId", params);
				    if(!CommonUtil.isCollectionEmpty(basePriceBidList)){
							 response.addObject("basePriceBid", basePriceBidList.get(0)); 
					}else{
							 response.addObject("basePriceBid", null);  
					} 
					int rank=priceBidService.getRankByMaterial(tahdrId, tahdrMaterial.getTahdrMaterialId(), partner.getbPartnerId(),typeCode);
					response.addObject("rank", rank);
						 
					PriceBidDto lowestBid=priceBidService.findDto("getQuickLowestPriceBidByTahdrMaterialIdAndTahdrId", params);
					response.addObject("lowestBid", lowestBid);
						 
					params.put("partnerId", partner.getbPartnerId());
					PriceBidDto partnerLatestBid=priceBidService.findDto("getPartnerLatestQuickBidByTahdrMaterialIdAndTahdrId", params);
					response.addObject("partnerLatestBid", partnerLatestBid);
						 
					response.addObject("result", true);
					response.addObject("responseList", tenderMaterialList);
					
					Map<String, Object> paramMap= new HashMap<>();		
					paramMap.put("tahdrId", tahdrId);
					TAHDRDetailDto tenderDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
					response.addObject("remainingTime", calculateRemainingTime(tenderDetail.getAuctionToDate()));
					
			}else{
					 response.addObject("result", false); 
					 response.addObject("resultMessage", "No Price Bid Found!"); 
				 }
		}else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}

	@Override
	public CustomResponseDto getBidderForLiveBidByTahdrId(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
		 if(tahdrMaterialId!=null && partner!=null){
			 String typeCode=tender.getTahdrTypeCode();
		     Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 params.put("tahdrId", tahdrId);
			 
			 List<PriceBidDto> basePriceBidList=tempPriceBidService.findDtos("getTempPriceBidListByTahdrMaterialId", params);
			 if(!CommonUtil.isCollectionEmpty(basePriceBidList)){
				 response.addObject("basePriceBid", basePriceBidList.get(0)); 
			 }else{
				 response.addObject("basePriceBid", null);  
			 }
			 
			 
			 PriceBidDto lowestBid=priceBidService.findDto("getLowestPriceBidByTahdrMaterialIdAndTahdrId", params);
			 response.addObject("lowestBid", lowestBid);
			 
			 int rank=priceBidService.getRankByMaterial(tahdrId, tahdrMaterialId, partner.getbPartnerId(),typeCode);
			 response.addObject("rank", rank);
			 
			 params.put("partnerId", partner.getbPartnerId());
			 PriceBidDto partnerLatestBid=priceBidService.findDto("getPartnerLatestBidByTahdrMaterialIdAndTahdrId", params);
			 response.addObject("partnerLatestBid", partnerLatestBid);
			 
			 response.addObject("result", true); 
			 
			  Map<String, Object> paramMap= new HashMap<>();		
			  paramMap.put("tahdrId", tahdrId);
			  TAHDRDetailDto updatedDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
			  response.addObject("newDetail", updatedDetail);
				
		 }else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}

	@Override
	public CustomResponseDto getBidderListByTahdrId(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response=new CustomResponseDto();
		 if(tahdrMaterialId!=null){
		     Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 params.put("tahdrId", tahdrId);
			 TAHDRDto tender=tAHDRService.findDto(tahdrId);
			 if(tender!=null){
				 String typeCode=tender.getTahdrTypeCode();
				 List<PriceBidDto> bidHistory=new ArrayList<>();
				 if(typeCode.equals("QFA") || typeCode.equals("QRA")){
					if(typeCode.equals("QFA")){
						 bidHistory=priceBidService.findDtos("getDecendingPBQuickBidderDetailListByTahdrMaterialId", params);
					 }else{
						 bidHistory=priceBidService.findDtos("getAscendingPBQuickBidderDetailListByTahdrMaterialId", params);
					 }
				 }
				 else if(typeCode.equals("FA")){
					 bidHistory=priceBidService.findDtos("getDecendingPBBidderDetailListByTahdrMaterialId", params);
				 }else if(typeCode.equals("RA") || typeCode.equals("PT")){
					 bidHistory=priceBidService.findDtos("getAscendingPBBidderDetailListByTahdrMaterialId", params);
				 }
				 response.addObject("bidHistory", bidHistory);
				 response.addObject("result", true); 
						
			 }else{
				 response.addObject("result", false); 
				 response.addObject("resultMessage", "Tender Not Found !"); 
			 }
				
		 }else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}

	@Override
	public CustomResponseDto getBidListByTahdrId(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		 if(tahdrMaterialId!=null && partner!=null){
		     Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 params.put("tahdrId", tahdrId);
			 params.put("partnerId", partner.getbPartnerId());
			 TAHDRDto tender=tAHDRService.findDto(tahdrId);
			 List<PriceBidDto> bidHistory=new ArrayList<>();
			 List<PriceBidDto> tempBidHistory=new ArrayList<>();
			 if(tender!=null){
				 if(tender.getTahdrTypeCode().equalsIgnoreCase("QFA") || tender.getTahdrTypeCode().equalsIgnoreCase("QRA")){
					 bidHistory=priceBidService.findDtos("getSelfPBQuickBidListByTahdrMaterialId", params);
					 tempBidHistory=tempPriceBidService.findDtos("getSelfTempQuickPriceBidListByTahdrMaterialId", params);
				 }else{
					 bidHistory=priceBidService.findDtos("getSelfPBBidListByTahdrMaterialId", params);
					 tempBidHistory=tempPriceBidService.findDtos("getSelfTempPriceBidListByTahdrMaterialId", params); 
				 }
				 bidHistory.addAll(tempBidHistory);
				 response.addObject("bidHistory", bidHistory);
			 }
			 response.addObject("bidHistory", bidHistory);
			 response.addObject("result", true); 
				
		 }else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}
	
	@Override
	public List<PriceBidDto> getBidListByBidderId(Long tahdrMaterialId,Long bidderId) {
		 if(tahdrMaterialId!=null && bidderId!=null){
		     Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 params.put("bidderId", bidderId);
			 List<PriceBidDto> currentBid=priceBidService.findDtos("getSelfBidHistoryByBidderIdTahdrMaterialId", params);
			 List<PriceBidDto> bidHistory=tempPriceBidService.findDtos("getTempPBHistoryByBidderId", params);
			 currentBid.addAll(bidHistory);
			 return currentBid;
		 }else{
			  return null;
		 }
	}

	@Override
	public List<PriceBidDto> getCompleteBidListTahdrMaterialId(Long tahdrMaterialId) {
		 if(tahdrMaterialId!=null){
		     Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 List<PriceBidDto> currentBid=priceBidService.findDtos("getCompleteBidHistoryTahdrMaterialId", params);
			 List<PriceBidDto> bidHistory=tempPriceBidService.findDtos("getCompleteTempPBHistoryByTahdrMaterialId", params);
			 currentBid.addAll(bidHistory);
			 return currentBid;
		 }else{
			  return null;
		 }
	}
	
	
	@Override
	public CustomResponseDto resendOtp(Long tahdrId, String tahdrName, HttpServletRequest request) {
		CustomResponseDto response=new CustomResponseDto();
		HttpSession session = request.getSession(true);
		OtpDto otp=generatOtp(tahdrName,tahdrId);
		session.setAttribute("otp", otp);
		boolean isOtpSent=otp.getIsOtpSent().equals("Y")?true:false;
		if(isOtpSent){
			 response.addObject("result", true); 
			 response.addObject("resultMessage", "Otp Sent to your Registered Mobile No "+otp.getMobileNo()+"--"+otp.getOtp()); 
		}else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		}
		return response;
	}

	@Override
	public CustomResponseDto validateOtp(OtpDto otp, HttpServletRequest request) {
		CustomResponseDto response=new CustomResponseDto();
		HttpSession session = request.getSession(true);
		OtpDto sessionOtp=(OtpDto) session.getAttribute("otp");
		if(sessionOtp!=null){
			response=validateOtp(sessionOtp, otp);
			boolean result=(boolean) response.getObjectMap().get("result");
				if(result){
					OtpDto newOtp=(OtpDto) response.getObjectMap().get("OTP");
			    	session.setAttribute("otp", newOtp);
				}
			}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Otp Yet not generate,Please generate OTP for current session!");
		}
		return response;
	}

	@Override
	public CustomResponseDto saveNewBid(PriceBidDto newBid, HttpServletRequest request) {
		CustomResponseDto response= new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		if(newBid!=null && partner!=null){
			response=submitNewBid(newBid, partner);
			boolean bidSubmitted=response.getObjectMap().get("result")==null?false:(boolean) response.getObjectMap().get("result");
			if(bidSubmitted){
				HttpSession session = request.getSession(true);
				session.setAttribute("isNewBid", false);
			}
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage","New Bid Not Saved !");
		}
		return response;
		
	}

	@Override
	public CustomResponseDto autoRefreshData(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response= new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		if(tahdrId!=null && tahdrMaterialId!=null){
			TAHDRDto tender=tAHDRService.findDto(tahdrId);
		     String typeCode=tender.getTahdrTypeCode();
			 Map<String, Object> params= new HashMap<>();		
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 params.put("tahdrId", tahdrId);
			 PriceBidDto lowestBid=null;
			 if(typeCode.equals("QFA") || typeCode.equals("QRA")){
				 lowestBid=priceBidService.findDto("getQuickLowestPriceBidByTahdrMaterialIdAndTahdrId", params);
			 }else{
				 lowestBid=priceBidService.findDto("getLowestPriceBidByTahdrMaterialIdAndTahdrId", params);
			 }
			
			 response.addObject("lowestBid", lowestBid);
			 params.put("partnerId", partner.getbPartnerId());
			/* PriceBidDto mylowestBid=priceBidService.findDto("getPartnerLatestBidByTahdrMaterialIdAndTahdrId", params);*/
			 PriceBidDto mylowestBid=priceBidService.findDto("getPartnerLatestQuickBidByTahdrMaterialIdAndTahdrId", params);
			 response.addObject("mylowestBid", mylowestBid);
			 
			 int rank=priceBidService.getRankByMaterial(tahdrId, tahdrMaterialId, partner.getbPartnerId(),typeCode);
			 response.addObject("rank", rank);
			 response.addObject("result", true);
			 
			 Map<String, Object> paramMap= new HashMap<>();		
			 paramMap.put("tahdrId", tahdrId);
			 TAHDRDetailDto tenderDetail=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", paramMap);
			 response.addObject("remainingTime", calculateRemainingTime(tenderDetail.getAuctionToDate()));
			 
			 CustomResponseDto responseBidHistory= getBidderListByTahdrId(tahdrId, tahdrMaterialId);
			 response.getObjectMap().putAll(responseBidHistory.getObjectMap());
		}else{
			response.addObject("result", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto sendOtp(Long tahdrId, String tahdrName, HttpServletRequest request) {
		CustomResponseDto response=new CustomResponseDto(); 
		HttpSession session = request.getSession(true);
		int isAttributePresent=session.getAttribute("otp")==null?0:1;
		if(isAttributePresent==0){
			OtpDto newOtp=generatOtp(tahdrName,tahdrId);
			session.setAttribute("otp", newOtp);
		}
		OtpDto otp=(OtpDto) session.getAttribute("otp"); // get value from session if new or what.
		if(otp!=null){
			response.addObject("result", true);
			boolean isOTPExpired=otpExpiry(otp);
			if(isOTPExpired){
				OtpDto newOtp=generatOtp(tahdrName,tahdrId);
				session.setAttribute("otp", newOtp);
				otp=newOtp;
			}
			boolean hasRegistered=otp.getHasRegistered()==null?false:true;
					    if(hasRegistered){
					    	response.addObject("hasRegistered", true);
							response.addObject("resultMessage", "has already registered for current session!");
					   }else{
							boolean isOtpSent=otp.getIsOtpSent().equals("Y")?true:false;
					    	if(isOtpSent){
						        response.addObject("otpResult", true);
						        response.addObject("hasRegistered", false);
								response.addObject("resultMessage", "Otp Sent to your Registered Mobile No "+otp.getMobileNo()+" -- "+otp.getOtp());
					    	}else{
						        response.addObject("otpResult", false);
						        response.addObject("hasRegistered", false);
								response.addObject("resultMessage", "Otp not sent,Something went wrong !");
					    	}	
					    }
		}else{
			         response.addObject("result", false);
			         response.addObject("otpResult", false);
					 response.addObject("resultMessage", "Something is wrong !");
			}
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto saveQuickAuctionWinner(Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			boolean result=itemBidService.updateQuickItemForBidWinner(tahdrId);
			if(result){
				result=bidderService.updateQuickBidderForWinner(tahdrId);	
				if(result){
					Map<String, Object> params=new HashMap<>();
					params.put("tahdrStatusCode", AppBaseConstant.QUICK_AUCTION_AWARD_WINNER_COMPLETED);
					int data=tAHDRService.updateByJpql(params, "tahdrId", tahdrId);
					if(data>0){
						response.addObject("Message", "Quick Auction Submmitted,Winner Selected !");
						response.addObject("Status", true);
					}else{
						response.addObject("Message", "Tender Status Not updated !");
						response.addObject("Status", false);
						throw new CustomException("Tender Status Not updated");
					}
				}else{
					response.addObject("Message", "Could Not Update Bidder Status");
					response.addObject("Status", false);
					throw new CustomException("Tender Status Not updated");
				}
			}else{
				response.addObject("Message", "Could Not Update Item Bid Status");
				response.addObject("Status", false);
				throw new CustomException("Tender Status Not updated");
			}
		}else{
			response.addObject("Message", "Something went wrong");
			response.addObject("Status", false);
			throw new CustomException("Tender Status Not updated");
		}
		return response;
	}
	
	public String calculateRemainingTime(Date bidEndDate){
		if(bidEndDate!=null){
			long bidEndTime=bidEndDate.getTime();
			long currendTime=new Date().getTime();
			long diff=bidEndTime-currendTime;
			if(diff>0){
	 			 int dd = (int) Math.floor(diff / (1000 * 60 * 60 * 24));
	 		     int hh = (int) Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	 		     int mm = (int) Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
	 		     int ss = (int) Math.floor((diff % (1000 * 60)) / 1000);
	 		
	 			return dd+"d"+hh+"h"+mm+"m"+ss+"s";
			}else
			return "0s";
		}else
			return "0s";
		
	}
}
