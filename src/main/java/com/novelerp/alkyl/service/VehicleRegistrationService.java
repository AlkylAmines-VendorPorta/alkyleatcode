package com.novelerp.alkyl.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import com.novelerp.alkyl.controller.OutwardReadDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.entity.VehicleRegistration;
import com.novelerp.core.service.CommonService;

public interface VehicleRegistrationService extends CommonService<VehicleRegistration, VehicleRegistrationDto>{

	void saveVehicleRegistration(VehicleRegistrationDto vehicleRegistrationDto);

	List<VehicleRegistrationDto> getOutwardReportbyFilter(OutwardReadDto dto);

	
//	CompletableFuture<Object> getVehicleRegistration(List<VehicleRegistrationDto> olddtoList);

	

}
