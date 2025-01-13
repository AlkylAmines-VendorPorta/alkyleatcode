package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.controller.OutwardReadDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.entity.VehicleRegistration;
import com.novelerp.core.dao.CommonDao;

public interface VehicleRegistrationDao extends CommonDao<VehicleRegistration, VehicleRegistrationDto>{

	String getNewReqNo();

	String getOutwardReportlist(OutwardReadDto dto);

	String queryToGetVehicleRegistrationreqno();

}
