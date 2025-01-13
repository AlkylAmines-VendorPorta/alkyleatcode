package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PublicNoticeConverter;
import com.novelerp.appbase.master.converter.PublicNoticePlainConverter;
import com.novelerp.appbase.master.dao.PublicNoticeDao;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.entity.PublicNotice;
import com.novelerp.appbase.master.service.PublicNoticeService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class PublicNoticeServiceImpl extends AbstractContextServiceImpl<PublicNotice, PublicNoticeDto> implements PublicNoticeService {

	@Autowired
	private PublicNoticeDao publicNoticeDao;
	
	@Autowired
	private PublicNoticeConverter publicNoticeConverter;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PublicNoticePlainConverter publicNoticePlainConverter;
	@PostConstruct
	private void init() {
		super.init(PublicNoticeServiceImpl.class, publicNoticeDao, PublicNotice.class, PublicNoticeDto.class);
		/*setObjectConverter(publicNoticeConverter);*/
		setByPassProxy(true);
	}

	@Override
	public List<PublicNoticeDto> getPublicNoticeList()
	{
		List<PublicNoticeDto> PublicNoticeList=new ArrayList<PublicNoticeDto>();
		List<PublicNotice> PublicNoticeEntity= publicNoticeDao.findAll("", "updated desc");
		if(!PublicNoticeEntity.isEmpty())
		{
			for(PublicNotice PublicNotice:PublicNoticeEntity)
			{
				PublicNoticeDto PublicNoticeDto=publicNoticePlainConverter.convertEntityToDto(PublicNotice, PublicNoticeDto.class);
				PublicNoticeList.add(PublicNoticeDto);
			}
		}
		return PublicNoticeList;
	}
	
	
	@Override
	public PublicNoticeDto getPublicNotice(Long entityId)
	{
		PublicNoticeDto PublicNoticeDto=null;
				
		try
		{
			PublicNotice PublicNotice=publicNoticeDao.findOne(entityId);
			PublicNoticeDto=publicNoticePlainConverter.convertEntityToDto(PublicNotice, PublicNoticeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching PublicNotice" + e.getMessage());
		}
		return PublicNoticeDto;
	}
	
	
	@Override
	@Transactional
	public CustomResponseDto savePublicNotice(PublicNoticeDto publicNoticeDto)
	{
		System.out.println("..PublicNoticeServiceImpl-savePublicNotice()..");
		PublicNoticeDto dto=null;
		try {
			BPartnerDto partner=contextService.getPartner();	
			publicNoticeDto.setPartner(partner);
			PublicNotice publicNotice=publicNoticePlainConverter.getEntityFromDto(publicNoticeDto, PublicNotice.class);
			publicNoticeDao.create(publicNotice);
				dto=publicNoticePlainConverter.convertEntityToDto(publicNotice, PublicNoticeDto.class);
				return new CustomResponseDto(dto,"PUBLIC NOTICE-"+dto.getName()+" is Saved Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Add PublicNotice " + e.getMessage());
			return new CustomResponseDto(false,"PUBLIC NOTICE-"+dto.getName()+" not Saved");
			
		}
		
	}
	@Override
	@Transactional
	public CustomResponseDto editPublicNotice(PublicNoticeDto publicNoticeDto)
	{
		System.out.println("..PublicNoticeServiceImpl-editPublicNotice()..");
		PublicNoticeDto dto=null;
		try {
				BPartnerDto partner=contextService.getPartner();	
				publicNoticeDto.setPartner(partner);
				PublicNotice publicNotice=publicNoticePlainConverter.getEntityFromDto(publicNoticeDto, PublicNotice.class);
				publicNoticeDao.update(publicNotice);
				dto=publicNoticePlainConverter.convertEntityToDto(publicNotice, PublicNoticeDto.class);
				return new CustomResponseDto(dto,"PUBLIC NOTICE-"+dto.getName()+" is Update Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Add PublicNotice " + e.getMessage());
			return new CustomResponseDto(false,"PUBLIC NOTICE-"+dto.getName()+" not Updated");
			
		}
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto deletePublicNotice(Long id)
	{
		System.out.println("..PublicNoticeServiceImpl-deletePublicNotice()..");
		try {
			 removePublicNotice(id);
				return new CustomResponseDto(true,"PUBLIC NOTICE deleted Successfully");
		} catch (Exception e) {
			
			System.err.println("Error in Deleting PublicNotice " + e.getMessage());
			return new CustomResponseDto(false,"PUBLIC NOTICE not deleted");
		}
		
	}

	@Transactional
	public void removePublicNotice(Long id)
	{
		publicNoticeDao.deleteById(id);
	}
}


