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
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.eat.dao.ItemScrutinyLineDao;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.entity.ItemScrutinyLine;
import com.novelerp.eat.service.ItemScrutinyLineService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class ItemScrutinyLineServiceImpl extends AbstractContextServiceImpl<ItemScrutinyLine, ItemScrutinyLineDto> implements ItemScrutinyLineService {

	@Autowired
	private  ItemScrutinyLineDao itemScrutinyLineDao;
	@PostConstruct
	private void init() {
		super.init(ItemScrutinyLineServiceImpl.class, itemScrutinyLineDao, ItemScrutinyLine.class, ItemScrutinyLineDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createCommercialScrutinyLines(ItemScrutinyDto itemScrutiny,List<ScrutinyPointDto> scrutinyPointList){
		
		List<ItemScrutinyLineDto> itemscrutinyLineDtoList=new ArrayList<ItemScrutinyLineDto>();
		
		for(ScrutinyPointDto scrutinyPoint:scrutinyPointList){
			ItemScrutinyLineDto itemScrutinyLine=createLineForCommcial(scrutinyPoint, itemScrutiny);
			itemscrutinyLineDtoList.add(itemScrutinyLine);	
		}
		return CommonUtil.isCollectionEmpty(itemscrutinyLineDtoList);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createTechnicalScrutinyLines(ItemScrutinyDto itemScrutiny,Set<BidderGtpDto> bidderGtpList)
	{
		List<ItemScrutinyLineDto> itemscrutinyLineDtoList=new ArrayList<ItemScrutinyLineDto>();
		if(!CommonUtil.isCollectionEmpty(bidderGtpList)){
			for(BidderGtpDto bidderGtp:bidderGtpList){
				ItemScrutinyLineDto itemScrutinyLine=createLineForTechnical(bidderGtp, itemScrutiny);
				itemscrutinyLineDtoList.add(itemScrutinyLine);	
			}
		}
		
		return CommonUtil.isCollectionEmpty(itemscrutinyLineDtoList);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyLineDto createLineForTechnical(BidderGtpDto bidderGtpDto,ItemScrutinyDto itemScrutiny){
		ItemScrutinyLineDto itemScrutinyLine=new ItemScrutinyLineDto();
		itemScrutinyLine.setBidderGtp(bidderGtpDto);
		itemScrutinyLine.setItemScrutiny(itemScrutiny);
		return saveItemScrutinyLine(itemScrutinyLine);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyLineDto createLineForCommcial(ScrutinyPointDto scrutinyPoint,ItemScrutinyDto itemScrutiny){
		ItemScrutinyLineDto itemScrutinyLine=new ItemScrutinyLineDto();
		itemScrutinyLine.setScrutinyPoint(scrutinyPoint);
		itemScrutinyLine.setItemScrutiny(itemScrutiny);
		return saveItemScrutinyLine(itemScrutinyLine);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createCommercialDocScrutinyLines(ItemScrutinyDto itemScrutiny, Set<BidderSectionDocDto> commercialDoc)
	{
		List<ItemScrutinyLineDto> itemscrutinyLineDtoList=new ArrayList<ItemScrutinyLineDto>();
		if(!CommonUtil.isCollectionEmpty(commercialDoc))
		{
			for(BidderSectionDocDto doc:commercialDoc){
				ItemScrutinyLineDto itemScrutinyLine=createLineForDoc(doc, itemScrutiny);
				itemscrutinyLineDtoList.add(itemScrutinyLine);	
			}
		}
		
		return CommonUtil.isCollectionEmpty(itemscrutinyLineDtoList);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createTechnicalDocScrutinyLines(ItemScrutinyDto itemScrutiny, Set<BidderSectionDocDto> technicalDoc)
	{
		List<ItemScrutinyLineDto> itemscrutinyLineDtoList=new ArrayList<ItemScrutinyLineDto>();
		if(!CommonUtil.isCollectionEmpty(technicalDoc))
		{
			for(BidderSectionDocDto doc:technicalDoc){
				ItemScrutinyLineDto itemScrutinyLine=createLineForDoc(doc, itemScrutiny);
				itemscrutinyLineDtoList.add(itemScrutinyLine);	
			}
		}
		
		return CommonUtil.isCollectionEmpty(itemscrutinyLineDtoList);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyLineDto createLineForDoc(BidderSectionDocDto technicalDoc,ItemScrutinyDto itemScrutiny){
		ItemScrutinyLineDto itemScrutinyLine=new ItemScrutinyLineDto();
		itemScrutinyLine.setBidderSectionDoc(technicalDoc);
		itemScrutinyLine.setItemScrutiny(itemScrutiny);
		return saveItemScrutinyLine(itemScrutinyLine);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ItemScrutinyLineDto saveItemScrutinyLine(ItemScrutinyLineDto itemScrutinylineDto)
	{
		if(itemScrutinylineDto.getItemScrutinyLineId() == null){
			return super.save(itemScrutinylineDto);
		}
		return super.updateDto(itemScrutinylineDto);	
		}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateTechnicalDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto)
	{
		int count=itemScrutinyLineDao.updateDeviationResponseInfo(itemScrutinylineDto);
		if(count==1) return true;
		else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateCommercialDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto)
	{
		int count=itemScrutinyLineDao.updateDeviationResponseInfo(itemScrutinylineDto);
		if(count==1) return true;
		else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFinalTechnicalDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto)
	{
		int count=itemScrutinyLineDao.updateFinalScrutinyResponseInfo(itemScrutinylineDto);
		if(count>0) return true;
		else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFinalCommercialDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto)
	{
		int count=itemScrutinyLineDao.updateFinalScrutinyResponseInfo(itemScrutinylineDto);
		if(count>0) return true;
		else return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFinalScrutinyAuditorResponseInfo(ItemScrutinyLineDto dto)
	{
		int count=itemScrutinyLineDao.updateFinalScrutinyAuditorResponseInfo(dto);
		if(count>0) return true;
		else return false;
	}
}
