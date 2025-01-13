package com.novelerp.appcontext.dao;

import java.util.List;

import com.novelerp.appcontext.dto.AnalyticsRevenueDto;

public interface AnalyticsDao {

	List<AnalyticsRevenueDto> getRegistrationRevenue(Long MChargeID,Long fyId);

	List<AnalyticsRevenueDto> getTotalSavingDetails(Long materialID, Long departmentID,String TahdrType, String IsAuction, Long fyId);

}
