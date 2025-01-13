/*file name: UserDepartmentDTO.java
 * created : Lakshmi
 * Description : DTO file of UserDepartment.
 * dependency : DepartmentController, DepartmentService, DepartmentDAO, DepartmentDAO 
 * */

package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class UserDepartmentDto extends CommonDto
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7818795938593093559L;
	
	private Long userDepartmentId;
	private String value;
	private String isTaxble;
	private String name;
	private String msg;
	private String deptHidId;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getUserDepartmentId() {
		return userDepartmentId;
	}
	public void setUserDepartmentId(Long userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsTaxble() {
		return isTaxble;
	}
	public void setIsTaxble(String isTaxble) {
		this.isTaxble = isTaxble;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptHidId() {
		return deptHidId;
	}
	public void setDeptHidId(String deptHidId) {
		this.deptHidId = deptHidId;
	}
}
