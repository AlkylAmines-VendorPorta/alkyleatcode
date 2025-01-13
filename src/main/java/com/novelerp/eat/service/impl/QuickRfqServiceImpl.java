package com.novelerp.eat.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.TAHDRDao;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;
import com.novelerp.eat.service.QuickRfqService;
import com.novelerp.eat.service.TAHDRDetailService;

@Service
public class QuickRfqServiceImpl extends AbstractContextServiceImpl<TAHDR, TAHDRDto> implements QuickRfqService{

	@Autowired
	private TAHDRDao tahdrDao;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@PostConstruct
	private void init() {
		super.init(QuickRfqServiceImpl.class, tahdrDao, TAHDR.class, TAHDRDto.class);
		setByPassProxy(true);
	}

	private static final Logger log = LoggerFactory.getLogger(MISServiceImpl.class);
	
	@Override
	public List<TAHDRDto> getQuickRfqList(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue) {
		log.info("Get Rfq Auction List");
		String queryString = tahdrDao.getTahdrListByCodeAndUser(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<TAHDRDto> tahdrEntity = findDtosByQuery(queryString, map, pageNumber, pageSize);
		return tahdrEntity;
	}
	
	@Override
	public long getQuickRfqListQueryCount(Map<String, Object> map,String searchColumn, String searchValue) {
		long totalCount;
		String queryString =tahdrDao.getQuickAuctionListQueryCountQry(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, map);
		}else{
			totalCount = getRecordCount(queryString, map);
		}
		return totalCount;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDto createQuickRfq(TAHDRDto tahdr){
		if(tahdr !=null && tahdr.getTahdrDetail().iterator().hasNext()){
			TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
			tahdr=super.save(tahdr);
			td.setTahdr(tahdr);
			td=tahdrDetailService.saveTAHDRDetail(td);
			Set<TAHDRDetailDto> tdSet=new HashSet();
			tdSet.add(td);
			tahdr.setTahdrDetail(tdSet);
			return tahdr;
		}else{
			ResponseDto resp=new ResponseDto(true, "Please check the details you have filled");
			tahdr.setResponse(resp);
			return tahdr;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDto updateQuickRfq(TAHDRDto tahdr,TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		if(tahdr !=null && tahdr.getTahdrDetail().iterator().hasNext()){
			TAHDRDetailDto td=newDto;
			tahdr=super.updateDto(tahdr);
			td.setTahdr(tahdr);
			td=tahdrDetailService.updateQuickRfq(tahdr,td, oldDto);
			Set<TAHDRDetailDto> tdSet=new HashSet();
			tdSet.add(td);
			tahdr.setTahdrDetail(tdSet);
			return tahdr;
		}else{
			ResponseDto resp=new ResponseDto(true, "Please check the details you have filled");
			tahdr.setResponse(resp);
			return tahdr;
		}
	}
}
