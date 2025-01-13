package com.novelerp.appbase.master.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.FinancialYearDao;
import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.entity.FinancialYear;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/**
 * 
 * @author varsha
 *
 */
@Service
public class FinancialYearServiceImpl extends AbstractContextServiceImpl<FinancialYear, FinancialYearDto> implements FinancialYearService{
	@Autowired
	private FinancialYearDao financialYearDao;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	private void init() {
		super.init(FinancialYearServiceImpl.class, financialYearDao, FinancialYear.class, FinancialYearDto.class);
		setByPassProxy(true);
	}
	@Override
	public List<FinancialYearDto> getYears() {
		List<FinancialYear> years=financialYearDao.getYear();
		return getDtoList(years);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public FinancialYearDto saveFinancialYear() {
		FinancialYearDto dto=new FinancialYearDto();
		try{
			/*Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerCode",ContextConstant.USER_TYPE_INTERNAL);
			BPartnerDto partner=partnerService.findDto("getPartneByCode",param);*/
			BPartnerDto partner=contextService.getPartner();
		   	dto.setPartner(partner);
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat codeFormat = new SimpleDateFormat("YY");
			SimpleDateFormat nameFormat=new SimpleDateFormat("YYYY");
			String code=codeFormat.format(cal.getTime());
			String name=nameFormat.format(cal.getTime());
		    dto.setValidfrom(cal.getTime());
			cal.add(Calendar.YEAR,1);
			dto.setName(name+"-"+cal.get(Calendar.YEAR));
			dto.setCode(code+"-"+codeFormat.format(cal.getTime()));
	        cal.add(Calendar.DATE,-1);
			dto.setValidTo(cal.getTime());
			dto.setIsActive("Y");
			dto=save(dto);
			}catch (Exception e) {
				log.error("Exception "+e);
			}
		return dto;
	}
	
}
