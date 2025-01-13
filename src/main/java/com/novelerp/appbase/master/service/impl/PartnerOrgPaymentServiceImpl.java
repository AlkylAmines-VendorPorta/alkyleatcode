package com.novelerp.appbase.master.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerOrgPaymentConverter;
import com.novelerp.appbase.master.dao.PartnerOrgPaymentDao;
import com.novelerp.appbase.master.dto.PartnerOrgPaymentDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgPayment;
import com.novelerp.appbase.master.service.PartnerOrgPaymentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgPaymentServiceImpl extends AbstractServiceImpl<PartnerOrgPayment, PartnerOrgPaymentDto> implements PartnerOrgPaymentService{

	@Autowired
	private PartnerOrgPaymentDao  partnerOrgPaymentDao;
	
	@Autowired
	private PartnerOrgPaymentConverter partnerOrgPaymentConverter;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgPaymentServiceImpl.class, partnerOrgPaymentDao, PartnerOrgPayment.class, PartnerOrgPaymentDto.class);
		setObjectConverter(partnerOrgPaymentConverter);
	}	
	
	@Override
	public PartnerOrgPaymentDto getPartnerOrgPayment(Long partnerId) {
		PartnerOrgPaymentDto dto=null;
		try
		{
			List<PartnerOrgPayment> list=partnerOrgPaymentDao.findAll(" where m_bpartner_id="+partnerId, null);
			PartnerOrgPayment entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerOrgPaymentConverter.getDtoFromEntity(entity, PartnerOrgPaymentDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerOrgPayment():"+ex);
		}
		return dto;
	}

	@Override
	@Transactional
	public ResponseDto updatePartnerOrgPayment(PartnerOrgPaymentDto dto) {
		System.out.println("..PartnerOrgPaymentServiceImpl-updatePartnerOrgPayment()..");
		
		try {
				PartnerOrgPayment entity=partnerOrgPaymentConverter.getEntityFromDto(dto, PartnerOrgPayment.class);
				partnerOrgPaymentDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "Org Payment Updated Successfully", entity.getPartnerOrgPaymentId());
				
		} catch (Exception e) {
			
			System.err.println("Error in Update Payment Details " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "Org Payment Not Updated");
		}
		
	}

}
