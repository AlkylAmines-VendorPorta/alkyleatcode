package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.ItemBidDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.ItemBid;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TechnicalBidService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class ItemBidServiceImpl extends AbstractContextServiceImpl<ItemBid, ItemBidDto> implements ItemBidService {

	@Autowired
	private ItemBidDao itemBidDao;

	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private BidderService bidderService;
	
	
	@PostConstruct
	private void init() {
		super.init(ItemBidServiceImpl.class, itemBidDao, ItemBid.class, ItemBidDto.class);
		/*setObjectConverter(itemBidConverter);*/
		setByPassProxy(true);
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto saveTechnicalBid(ItemBidDto dto) {
		
		if(null==dto.getItemBidId() || !(dto.getItemBidId()>0)){
			TechnicalBidDto tb= dto.getTechnicalBid();
			dto.setTechnicalBid(null);
			dto =super.save(dto);
			dto.setTechnicalBid(tb);
		}
		dto.getTechnicalBid().setItemBid(dto);
		dto.setTechnicalBid(technicalBidService.save(dto.getTechnicalBid()));
		if(!dto.getTechnicalBid().getResponse().isHasError()){
			technicalBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
			priceBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
			updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
		}
		dto.setResponse(dto.getTechnicalBid().getResponse());
		
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#savePriceBid(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto savePriceBid(ItemBidDto dto) {
		
		if(null==dto.getItemBidId() || !(dto.getItemBidId()>0)){
			PriceBidDto pb= dto.getPriceBid();
			dto.setPriceBid(null);
			dto =super.save(dto);
			dto.setPriceBid(pb);
			}
			dto.getPriceBid().setItemBid(dto);
			
			TAHDRDto tenderDto=dto.getBidder().getTahdr();
			if(tenderDto!=null && (tenderDto.getTahdrTypeCode().equalsIgnoreCase("QRFQ") || tenderDto.getTahdrTypeCode().equalsIgnoreCase("RFQ"))){
				dto.getPriceBid().setEncryptBid(false);	
			}else{
				dto.getPriceBid().setEncryptBid(true);
			}
			
			if(dto!=null && dto.getPriceBid()!=null){
				priceBidService.createPreviousBid(dto.getItemBidId(),dto.getPriceBid().getPriceBidId(),AppBaseConstant.PRICE_BID_CHANGE);	
			}
			dto.getPriceBid().setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto.setPriceBid(priceBidService.save(dto.getPriceBid()));
			if(!dto.getPriceBid().getResponse().isHasError() && !"Y".equals(dto.getPriceBid().getIsRevised()) ){
				updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
				commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
				bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
			}
			dto.setResponse(dto.getPriceBid().getResponse());
		return dto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto savePriceBid(ItemBidDto dto,PriceBidDto pbNew) {
		dto=savePriceBid(dto);
		if(!dto.getPriceBid().getResponse().isHasError() && pbNew!=null){
			PriceBidDto part=dto.getPriceBid();
			pbNew=priceBidService.mergePart(pbNew, part);
			pbNew=priceBidService.calculate(pbNew);
			pbNew.setEncryptBid(true);
			pbNew=priceBidService.encryptPB(pbNew);
			if(pbNew.getItemBid()!=null && dto.getPriceBid()!=null){
				priceBidService.createPreviousBid(pbNew.getItemBid().getItemBidId(),dto.getPriceBid().getPriceBidId(),AppBaseConstant.PRICE_BID_CHANGE);	
			}
			pbNew=priceBidService.updateDto(pbNew);
			pbNew=priceBidService.decryptPB(pbNew);
			dto.setResponse(pbNew.getResponse());
		}
		if(!"Y".equals(dto.getPriceBid().getIsRevised())){
			updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
		}
		
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto saveRevisedBid(ItemBidDto dto){
			
			/*
		  	Long priceBidId=dto.getPriceBid().getPriceBidId();
			priceBidService.createRevisedBid(dto.getItemBidId(),dto.getPriceBid().getPriceBidId());
			dto.getPriceBid().setPriceBidId(null);

			dto.setPriceBid(priceBidService.save(dto.getPriceBid()));
			if(!dto.getPriceBid().getResponse().isHasError()){
				updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
				bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
			}
			if(priceBidId !=null){
				priceBidService.updateReqDocsForRevisedBid(priceBidId, dto.getPriceBid().getPriceBidId());
			}
			priceBidService.deleteOldBids(dto.getItemBidId());*/
		
			dto.getPriceBid().setItemBid(dto);
			dto.getPriceBid().setEncryptBid(true);
			dto.getPriceBid().setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto.setPriceBid(priceBidService.save(dto.getPriceBid()));
			dto.setResponse(dto.getPriceBid().getResponse());
			
			updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
			
			return dto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto saveNewRevisedBid(ItemBidDto dto,Long tahdrId){
		if(null==dto.getItemBidId() || !(dto.getItemBidId()>0)){
			PriceBidDto pb= dto.getPriceBid();
			dto.setPriceBid(null);
			dto =super.save(dto);
			dto.setPriceBid(pb);
			
			dto.getPriceBid().setItemBid(dto);
			dto.getPriceBid().setEncryptBid(false);
			dto.getPriceBid().setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto.setPriceBid(priceBidService.save(dto.getPriceBid()));
			dto.setResponse(dto.getPriceBid().getResponse());
		}else{
			Long priceBidId=dto.getPriceBid().getPriceBidId();
			priceBidService.createRevisedBid(dto.getItemBidId(),dto.getPriceBid().getPriceBidId());
			dto.getPriceBid().setPriceBidId(null);

			dto.setPriceBid(priceBidService.saveLiveBid(dto.getPriceBid()));
			if(priceBidId !=null){
				priceBidService.updateReqDocsForRevisedBid(priceBidId, dto.getPriceBid().getPriceBidId());
			}
			priceBidService.deleteOldBids(dto.getItemBidId());
			dto.setResponse(dto.getPriceBid().getResponse());
		}
		return dto;
    }
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#saveSectionDocument(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto saveTechnicalSectionDocument(ItemBidDto dto) {
		if(null==dto.getItemBidId() || !(dto.getItemBidId()>0)){
			TechnicalBidDto tb= dto.getTechnicalBid();
			dto.setTechnicalBid(null);
			dto =super.save(dto);
			dto.setTechnicalBid(tb);
		}
		if(null!=dto.getTechnicalBid()){
			dto.getTechnicalBid().setItemBid(dto);
			dto.setTechnicalBid(technicalBidService.save(dto.getTechnicalBid()));
		}
		
		if(null!=dto.getTechnicalBid().getResponse() && !dto.getTechnicalBid().getResponse().isHasError()){
			technicalBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
			priceBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
			updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
		}
		dto.setResponse(dto.getTechnicalBid().getResponse());
		
		return dto;
	
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto savePriceSectionDocument(ItemBidDto dto) {
		if(null==dto.getItemBidId() || !(dto.getItemBidId()>0)){
			PriceBidDto pb=dto.getPriceBid();
			dto.setPriceBid(null);
			dto =super.save(dto);
			dto.setPriceBid(pb);
		}
		if(null!=dto.getPriceBid()){
			dto.getPriceBid().setItemBid(dto);
			dto.setPriceBid(priceBidService.save(dto.getPriceBid()));
		}
		priceBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
		if(!dto.getPriceBid().getResponse().isHasError() && !"Y".equals(dto.getPriceBid().getIsRevised())){
			updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId());
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
		}
		dto.setResponse(dto.getPriceBid().getResponse());
		return dto;
	
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#saveAnnexuteX1Bid(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PriceBidDto saveAnnexuteX1Bid(PriceBidDto dto,PriceBidDto oldPriceBid) {
		oldPriceBid.setEncryptBid(false);
		if("N".equals(oldPriceBid.getIsMatched())){
			oldPriceBid.setIsQuashed("Y");
			oldPriceBid.setItemBid(null);
			priceBidService.updateDto(oldPriceBid);
			oldPriceBid.setPriceBidId(null);			
		}else if("Y".equals(oldPriceBid.getIsMatched())){
			oldPriceBid.setExGroupPriceRate(dto.getExGroupPriceRate());
			oldPriceBid.setOfferedQuantity(dto.getOfferedQuantity());
		}
		oldPriceBid.setIsQuashed("N");
		oldPriceBid.setIsAnnexureC1("Y");
		oldPriceBid.setItemBid(dto.getItemBid());
		oldPriceBid.setIsMatched(dto.getIsMatched());
		oldPriceBid.setStatus(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED);
		//Added by Aman
		String clauseA=dto.getClauseA()==null?"N":dto.getClauseA();
		String clauseB=dto.getClauseB()==null?"N":dto.getClauseB();
		oldPriceBid.setClauseA(clauseA);
		oldPriceBid.setClauseB(clauseB);
		
		oldPriceBid=priceBidService.save(oldPriceBid);
		//reset previous digisigned file.
		priceBidService.resetAnnexureC1FileResposne(oldPriceBid);
		updateItemBidStatus(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED,dto.getItemBid().getItemBidId());
			
		//bidderService.updateStatus(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED, dto.getItemBid().getBidder().getBidderId());
		
		return oldPriceBid;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#submit(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto submit(ItemBidDto dto) {
			ResponseDto response= new ResponseDto();
			int count=updateStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED,dto.getItemBidId());
			if(count>0){
				response.setHasError(false);
				response.setMessage("Item Bid Submitted");
			}
			dto.setResponse(response);
			return dto;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#submitPriceBId(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto submitPriceBid(ItemBidDto dto,List<PriceBidDto> parts) {
		
		TAHDRDto tenderDto=dto==null?null:dto.getBidder()==null?null:dto.getBidder().getTahdr();
		if(tenderDto!=null && (tenderDto.getTahdrTypeCode().equalsIgnoreCase("QRFQ") || tenderDto.getTahdrTypeCode().equalsIgnoreCase("RFQ"))){
			dto.setPriceBid(priceBidService.submit(dto.getPriceBid(),false));	
		}else{
			dto.setPriceBid(priceBidService.submit(dto.getPriceBid(),true));
		}
		
		if(null!= dto.getPriceBid().getResponse() && !dto.getPriceBid().getResponse().isHasError()){
			updateStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED,dto.getItemBidId());
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED,dto.getBidder().getBidderId());
			boolean flag=priceBidService.encryptAllPb(parts);
			if(flag){
				dto.setResponse(dto.getPriceBid().getResponse());
			}else{
				throw new RuntimeException("Encryption Failed please try after some time.");
			}
		}else{
			dto.setResponse(dto.getPriceBid().getResponse());
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#submitPriceBId(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto submitRevisedPriceBid(ItemBidDto dto,List<PriceBidDto> parts) {
		
		TAHDRDto tenderDto=dto==null?null:dto.getBidder()==null?null:dto.getBidder().getTahdr();
		if(tenderDto!=null && (tenderDto.getTahdrTypeCode().equalsIgnoreCase("QRFQ") || tenderDto.getTahdrTypeCode().equalsIgnoreCase("RFQ"))){
			dto.setPriceBid(priceBidService.submit(dto.getPriceBid(),false));	
		}else{
			dto.setPriceBid(priceBidService.submit(dto.getPriceBid(),true));	
		}
		
		if(!dto.getPriceBid().getResponse().isHasError()){
			boolean flag=priceBidService.encryptAllPb(parts);
			if(flag){
				dto.setResponse(dto.getPriceBid().getResponse());
			}else{
				throw new RuntimeException("Encryption Failed please try after some time.");
			}
		}else{
			dto.setResponse(dto.getPriceBid().getResponse());
		}
		return dto;
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.ItemBidService#submitTechnicalBId(com.novelerp.eat.dto.ItemBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemBidDto submitTechnicalBid(ItemBidDto dto) {
		dto.setTechnicalBid(technicalBidService.submit(dto.getTechnicalBid()));
		if(null!=dto.getTechnicalBid().getResponse() && !dto.getTechnicalBid().getResponse().isHasError()){
			updateStatus(AppBaseConstant.BIDDER_STATUS_TC_BID_SUBMITED,dto.getItemBidId());
			priceBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getItemBidId(), null);
			commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId(), null);
			bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, dto.getBidder().getBidderId());
		}
		dto.setResponse(dto.getTechnicalBid().getResponse());
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(String status,Long id){
		Map<String,Object> map= new HashMap<>();
		map.put("status",status);
		return updateByJpql(map, "itemBidId",id );
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateItemBidStatusByOpeningType(String status,String checkStatus,Long tahdrId){
		return itemBidDao.updateItemBidStatusByOpeningType(status,checkStatus, tahdrId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateItemBidStatusByPBOpeningTypeForSingle(String status,String checkStatus,Long tahdrId){
		return itemBidDao.updateItemBidStatusByPBOpeningTypeForSingle(status,checkStatus, tahdrId);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateItemBidStatusByPBOpeningTypeForTwo(String status,String checkStatus,Long tahdrId){
		return itemBidDao.updateItemBidStatusByPBOpeningTypeForTwo(status,checkStatus, tahdrId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateItemBidStatusForMarkingLowestBid(Long tahdrId){
		int count=itemBidDao.updateItemBidStatusForMarkingLowestBid(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean selectLowestItemBidByTahdrId(Long tahdrId){
		int count=itemBidDao.updateItemBidStatusForMarkingLowestBid(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean selectLowestQuickItemBidByTahdrId(Long tahdrId){
		int count=itemBidDao.updateQuickItemBidStatusForMarkingLowestBid(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean selectHighestItemBidByTahdrId(Long tahdrId){
		int count=itemBidDao.updateItemBidStatusForMarkingHighestBid(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean selectHighestQuickItemBidByTahdrId(Long tahdrId){
		int count=itemBidDao.updateQuickItemBidStatusForMarkingHighestBid(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean selectHighOrLowItemBidByTahdrId(Long tahdrId,String typeCode,String isAuction){
		int count =0;
		if(typeCode.equals("PT") || typeCode.equals("RA")){
			count=itemBidDao.updateItemBidStatusForMarkingLowestBid(tahdrId);
		}else if(typeCode.equals("FA")){
			count=itemBidDao.updateItemBidStatusForMarkingHighestBid(tahdrId);
		}
		if(count>0){
			return true;
			}
		return false;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateItemBidStatusForMarkingAnnexureC1Called(Long tahdrId, int baseRate,String status){
		int count=itemBidDao.updateItemBidStatusForMarkingAnnexureC1Called(tahdrId,baseRate,status);
		if(count>0){
			return true;
			}
		return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateItemBidStatus(String status,Long itemBidId){
		int count=itemBidDao.updateItemBidStatus(status, itemBidId);
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateItemBidScrutinyStatus(String newStatus,ItemBidDto itemBid){
		Long itemBidId=itemBid.getItemBidId();
		String oldStatus=itemBid.getStatus();
		boolean doUpdate = false;
		if(null==oldStatus){
			doUpdate = true;
		}else if(AppBaseConstant.DEVIATION_CALLED.equals(newStatus)){
			doUpdate=true;
		}else if(oldStatus.equals(newStatus)){
			doUpdate=false;
		}else if(AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED.equals(oldStatus)){
			doUpdate=false;
		}else if(AppBaseConstant.DEVIATION_CALLED.equals(oldStatus)  && AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED.equals(newStatus)){
			doUpdate=false;
		}else{
			doUpdate=true;
		}
		int count=0;
		if(doUpdate){
			count = itemBidDao.updateItemBidStatus(newStatus, itemBidId);
		}
		
		return count>0?true:false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateQuickItemForBidWinner(Long tahdrId){
		int count=itemBidDao.updateQuickItemBidForWinner(tahdrId);
		if(count>0){
			return true;
			}
		return false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ItemBidDto> saveEnquiryLines(List<ItemBidDto> enquiryLines,BidderDto enquiry,BPartnerDto partner){
		List<ItemBidDto> newEnquiryLines = new ArrayList<ItemBidDto>();
		int i=10;
		for(ItemBidDto enquiryLine : enquiryLines){
			if(null!=enquiryLine){
			/*BPartnerDto partner = new BPartnerDto();
			partner.setbPartnerId(1l);*/
			enquiryLine.setPartner(partner);
			enquiryLine.setLineNumber("000"+String.valueOf(i) );
			i=i+10;
			enquiryLine.setBidder(enquiry);
			enquiryLine=super.save(enquiryLine);
			newEnquiryLines.add(enquiryLine);
			}
		}
		return newEnquiryLines;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateLowestBid(Long prId) {
		
		return itemBidDao.updateLowestBid(prId);
	}
	
}
