package com.novelerp.appbase.master.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerOrgPBGConverter;
import com.novelerp.appbase.master.dao.PartnerOrgPBGDao;
import com.novelerp.appbase.master.dto.PartnerOrgPBGDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgPBG;
import com.novelerp.appbase.master.service.PartnerOrgPBGService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgPBGServiceImpl extends AbstractContextServiceImpl<PartnerOrgPBG, PartnerOrgPBGDto> implements PartnerOrgPBGService{

	@Autowired
	private PartnerOrgPBGDao  partnerOrgPBGDao;
	
	@Autowired
	private PartnerOrgPBGConverter partnerOrgPBGConverter;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgPBGServiceImpl.class, partnerOrgPBGDao, PartnerOrgPBG.class, PartnerOrgPBGDto.class);
		/*setObjectConverter(partnerOrgPBGConverter);*/
		setByPassProxy(true);
	}	
	
	@Override
	public PartnerOrgPBGDto getPartnerOrgPBG(Long partnerId) {
		PartnerOrgPBGDto dto=null;
		try
		{
			List<PartnerOrgPBG> list=partnerOrgPBGDao.findAll(" where m_bpartner_id="+partnerId, null);
			PartnerOrgPBG entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerOrgPBGConverter.getDtoFromEntity(entity, PartnerOrgPBGDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerOrgPBG():"+ex);
		}
		return dto;
	}

	@Override
	@Transactional
	public ResponseDto updatePartnerOrgPBG(PartnerOrgPBGDto dto) {
		System.out.println("..PartnerOrgPBGServiceImpl-updatePartnerOrgPBG()..");
		
		try {
				PartnerOrgPBG entity=partnerOrgPBGConverter.getEntityFromDto(dto, PartnerOrgPBG.class);
				partnerOrgPBGDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "Org PBG Details Updated Successfully", entity.getPartnerOrgPbgId());
		} catch (Exception e) {
			
			System.err.println("Error in Update PBG Details " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "Org PBG Details Not Updated");
		}
	}

}
