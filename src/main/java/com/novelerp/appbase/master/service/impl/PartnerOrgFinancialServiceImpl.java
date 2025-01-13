package com.novelerp.appbase.master.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerOrgFinancialConverter;
import com.novelerp.appbase.master.dao.PartnerOrgFinancialDao;
import com.novelerp.appbase.master.dto.PartnerOrgFinancialDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgFinancial;
import com.novelerp.appbase.master.service.PartnerOrgFinancialService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgFinancialServiceImpl extends AbstractServiceImpl<PartnerOrgFinancial, PartnerOrgFinancialDto> implements PartnerOrgFinancialService{

	
	@Autowired
	private PartnerOrgFinancialDao  partnerOrgFinancialDao;
	
	@Autowired
	private PartnerOrgFinancialConverter partnerOrgFinancialConverter;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgFinancialServiceImpl.class, partnerOrgFinancialDao, PartnerOrgFinancial.class, PartnerOrgFinancialDto.class);
		setObjectConverter(partnerOrgFinancialConverter);
	}	
	
	@Override
	public PartnerOrgFinancialDto getPartnerOrgFinancial(Long partnerId) {
		PartnerOrgFinancialDto dto=null;
		try
		{
			List<PartnerOrgFinancial> list=partnerOrgFinancialDao.findAll(" where m_bpartner_id="+partnerId, null);
			PartnerOrgFinancial entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerOrgFinancialConverter.getDtoFromEntity(entity, PartnerOrgFinancialDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerOrgFinancial():"+ex);
		}
		return dto;
	}

	@Override
	@Transactional
	public ResponseDto updatePartnerOrgFinancial(PartnerOrgFinancialDto dto) {
		System.out.println("..PartnerOrgFinancialServiceImpl-updatePartnerOrgFinancial()..");
		
		try {
				PartnerOrgFinancial entity=partnerOrgFinancialConverter.getEntityFromDto(dto, PartnerOrgFinancial.class);
				partnerOrgFinancialDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "Org Financial Updated Successfully", entity.getPartnerOrgFinancialId());
		} catch (Exception e) {
			
			System.err.println("Error in Update Financial Details " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "Org Financial Updated Successfully");
		}
		
	}

}
