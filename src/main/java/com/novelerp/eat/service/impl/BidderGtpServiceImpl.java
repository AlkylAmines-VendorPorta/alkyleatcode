package com.novelerp.eat.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.BidderGtpDao;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.BidderGtp;
import com.novelerp.eat.service.BidderGtpService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class BidderGtpServiceImpl extends AbstractContextServiceImpl<BidderGtp, BidderGtpDto> implements BidderGtpService {

	@Autowired
	private BidderGtpDao bidderGtpDao;
	
	@PostConstruct
	private void init() {
		super.init(BidderGtpServiceImpl.class, bidderGtpDao, BidderGtp.class, BidderGtpDto.class);
		setByPassProxy(true);
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.BidderGtpService#save(com.novelerp.eat.dto.TechnicalBidDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TechnicalBidDto save(TechnicalBidDto technicalBid) {
		ResponseDto response=new ResponseDto();
		Set<BidderGtpDto> bidderGtpSet= new HashSet<>();
		try{
			if(!technicalBid.getBidderGtp().isEmpty()){
				for(BidderGtpDto dto:technicalBid.getBidderGtp()){
					if(dto!=null && dto.getTahdrMaterialgtp()!=null && dto.getTahdrMaterialgtp().getTahdrMaterialGtpId()!=null){
						dto.setTechnicalBid(technicalBid);
						try{
							if(null==dto.getBidderGtpId() || !(dto.getBidderGtpId()>0)){
								dto=super.save(dto);
							}else{
								dto=super.updateDto(dto);
							}
							bidderGtpSet.add(dto);
						}catch(Exception ex){
							log.error(ex.getMessage());
						}
					}
					
				}
			}
			technicalBid.setBidderGtp(bidderGtpSet);
			response.setHasError(false);
			response.setMessage("Record Saved");
			technicalBid.setResponse(response);
		}catch(Exception e){
			log.error(e.getMessage());
			response.setHasError(true);
			response.setMessage("Error while saving records");
			technicalBid.setResponse(response);
		}
		return technicalBid;
	}
	
}
