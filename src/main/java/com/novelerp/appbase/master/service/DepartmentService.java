package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface DepartmentService extends CommonService<Department, DepartmentDto> {

	public List<DepartmentDto> getDepartmentList(BPartnerDto partner);
	public DepartmentDto getDepartment(Long id);
	public boolean deleteDepartment(Long id);
	/*public ResponseDto addDepartment(DepartmentDto dto);
	public ResponseDto editDepartment(DepartmentDto dto);
	public ResponseDto deleteDepartment(Long id);*/
}