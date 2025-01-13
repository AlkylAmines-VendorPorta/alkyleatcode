/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.novelerp.eat.dao.TechnicalBidDao;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;
import com.novelerp.eat.service.BidderGtpService;
import com.novelerp.eat.service.BidderSectionDocService;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SectionDocumentService;
import com.novelerp.eat.service.TAHDRMaterialGTPService;
import com.novelerp.eat.service.TechnicalBidService;

@Service
public class TechnicalBidServiceImpl extends AbstractContextServiceImpl<TechnicalBid, TechnicalBidDto>
		implements TechnicalBidService {
	
	@Autowired
	private TechnicalBidDao tachnicalBidDao;
	
	@Autowired
	private BidderGtpService bidderGtpService;
	
	@Autowired
	private BidderSectionDocService bidderSecDocService;
	
	@Autowired
	private TAHDRMaterialGTPService tahdrMaterialGtpService;
	
	@Autowired
	private SectionDocumentService secDocService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private BidderService bidderService;
	
	@PostConstruct
	void init(){
		super.init(TechnicalBidServiceImpl.class, tachnicalBidDao, TechnicalBid.class, TechnicalBidDto.class);
		setByPassProxy(true);
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TechnicalBidDto save(TechnicalBidDto dto) {
		if(null==dto.getTechnicalBidId() || !(dto.getTechnicalBidId()>0)){
			Set<BidderGtpDto> bidderGtpSet=dto.getBidderGtp();
			Set<BidderSectionDocDto> bidderSecDocSet=dto.getBidderSecDoc();
			dto.setBidderGtp(null);
			dto.setBidderSecDoc(null);
			dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto=super.save(dto);
			dto.setBidderGtp(bidderGtpSet);
			dto.setBidderSecDoc(bidderSecDocSet);
		}
		if(null!=dto.getBidderGtp() && !dto.getBidderGtp().isEmpty())
			dto=bidderGtpService.save(dto);
		
		if(null!=dto.getBidderSecDoc() && !dto.getBidderSecDoc().isEmpty())
			dto=bidderSecDocService.save(dto);
		
		
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TechnicalBidDto submit(TechnicalBidDto dto){
		dto.setStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		dto=updateDto(dto);
		return dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateTechnicalBidStatus(String status,Long itemBidId){
		int count=tachnicalBidDao.updateTechnicalBidStatus(status, itemBidId);
		if(count>0) return true;
		else return false;
	}
	
	@Override
	public List<BidderGtpDto> getTechnicalBidBidderGtp(Long tahdrMaterialId,Long partnerId){
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		List<BidderGtpDto> bidderGtpList=bidderGtpService.findDtos("getBidderGtpByTahdrMaterialId", map);
		List<TAHDRMaterialGTPDto> tahdrMaterialGtpList = tahdrMaterialGtpService.findDtos("getTahdrMaterialGtpByTahdrMaterialId", getParamMap("tahdrMaterialId", tahdrMaterialId));
		if(CommonUtil.isCollectionEmpty(bidderGtpList)){
			bidderGtpList=getBidderGtpListFromTMG(tahdrMaterialGtpList);
		}else if(bidderGtpList.size()!=tahdrMaterialGtpList.size()){
			bidderGtpList=getBidderGtpListFromTMG(tahdrMaterialGtpList,bidderGtpList);
		}
		return bidderGtpList;
	}
	
	@Override
	public List<BidderSectionDocDto> getTechnicalBidSectionDoc(Long tahdrMaterialId,Long partnerId){
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		List<BidderSectionDocDto> bidderSecDoc=bidderSecDocService.findDtos("getTechnicalBidSectionDoc", map);
		if(CommonUtil.isCollectionEmpty(bidderSecDoc)){
			Map<String,Object> map1=new HashMap<>();
			map1.put("tahdrMaterialId", tahdrMaterialId);
			map1.put("sdCode", AppBaseConstant.TECHNICAL_SECTION);
			List<SectionDocumentDto> sectionDocList= secDocService.findDtos("getSectionDocByCodeAndTahdrMaterialId", map1);
			bidderSecDoc=getBidderSecDocSetFromTMG(sectionDocList);
		}
		return bidderSecDoc;
	}
	
	private List<BidderGtpDto> getBidderGtpListFromTMG(List<TAHDRMaterialGTPDto> tahdrmaterialGtpSet){
		List<BidderGtpDto> bidderGtpList=new ArrayList<>();
		for(TAHDRMaterialGTPDto tmg:tahdrmaterialGtpSet){
			BidderGtpDto bidderGtpDto=new BidderGtpDto();
			bidderGtpDto.setTahdrMaterialgtp(tmg);
			bidderGtpList.add(bidderGtpDto);
		}
		return bidderGtpList;
	}
	
	private List<BidderGtpDto> getBidderGtpListFromTMG(List<TAHDRMaterialGTPDto> tahdrmaterialGtpSet,List<BidderGtpDto> bidderGtpList){
		for(TAHDRMaterialGTPDto tmg:tahdrmaterialGtpSet){
			int count=0;
			for(BidderGtpDto bidderGtp:bidderGtpList){
				TAHDRMaterialGTPDto tmgDto=bidderGtp.getTahdrMaterialgtp();
				if(tmgDto!=null && !(CommonUtil.isEqual(tmgDto.getTahdrMaterialGtpId(), tmg.getTahdrMaterialGtpId()))){
					count++;
				}else{
					continue;
				}
			}
			if(count==bidderGtpList.size()){
				BidderGtpDto bidderGtpDto=new BidderGtpDto();
				bidderGtpDto.setTahdrMaterialgtp(tmg);
				bidderGtpList.add(bidderGtpDto);
			}
		}
		return bidderGtpList;
	}
	
	private List<BidderSectionDocDto> getBidderSecDocSetFromTMG(List<SectionDocumentDto> sectionDocSet){
		List<BidderSectionDocDto> bidderSectionDocSet= new ArrayList<>();
		if(null!=sectionDocSet){
			for(SectionDocumentDto sd:sectionDocSet){
					BidderSectionDocDto bidderSecDocDto=new BidderSectionDocDto();
					bidderSecDocDto.setSectionDocument(sd);
					bidderSecDocDto.setBidSection(AppBaseConstant.TECHNICAL_SECTION);
					bidderSectionDocSet.add(bidderSecDocDto);
			}
		}
		
		return bidderSectionDocSet;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TechnicalBidService#getTechnicalBid(java.lang.Long, java.lang.Long)
	 */
	@Override
	public TechnicalBidDto getTechnicalBid(Long tahdrMaterialId, Long partnerId) {
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		TechnicalBidDto tb=findDto("getTechnicalBid",map);
		return tb;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(String status,Long id,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("technicalBidId", id);
		
		return tachnicalBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatusByItemBidId(String status,Long itemBidId,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("itemBid.itemBidId", itemBidId);
		
		return tachnicalBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public long updateStatusForTechnicalBid(String bidSection,Long bidderId,Long itemBidId){
		long result = 0; 
		try{
		if(bidSection.equalsIgnoreCase(AppBaseConstant.TECHNICAL_SECTION) && bidderId!=null && itemBidId!=null ){
		updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, itemBidId, null);
		priceBidService.updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, itemBidId, null);
		itemBidService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, itemBidId);
		commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId, null);
		bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId);
		result = 1;
		}
		}
		catch (Exception e) {
			log.error("EXCEPTION", e);
		}
		
		return result;
	}
	
}
