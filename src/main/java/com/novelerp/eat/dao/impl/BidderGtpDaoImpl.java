package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.BidderGtpDao;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.entity.BidderGtp;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class BidderGtpDaoImpl extends AbstractJpaDAO<BidderGtp, BidderGtpDto> implements BidderGtpDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(BidderGtp.class, BidderGtpDto.class);
	}
	
	public String getBidderGtpQuery(){
		String jpql=" SELECT bg from BidderGtp bg "
				+ " LEFT JOIN bidder b "
				+ " LEFT JOIN TAHDRMaterialGTP tmg "
				+ " WHERE b.bidder.bidderId=:bidderId and tmg ";
		return jpql;
	}
	
	public String getBidderGtpByTahdrMaterialId(){

		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bg from BidderGtp bg ")
		.append(" LEFT JOIN FETCH bg.fileResponse fr ")
		.append(" INNER JOIN FETCH bg.tahdrMaterialgtp tmg ")
		.append(" INNER JOIN FETCH tmg.gtp gtp ")
		.append(" INNER JOIN FETCH gtp.gtpParameterType gtpType ")
		.append(" INNER JOIN FETCH tmg.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH bg.technicalBid tb ")
		.append(" INNER JOIN FETCH tb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.bidder ib ")
		.append(" INNER JOIN FETCH bg.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId ");
		return jpql.toString();
	
	}
	
}
