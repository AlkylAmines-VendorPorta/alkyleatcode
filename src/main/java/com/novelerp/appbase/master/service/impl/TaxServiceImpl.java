package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.TaxConverter;
import com.novelerp.appbase.master.dao.TaxDao;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.appbase.master.service.TaxService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class TaxServiceImpl extends AbstractServiceImpl<Tax, TaxDto> implements TaxService {

	@Autowired
	private TaxDao taxDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TaxConverter taxConverter;
	
	@PostConstruct
	private void init() {
		super.init(TaxServiceImpl.class, taxDao, Tax.class, TaxDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<TaxDto> getTaxList()
	{
		List<TaxDto> TaxList=new ArrayList<TaxDto>();
		/*List<Tax> TaxEntity= taxDao.findAll("", "updated desc");*/
		List<Tax> TaxEntity= taxDao.getTaxList();
		
		if(!TaxEntity.isEmpty())
		{
			for(Tax Tax:TaxEntity)
			{
				/*TaxDto TaxDto=taxConverter.getDtoFromEntity(Tax, TaxDto.class);*/
				TaxDto TaxDto=taxConverter.convertEntityToDto(Tax, TaxDto.class);
				TaxList.add(TaxDto);
			}
		}
		return TaxList;
	}
	
	@Override
	public TaxDto getTax(Long entityId)
	{
		TaxDto taxDto=null;
				
		try
		{
			/*Tax Tax=taxDao.findOne(entityId);*/
			/*TaxDto=taxConverter.getDtoFromEntity(Tax, TaxDto.class);*/
			List<Tax> taxEntity= taxDao.getTaxById(entityId);
			
			if(!taxEntity.isEmpty())
			{
				
					taxDto=taxConverter.convertEntityToDto(taxEntity.get(0), TaxDto.class);
					
			}
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching Tax" + e.getMessage());
		}
		return taxDto;
	}
	
	@Override
	@Transactional
	public CustomResponseDto saveTax(TaxDto TaxDto)
	{
		TaxDto dto=null;
		try{
			BPartnerDto partner= contextService.getPartner();
			TaxDto.setPartner(partner);
			Tax tax =taxConverter.getEntityFromDto(TaxDto, Tax.class);
			   taxDao.create(tax);
			   dto=taxConverter.getDtoFromEntity(tax, TaxDto.class);
			   return new CustomResponseDto(dto, "Tax "+TaxDto.getName()+" is Saved Successfully",true);
			   
		}
		catch(Exception e){
			System.err.println("Error is updated Tax "+e.getMessage());
			return new CustomResponseDto(false ,"Tax "+ TaxDto.getName()+" is Not Saved");
		}
		
	}
	/*@Override
	@Transactional
	public CustomResponseDto deleteTax(Long id)
	{
		System.out.println("..TaxServiceImpl-deleteTax()..");
		TaxDto taxDto = new TaxDto();
		try {
			removeTax(id);
			return new CustomResponseDto(true,"Tax is Deleted Successfully");
	} catch (Exception e) {
		
		System.err.println("Error in Deleting GtpParameter " + e.getMessage());
		return new CustomResponseDto(false,"Tax is Not Deleted");
	}
		
	}*/
	@Override
	@Transactional
	public boolean deleteTax(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	@Transactional
	public void removeTax(Long id)
	{
		taxDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public CustomResponseDto editTax(TaxDto TaxDto)
	{
		TaxDto dto=null;
		try {
			BPartnerDto partner=contextService.getPartner();	
			TaxDto.setPartner(partner);
				Tax gtpParameter=taxConverter.getEntityFromDto(TaxDto, Tax.class);
				taxDao.update(gtpParameter);
				dto=taxConverter.convertEntityToDto(gtpParameter, TaxDto.class);
				return new CustomResponseDto(dto,"GTP PARAMETER-"+TaxDto.getName()+" is Updated Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated GtpParameter " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER-"+TaxDto.getName()+" is Not Updated");
			
		}
	}
	
	/*@Override
	@Transactional
	public CustomResponseDto editTax(GtpParameterDto gtpParameterDto)
	{
		GtpParameterDto dto=null;
		try {
			BPartnerDto partner=contextService.getPartner();	
			gtpParameterDto.setPartner(partner);
				GtpParameter gtpParameter=gtpParameterConverter.getEntityFromDto(gtpParameterDto, GtpParameter.class);
				gtpParameterDao.update(gtpParameter);
				dto=gtpParameterConverter.convertEntityToDto(gtpParameter, GtpParameterDto.class);
				return new CustomResponseDto(dto,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Updated Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated GtpParameter " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Not Updated");
			
		}
	}*/
	


	
	/*@Override
	@Transactional
	public ResponseDto editTax(TaxDto TaxDto)
	{
		System.out.println("..TaxServiceImpl-editTax()..");
		
		try {
				Tax Tax=TaxConverter.getEntityFromDto(TaxDto, Tax.class);
				TaxDao.update(Tax);
				return new ResponseDto(AppBaseConstant.SUCCESS," Tax- "+TaxDto.getName()+" Updated Succesfully ",TaxDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated Tax " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Tax- "+TaxDto.getName()+"  Not Updated ",TaxDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteTax(Long id)
	{
		System.out.println("..TaxServiceImpl-deleteTax()..");
		TaxDto TaxDto = new TaxDto();
		try {
			
			TaxDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," Tax- "+TaxDto.getName()+" Deleted Succesfully ",TaxDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting Tax " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Tax- "+TaxDto.getName()+" Cannot be Deleted",TaxDto.getId());
		}
		
	}*/

	
}

