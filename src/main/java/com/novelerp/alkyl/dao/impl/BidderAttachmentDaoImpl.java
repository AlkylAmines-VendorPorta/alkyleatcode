
package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.BidderAttachmentDao;
import com.novelerp.alkyl.dto.BidderAttachmentDto;
import com.novelerp.alkyl.entity.BidderAttachment;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class BidderAttachmentDaoImpl extends AbstractJpaDAO<BidderAttachment, BidderAttachmentDto> implements BidderAttachmentDao{

	@PostConstruct
	public void postconstruct(){
		
		setClazz(BidderAttachment.class, BidderAttachmentDto.class);
	}
	
	public String getAllAttachmentByBidderID(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM BidderAttachment A ")
		.append(" INNER JOIN FETCH A.bidder B ")
		.append(" INNER JOIN FETCH A.attachment C ")
		.append("  WHERE B.bidderId=:bidderId  AND A.isActive='Y' ");
		return jpql.toString();
	}
}

