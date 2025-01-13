package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.BidderAttachmentDao;
import com.novelerp.alkyl.dto.BidderAttachmentDto;
import com.novelerp.alkyl.entity.BidderAttachment;
import com.novelerp.alkyl.service.BidderAttachmentService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.BidderDto;
@Service
public class BidderAttachmentServiceImpl extends AbstractContextServiceImpl<BidderAttachment, BidderAttachmentDto> implements BidderAttachmentService{

	@Autowired
	private BidderAttachmentDao bidderAttachmentDao;
	@PostConstruct
	public void init(){
		super.init(BidderAttachmentService.class, bidderAttachmentDao, BidderAttachment.class, BidderAttachmentDto.class);
		setByPassProxy(true);
	}
	@Override
	public List<BidderAttachmentDto> save(List<BidderAttachmentDto> bidderAttachment,BidderDto bidder) {
		List<BidderAttachmentDto> resDto=new ArrayList<>();
		for(BidderAttachmentDto dto:bidderAttachment){
			dto.setBidder(bidder);
			if(null==dto.getBidderAttachmentId()){
				dto = super.save(dto);
			}else{
				dto = super.updateDto(dto);
			}
			resDto.add(dto);
		}
		return resDto;
	}
}
