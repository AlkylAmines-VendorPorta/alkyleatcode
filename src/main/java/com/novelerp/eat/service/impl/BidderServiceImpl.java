/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.BidderFilterDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.BidderDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.Bidder;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRDetailService;

@Service
public class BidderServiceImpl extends AbstractContextServiceImpl<Bidder, BidderDto> implements BidderService {

	private Logger log = LoggerFactory.getLogger(BidderServiceImpl.class);
	
	@Autowired
	private BidderDao bidderDao;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@Autowired
	private BidderService bidderService;
	
	@PostConstruct
	private void init(){
		super.init(BidderServiceImpl.class, bidderDao, Bidder.class, BidderDto.class);
		/*setObjectConverter(bidderConverter);*/
		setByPassProxy(true);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.BidderService#createBidder(com.novelerp.eat.dto.PaymentDetailDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto createBidder(PaymentDetailDto payment) {
		if(payment==null){
			return null;
		}
		BidderDto bidder= new BidderDto();
		bidder.setFactory(payment.getPartnerOrg());
		if(!payment.getResponse().isHasError()){
			bidder.setTahdr(payment.getTahdr());
			bidder.setTenderDetail(payment.getTahdrDetail());
			bidder.setTenderPurchase(payment);
			bidder.setStatus(AppBaseConstant.BIDDER_STATUS_PURCHASED);
			
			bidder=save(bidder);
			/*BidderDto existingBidder=isAlreadyExist(payment.getTahdr());
			if(null==existingBidder){
				bidder=save(bidder);
			}else{
				bidder=existingBidder;
			}*/
		}else{
			bidder.setResponse(payment.getResponse());
		}
		return bidder;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto createBidderForOP(PaymentDetailDto payment) {
		if(payment==null){
			return null;
		}
		BidderDto bidder= new BidderDto();
		bidder.setFactory(payment.getPartnerOrg());
		if(!payment.getResponse().isHasError()){
			bidder.setTahdr(payment.getTahdr());
			bidder.setTenderDetail(payment.getTahdrDetail());
			bidder.setTenderPurchase(payment);
			bidder.setStatus(AppBaseConstant.BIDDER_STATUS_PURCHASED);
			bidder.setPartner(payment.getPartner());
			bidder.setCreatedBy(payment.getCreatedBy());
			bidder.setUpdatedBy(payment.getUpdatedBy());
			bidder.setCreated(new Timestamp(System.currentTimeMillis()));
			bidder.setUpdated(new Timestamp(System.currentTimeMillis()));
			bidder=save(bidder);
			/*BidderDto existingBidder=isAlreadyExist(payment.getTahdr());
			if(null==existingBidder){
				bidder=save(bidder);
			}else{
				bidder=existingBidder;
			}*/
		}else{
			bidder.setResponse(payment.getResponse());
		}
		return bidder;
	}
	
	/*private BidderDto isAlreadyExist(TAHDRDto tahdr){
		BPartnerDto partner=contextService.getPartner();
		Map<String, Object> map=new HashMap<>();
		map.put("tahdrId", tahdr.getTahdrId());
		map.put("partnerId", partner.getbPartnerId());
		BidderDto bidder=findDto("getBidderByTahdrId",map);
		return bidder;
	}*/
	
	/*@Override
	public BidderDto getBidderByTahdrDetailId(Long id){
		BidderDto bidder=null;
		try{
			BPartnerDto partner=contextService.getPartner();
			Map<String,Object> map=new HashMap<>();
			map.put("tahdrDetailId", id);
			map.put("partnerId", partner.getbPartnerId());
			bidder= findDto("getBidderByTahdrDetailId", map);
		}
		catch(Exception ex)
		{
			log.error("Exception", ex);
		}
		
		return bidder;
	}
	*/
	@Override
	public List<BidderDto> getBidderTahdrDetailList(String typeCode,String queryMethodName){
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrTypeCode",typeCode);
		map.put("partnerId", partner.getbPartnerId());
		List<BidderDto> bidderList= findDtos(queryMethodName, map );
		return bidderList;
	}
	
	@Override
	public List<BidderDto> getBidderDeviationTahdrDetailList(String typeCode){
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrTypeCode",typeCode);
		map.put("partnerId", partner.getbPartnerId());
		map.put("status", "DBSCH");
		List<BidderDto> bidderList= findDtos("getBidderDeviationTenderDetailByTypeCode", map );
		return bidderList;
	}
	
	@Override
	public BidderDto getBidderTahdrDetail(Long tahdrDetailId){
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrDetailId",tahdrDetailId);
		map.put("partnerId", partner.getbPartnerId());
		BidderDto bidder=findDto("getTenderForBid", map);
		return bidder;
	}

	@Override
	public BidderDto getBidsByBidderQuery(Long tahdrDetailId){
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrDetailId",tahdrDetailId);
		map.put("partnerId", partner.getbPartnerId());
		BidderDto bidder=findDto("getBidderByTahdrDetailId",map);
		try{
			Set<ItemBidDto> itemBidSet=new HashSet<>();
			List<ItemBidDto>itemBidList=itemBidService.findDtos("getItemBidsForBidder", map);
			itemBidSet.addAll(itemBidList);
			bidder.setItemBidList(itemBidSet);
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
		map= new HashMap<>();
		map.put("tahdrDetailId", tahdrDetailId);
		TAHDRDetailDto tahdrDetailDto=null;
		
		/*String isTradingItem=sysConfiguratorService.getPropertyConfigurator("eat.material.trading_item_check");
		boolean tradingItemCheck=false;
		if(isTradingItem.equalsIgnoreCase("Y")){
			tradingItemCheck=true;
		}*/
		
		if(bidder.getTahdr().getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			map.put("partnerId", partner.getbPartnerId());
			if(bidder.getFactory()!=null){
				map.put("factoryId", bidder.getFactory().getPartnerOrgId());
				if(bidder.getTenderPurchase().getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
					tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturerEXEM", map);
				}else{
					tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturer", map);
				}
			}else{
				map= new HashMap<>();
				map.put("tahdrDetailId", tahdrDetailId);
				tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPTrader", map);
			}
		}else{
			map= new HashMap<>();
			map.put("tahdrDetailId", tahdrDetailId);
			tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTP", map);
		}
		bidder=mergeUnsavedItemBids(bidder,tahdrDetailDto);
		return bidder;
	}	
	
	/*@Override
	public BidderDto getCommercialBid(Long tahdrId,Long partnerId, String section){
		
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("partnerId", partnerId);
		BidderDto bidder=findDto("getCommercialBid",map);
		
		map=new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("sectionCode", section);
		List<SectionDocumentDto> sectionDocList=secDocService.findDtos("getRequiredDocBySection", map);
		
		if(bidder.getCommercialBid()==null){
			bidder.setCommercialBid(new CommercialBidDto());
		}
		
		bidder.getCommercialBid().setBidderSecDoc(getBidderSectionDocSet(sectionDocList,bidder.getCommercialBid().getBidderSecDoc()));
		
		return bidder;
	}*/
	
	
	
	private ItemBidDto getItemBidFromTM(TAHDRMaterialDto tmDto){
		ItemBidDto itemBidDto=new ItemBidDto();
		itemBidDto.setTahdrMaterial(tmDto);
		/*itemBidDto.setTechnicalBid(getTechnicalBidFromBGSet(tmDto));
		itemBidDto.setPriceBid(getPriceBidFromTM(tmDto));*/
		return itemBidDto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateStatus(String status,Long bidderId){
		int result=0;
		try{
				result =bidderDao.updatBidderStatus(status, bidderId,null);
		}catch(Exception e){
			throw new RuntimeException("Exception in Updating Bidder Status");
		}
		if(result>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateStatusWithItemBidStatus(String status,String itemBidStatus,Long bidderId){
		int result=0;
		try{
				result =bidderDao.updateStatusWithItemBidStatus(status,itemBidStatus, bidderId);
		}catch(Exception e){
			throw new RuntimeException("Exception in Updating Bidder Status");
		}
	     
		if(result>0){
			return true;
			}
		return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBidderStatusWithSelectedStatus(String status,String oldStatus,Long bidderId){
		int result=0;
		try{
				result =bidderDao.updateBidderStatusWithSelectedStatus(status, oldStatus, bidderId);
		}catch(Exception e){
			throw new RuntimeException("Exception in Updating Bidder Status");
		}
		if(result>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBidderStatusForNoCommercialDeviation(String status,Long bidderId){
		int result=0;
		try{
				result =bidderDao.updateBidderStatusForNoCommercialDeviation(status,bidderId);
		}catch(Exception e){
			throw new RuntimeException("Exception in Updating Bidder Status");
		}
		if(result>0){
			return true;
			}
		return false;
	}

	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto saveCommercialBid(BidderDto dto){
		if(dto.getBidderId()!=null || dto.getBidderId()>0){
			dto.getCommercialBid().setBidder(dto);
			dto.setCommercialBid(commercialBidService.save(dto.getCommercialBid()));
			dto.setResponse(dto.getCommercialBid().getResponse());
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CommercialBidDto saveCommercialBid(CommercialBidDto dto){
		if(dto!=null && dto.getBidder()!=null && (dto.getBidder().getBidderId()!=null || dto.getBidder().getBidderId()>0)){
			return commercialBidService.save(dto);
		}
		return dto;
	}*/
	
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto saveCommercialSectionDocument(BidderDto dto) {
		if(null!=dto.getBidderId() && (dto.getBidderId()>0)){
			dto.getCommercialBid().setBidder(dto);
			commercialBidService.save(dto.getCommercialBid());
		}
		
		return dto;
	}*/
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateBidderStatusByOpeningType(String status,String oldStatus,Long tahdrId){
		return bidderDao.updateBidderStatusByOpeningType(status, oldStatus,tahdrId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateBidderStatusByPBOpeningType(String status,String oldStatus,Long tahdrId){
		return bidderDao.updateBidderStatusByPBOpeningType(status, oldStatus,tahdrId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateBidderStatusBySBPBOpeningType(String status,String oldStatus,Long tahdrId){
		return bidderDao.updateBidderStatusBySBPBOpeningType(status, oldStatus,tahdrId);
	}
	
	@Override
	public List<BidderDto> getBidderAssociatedTenders(TenderCommitteeDto tenderCommitteeDto,BPartnerDto partner){
		 List<BidderDto> bidderTenderList=new ArrayList<BidderDto>();
		Map<String, Object> params=getParameterMap(tenderCommitteeDto,partner);
		String query =bidderDao.getBidderAssociatedTendersQuery(tenderCommitteeDto);
		bidderTenderList=findDtosByQuery(query, params);
		return bidderTenderList;
	}
	private Map<String,Object> getParameterMap(TenderCommitteeDto tenderCommitteeDto,BPartnerDto partner){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if( partner!=null){
			params.put("partnerId", partner.getbPartnerId());
		}
		if(tenderCommitteeDto.getTahdr().getTahdrTypeCode()!=null){
			params.put("tahdrTypeCode",tenderCommitteeDto.getTahdr().getTahdrTypeCode());
		}
		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
			Date technicalOpening =  tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate() ;
			params.put("technicalOpening", technicalOpening);
		}
		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
			Date deviationOpening =tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening );
		}	
		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
			Date c1Opening = tenderCommitteeDto.getTenderVersion().getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
			Date priceBidOpening = tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if(tenderCommitteeDto.getTahdr()!=null){
			/*params.put("status",tenderCommitteeDto.getTahdr().getTahdrStatusCode());*/
			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
				params.put("tahdrCode","%"+tenderCommitteeDto.getTahdr().getTahdrCode().toUpperCase()+"%");
			}
		}
		
		return params;
	}
	private Map<String,Object> getParameterMap(TAHDRDetailDto tahdrDetailDto,BPartnerDto partner){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if( partner!=null){
			params.put("partnerId", partner.getbPartnerId());
		}
		if(tahdrDetailDto.getTahdr().getTahdrTypeCode()!=null){
			params.put("tahdrTypeCode",tahdrDetailDto.getTahdr().getTahdrTypeCode());
		}
		if(tahdrDetailDto.getTechBidOpenningDate()!=null){
			Date technicalOpening =  tahdrDetailDto.getTechBidOpenningDate() ;
			params.put("technicalOpening", technicalOpening);
		}
		if(tahdrDetailDto.getDeviationOpenningDate()!=null ){
			Date deviationOpening =tahdrDetailDto.getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening );
		}	
		if(tahdrDetailDto.getC1OpenningDate()!=null){
			Date c1Opening = tahdrDetailDto.getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if(tahdrDetailDto.getPriceBidOpenningDate()!=null){
			Date priceBidOpening = tahdrDetailDto.getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if(tahdrDetailDto.getTahdr()!=null){
			/*params.put("status",tenderCommitteeDto.getTahdr().getTahdrStatusCode());*/
			if(!tahdrDetailDto.getTahdr().getTahdrCode().equals("")){
				params.put("tahdrCode","%"+tahdrDetailDto.getTahdr().getTahdrCode().toUpperCase()+"%");
			}
		}
		
		return params;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBidderPreliminaryScrutinyStatus(String newStatus,BidderDto bidderDto){
		Long bidderId=bidderDto.getBidderId();
		String oldStatus=bidderDto.getStatus();
		String clauseStatus=null;
		boolean doUpdate = false;
		if(null==oldStatus){
			doUpdate = true;
		}
		else if(oldStatus.equals(newStatus)){
			doUpdate=false;
		}else if(AppBaseConstant.PRELIMINARY_COMMERCIAL_FAILED.equals(newStatus)){
			doUpdate=true;
		}else if(AppBaseConstant.DEVIATION_CALLED.equals(oldStatus)){
			if(AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED.equals(newStatus)){
				newStatus=AppBaseConstant.SCRUTINY_PARTIAL_PASSED;
				clauseStatus=AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED;
				doUpdate=true;
			}else{
				doUpdate=false;
			}
		}else if(AppBaseConstant.DEVIATION_CALLED.equals(newStatus)){
			doUpdate=true;
		}
		else{
			doUpdate=true;
			newStatus=AppBaseConstant.SCRUTINY_PARTIAL_PASSED;
		}
		int count=0;
		if(doUpdate){
			/*String resultantStatus=getBidderUpdatedScrutinyStatus(bidderId);
			System.out.println("resultant bidder status: "+resultantStatus);*/
			count = bidderDao.updatBidderStatus(newStatus, bidderId,clauseStatus);
		}
		return count>0?true:false;
	}
	
	@Override
	public List<String> getBidderMailListByTahdrId(Long tahdrId){
		List<String> mailList=new ArrayList<String>();
		Map<String,Object> params=new HashMap<>();
		params.put("tahdrId",tahdrId);
		List<BidderDto> bidderList=findDtos("getQueryForMailListByTahdrId", params);
		if(!CommonUtil.isCollectionEmpty(bidderList)){
			for(BidderDto bidder:bidderList){
				UserDto user=bidder.getCreatedBy();
				if(user!=null){
					mailList.add(user.getEmail());
				}
			}
		}
		return mailList;
	}
	
	@Override
	public List<String> getBidderMailListByTahdrId(Long tahdrId,String status){
		List<String> mailList=new ArrayList<String>();
		Map<String,Object> params=new HashMap<>();
		params.put("tahdrId",tahdrId);
		params.put("status",status);
		List<BidderDto> bidderList=findDtos("getQueryForMailListByTahdrIdAndStatus", params);
		for(BidderDto bidder:bidderList){
			UserDto user=bidder.getCreatedBy();
			if(user!=null){
				mailList.add(user.getEmail());
			}
		}
		return mailList;
	}
	@Override
	public boolean sendMailToAllBidder(List<String> emailList,String sub,String message){
		if(!CommonUtil.isCollectionEmpty(emailList)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setRecipientList(emailList);
			mailService.sendEmail(mailDto,true);
			return true;
		}
		return false;
	}
	@Override
	public boolean sendCustomMailToAllBidder(Map<String,StringBuilder> mails,String sub){
		if(!mails.isEmpty()){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			for(Map.Entry<String, StringBuilder> entry : mails.entrySet()){
				mailDto.setMailContent(entry.getValue().toString());
				mailDto.setSingleRecipient(entry.getKey());
				mailService.sendSingleEmailWithResult(mailDto,true);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean sendMailBidder(String email,String sub,String message){
		if(!"".equals(email)){
			List<String> emailList=new ArrayList<String>();
			emailList.add(email);
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setRecipientList(emailList);
			mailService.sendEmail(mailDto);
			return true;
		}
		return false;
	}
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBidderFinalScrutinyStatus(String newStatus,BidderDto bidderDto){
		Long bidderId=bidderDto.getBidderId();
		String oldStatus=bidderDto.getStatus();
		boolean doUpdate = false;
		if(null==oldStatus){
			doUpdate = true;
		}else if(oldStatus.equals(newStatus)){
			doUpdate=false;
		}
		else if(AppBaseConstant.FINAL_COMMERCIAL_FAILED.equals(newStatus)){
			doUpdate=true;
		}else{
			doUpdate=true;
			newStatus=AppBaseConstant.SCRUTINY_PARTIAL_PASSED;
		}
		int count=0;
		if(doUpdate){
			count = bidderDao.updatBidderStatus(newStatus, bidderId);
		}
		
		return count>0?true:false;
	}*/
	
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto submitBidder(CommercialBid cb){
		ResponseDto response=new ResponseDto();
		CommercialBidDto cb=bidder.getCommercialBid();
		cb.setStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		bidder=saveCommercialBid(bidder);
		commercialBidService.save(cb);
		if(bidder.getBidderId()<=0){
			response.setHasError(true);
			response.setMessage("Something is not right");
		}else{
			int count=bidderDao.submitBidder(bidder);
			updateStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED, bidder.getBidderId());
			response.setMessage("All Bids Submitted");
		}
		return bidder;
	}*/
	
	@Override
	public CustomResponseDto checkDeviationSchedule(Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrDetailId", tahdrDetailId);
		int tcopCount=0;
		int dvtnCount=0;
		boolean status=false;
		List<BidderDto> bidderList=findDtos("getBidderDetailListForScrutiny",params);
		if(!CommonUtil.isCollectionEmpty(bidderList)){
			for(BidderDto bidder:bidderList){
				if(bidder.getStatus()!=null){
					if(bidder.getStatus().equals("TCOP")){
						tcopCount++;
					}else if(bidder.getStatus().equals("DVTN")){
						dvtnCount++;
					}
				}
			}
		}
		response.addObject("tcopCount", tcopCount);
		response.addObject("dvtnCount", dvtnCount);
		response.addObject("status", status);
		return response;
	}
	
	private BidderDto mergeUnsavedItemBids(BidderDto bidder, TAHDRDetailDto tahdrDetail){
		for(TAHDRMaterialDto tmDto: tahdrDetail.getTahdrMaterial()){
			if(bidder.getItemBidList().isEmpty()){
				bidder.getItemBidList().add(getItemBidFromTM(tmDto));
			}else{
				int count=0;	
				for(ItemBidDto itembid:bidder.getItemBidList()){
					if(tmDto.getTahdrMaterialId().compareTo(itembid.getTahdrMaterial().getTahdrMaterialId())!=0){
						count++;
					}
				}
				if(count==bidder.getItemBidList().size()){
					bidder.getItemBidList().add(getItemBidFromTM(tmDto));
				}
			}
		}
		
		return bidder;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBidderStatusForC1Called(String status,Long tahdrId){
		int count=bidderDao.updateBidderStatusForC1Called(status,tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateBidderRating(BidderDto bidderDto, double newRating){
		double oldRating = bidderDto.getRating();
		long oldRateCount = bidderDto.getRateCount(); 
		double updatedRating;
		updatedRating = ((oldRating*oldRateCount)+newRating)/(oldRateCount+1);
		Map<String, Object> map = new HashMap<>();
		map.put("rating", updatedRating);
		map.put("rateCount", oldRateCount+1);
		return updateByJpql(map, "bidderId",bidderDto.getBidderId());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto purchaseTender(PaymentDetailDto payment){
		BidderDto bidder=null;
			payment=paymentDetailService.save(payment);
			bidder=createBidder(payment);
		
		return bidder;
	}
	
	public long getAwardWinnerCount(Long  tahdrId){
		long totalCount=0;
		try{
			totalCount =bidderDao.getAwardWinnerCount(tahdrId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return totalCount;
	}
	
	public Map<Long, String> getBidderFinalStatus(Long tahdrId,String scrutinyLevel){
		List<BidderDto> bidderList=findDtos("getBidderListOnlyByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		Map<Long, String> bidderStatus= new HashMap<>();
		for(BidderDto dto:bidderList){
			Long bidderId=dto.getBidderId();
			String status=getBidderUpdatedScrutinyStatus(bidderId,scrutinyLevel);
			bidderStatus.put(bidderId, status);
		}
		System.out.println(bidderStatus.toString());
		return bidderStatus;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setBidderStatus(Map<Long, String> bidderStatus){
		boolean result=false;
		for(Map.Entry<Long, String> entry : bidderStatus.entrySet()){
		result=updateStatus(entry.getValue(),entry.getKey());
		if(!result)
			return result;
		}
		return result;
	}
	
	
    public String getBidderUpdatedScrutinyStatus(Long bidderId,String scrutinyLevel){
    	BidderDto bidder=findDto(bidderId);
    	List<ItemBidDto> allItemBid=itemBidService.findDtos("getItemListByBidderId",AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
    	String status="";
    	List<String> itemBidStatus=new ArrayList<String>();
    	if(!CommonUtil.isCollectionEmpty(allItemBid) && bidder!=null){
    		for(ItemBidDto dto:allItemBid){
        		itemBidStatus.add(dto.getStatus());
        	}
        	String bidderStatus=bidder.getStatus();
        	if(scrutinyLevel.equals("PRELIMINARY")){
        		status=getResultantBidderStatus(itemBidStatus,bidderStatus);	
        	}else{
        		status=getFinalResultantBidderStatus(itemBidStatus,bidderStatus);
        	}
        	
    	}
    	return status;
    }

   
    private String getResultantBidderStatus(List<String> itemBidStatus,String bidderStatus){
    	String resultantItemBidStatus="";
    	String resultantStatus=bidderStatus;
    	if(!bidderStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_COMMERCIAL_FAILED)){
    		resultantItemBidStatus=getResultantItemBidStatus(itemBidStatus);
    		if(!resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED)){
    			if(resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.DEVIATION_CALLED)){
    				resultantStatus=AppBaseConstant.DEVIATION_CALLED;
    			}else if(bidderStatus.equalsIgnoreCase(AppBaseConstant.DEVIATION_CALLED) && resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED)){
    				resultantStatus=AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED;
    			}else if(bidderStatus.equalsIgnoreCase(AppBaseConstant.DEVIATION_CALLED) && resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED)){
    				resultantStatus=AppBaseConstant.DEVIATION_CALLED;
    			}else{
    				resultantStatus=resultantItemBidStatus;
    			}
    		}else{
    			resultantStatus=resultantItemBidStatus;
    		}
    	}
    	return resultantStatus;
    }
    
    public String getFinalResultantBidderStatus(List<String> itemBidStatus,String bidderStatus){
    	String resultantItemBidStatus="";
    	String resultantStatus=bidderStatus;
    	if( !bidderStatus.equalsIgnoreCase(AppBaseConstant.FINAL_COMMERCIAL_FAILED)){
    		resultantItemBidStatus=getFinalResultantItemBidStatus(itemBidStatus);
    		if(!resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED) || !resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.SCRUTINY_FAILED)){
    			if(resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.FINAL_TECHNICAL_PASSED)){
    				resultantStatus=AppBaseConstant.FINAL_TECHNICAL_PASSED;
    			}else if(resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.FINAL_TECHNICAL_FAILED)){
    				resultantStatus=AppBaseConstant.FINAL_TECHNICAL_FAILED;
    			}else{
    				resultantStatus=resultantItemBidStatus;
    			}
    		}
    	}
    	return resultantStatus;
    }
    
    private String getResultantItemBidStatus(List<String> itemBidStatus){
    	String resultantItemBidStatus="";
    	for(String s:itemBidStatus){
    		if(resultantItemBidStatus.equals("")){
    			resultantItemBidStatus=s;
    		}else if(s.equals(AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED)){
    			resultantItemBidStatus=s;
    		}else if(s.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED)){
    			resultantItemBidStatus=AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED;
    		}
    		else if(s.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED) && resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED)){
    			resultantItemBidStatus=AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED;
    		}else{
    			resultantItemBidStatus=s;
    		}
    	}
    	return resultantItemBidStatus;
    }
    
    private String getFinalResultantItemBidStatus(List<String> itemBidStatus){
    	String resultantItemBidStatus="";
    	for(String s:itemBidStatus){
    		if(s.equalsIgnoreCase(AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED)){
    			resultantItemBidStatus=AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED;
    			continue;
    		}else if(!s.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED) && !s.equalsIgnoreCase(AppBaseConstant.SCRUTINY_PASSED)){
    			if(resultantItemBidStatus.equals("")){
    				resultantItemBidStatus=s;
	    		}else if(s.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED) 
    				&& (resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.SCRUTINY_FAILED) || resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED))){
    			resultantItemBidStatus=AppBaseConstant.SCRUTINY_FAILED;
	    		}else if(s.equalsIgnoreCase(AppBaseConstant.SCRUTINY_FAILED) 
	    				&& (resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.SCRUTINY_FAILED) || resultantItemBidStatus.equalsIgnoreCase(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED))){
	    			resultantItemBidStatus=AppBaseConstant.SCRUTINY_FAILED;
	    		}else{
	    			resultantItemBidStatus=s;
	    		}
    		}else{
    			resultantItemBidStatus=s;
    			break;
    		}
    	}
    	return resultantItemBidStatus;
    }
    
    @Override
	public List<BidderDto> getBidderListForSubmissionReminderMail(Long tahdrId, String status){
		String queryString = bidderDao.getTahdrListOfSubmissionForReminderMail(status);
		Map<String, Object> params=new HashMap<>();
		params.put("tahdrId", tahdrId);
		List<BidderDto> bidderEntity = findDtosByQuery(queryString, params);
		return bidderEntity;
	}
    
    @Override
	public void mailNotificationForReSubmission(Long tahdrDetailId){
    	try{
    	if(tahdrDetailId!=null){
    		Map<String, Object> parameter=new HashMap<>();
    		parameter.put("tahdrDetailId", tahdrDetailId);
    		TAHDRDetailDto tahdrDetail =tahdrDetailService.findDto("getTahdrDetailFromTahdrDetailId",parameter);
    		
    		UserDto userDto=contextService.getUser();
    		
    		MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.BID_RESUBMITTED);
			Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", map);
			if(mailData!=null  && internalUser!=null){
				mailNotificationForReSubmissionOfBid(userDto,tahdrDetail.getTahdr(),mailData,internalUser);
			}
    	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
			log.info("mailNotificationForReSubmission/BidderServiceIMPL" + e);
		}
		
		}
	@Override
	public void mailNotificationForReSubmissionOfBid(UserDto bidder,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
			if(mailData!=null  && internalUser!=null){
				String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				Map<String, Object> params=new HashMap<>();
				params.put("@sourceCompanyName@",internalUser.getPartner().getName());
				params.put("@honour@","");
				params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
				params.put("@emailCorrCode@","Renewal");
				params.put("@generalDetailDate@",today);
				params.put("@vendorComapnyName@",bidder.getPartner().getName());
				params.put("@factoryName@","");
				params.put("@factoryCity@","");
				params.put("@vendorEmail@",bidder.getEmail());
				params.put("@tenderCode@", tahdr.getTahdrCode());
				params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
				params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
				params.put("@companyFax@",internalUser.getUserDetails().getFax1());
				if(bidder.getEmail()!=null){
					mailService.sentMailByTemplate(mailData, params, bidder.getEmail());
				}
			}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			log.info("mailNotificationForReSubmissionOfBid/BidderServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("mailNotificationForReSubmissionOfBid/BidderServiceIMPL" + e);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateQuickBidderForWinner(Long tahdrId){
		int result=0;
		try{
				result =bidderDao.updateQuickBidderForWinner(tahdrId);
		}catch(Exception e){
			throw new RuntimeException("Exception in Updating Bidder Status");
		} 
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public BidderDto updateBidderTotalBid(BidderDto bidder) {
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", bidder.getBidderId());
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("basicAmt", bidder.getBasicAmt());
		params.put("otherChargeType", bidder.getOtherChargeType());
		params.put("otherCharge", bidder.getOtherCharge());
		params.put("taxAmt", bidder.getTaxAmt());
		params.put("grossAmt", bidder.getGrossAmt());
		params.put("otherRates", bidder.getOtherRates());
		params.put("totalFreight", bidder.getTotalFreight());
		params.put("totalPKFWD", bidder.getTotalPKFWD());
		params.put("paymentTerms", bidder.getPaymentTerms());
		params.put("negotiatorPaymentTerms", bidder.getNegotiatorPaymentTerms());
		params.put("uploadExcelFile", bidder.getUploadExcelFile());
		params.put("otherTaxAmt", bidder.getOtherTaxAmt());
		params.put("validityDateFrom", bidder.getValidityDateFrom());
		params.put("validityDateTo", bidder.getValidityDateTo());
		params.put("vendorOfferNo", bidder.getVendorOfferNo());
		params.put("vendorOfferDate", bidder.getVendorOfferDate());
		params.put("headerFreightType", bidder.getHeaderFreightType());
		params.put("headerPKFWDType", bidder.getHeaderPKFWDType());
		params.put("headerOtherType", bidder.getHeaderOtherType());
		params.put("incoTerms", bidder.getIncoTerms());
		params.put("totalFreightChargeAmt", bidder.getTotalFreightChargeAmt());
		params.put("totalPackingChargeAmt", bidder.getTotalPackingChargeAmt());
		params.put("totalOtherChargeAmt", bidder.getTotalOtherChargeAmt());
		params.put("vendorIncoDescription", bidder.getVendorIncoDescription());
//		if (bidder.getStatus().equals("SBMT")) {
//		params.put("rfqNo", bidderDao.getNewrfqNo());
//		}
		
		if(null!=bidder.getStatus()){
			params.put("status", bidder.getStatus());
		}
		int result = bidderService.updateByJpql(params, whereCls);
		if(result>0){
			return bidder;
		}else{
			throw new RuntimeException("Error while updating Enquiry status");
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BidderDto> updateTotalSplitValue(List<BidderDto> bidderList) {
		List<BidderDto> bidderResponseLsit= new ArrayList<BidderDto>();
	
		for(BidderDto dto:bidderList){
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", dto.getBidderId());
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("splitBasicValue", dto.getSplitBasicValue());
		params.put("splitGrossValue", dto.getSplitGrossValue());
		params.put("splitLandedCost", dto.getSplitLandedCost());
		int result = bidderService.updateByJpql(params, whereCls);
		if(result>0){
			bidderResponseLsit.add(dto);
		}else{
			throw new RuntimeException("Error while updating Enquiry status");
		}
		
		}
		
		return bidderResponseLsit;
	}

	@Override
	public List<BidderDto> getBidderByFilter(BidderFilterDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		String query=bidderDao.getBidderFilterQuery(dto);
		List<BidderDto> bidderList=findDtosByQuery(query, params);
		return bidderList;
	}
	private Map<String,Object> getParameterMap(BidderFilterDto dto){
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getEnqNoFrom()!=null && dto.getEnqNoTo()!=null){
			params.put("enqNoFrom", dto.getEnqNoFrom());
			params.put("enqNoTo", dto.getEnqNoTo());
   		}
   		if(dto.getEnqNoFrom()!=null && dto.getEnqNoTo()==null){
   			params.put("enqNoFrom", dto.getEnqNoFrom());
   		}
   		if(dto.getEnqNoFrom()==null && dto.getEnqNoTo()!=null){
   			params.put("enqNoTo", dto.getEnqNoTo());
   		}
   		if(dto.getEnqDateFrom()!=null && dto.getEnqDateTo()!=null){
   			
//   			params.put("enqFromDate", dto.getEnqDateFrom());
//			params.put("enqToDate", dto.getEnqDateTo());
			
			params.put("enqFromDate", dto.getStartEnqDateFrom());
   			dto.setEnqDateFrom(dto.getStartEnqDateFrom());
   			params.put("enqToDate", dto.getLastEnqDateTo());
   			dto.setEnqDateTo(dto.getLastEnqDateTo());
   		}
   		if(dto.getEnqDateFrom()!=null && dto.getEnqDateTo()==null){
//   			params.put("enqFromDate", dto.getEnqDateFrom());
   			
   			params.put("enqFromDate", dto.getStartEnqDateFrom());
			params.put("enqToDate", dto.getLastEnqDateFrom());
			dto.setEnqDateFrom(dto.getStartEnqDateFrom());
			dto.setEnqDateTo(dto.getLastEnqDateFrom());
   		}
   		if(dto.getEnqDateFrom()==null && dto.getEnqDateTo()!=null){
//   			params.put("enqToDate", dto.getEnqDateTo());
   			
   			params.put("enqFromDate", dto.getStartEnqDateTo());
			params.put("enqToDate", dto.getLastEnqDateTo());
			dto.setEnqDateFrom(dto.getStartEnqDateTo());
			dto.setEnqDateTo(dto.getLastEnqDateTo());
   		}
   		if(dto.getEnqEndDateFrom()!=null && dto.getEnqEndDateTo()!=null){
   			params.put("enqEndFromDate", dto.getEnqEndDateFrom());
			params.put("enqEndToDate", dto.getEnqEndDateTo());
   		}
   		if(dto.getEnqEndDateFrom()!=null && dto.getEnqEndDateTo()==null){
   			params.put("enqEndFromDate", dto.getEnqEndDateFrom());
   		}
   		if(dto.getEnqEndDateFrom()==null && dto.getEnqEndDateTo()!=null){
   			params.put("enqEndToDate", dto.getEnqEndDateTo());
   		}
   		if(dto.getBuyerCode()!=null){
   			params.put("buyerId", dto.getBuyerCode());
   		}
   		
   		if(dto.getEnqStatus()!=null){
   			params.put("enqStatus", dto.getEnqStatus());
   		}
   		if(dto.getBiiderStatus()!=null){
   			params.put("bidStatus", dto.getBiiderStatus());
   		}
   		if(dto.getVendorCode()!=null){
   			params.put("vendorCode",  dto.getVendorCode());
   			
   		}
   		return params;
	}
	
	/*public void mailNotificationForBidSubmitted(BidderDto bidder,TAHDRDto tahdrDto){
		try{
			BidderDto bidderData= findDto("getBidderDataFromBidderId", AbstractServiceImpl.getParamMap("bidderId",bidder.getBidderId()));
			MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.BID_SUBMITTED_SUCCESSFULLY);
			Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", map);
			if(mailData!=null  && internalUser!=null){
				String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				Map<String, Object> params=new HashMap<>();
				params.put("@sourceCompanyName@",internalUser.getPartner().getName());
				params.put("@honour@","");
				params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
				params.put("@emailCorrCode@","Renewal");
				params.put("@generalDetailDate@",today);
				params.put("@vendorComapnyName@",bidder.getPartner().getName());
				params.put("@factoryName@","");
				params.put("@factoryCity@","");
				params.put("@vendorEmail@",bidder.getCreatedBy().getEmail());
				params.put("@tenderCode@", tahdrDto.getTahdrCode());
				params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
				params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
				params.put("@companyFax@",internalUser.getUserDetails().getFax1());
				if(bidder.getCreatedBy().getEmail()!=null){
					mailService.sentMailByTemplate(mailData, params, bidder.getCreatedBy().getEmail());
				}
			}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			log.info("mailNotificationForBidSubmitted/BidderServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("mailNotificationForBidSubmitted/BidderServiceIMPL" + e);
		}
	}
*/
}
