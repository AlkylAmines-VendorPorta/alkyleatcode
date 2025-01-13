package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MAuctionParticipantDao;
import com.novelerp.appbase.master.dto.InvitationAuctionParticipantDto;
import com.novelerp.appbase.master.dto.MAuctionParticipantMapDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.MAuctionParticipantMap;
import com.novelerp.appbase.master.service.MAuctionParticipantService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.TAHDRDto;
@Service
public class MAuctionParticipantServiceImpl extends AbstractContextServiceImpl<MAuctionParticipantMap, MAuctionParticipantMapDto>  implements MAuctionParticipantService{
@Autowired
private MAuctionParticipantDao mAuctionParticipantDao;

	@PostConstruct
	private void init() {
		super.init(MAuctionParticipantServiceImpl.class,mAuctionParticipantDao , MAuctionParticipantMap.class, MAuctionParticipantMapDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto saveAuctionParticipantMap(InvitationAuctionParticipantDto dto,TAHDRDto tahdto) {
		/*for(int i=0; i<=dto.getPartners().size(); i++){*/
		Map<String, Object> params= new HashMap<>();
		ResponseDto response = new ResponseDto();
		for(BPartnerDto partner:dto.getPartners()){
			
			MAuctionParticipantMap mAuctionParticipantMapDtoS = mAuctionParticipantDao.findOne("b_partner_id ="+partner.getbPartnerId()+ "AND t_tahdr_id ="+tahdto.getTahdrId()  , params, "updated desc");
			if(mAuctionParticipantMapDtoS == null){
			MAuctionParticipantMapDto mAuctionParticipantMapDto = new MAuctionParticipantMapDto();
			mAuctionParticipantMapDto.setTahdr(tahdto);
			mAuctionParticipantMapDto.setbPartner(partner);
			mAuctionParticipantMapDto.setStatus("N");
			save(mAuctionParticipantMapDto);
			}
			
		}
		response.setResponseMsg("Success");
		return response;
		
	}
	@Override
	public MAuctionParticipantMapDto getALLSelectedParticipant(Long auctionID) {
		
		// TODO Auto-generated method stub
		return null;
	}

	
}
