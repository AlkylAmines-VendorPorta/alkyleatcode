package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.PaymentModeConverter;
import com.novelerp.appbase.master.dao.PaymentModeDao;
import com.novelerp.appbase.master.dto.PaymentModeDto;
import com.novelerp.appbase.master.entity.PaymentMode;
import com.novelerp.appbase.master.service.PaymentModeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class PaymentModeServiceImpl extends AbstractServiceImpl<PaymentMode, PaymentModeDto> implements PaymentModeService {

	@Autowired
	private PaymentModeDao paymentModeDao;
	
	@Autowired
	private PaymentModeConverter paymentModeConverter;
	
	@PostConstruct
	private void init() {
		super.init(PaymentModeServiceImpl.class, paymentModeDao, PaymentMode.class, PaymentModeDto.class);
		setObjectConverter(paymentModeConverter);
	}

	@Override
	public List<PaymentModeDto> getPaymentModeList()
	{
		List<PaymentModeDto> PaymentModeList=new ArrayList<PaymentModeDto>();
		List<PaymentMode> PaymentModeEntity= paymentModeDao.findAll("", "updated desc");
		
		if(!PaymentModeEntity.isEmpty())
		{
			for(PaymentMode PaymentMode:PaymentModeEntity)
			{
				PaymentModeDto PaymentModeDto=paymentModeConverter.getDtoFromEntity(PaymentMode, PaymentModeDto.class);
				PaymentModeList.add(PaymentModeDto);
			}
		}
		return PaymentModeList;
	}
	
	@Override
	public PaymentModeDto getPaymentMode(Long entityId)
	{
		PaymentModeDto PaymentModeDto=null;
				
		try
		{
			PaymentMode PaymentMode=paymentModeDao.findOne(entityId);
			PaymentModeDto=paymentModeConverter.getDtoFromEntity(PaymentMode, PaymentModeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching PaymentMode" + e.getMessage());
		}
		return PaymentModeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editPaymentMode(PaymentModeDto PaymentModeDto)
	{
		System.out.println("..PaymentModeServiceImpl-editPaymentMode()..");
		
		try {
				PaymentMode PaymentMode=PaymentModeConverter.getEntityFromDto(PaymentModeDto, PaymentMode.class);
				PaymentModeDao.update(PaymentMode);
				return new ResponseDto(AppBaseConstant.SUCCESS," PaymentMode- "+PaymentModeDto.getName()+" Updated Succesfully ",PaymentModeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated PaymentMode " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," PaymentMode- "+PaymentModeDto.getName()+"  Not Updated ",PaymentModeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deletePaymentMode(Long id)
	{
		System.out.println("..PaymentModeServiceImpl-deletePaymentMode()..");
		PaymentModeDto PaymentModeDto = new PaymentModeDto();
		try {
			
			PaymentModeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," PaymentMode- "+PaymentModeDto.getName()+" Deleted Succesfully ",PaymentModeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting PaymentMode " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," PaymentMode- "+PaymentModeDto.getName()+" Cannot be Deleted",PaymentModeDto.getId());
		}
		
	}*/

}


