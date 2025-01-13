package com.novelerp.appcontext.service;

import java.util.List;

import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.core.service.CommonService;


/**
 * 
 * @author Ankita Tirodkar
 *
 */

public interface DesignationService extends CommonService<Designation, DesignationDto> {

	public List<DesignationDto> getDesignationList();
	public DesignationDto getDesignation(Long entityId);
	public boolean deleteDesignation(Long id);
	/*public ResponseDto addDesignation(DesignationDto designationDto);*/
	/*public ResponseDto editDesignation(DesignationDto designationDto);
	public ResponseDto deleteDesignation(Long id);*/
	
}
