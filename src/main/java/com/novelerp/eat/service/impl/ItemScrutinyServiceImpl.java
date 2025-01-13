package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.eat.dao.ItemScrutinyDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.entity.ItemScrutiny;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.ItemScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class ItemScrutinyServiceImpl extends AbstractContextServiceImpl<ItemScrutiny, ItemScrutinyDto> implements ItemScrutinyService {

	@Autowired
	private ItemScrutinyDao itemScrutinyDao;

	@Autowired
	private ItemScrutinyLineService scrutinyLineService;
	
	@PostConstruct
	private void init() {
		super.init(ItemScrutinyServiceImpl.class, itemScrutinyDao, ItemScrutiny.class, ItemScrutinyDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation =Propagation.REQUIRED)
	public List<ItemScrutinyDto> saveItemScrutiny(List<ItemScrutinyDto> dtos){
		ArrayList<ItemScrutinyDto> itemScrutiny = new ArrayList<>();
		if(CommonUtil.isListEmpty(dtos)){
			return itemScrutiny;
		}
		
		for(ItemScrutinyDto dto : dtos){
			ItemScrutinyDto result = processSave(dto);
			itemScrutiny.add(result);
		}
		return itemScrutiny;
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyDto createTechnicalScrutiny(ItemBidDto itemBid ,Set<BidderGtpDto> bidderGtpList,Set<BidderSectionDocDto> technicalDoc){
		ItemScrutinyDto  dto= createItemScrutinyForTechnical(itemBid, AppBaseConstant.TECHNICAL_SCRUTINY);
		 scrutinyLineService.createTechnicalScrutinyLines(dto, bidderGtpList);
		 scrutinyLineService.createTechnicalDocScrutinyLines(dto, technicalDoc);
		 return dto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyDto createCommercialScrutiny(BidderDto bidder,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> commercialDoc){		
		ItemScrutinyDto  dto= createItemScrutinyForCommercial(bidder, AppBaseConstant.COMMERCIAL_SCRUTINY);
		scrutinyLineService.createCommercialScrutinyLines(dto, scrutinyPointList);
		scrutinyLineService.createCommercialDocScrutinyLines(dto, commercialDoc);
		return dto;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyDto createItemScrutinyForTechnical(ItemBidDto itemBid, String scrutinyType){		
		ItemScrutinyDto dto =  new ItemScrutinyDto();
		dto.setBidder(itemBid.getBidder());
		dto.setItemBid(itemBid);
		dto.setScrutinyType(scrutinyType);
		return processSave(dto);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyDto createItemScrutinyForCommercial(BidderDto bidder, String scrutinyType){		
		ItemScrutinyDto dto =  new ItemScrutinyDto();
		dto.setBidder(bidder);
		
		dto.setScrutinyType(scrutinyType);
		return processSave(dto);
	}
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ItemScrutinyDto> createScrutiny(ItemBidDto itemBidDto,Set<BidderGtpDto> bidderGtpList,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> technicalDoc,Set<BidderSectionDocDto> commercialDoc){
		List<ItemScrutinyDto> scrutinyList =  new ArrayList<>();
		
		ItemBidDto itemBid =  new ItemBidDto();
		itemBid.setItemBidId(itemBidDto.getItemBidId());
		itemBid.setBidder(itemBidDto.getBidder());
	
		ItemScrutinyDto techScrutiny = createTechnicalScrutiny(itemBid, bidderGtpList,technicalDoc);
		ItemScrutinyDto commScrutiny = createCommercialScrutiny(itemBid,scrutinyPointList,commercialDoc);
		scrutinyList.add(techScrutiny);
		scrutinyList.add(commScrutiny);
		
		return scrutinyList;
	}*/
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ItemScrutinyDto> createScrutinyForTechnical(ItemBidDto itemBidDto,Set<BidderGtpDto> bidderGtpList,Set<BidderSectionDocDto> technicalDoc){
		List<ItemScrutinyDto> scrutinyList =  new ArrayList<>();
		
		ItemBidDto itemBid =  new ItemBidDto();
		itemBid.setItemBidId(itemBidDto.getItemBidId());
		itemBid.setBidder(itemBidDto.getBidder());
	
		ItemScrutinyDto techScrutiny = createTechnicalScrutiny(itemBid, bidderGtpList,technicalDoc);
		scrutinyList.add(techScrutiny);
		
		return scrutinyList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ItemScrutinyDto> createScrutinyForCommercial(BidderDto bidderDto,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> commercialDoc){
		List<ItemScrutinyDto> scrutinyList =  new ArrayList<>();
		
		BidderDto bidder =  new BidderDto();
		bidder.setBidderId(bidderDto.getBidderId());
		
	
		ItemScrutinyDto commScrutiny = createCommercialScrutiny(bidder,scrutinyPointList,commercialDoc);
		scrutinyList.add(commScrutiny);
		
		return scrutinyList;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyDto processSave(ItemScrutinyDto dto){
		if(dto.getItemScrutinyId() == null){
			return super.save(dto);
		}
		return super.updateDto(dto);
				
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean savePreliminarySatus(ItemScrutinyDto newItemScrutinyDto)
	{
		String comment=newItemScrutinyDto.getPreliminaryScrutinyComment();
		String status=newItemScrutinyDto.getPreliminaryScrutinyStatus();
		Long itemScrutinyId=newItemScrutinyDto.getItemScrutinyId();
		 int result=itemScrutinyDao.getItemscrutinyForPreliminaryScrutiny(itemScrutinyId,comment,status);
		 if(result==1) return true;
			else return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean resetPreliminaryScrutinySatus(Long itemScrutinyId){
		if(itemScrutinyId!=null){
			int result=itemScrutinyDao.resetItemscrutinyForPreliminaryScrutiny(itemScrutinyId);
			 if(result==1) return true;
				else return false;
		}
		return false; 
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean resetFinalScrutinySatus(Long itemScrutinyId){
		if(itemScrutinyId!=null){
			int result=itemScrutinyDao.resetItemscrutinyForFinalScrutiny(itemScrutinyId);
			 if(result==1) return true;
				else return false;
		}
		return false; 
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveBidderDevitionSatus(ItemScrutinyDto newItemScrutinyDto)
	{
		String status=newItemScrutinyDto.getBidderDeviationStatus();
		Long itemScrutinyId=newItemScrutinyDto.getItemScrutinyId();
		Long fileId=newItemScrutinyDto.getScrutinyFile().getAttachmentId();
		 int result=itemScrutinyDao.saveBidderDeviationResposne(itemScrutinyId,status,fileId);
		 if(result>0) return true;
			else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFinalScrutinyInfo(ItemScrutinyDto itemScrutinyDto)
	{
		 int result=itemScrutinyDao.updateFinalScrutinyResponseInfo(itemScrutinyDto);
		 if(result>0) return true;
			else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFinalScrutinyAuditorResponseInfo(ItemScrutinyDto dto)
	{
		 int result=itemScrutinyDao.updateFinalScrutinyAuditorResponseInfo(dto);
		 if(result>0) return true;
			else return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean revokeDeviationConfirmation(Long itemScrutinyId){
		 int result=itemScrutinyDao.revokeDeviationConfirmation(itemScrutinyId);
		 if(result>0) return true;
			else return false;
	}

	@Override
	public List<String> getScrutinyEngrEmailList(Long tahdrId) {
		List<Object> objectList=itemScrutinyDao.getScrutinyEngrEmailList(tahdrId);
		List<String> emailList=new ArrayList<String>();
		if(!CommonUtil.isCollectionEmpty(objectList)){
			for(Object o:objectList){
				emailList.add(o.toString());	
			}
		}
		return emailList;
	}
}
