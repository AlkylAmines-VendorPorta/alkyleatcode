package com.novelerp.appbase.master.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerCompanyContactConverter;
import com.novelerp.appbase.master.dao.PartnerCompanyContactDao;
import com.novelerp.appbase.master.dto.PartnerCompanyContactDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerCompanyContact;
import com.novelerp.appbase.master.service.PartnerCompanyContactService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerCompanyContactServiceImpl  extends AbstractContextServiceImpl<PartnerCompanyContact, PartnerCompanyContactDto> implements PartnerCompanyContactService{

	@Autowired
	private PartnerCompanyContactDao  partnerCompanyContactDao;
	
	@Autowired
	private PartnerCompanyContactConverter partnerCompanyContactConverter;
	
	@PostConstruct
	public void init(){
		super.init(PartnerCompanyContactServiceImpl.class, partnerCompanyContactDao, PartnerCompanyContact.class, PartnerCompanyContactDto.class);
		setObjectConverter(partnerCompanyContactConverter);
	}	
	
	@Override
	public PartnerCompanyContactDto getPartnerCompanyContact(Long entityId) {
		PartnerCompanyContactDto dto=null;
		try
		{
			List<PartnerCompanyContact> list=partnerCompanyContactDao.findAll(" where m_bpartner_id="+entityId, null);
			PartnerCompanyContact entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerCompanyContactConverter.getDtoFromEntity(entity, PartnerCompanyContactDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerCompanyContact():"+ex);
		}
		return dto;
	}

	@Override
	@Transactional
	public ResponseDto updatePartnerCompanyContact(PartnerCompanyContactDto dto) {
		System.out.println("..PartnerCompanyContactServiceImpl-updatePartnerCompanyContact()..");
		
		try {
				PartnerCompanyContact entity=partnerCompanyContactConverter.getEntityFromDto(dto, PartnerCompanyContact.class);
				partnerCompanyContactDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "Company Contact Updated Successfully", entity.getPartnerCompanyContactId());
		} catch (Exception e) {
			
			System.err.println("Error in Update Company Contact " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "Company Contact Not Updated");
		}
	}

}
