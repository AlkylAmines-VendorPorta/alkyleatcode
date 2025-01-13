package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.PaymentTypeConverter;
import com.novelerp.appbase.master.dao.PaymentTypeDao;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;

/** 
 * @author Aman
 *
 */
@Service
public class PaymentTypeServiceImpl extends AbstractContextServiceImpl<PaymentType, PaymentTypeDto> implements PaymentTypeService {

	@Autowired
	private PaymentTypeDao paymentTypeDao;
	
	@Autowired
	private PaymentTypeConverter paymentTypeConverter;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private BPartnerService partnerService;
	@PostConstruct
	private void init() {
		super.init(PaymentTypeServiceImpl.class, paymentTypeDao, PaymentType.class, PaymentTypeDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<PaymentTypeDto> getPaymentTypeList()
	{
		List<PaymentTypeDto> PaymentTypeList=new ArrayList<PaymentTypeDto>();
		List<PaymentType> PaymentTypeEntity= paymentTypeDao.findAll(" where isActive='Y' ", "updated desc");
		
		if(!PaymentTypeEntity.isEmpty())
		{
			for(PaymentType PaymentType:PaymentTypeEntity)
			{
				PaymentTypeDto PaymentTypeDto=paymentTypeConverter.getDtoFromEntity(PaymentType, PaymentTypeDto.class);
				PaymentTypeList.add(PaymentTypeDto);
			}
		}
		return PaymentTypeList;
	}
	
	@Override
	public PaymentTypeDto getPaymentType(Long entityId)
	{
		PaymentTypeDto PaymentTypeDto=null;
				
		try
		{
			PaymentType PaymentType=paymentTypeDao.findOne(entityId);
			PaymentTypeDto=paymentTypeConverter.getDtoFromEntity(PaymentType, PaymentTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching PaymentType" + e.getMessage());
		}
		return PaymentTypeDto;
	}

	@Override
	public List<PaymentTypeDto> getPaymentTypeForPartner() {
		RoleDto role=contextService.getDefaultRole();
		BPartnerDto partner=contextService.getPartner();
		if(partner==null || partner.getbPartnerId()==null || role==null || role.getValue()==null)
		{
			log.error("Session Timout");
			return null;
		}
		
		String query=paymentTypeDao.getQueryForVenderPayment();
		StringBuilder jpql=new StringBuilder(query);
		/*jpql.append(" OR pt.code='RN'");
		query=jpql.toString();*/
		/*String query=paymentTypeDao.getQueryForVenderPayment();
		if((partner.getValidTo()!=null && DateUtil.getDateWithoutTime(partner.getValidTo()).compareTo(DateUtil.getDateWithoutTime(new Date()))<0)
			|| (partner.getIsRenewed() != null && partner.getIsRenewed().equals("N"))
			)*/
		/*boolean codeFlag=false;*/
	    if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role.getValue()) || 
	       ContextConstant.USER_TYPE_PATNER_USER.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
	    BPartnerDto dto=partnerService.findDto(partner.getbPartnerId());
	    if("Y".equals(dto.getIsInfra()))
		{
			jpql.append(" OR pt.code='INFRG'");
			query=jpql.toString();
			/*codeFlag=true;*/
		}
		if("Y".equals(dto.getIsTrader()) || "Y".equals(dto.getIsManufacturer())){
			/*if(codeFlag)
			{
				jpql.append(" OR ");
			}*/
			jpql.append(" OR pt.code='RG'");
			query=jpql.toString();
			/*codeFlag=true;*/
		}
		
		if(partner.getValidTo()!=null && ("Y".equals(dto.getIsTrader()) || ("Y".equals(dto.getIsManufacturer()))))
		{
			/*if(codeFlag)
			{
				jpql.append(" OR ");
			}*/
			jpql.append(" OR pt.code='RN'");
			query=jpql.toString();
		}else{
			if(partner.getIsManufacturer().equals("Y")){
			Map<String,Object> param=getParamMap("partnerId", partner.getbPartnerId());
			/*param.put("date",DateUtil.getDateWithoutTime(new Date()));*/
			List<PartnerOrgDto> orgs=partnerOrgService.findDtos("getOrgWithValidDate",param);
			if(!CommonUtil.isCollectionEmpty(orgs))
			{
				/*if(codeFlag)
				{
					jpql.append(" OR ");
				}*/
			    jpql.append(" OR pt.code='RN'");
			    query=jpql.toString();
			}
		  }
		}
	    }else{
	    	jpql.append(" OR pt.code='RG' OR pt.code='INFRG' OR pt.code='RN'  ");
	    	query=jpql.toString();
	    }
		List<PaymentTypeDto> paymentTypes=findDtosByQuery(query, null);
		return paymentTypes;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editPaymentType(PaymentTypeDto PaymentTypeDto)
	{
		System.out.println("..PaymentTypeServiceImpl-editPaymentType()..");
		
		try {
				PaymentType PaymentType=PaymentTypeConverter.getEntityFromDto(PaymentTypeDto, PaymentType.class);
				PaymentTypeDao.update(PaymentType);
				return new ResponseDto(AppBaseConstant.SUCCESS," PaymentType- "+PaymentTypeDto.getName()+" Updated Succesfully ",PaymentTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated PaymentType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," PaymentType- "+PaymentTypeDto.getName()+"  Not Updated ",PaymentTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deletePaymentType(Long id)
	{
		System.out.println("..PaymentTypeServiceImpl-deletePaymentType()..");
		PaymentTypeDto PaymentTypeDto = new PaymentTypeDto();
		try {
			
			PaymentTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," PaymentType- "+PaymentTypeDto.getName()+" Deleted Succesfully ",PaymentTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting PaymentType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," PaymentType- "+PaymentTypeDto.getName()+" Cannot be Deleted",PaymentTypeDto.getId());
		}
		
	}*/
	
}


