/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.HashSet;
import java.util.List;
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
import com.novelerp.eat.dao.BidderSectionDocDao;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.BidderSectionDoc;
import com.novelerp.eat.service.BidderSectionDocService;

@Service
public class BidderSectionDocServiceImpl extends AbstractContextServiceImpl<BidderSectionDoc, BidderSectionDocDto>
		implements BidderSectionDocService {

	@Autowired
	private BidderSectionDocDao bidderSectionDocDao;
	
	@PostConstruct
	void init(){
		super.init(BidderSectionDocServiceImpl.class, bidderSectionDocDao, BidderSectionDoc.class, BidderSectionDocDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TechnicalBidDto save(TechnicalBidDto technicalBid) {
		ResponseDto response=new ResponseDto();
		try{
			Set<BidderSectionDocDto> bidderSectionDocSet= new HashSet<>();
			for(BidderSectionDocDto dto:technicalBid.getBidderSecDoc()){
				dto.setTechnicalBid(technicalBid);
				dto.setBidSection(AppBaseConstant.TECHNICAL_SECTION);
				dto=save(dto);
				bidderSectionDocSet.add(dto);
			}
			
			technicalBid.setBidderSecDoc(bidderSectionDocSet);
			response.setHasError(false);
			response.setMessage("Record Saved");
		}catch(Exception ex){
			response.setHasError(true);
			response.setMessage("Error Occured");
		}
		technicalBid.setResponse(response);
		return technicalBid;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CommercialBidDto save(CommercialBidDto commercialBid) {
		ResponseDto response=new ResponseDto();
		try{
			Set<BidderSectionDocDto> bidderSectionDocSet= new HashSet<>();
			for(BidderSectionDocDto dto:commercialBid.getBidderSecDoc()){
				dto.setCommercialBid(commercialBid);
				dto.setBidSection(AppBaseConstant.COMMERCIAL_SECTION);
				dto=save(dto);
				bidderSectionDocSet.add(dto);
			}
			
			commercialBid.setBidderSecDoc(bidderSectionDocSet);
			response.setHasError(false);
			response.setMessage("Record Saved");
		}catch(Exception ex){
			response.setHasError(true);
			response.setMessage("Error Occured");
		}
		commercialBid.setResponse(response);
		return commercialBid;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.core.service.BidderSectionDocService#save(com.novelerp.eat.dto.PriceBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PriceBidDto save(PriceBidDto priceBid) {
		ResponseDto response=new ResponseDto();
		try{
			Set<BidderSectionDocDto> bidderSectionDocSet= new HashSet<>();
			for(BidderSectionDocDto dto:priceBid.getBidderSecDoc()){
				dto.setPriceBid(priceBid);
				dto.setBidSection(AppBaseConstant.PRICE_BID);
				dto=save(dto);
				bidderSectionDocSet.add(dto);
			}
			
			priceBid.setBidderSecDoc(bidderSectionDocSet);
			response.setHasError(false);
			response.setMessage("Record Saved");
		}catch(Exception ex){
			response.setHasError(true);
			response.setMessage("Error Occured");
		}
		priceBid.setResponse(response);
		return priceBid;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderSectionDocDto save(BidderSectionDocDto dto){
		if(!CommonUtil.isStringEmpty(dto.getBidSection()) 
				&& dto.getSectionDocument()!=null
				&& (dto.getCommercialBid()!=null || dto.getTechnicalBid()!=null || dto.getPriceBid()!=null)){
			if(null==dto.getBidderSectionDocId() || !(dto.getBidderSectionDocId()>0)){
				dto=super.save(dto);
			}else{
				dto=super.updateDto(dto);
			}
		}
		return dto;
	}
	
	@Override
	public Set<BidderSectionDocDto> getBidderSectionDocSet(List<SectionDocumentDto> secDocList, Set<BidderSectionDocDto> bidderSecDocSet){
		if(bidderSecDocSet==null){
			bidderSecDocSet=new HashSet<>();
		}
		
		for(SectionDocumentDto secDoc: secDocList){
			int count=0;
			for(BidderSectionDocDto bidderSecDoc:bidderSecDocSet){
				if(!CommonUtil.isEqual(secDoc.getSectionDocumentId(), bidderSecDoc.getSectionDocument().getSectionDocumentId())){
					count++;
				}
			}
			if(count==bidderSecDocSet.size()){
				BidderSectionDocDto bidderSecDocDto=new BidderSectionDocDto();
				bidderSecDocDto.setSectionDocument(secDoc);
				bidderSecDocDto.setBidSection(secDoc.getCode());
				bidderSecDocSet.add(bidderSecDocDto);
			}
		}
		
		return bidderSecDocSet;
	}
}
