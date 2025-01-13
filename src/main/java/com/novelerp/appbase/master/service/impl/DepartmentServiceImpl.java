package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.DepartmentConverter;
import com.novelerp.appbase.master.dao.DepartmentDao;
import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class DepartmentServiceImpl extends AbstractContextServiceImpl<Department, DepartmentDto> implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private DepartmentConverter departmentConverter;
	
	@PostConstruct
	private void init() {
		super.init(DepartmentServiceImpl.class, departmentDao, Department.class, DepartmentDto.class);
		setByPassProxy(true);
	}

	@Override
	/*@Cacheable("DepartmentDto")*/
	public List<DepartmentDto> getDepartmentList(BPartnerDto partner)
	{
		List<DepartmentDto> departmentList=new ArrayList<DepartmentDto>();
		List<Department> departmentEntity= departmentDao.findAll(" WHERE c.partner="+partner.getbPartnerId()+"", "updated desc");
		
		if(!departmentEntity.isEmpty())
		{
			for(Department department:departmentEntity)
			{
				DepartmentDto departmentDto=departmentConverter.getDtoFromEntity(department, DepartmentDto.class);
				//DepartmentDto DepartmentDto=departmentConverter.convertEntityToDto(Department, DepartmentDto.class);
				departmentList.add(departmentDto);
			}
		}
		return departmentList;
	}
	
	@Override
	/*@Cacheable("getDepartment")*/
	public DepartmentDto getDepartment(Long entityId)
	{
		DepartmentDto DepartmentDto=null;
				
		try
		{
			Department Department=departmentDao.findOne(entityId);
			DepartmentDto=departmentConverter.getDtoFromEntity(Department, DepartmentDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching Department" + e.getMessage());
		}
		return DepartmentDto;
	}

	@Override
	@Transactional
	public boolean deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editDepartment(DepartmentDto DepartmentDto)
	{
		System.out.println("..DepartmentServiceImpl-editDepartment()..");
		
		try {
				Department Department=DepartmentConverter.getEntityFromDto(DepartmentDto, Department.class);
				DepartmentDao.update(Department);
				return new ResponseDto(AppBaseConstant.SUCCESS," Department- "+DepartmentDto.getName()+" Updated Succesfully ",DepartmentDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated Department " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Department- "+DepartmentDto.getName()+"  Not Updated ",DepartmentDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteDepartment(Long id)
	{
		System.out.println("..DepartmentServiceImpl-deleteDepartment()..");
		DepartmentDto DepartmentDto = new DepartmentDto();
		try {
			
			DepartmentDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," Department- "+DepartmentDto.getName()+" Deleted Succesfully ",DepartmentDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting Department " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Department- "+DepartmentDto.getName()+" Cannot be Deleted",DepartmentDto.getId());
		}
		
	}*/

}


