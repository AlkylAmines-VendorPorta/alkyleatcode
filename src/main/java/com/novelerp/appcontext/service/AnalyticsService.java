package com.novelerp.appcontext.service;

import java.util.List;

import com.novelerp.appcontext.dto.AnalyticsRevenueDto;

public interface AnalyticsService {

	List<AnalyticsRevenueDto> getRegistrationRevenue(Long MChargeID,Long fyId);

	List<AnalyticsRevenueDto> getTotalSavingData(Long materialID, Long departmentID,String TahdrType, String IsAuction, Long fiscalYear);

}
