package com.novelerp.appcontext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appcontext.dto.AnalyticsRevenueDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.AnalyticsService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	@Autowired
	private FinancialYearService financialyear;
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	
	
	@RequestMapping(value= {"/analytics"},method =RequestMethod.GET)
	public ModelAndView analytics()
	{
		ModelAndView mv=new ModelAndView("analytics");
		mv.addObject("finYear", financialyear.findAll());
		/*mv.addObject("materialList", materialService.get);*/
		BPartnerDto partner=contextService.getPartner();
		mv.addObject("departmentList", departmentService.getDepartmentList(partner));
		
		return mv;
	}
	
	@RequestMapping(value= {"/getTotalRevenue/{FiscalYear}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTotalRevenue(@PathVariable("FiscalYear") Long FiscalYear){
		long mChargeID=1;
		List<AnalyticsRevenueDto> analyticsRegRevenueDto =analyticsService.getRegistrationRevenue(mChargeID,FiscalYear);
		mChargeID =2;
		List<AnalyticsRevenueDto> analyticsRegRenuewelRevenueDto =analyticsService.getRegistrationRevenue(mChargeID,FiscalYear);
		mChargeID =3;
		List<AnalyticsRevenueDto> analyticsTenderFeeRevenueDto =analyticsService.getRegistrationRevenue(mChargeID,FiscalYear);
		mChargeID =4;
		List<AnalyticsRevenueDto> analyticsEmdRevenueDto =analyticsService.getRegistrationRevenue(mChargeID,FiscalYear);
		
		CustomResponseDto customResponseDto = new CustomResponseDto();
		customResponseDto.addObject("RegRevenue", analyticsRegRevenueDto);
		customResponseDto.addObject("RegRenuewelRevenue", analyticsRegRenuewelRevenueDto);
		customResponseDto.addObject("TenderFeeRevenue", analyticsTenderFeeRevenueDto);
		customResponseDto.addObject("EmdRevenue", analyticsEmdRevenueDto);
		return customResponseDto;
	
	}
	@RequestMapping(value= {"/getSavingDetails/{FiscalYear}/{MaterialID}/{DepartmentID}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getSavingDetails(@PathVariable("FiscalYear") Long FiscalYear,@PathVariable("MaterialID") Long MaterialID,
			@PathVariable("DepartmentID") Long DepartmentID){
		List<AnalyticsRevenueDto> analyticsPTSavingDto =analyticsService.getTotalSavingData(MaterialID,DepartmentID,"PT","N",FiscalYear);
		List<AnalyticsRevenueDto> analyticsWTSavingDto =analyticsService.getTotalSavingData(MaterialID,DepartmentID,"WT","N",FiscalYear);
		List<AnalyticsRevenueDto> analyticsFASavingDto =analyticsService.getTotalSavingData(MaterialID,DepartmentID,"FA","Y",FiscalYear);
		List<AnalyticsRevenueDto> analyticsRASavingDto =analyticsService.getTotalSavingData(MaterialID,DepartmentID,"RA","Y",FiscalYear);
		CustomResponseDto customResponseDto = new CustomResponseDto();
		customResponseDto.addObject("PTSaving", analyticsPTSavingDto);
		customResponseDto.addObject("WTSaving", analyticsWTSavingDto);
		customResponseDto.addObject("FASaving", analyticsFASavingDto);
		customResponseDto.addObject("RASaving", analyticsRASavingDto);
		return customResponseDto;
	
	}

}
