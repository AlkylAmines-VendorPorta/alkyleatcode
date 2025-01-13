package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dao.ThirdPartyPRApproverDao;
import com.novelerp.alkyl.dto.ThirdPartyPRApproverDto;
import com.novelerp.alkyl.entity.ThirdPartyPRApprover;
import com.novelerp.alkyl.service.ThirdPartyPRApproverService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.exception.CustomException;
@Service
public class ThirdPartyPRApproverServiceImpl extends AbstractContextServiceImpl<ThirdPartyPRApprover, ThirdPartyPRApproverDto> implements ThirdPartyPRApproverService {

	@Autowired
	private ThirdPartyPRApproverDao thirdPartyPRApproverDao;
	
	@Autowired
	private PRComponent prComponent;
	
	@PostConstruct
	public void init(){
		
		 super.init(ThirdPartyPRApproverServiceImpl.class, thirdPartyPRApproverDao, ThirdPartyPRApprover.class, ThirdPartyPRApproverDto.class);
	setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ThirdPartyPRApproverDto> save(List<ThirdPartyPRApproverDto> tpPrApp){
		List<ThirdPartyPRApproverDto> resDto=new ArrayList<>();
		if(tpPrApp==null){
			return new ArrayList<>();
		}
		
		for(ThirdPartyPRApproverDto dto:tpPrApp){
			
			if(dto==null){
				throw new CustomException("Empty Email");
			}
			
			if(CommonUtil.isStringEmpty(dto.getEmail())){
				throw new CustomException("Empty Email");
			}
			
			if(dto.getPr()==null){
				throw new CustomException("Empty PR in Multiseller Email");
			}
			
			ThirdPartyPRApproverDto oldDto = prComponent.findThirdPartyById(dto.getThirdPartyPRApproverId());
			
			if(oldDto!=null){
				dto = copyNewDtoToOld(dto, oldDto);
			}
			
			if(dto.getThirdPartyPRApproverId()==null){
				dto = super.save(dto);
			}else{
				dto = super.updateDto(dto);
			}
			
			resDto.add(dto);

		}
			
		return resDto;
	}
	
	
	private ThirdPartyPRApproverDto copyNewDtoToOld(ThirdPartyPRApproverDto newDto, ThirdPartyPRApproverDto oldDto){
		
		oldDto.setCode(newDto.getCode());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setName(newDto.getName());
		oldDto.setEmail(newDto.getEmail());
		oldDto.setPr(newDto.getPr());
		
		return oldDto;
	}
	
}
