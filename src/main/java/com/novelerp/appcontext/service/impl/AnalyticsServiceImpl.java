package com.novelerp.appcontext.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.AnalyticsDao;
import com.novelerp.appcontext.dto.AnalyticsRevenueDto;
import com.novelerp.appcontext.service.AnalyticsService;
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
	
	@Autowired 
	private AnalyticsDao analyticsDao;

	@Override
	public List<AnalyticsRevenueDto> getRegistrationRevenue(Long MChargeID,Long fyId) {
		List<AnalyticsRevenueDto> analyticsRevenueDto =analyticsDao.getRegistrationRevenue(MChargeID,fyId);
		return analyticsRevenueDto;
	}

	@Override
	public List<AnalyticsRevenueDto> getTotalSavingData(Long materialID,
			Long departmentID,String TahdrType, String IsAuction, Long fyId) {
		List<AnalyticsRevenueDto> analyticsRevenueDto =analyticsDao.getTotalSavingDetails(materialID,departmentID, TahdrType,  IsAuction,fyId);
		return analyticsRevenueDto;
	}



}
