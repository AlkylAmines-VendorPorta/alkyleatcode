package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface DepartmentDao extends CommonDao<Department, DepartmentDto> {

	/**
	 * @return
	 */
	String getDepartmentById();

}
