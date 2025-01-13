/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.CommercialBidDao;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.CommercialBid;
import com.novelerp.eat.service.BidderSectionDocService;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.SectionDocumentService;

@Service
public class CommercialBidServiceImpl extends AbstractContextServiceImpl<CommercialBid, CommercialBidDto>
		implements CommercialBidService {

	@Autowired
	private CommercialBidDao commercialBidDao;
	
	@Autowired
	private BidderSectionDocService bidderSecDocService;
	
	@Autowired
	private SectionDocumentService secDocService;
	
	@Autowired
	private BidderService bidderService;
	
	@PostConstruct
	void init(){
		super.init(CommercialBidServiceImpl.class, commercialBidDao, CommercialBid.class, CommercialBidDto.class);
		setByPassProxy(true);
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CommercialBidDto save(CommercialBidDto dto) {
		Set<BidderSectionDocDto> bidderSecDocSet=dto.getBidderSecDoc();
		dto.setBidderSecDoc(null);
		dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
		if(null==dto.getCommercialBidId() || !(dto.getCommercialBidId()>0)){
			dto=super.save(dto);
		}else if(null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty()){
			dto=super.updateDto(dto);
		}
		dto.setBidderSecDoc(bidderSecDocSet);
		if(null!=dto.getBidderSecDoc() && !dto.getBidderSecDoc().isEmpty())
			dto=bidderSecDocService.save(dto);
		
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(String status,Long id,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("commercialBidId", id);
		
		return commercialBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatusByBidderId(String status,Long bidderId,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("bidder.bidderId", bidderId);
		
		return commercialBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.CommercialBidService#getCommercialBid(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public CommercialBidDto getCommercialBid(Long tahdrId, Long getbPartnerId) {
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("partnerId", getbPartnerId);
		CommercialBidDto cb=findDto("getCommercialBid",map);
		return cb;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.CommercialBidService#getCommercialBidDocs(java.lang.Long, java.lang.String)
	 */
	@Override
	public Set<BidderSectionDocDto> getCommercialBidDocs(Long tahdrId, String section,Long partnerId) {
		Set<BidderSectionDocDto> bidderSecDocSet=new HashSet<>();
		Map<String, Object>map=new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("sectionCode", section);
		List<SectionDocumentDto> sectionDocList=secDocService.findDtos("getRequiredDocBySection", map);
		
		map=new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("partnerId", partnerId);
		List<BidderSectionDocDto> bidderSectionDocList=bidderSecDocService.findDtos("getCommercialBidSectionDoc", map);
		
		if(!CommonUtil.isCollectionEmpty(bidderSectionDocList)){
			bidderSecDocSet.addAll(bidderSectionDocList);
		}
		
		bidderSecDocSet=bidderSecDocService.getBidderSectionDocSet(sectionDocList, bidderSecDocSet);
		return bidderSecDocSet;
	}
	
	@Override
	public CommercialBidDto copyNewDtoToOld(CommercialBidDto newDto,CommercialBidDto oldDto){
		if(oldDto!=null && newDto!=null){
			oldDto.setFirstLot(newDto.getFirstLot());
			oldDto.setDeliveringMonth(newDto.getDeliveringMonth());
			oldDto.setRatePerMonth(newDto.getRatePerMonth());
			oldDto.setGst(newDto.getGst());
			oldDto.setCgst(newDto.getCgst());
			oldDto.setSgst(newDto.getSgst());
			oldDto.setDigiSignedDoc(null);
			oldDto.setTax(newDto.getTax());
			oldDto.setTahdrDetail(newDto.getTahdrDetail());
			oldDto.setBidderSecDocList(newDto.getBidderSecDocList());
			oldDto.setBidderSecDoc(newDto.getBidderSecDoc());
			oldDto.setIgst(newDto.getIgst());
		}
		
		return oldDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CommercialBidDto submitCommercailBid(CommercialBidDto cb){
		cb=super.updateDto(cb);
		ResponseDto response=new ResponseDto();
		boolean result=bidderService.updateStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED, cb.getBidder().getBidderId());
		response.setMessage("All Bids Submitted");
		cb.setResponse(response);
		/*if(result){
			bidderService.mailNotificationForBidSubmitted(cb.getBidder(),cb.getBidder().getTahdr());
		}*/
		return cb;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public long updateStatusForCommercialBid(String bidSection,Long bidderId){
		long result = 0; 
		try{
		if(bidSection.equalsIgnoreCase(AppBaseConstant.COMMERCIAL_SECTION) && bidderId!=null){
		bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId);
		updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId, null);
		result = 1;
		}
		}
		catch (Exception e) {
			log.error("EXCEPTION", e);
		}
		
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CommercialBidDto getCommercialBidByBidderId(Long bidderId){
		CommercialBidDto commercialBid=findDto("", AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
		return commercialBid;
	}
}
