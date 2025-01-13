package com.novelerp.appcontext.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.converter.PartnerCompanyDetailsConverter;
import com.novelerp.appcontext.dao.PartnerCompanyDetailsDao;
import com.novelerp.appcontext.dto.PartnerCompanyDetailsDto;
import com.novelerp.appcontext.entity.PartnerCompanyDetails;
import com.novelerp.appcontext.service.PartnerCompanyDetailsService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * @author Aman
 *
 */
@Service
public class PartnerCompanyDetailsServiceImpl extends AbstractServiceImpl<PartnerCompanyDetails, PartnerCompanyDetailsDto> implements PartnerCompanyDetailsService{


	@Autowired
	private PartnerCompanyDetailsDao  partnerCompanyDetailsDao;
	
	@Autowired
	private PartnerCompanyDetailsConverter partnerCompanyDetailsConverter;
	
	@PostConstruct
	public void init(){
		super.init(PartnerCompanyDetailsServiceImpl.class, partnerCompanyDetailsDao, PartnerCompanyDetails.class, PartnerCompanyDetailsDto.class);
		setObjectConverter(partnerCompanyDetailsConverter);
	}	
	
	@Override
	public PartnerCompanyDetailsDto getPartnerCompanyDetails(Long partnerId) {
		PartnerCompanyDetailsDto dto=null;
		try
		{
			List<PartnerCompanyDetails> list=partnerCompanyDetailsDao.findAll(" where m_bpartner_id="+partnerId, null);
			PartnerCompanyDetails entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerCompanyDetailsConverter.getDtoFromEntity(entity, PartnerCompanyDetailsDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerCompanyDetails():"+ex);
		}
		return dto;
	}

	@Override
	@Transactional
	public ResponseDto updatePartnerCompanyDetails(PartnerCompanyDetailsDto dto) {
		System.out.println("..PartnerCompanyDetailsServiceImpl-updatePartnerCompanyDetails()..");
		
		try {
				PartnerCompanyDetails entity=partnerCompanyDetailsConverter.getEntityFromDto(dto, PartnerCompanyDetails.class);
				partnerCompanyDetailsDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "Company Details Updated Successfully", entity.getPartnerCompanyDetailsId());
		} catch (Exception e) {
			
			System.err.println("Error in Update Company Details " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "Company Details Not Updated");
		}
	}

}
